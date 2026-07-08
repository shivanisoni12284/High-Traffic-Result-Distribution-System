package com.shivani.hightrafficresultdistributionsystem.student.service;


import com.shivani.hightrafficresultdistributionsystem.common.exception.StudentAlreadyExistException;
import com.shivani.hightrafficresultdistributionsystem.common.exception.StudentNotFoundException;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentRequestDto;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentResponseDto;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentUpdateRequestDto;
import com.shivani.hightrafficresultdistributionsystem.student.mapper.StudentMapper;
import com.shivani.hightrafficresultdistributionsystem.student.repository.StudentRepository;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    // create student

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto){

        if(studentRepository.existsByRollNumber(studentRequestDto.getRollNumber())){
            throw new
                    StudentAlreadyExistException("student with roll number already exists");
        }

        Student student = studentMapper.toEntity(studentRequestDto );

        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponseDto(savedStudent);

    }

    // get student with id
    public StudentResponseDto getStudentById(Long studentId) {
        Student student = studentRepository.findByIdAndDeletedAtIsNull(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id not found: "+ studentId));
        return studentMapper.toResponseDto(student);
    }



    // get All only Active Students not soft deleted ones
    public List<StudentResponseDto> getAllStudents(){
        List<Student> students = studentRepository.findByDeletedAtIsNull();
        return students.stream().map(studentMapper::toResponseDto)
                .toList();
    }

    // get student with rollNumber
    public StudentResponseDto getStudentByRollNumber(String rollNo) {

        Student student = studentRepository.findByRollNumberAndDeletedAtIsNull(rollNo).orElseThrow(() -> new StudentNotFoundException(" student not found with this roll number: "+rollNo));
        return studentMapper.toResponseDto(student);
    }

    // full update student
    @Transactional
    public StudentResponseDto updateStudent(Long studentId, StudentRequestDto requestDto) {

        Student student = studentRepository.findByIdAndDeletedAtIsNull(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id not found: "+ studentId));

        Student updatedStudent = studentMapper.updateStudentFromDto(requestDto,student);
//        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toResponseDto(updatedStudent);

    }

    // partial update
    @Transactional
    public StudentResponseDto partialUpdateStudent(Long studentId, StudentUpdateRequestDto requestDto) {

        Student student = studentRepository.findByIdAndDeletedAtIsNull(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id not found: "+ studentId));


        Student partialUpdatedStudent = studentMapper.partialUpdateStudentFromDto(requestDto,student);

        //        Student partialUpdatedStudent = studentRepository.save(student);  // unnecessary

        return studentMapper.toResponseDto(partialUpdatedStudent);

    }

    //  ADMINS

    // get all students   this is for admin
    public List<StudentResponseDto> getAllStudentsIncludingDeleted(){
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::toResponseDto)
                .toList();
    }

    // get all students   this is for admin
    public List<StudentResponseDto> getAllDeletedStudents(){
        List<Student> students = studentRepository.findByDeletedAtIsNotNull();
        return students.stream().map(studentMapper::toResponseDto)
                .toList();
    }



    // get student with id
    public StudentResponseDto getStudentByIdIncludingDeleted(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id not found: "+ studentId));
        return studentMapper.toResponseDto(student);
    }


    // get student with rollNumber
    public StudentResponseDto getStudentByRollNumberIncludingDeleted(String rollNo) {

        Student student = studentRepository.findByRollNumber(rollNo).orElseThrow(() -> new StudentNotFoundException(" student not found with this roll number: "+rollNo));
        return studentMapper.toResponseDto(student);
    }

    // restore student
    @Transactional
    public void restoreStudent(Long studentId){

        Student student = studentRepository.findByIdAndDeletedAtIsNotNull(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id not found: "+ studentId));


        student.setDeletedAt(null);
        student.setActive(true);
//        studentRepository.save(student);
    }

    // delete student
    @Transactional
    public void deleteStudent(Long studentId) {

        Student student = studentRepository.findByIdAndDeletedAtIsNull(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id not found: "+ studentId));
        student.setDeletedAt(LocalDateTime.now());
        student.setActive(false);
//        studentRepository.save(student);
    }
}
