package com.krytest.controller;

import com.krytest.entity.Endpoint;
import com.krytest.service.EndpointService;
import com.krytest.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EndpointController {

    @Autowired
    EndpointService endpointService;

    @Autowired
    PollService pollService;

    @GetMapping("/endpoints")
    public ResponseEntity<List<Endpoint>> getAllEndpoints(@RequestParam(required = false) String url) {
        try {
            List<Endpoint> endpoints = new ArrayList<Endpoint>();

            if (url == null)
                endpointService.findAll().forEach(endpoints::add);

            if (endpoints.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(endpoints, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/endpoints/{id}")
    public ResponseEntity<Endpoint> getEndpointById(@PathVariable("id") long id) {
        Optional<Endpoint> endpointData = endpointService.findById(id);

        if (endpointData.isPresent()) {
            return new ResponseEntity<>(endpointData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/endpoints")
    public ResponseEntity<Endpoint> createEndpoint(@RequestBody Endpoint endpoint) {
        try {
            Endpoint _endpoint = endpointService.create(endpoint);
            return new ResponseEntity<>(_endpoint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/endpoints/{id}")
    public ResponseEntity<Endpoint> updateEndpoint(@PathVariable("id") long id, @RequestBody Endpoint endpoint) {
        try {
            Endpoint updatedEndpoint = endpointService.update(id, endpoint);
            return new ResponseEntity<>(updatedEndpoint, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/endpoints/{id}")
    public ResponseEntity<HttpStatus> deleteEndpoint(@PathVariable("id") long id) {
        try {
            endpointService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
