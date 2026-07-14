package com.shivani.hightrafficresultdistributionsystem.subject.service;


import com.shivani.hightrafficresultdistributionsystem.common.exception.SubjectAlreadyExistsException;
import com.shivani.hightrafficresultdistributionsystem.common.exception.SubjectNotFoundException;
import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectRequestDto;
import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectResponseDto;
import com.shivani.hightrafficresultdistributionsystem.subject.mapper.SubjectMapper;
import com.shivani.hightrafficresultdistributionsystem.subject.repository.SubjectRepository;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;


    // create subject
    @Transactional
    public SubjectResponseDto createSubject(SubjectRequestDto subjectRequestDto){

        if(subjectRepository.existsBySubjectCode(subjectRequestDto.getSubjectCode())){
            throw new SubjectAlreadyExistsException("Subject code already exists");
        }

        Subject subject = subjectMapper.toEntity(subjectRequestDto);
        Subject createdSubject = subjectRepository.save(subject);

        return subjectMapper.toResponseDto(createdSubject);
    }

    // update Subject
    @Transactional
    public SubjectResponseDto updateSubject(SubjectRequestDto subjectRequestDto,Long subjectId){
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("subject not found with id: "+subjectId));

        subjectMapper.toUpdateEntity(subjectRequestDto,subject);

        return subjectMapper.toResponseDto(subject);




    }

    // get subject by id
    public SubjectResponseDto getSubjectById(Long subjectId){
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("subject not found with id: "+subjectId));
        return subjectMapper.toResponseDto(subject);
    }

    //delete subject
    public void deleteSubject(Long subjectId){
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("subject not found with id: "+subjectId));
        subject.setActive(false);
        subject.setDeletedAt(LocalDateTime.now());
        subjectRepository.save(subject);
    }

    // restore subject
    public SubjectResponseDto restoreSubject(Long subjectId){
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("subject not found with id: "+subjectId));
        subject.setActive(true);
        subject.setDeletedAt(null);
        subjectRepository.save(subject);
        return subjectMapper.toResponseDto(subject);
    }


    // get all subjects
    public List<SubjectResponseDto> getAllSubjects() {

        List<Subject> allSubjects  = subjectRepository.findAll();

        return allSubjects.stream().map(subjectMapper::toResponseDto).toList();
    }
}
