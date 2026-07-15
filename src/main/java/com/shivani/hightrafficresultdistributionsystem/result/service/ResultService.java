package com.shivani.hightrafficresultdistributionsystem.result.service;

import com.shivani.hightrafficresultdistributionsystem.common.exception.ResultAlreadyGeneratedException;
import com.shivani.hightrafficresultdistributionsystem.common.exception.ResultNotFoundException;
import com.shivani.hightrafficresultdistributionsystem.common.exception.StudentNotFoundException;
import com.shivani.hightrafficresultdistributionsystem.kafka.event.ResultGenerationEvent;
import com.shivani.hightrafficresultdistributionsystem.kafka.producer.ResultEventProducer;
import com.shivani.hightrafficresultdistributionsystem.marks.repository.MarksRepository;
import com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade;
import com.shivani.hightrafficresultdistributionsystem.marks.schema.Marks;
import com.shivani.hightrafficresultdistributionsystem.result.dto.ResultResponseDto;
import com.shivani.hightrafficresultdistributionsystem.result.dto.SubjectResultDto;
import com.shivani.hightrafficresultdistributionsystem.result.mapper.ResultMapper;
import com.shivani.hightrafficresultdistributionsystem.result.repository.ResultRepository;
import com.shivani.hightrafficresultdistributionsystem.result.schema.Result;
import com.shivani.hightrafficresultdistributionsystem.result.schema.ResultType;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentResponseDto;
import com.shivani.hightrafficresultdistributionsystem.student.repository.StudentRepository;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ResultService {

    private final ResultRedisCache resultRedisCache;
    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final MarksRepository marksRepository;
    private final ResultMapper resultMapper;
    private final ResultEventProducer resultEventProducer;

    public ResultResponseDto generateResult(String rollNumber){


        Student student = studentRepository.findByRollNumberAndDeletedAtIsNull(rollNumber).orElseThrow(() -> new StudentNotFoundException("student not found with rollNumber:"+rollNumber));
        if (resultRepository.existsByStudent(student)) {
            log.info("Result already generated for {}", rollNumber);
            return null;   // or simply return if your method is void
        }
        List<Marks> studentMarks = marksRepository.findByStudent(student);

        boolean pass = true;

        for(Marks marks: studentMarks){
            if(marks.getGrade() == Grade.E){
                pass = false;
            }
        }

        // filled result entity
        Result createResult = Result.builder()
                .student(student)
                .result(pass? ResultType.PAAS :ResultType.FAIL)
                .build();

        Result savedResult = resultRepository.save(createResult);

        // convert to responseDto
        ResultResponseDto resultResponseDto = ResultResponseDto.builder()
                .studentName(savedResult.getStudent().getStudentName())
                .rollNumber(savedResult.getStudent().getRollNumber())
                .motherName(savedResult.getStudent().getMotherName())
                .fatherName(savedResult.getStudent().getFatherName())
                .dateOfBirth(savedResult.getStudent().getDateOfBirth())
                .schoolName(savedResult.getStudent().getSchoolName())
                .result(savedResult.getResult())
                .build();

        List<SubjectResultDto> subjects = new ArrayList<>();

        for(Marks mark: studentMarks){
            SubjectResultDto subjectResultDto = SubjectResultDto.builder()
                    .subjectCode(mark.getSubject().getSubjectCode())
                    .subjectName(mark.getSubject().getSubjectName())
                    .theoryMarks(mark.getTheoryMarks())
                    .practicalMarks(mark.getPracticalMarks())
                    .grade(mark.getGrade())
                    .totalMarks(mark.getTotalMarks())
                    .build();
            subjects.add(subjectResultDto);
        }

        resultResponseDto.setSubjects(subjects);

        return resultResponseDto;

    }


    public ResultResponseDto getResult(String rollNumber){
        Student student = studentRepository.findByRollNumberAndDeletedAtIsNull(rollNumber).orElseThrow(() -> new StudentNotFoundException("student not found with rollNumber:"+rollNumber));

        Optional<ResultResponseDto> cachedResult = resultRedisCache.getResult(rollNumber);

        if(cachedResult.isPresent()){
            return cachedResult.get();
        }
        Result result = resultRepository.findByStudent(student);
        resultRepository.save(result);
        ResultResponseDto response = resultMapper.toResponseDto(result);

        List<Marks> studentMarks = marksRepository.findByStudent(student);

        List<SubjectResultDto> subjects = studentMarks.stream()
                .map(resultMapper::toSubjectResultDto)
                .toList();

        response.setSubjects(subjects);

        resultRedisCache.putResult(rollNumber,response);
        return response;
    }


    public void createResult(String rollNumber){
        Student student = studentRepository
                .findByRollNumberAndDeletedAtIsNull(rollNumber)
                .orElseThrow(()-> new StudentNotFoundException("student not found"));

        if (resultRepository.existsByStudent(student)) {
            throw new ResultAlreadyGeneratedException(
                    "A result already exists for roll number: "+rollNumber);
        }
        ResultGenerationEvent event = ResultGenerationEvent.builder()
                .eventId(UUID.randomUUID())
                .rollNumber(rollNumber)
                .createdAt(LocalDateTime.now())
                .build();

        resultEventProducer.publish(event);

    }

}
