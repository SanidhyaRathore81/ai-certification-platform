package com.platform.certtracker.controller;

import com.platform.certtracker.dto.ExamMilestoneRequest;
import com.platform.certtracker.dto.ExamMilestoneResponse;
import com.platform.certtracker.service.ExamMilestoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/milestones")
public class ExamMilestoneController {

    private final ExamMilestoneService service;

    public ExamMilestoneController(ExamMilestoneService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ExamMilestoneResponse>> getAllMilestones() {
        return ResponseEntity.ok(service.getAllMilestones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamMilestoneResponse> getMilestoneById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMilestoneById(id));
    }

    @GetMapping("/code/{examCode}")
    public ResponseEntity<ExamMilestoneResponse> getMilestoneByExamCode(@PathVariable String examCode) {
        return ResponseEntity.ok(service.getMilestoneByExamCode(examCode));
    }

    @PostMapping
    public ResponseEntity<ExamMilestoneResponse> createMilestone(@RequestBody ExamMilestoneRequest request) {
        ExamMilestoneResponse created = service.createMilestone(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamMilestoneResponse> updateMilestone(@PathVariable Long id,
                                                                 @RequestBody ExamMilestoneRequest request) {
        return ResponseEntity.ok(service.updateMilestone(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
        service.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }
}
