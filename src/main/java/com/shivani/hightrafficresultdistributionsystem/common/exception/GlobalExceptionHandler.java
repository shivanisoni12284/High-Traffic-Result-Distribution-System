package com.shivani.hightrafficresultdistributionsystem.common.exception;

import com.shivani.hightrafficresultdistributionsystem.common.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(StudentAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleStudentAlreadyExistException(StudentAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"student Aready exist"));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleStudentNotFoundException(StudentNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage(),"student not found"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(ApiResponse.error(errors,"validation failed"));
    }

    @ExceptionHandler(StudentAlreadyRegisteredException.class)
    public ResponseEntity<ApiResponse<String>> handleStudentAreadyRegisteredException(StudentAlreadyRegisteredException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"student already registered "));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"user not found"));
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleSubjectNotFoundException(SubjectNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage(),"subject not found"));
    }

    @ExceptionHandler(MarksAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleMarksAlreadyExistsException(MarksAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"Marks already exists "));
    }

    @ExceptionHandler(InvalidPracticalMarksException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidPracticalMarksException(InvalidPracticalMarksException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"practical marks must be in range"));
    }

    @ExceptionHandler(InvalidTheoryMarksException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidTheoryMarksException(InvalidTheoryMarksException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"theory marks should be within range"));
    }



    @ExceptionHandler(ExamAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleExamAlreadyExistsException(ExamAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"Exam already exists."));
    }


    @ExceptionHandler(ResultNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResultNotFoundException(ResultNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage(),"Exam already exists."));
    }

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ApiResponse<String>> handleRateLimitExceededException(RateLimitExceededException ex){
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ApiResponse.error(ex.getMessage(),"Request exceeded its limit."));
    }



}
