package com.shivani.hightrafficresultdistributionsystem.student.controller;


import com.shivani.hightrafficresultdistributionsystem.common.util.ApiResponse;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentRequestDto;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentResponseDto;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentUpdateRequestDto;
import com.shivani.hightrafficresultdistributionsystem.student.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {


    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto){

       StudentResponseDto response = studentService.createStudent(studentRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("created student information successfully",response));
    }

    // get all students
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents(){
        List<StudentResponseDto> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.success("fetched student all students details ",allStudents));

    }

    // get student with rollNumber
    @GetMapping("/roll/{rollNo}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentWithRollNumber(@PathVariable String rollNo) {
       StudentResponseDto response = studentService.getStudentByRollNumber(rollNo);
        return ResponseEntity.ok(ApiResponse.success("fetched student details with roll number: "+rollNo,response));

    }

    // get student with id
    @GetMapping("/id/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentWithId(@PathVariable Long studentId) {
        StudentResponseDto response = studentService.getStudentById(studentId);
        return ResponseEntity.ok(ApiResponse.success("fetched student details with id: "+studentId,response));

    }

    //full update student
    @PutMapping("/fullUpdate/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(@PathVariable Long studentId, @Valid @RequestBody StudentRequestDto studentRequestDto){

        StudentResponseDto response = studentService.updateStudent(studentId,studentRequestDto);
        return ResponseEntity.ok(ApiResponse.success("fully updated student details with id: "+studentId,response));
    }

    //update student
    @PatchMapping( "/partialUpdate/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> partialUpdateStudent(@PathVariable Long studentId, @Valid @RequestBody StudentUpdateRequestDto studentRequestDto){

        StudentResponseDto response = studentService.partialUpdateStudent(studentId,studentRequestDto);
        return ResponseEntity.ok(ApiResponse.success("partially updated student details with id: "+studentId,response));
    }

}
