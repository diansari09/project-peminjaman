package com.project.technicaltest.service;


import com.project.technicaltest.dto.LokerRequest;
import com.project.technicaltest.dto.LokerResponse;
import com.project.technicaltest.dto.LokerUpdateRequest;

public interface LokerService {

    LokerResponse create(LokerRequest request);

    LokerResponse update(LokerUpdateRequest request);
}
