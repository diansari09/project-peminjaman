package com.project.technicaltest.controller;


import com.project.technicaltest.dto.LokerRequest;
import com.project.technicaltest.dto.LokerResponse;
import com.project.technicaltest.dto.LokerUpdateRequest;
import com.project.technicaltest.dto.WebResponse;
import com.project.technicaltest.service.LokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loker")
public class LokerController {

    @Autowired
    private LokerService lokerService;

    @PostMapping("/create")
    public WebResponse<LokerResponse> create(@RequestBody LokerRequest request){
        LokerResponse response = lokerService.create(request);
        return WebResponse.<LokerResponse>builder().data(response).build();
    }

    @PostMapping("/update")
    public WebResponse<LokerResponse> update(@RequestBody LokerUpdateRequest request){
        LokerResponse response = lokerService.update(request);
        return WebResponse.<LokerResponse>builder().data(response).build();
    }
}
