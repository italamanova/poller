package com.krytest.service;

import com.krytest.entity.Endpoint;
import com.krytest.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EndpointService {

    @Autowired
    private final EndpointRepository endpointRepository;

    public EndpointService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    public Optional<Endpoint> findById(Long endpointId) {
        return endpointRepository.findById(endpointId);
    }

    public List<Endpoint> findAll() {
        return endpointRepository.findAll();
    }

    public Endpoint create(Endpoint endpoint) {
        return endpointRepository
                .save(new Endpoint(endpoint.getUrl()));
    }

    public Endpoint update(Long id, Endpoint endpoint) {
        Endpoint endpointEntity = endpointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Endpoint not found"));
        ;
        endpointEntity.setUrl(endpoint.getUrl());
        endpointRepository.save(endpointEntity);
        return endpointEntity;
    }

    public void deleteById(Long id) {
        endpointRepository.deleteById(id);
    }
}
