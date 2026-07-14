package com.shivani.hightrafficresultdistributionsystem.marks.service;

import com.shivani.hightrafficresultdistributionsystem.common.exception.*;
import com.shivani.hightrafficresultdistributionsystem.common.util.CalculateGrade;
import com.shivani.hightrafficresultdistributionsystem.marks.dto.MarksRequestDto;
import com.shivani.hightrafficresultdistributionsystem.marks.dto.MarksResponseDto;
import com.shivani.hightrafficresultdistributionsystem.marks.mapper.MarksMapper;
import com.shivani.hightrafficresultdistributionsystem.marks.repository.MarksRepository;
import com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade;
import com.shivani.hightrafficresultdistributionsystem.marks.schema.Marks;
import com.shivani.hightrafficresultdistributionsystem.student.repository.StudentRepository;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import com.shivani.hightrafficresultdistributionsystem.student.service.StudentService;
import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectRequestDto;
import com.shivani.hightrafficresultdistributionsystem.subject.repository.SubjectRepository;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade.*;

@Service
@RequiredArgsConstructor
public class MarksService {

    private final MarksRepository marksRepository;
    private final MarksMapper marksMapper;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final CalculateGrade calculateGrade;

    //create Marks
    public MarksResponseDto createMarks(MarksRequestDto marksRequestDto){

        Student student  = studentRepository.findByIdAndDeletedAtIsNull(marksRequestDto.getStudent().getId()).orElseThrow(() -> new StudentNotFoundException("student with id doesn't exist"));

        Subject subject = subjectRepository.findByIdAndDeletedAtIsNull(marksRequestDto.getSubject().getId()).orElseThrow(() -> new SubjectNotFoundException("subject with id doesn't exist"));

        if(marksRepository.existsByStudentAndSubject(student,subject)){
            throw new MarksAlreadyExistsException("Marks already exists for this subject ");
        }

        if(marksRequestDto.getTheoryMarks() > subject.getTotalTheoryMarks()){
            throw new InvalidTheoryMarksException("theory marks exceeds total theory marks");

        }

        if(marksRequestDto.getPracticalMarks() > subject.getTotalPracticalMarks()){
            throw new InvalidPracticalMarksException("practical marks exceeds total practical marks");

        }

        Marks marks = marksMapper.toEntity(marksRequestDto);
        marks.setStudent(student);
        marks.setSubject(subject);

        marks.setTotalMarks(marks.getTheoryMarks()+ marks.getPracticalMarks());

        marks.setGrade(calculateGrade.gradeEnum(marks.getTotalMarks()));

        Marks savedMarks = marksRepository.save(marks);

        return marksMapper.toResponseDto(savedMarks);

    }

    // get All marks
    public List<MarksResponseDto> getAllMarks(){
        List<Marks> marks =  marksRepository.findAll();
        return marks.stream().map(marksMapper::toResponseDto).toList();
    }

    // get All subject marks of student
    public List<MarksResponseDto> getAllSubjectMarksByStudent(Long studentId){
        Student student = studentRepository.findByIdAndDeletedAtIsNull(studentId).orElseThrow(()-> new StudentNotFoundException("student not found with this id"));

        List<Marks> marks =  marksRepository.findByStudent(student);
        return marks.stream().map(marksMapper::toResponseDto).toList();

    }

}




