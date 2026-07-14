package com.shivani.hightrafficresultdistributionsystem.result.controller;

import com.shivani.hightrafficresultdistributionsystem.common.util.ApiResponse;
import com.shivani.hightrafficresultdistributionsystem.result.dto.ResultResponseDto;
import com.shivani.hightrafficresultdistributionsystem.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/{rollNumber}")
    public ResponseEntity<ApiResponse<ResultResponseDto>> createResultbyRollNumber(@PathVariable String rollNumber){
        ResultResponseDto result = resultService.createResult(rollNumber);
        return ResponseEntity.ok(ApiResponse.success("successfully fetched result",result));

    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<ApiResponse<ResultResponseDto>> getResultbyRollNumber(@PathVariable String rollNumber){
        ResultResponseDto result = resultService.getResult(rollNumber);
        return ResponseEntity.ok(ApiResponse.success("successfully fetched result",result));

    }
}
