package com.example.userservice.controller;

import com.example.userservice.entity.ResponseEntity;
import com.example.userservice.service.responseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class responseController {
    private final responseService responseService;

    @Autowired
    public responseController( responseService responseService){
        this.responseService = responseService;
    }

    @GetMapping
    public List<ResponseEntity> getAllResponses(){
        return responseService.getAllResponses();
    }

    @GetMapping("/{id}")
    public ResponseEntity getResponseById(Long id){
        return responseService.getResponseById(id);
    }

    @PostMapping
    public ResponseEntity createResponse(@RequestBody ResponseEntity response){
        return responseService.createResponse(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateResponse(@PathVariable Long id, @RequestBody ResponseEntity response){
        ResponseEntity existingRespone = getResponseById(id);
        if (existingRespone != null){
            return responseService.updateResponse(id,response);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteResponse(@PathVariable Long id){
        responseService.deleteResponse(id);
    }
}
