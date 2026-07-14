package com.shivani.hightrafficresultdistributionsystem.subject.controller;


import com.shivani.hightrafficresultdistributionsystem.common.exception.SubjectAlreadyExistsException;
import com.shivani.hightrafficresultdistributionsystem.common.exception.SubjectNotFoundException;
import com.shivani.hightrafficresultdistributionsystem.common.util.ApiResponse;
import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectRequestDto;
import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectResponseDto;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import com.shivani.hightrafficresultdistributionsystem.subject.service.SubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<ApiResponse<SubjectResponseDto>> createSubject(@RequestBody SubjectRequestDto subjectRequestDto){
        SubjectResponseDto createdSubject = subjectService.createSubject(subjectRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("subject created successfully",createdSubject));
    }


    @PostMapping("/{subjectId}/update")
    public ResponseEntity<ApiResponse<SubjectResponseDto>> updateSubject(@RequestBody SubjectRequestDto subjectRequestDto,@PathVariable Long subjectId){
        SubjectResponseDto updatedSubject = subjectService.updateSubject(subjectRequestDto,subjectId);
        return ResponseEntity.ok(ApiResponse.success("subject updated successfully",updatedSubject));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubjectResponseDto>> getSubjectById(@PathVariable("id") Long subjectId){
        SubjectResponseDto subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(ApiResponse.success("Successfully fetched subject with id: "+subjectId,subject));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubjectResponseDto>>> getAllSubjects(){
        List<SubjectResponseDto> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(ApiResponse.success("Successfully fetched all subjects",subjects));
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}/restore")
    public ResponseEntity<ApiResponse<SubjectResponseDto>> restoreSubject(@PathVariable("id") Long subjectId){
        SubjectResponseDto restoredSubject = subjectService.restoreSubject(subjectId);
        return ResponseEntity.ok(ApiResponse.success("subject restored Successfully",restoredSubject));

    }
}
