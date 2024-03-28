package com.example.userservice.service;

import com.example.userservice.entity.ResponseEntity;

import java.util.List;


public interface responseService {

    List<ResponseEntity> getAllResponses();

    ResponseEntity getResponseById(Long id);
    ResponseEntity createResponse(ResponseEntity response);

    ResponseEntity updateResponse(Long id, ResponseEntity updatedResponse);

    void deleteResponse(Long id);
}
