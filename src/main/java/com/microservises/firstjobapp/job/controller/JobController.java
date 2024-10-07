package com.microservises.firstjobapp.job.controller;

import org.springframework.web.bind.annotation.RestController;
import com.microservises.firstjobapp.job.model.Job;
import com.microservises.firstjobapp.job.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    //@GetMapping
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    //@PostMapping
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>( "Job added successfully", HttpStatus.CREATED);
    }

    // @GetMapping("/{id}")
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job= jobService.getJobById(id);
        if(job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@DeleteMapping("/{id}")
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return ResponseEntity.ok("Job deleted successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // @PutMapping("/{id}")
    // @PutMapping("/jobs/{id}")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob){
        boolean updated= jobService.updateJob(id, updatedJob);
        if (updated)
            return ResponseEntity.ok("Job updated successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
