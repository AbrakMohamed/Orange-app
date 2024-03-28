package com.example.userservice.service.impl;

import com.example.userservice.entity.ResponseEntity;
import com.example.userservice.repository.ResponseRepository;
import com.example.userservice.service.responseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class responseServiceImpl implements responseService {
    private final ResponseRepository responseRepository;

    @Autowired
    public responseServiceImpl(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public List<ResponseEntity> getAllResponses() {
        return responseRepository.findAll();
    }

    @Override
    public ResponseEntity getResponseById(Long id) {
        return responseRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity createResponse(ResponseEntity response) {
        // Add any business logic or validation here before saving
        return responseRepository.save(response);
    }

    @Override
    public ResponseEntity updateResponse(Long id, ResponseEntity updatedResponse) {
        ResponseEntity existingResponse = getResponseById(id);

        if (existingResponse != null) {
            // Update existingResponse with data from updatedResponse
            // Add any business logic or validation here before saving
            return responseRepository.save(existingResponse);
        }

        return null; // or throw an exception based on your requirements
    }

    @Override
    public void deleteResponse(Long id) {
        responseRepository.deleteById(id);
    }

}
