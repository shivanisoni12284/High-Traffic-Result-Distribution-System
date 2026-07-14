package com.shivani.hightrafficresultdistributionsystem.marks.controller;

import com.shivani.hightrafficresultdistributionsystem.common.exception.*;
import com.shivani.hightrafficresultdistributionsystem.common.util.ApiResponse;
import com.shivani.hightrafficresultdistributionsystem.marks.dto.MarksRequestDto;
import com.shivani.hightrafficresultdistributionsystem.marks.dto.MarksResponseDto;
import com.shivani.hightrafficresultdistributionsystem.marks.schema.Marks;
import com.shivani.hightrafficresultdistributionsystem.marks.service.MarksService;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/marks")
@RequiredArgsConstructor
public class MarksController {

    private final MarksService marksService;

    @PostMapping
    public ResponseEntity<ApiResponse<MarksResponseDto>> createMarks(@Valid @RequestBody MarksRequestDto marksRequestDto){
        MarksResponseDto createdMarks = marksService.createMarks(marksRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("marks created successfully",createdMarks));

    }

    // get All marks
    @GetMapping
    public ResponseEntity<ApiResponse<List<MarksResponseDto>>> getAllMarks(){
        List<MarksResponseDto> marks = marksService.getAllMarks();
        return ResponseEntity.ok(ApiResponse.success("fetched all marks successfully",marks));

    }

    // get All subject marks of student
    @GetMapping("{studentId}/studentMarks")
    public ResponseEntity<ApiResponse<List<MarksResponseDto>>> getAllSubjectMarksByStudentsId(@PathVariable Long studentId){
        List<MarksResponseDto> marks = marksService.getAllSubjectMarksByStudent(studentId);
        return ResponseEntity.ok(ApiResponse.success("fetched all subject marks of student successfully",marks));

    }
}
