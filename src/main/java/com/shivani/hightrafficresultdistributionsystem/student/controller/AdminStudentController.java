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
@RequestMapping("/api/v1/admin/students")
public class AdminStudentController {


    private final StudentService studentService;


    // get all students
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudentsIncludingDeleted(){
        List<StudentResponseDto> allStudents = studentService.getAllStudentsIncludingDeleted();
        return ResponseEntity.ok(ApiResponse.success("Fetched all student details including deleted records.",allStudents));

    }

    @GetMapping("/deleted")
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllDeletedStudents(){
        List<StudentResponseDto> allDeletedStudents = studentService.getAllDeletedStudents();
        return ResponseEntity.ok(ApiResponse.success("Fetched all deleted students  ",allDeletedStudents));

    }


    // get student with rollNumber
    @GetMapping("/roll/{rollNo}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentByRollNumberIncludingDeleted(@PathVariable String rollNo) {
       StudentResponseDto response = studentService.getStudentByRollNumberIncludingDeleted(rollNo);
        return ResponseEntity.ok(ApiResponse.success("fetched deleted student details with roll number: "+rollNo,response));

    }

    // get student with id
    @GetMapping("/id/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>>  getStudentByIdIncludingDeleted(@PathVariable Long studentId) {
        StudentResponseDto response = studentService.getStudentByIdIncludingDeleted(studentId);
        return ResponseEntity.ok(ApiResponse.success("Fetched deleted student details with id: "+studentId,response));

    }

    // delete student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);

        return ResponseEntity.ok(ApiResponse.success("Student soft deleted successfully. ",HttpStatus.NO_CONTENT));
    }

    //restore student
    @PatchMapping("/{studentId}/restore")
    public ResponseEntity<ApiResponse<Void>> restoreStudent(@PathVariable Long studentId){
        studentService.restoreStudent(studentId);

        return ResponseEntity.ok(ApiResponse.success("Student restored successfully.",HttpStatus.OK));
    }

}
