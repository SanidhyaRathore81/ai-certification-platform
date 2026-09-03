package com.platform.certtracker.service;

import com.platform.certtracker.dto.ExamMilestoneRequest;
import com.platform.certtracker.dto.ExamMilestoneResponse;
import com.platform.certtracker.entity.ExamMilestone;
import com.platform.certtracker.repository.ExamMilestoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ExamMilestoneService {

    private final ExamMilestoneRepository repository;

    public ExamMilestoneService(ExamMilestoneRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ExamMilestoneResponse> getAllMilestones() {
        return repository.findAll().stream()
                .map(ExamMilestoneResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ExamMilestoneResponse getMilestoneById(Long id) {
        ExamMilestone milestone = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Exam milestone not found with id: " + id));
        return ExamMilestoneResponse.fromEntity(milestone);
    }

    @Transactional(readOnly = true)
    public ExamMilestoneResponse getMilestoneByExamCode(String examCode) {
        ExamMilestone milestone = repository.findByExamCode(examCode)
                .orElseThrow(() -> new NoSuchElementException("Exam milestone not found with code: " + examCode));
        return ExamMilestoneResponse.fromEntity(milestone);
    }

    public ExamMilestoneResponse createMilestone(ExamMilestoneRequest request) {
        ExamMilestone entity = ExamMilestone.builder()
                .examCode(request.getExamCode())
                .title(request.getTitle())
                .targetDate(request.getTargetDate())
                .status(request.getStatus())
                .targetScore(request.getTargetScore())
                .actualScore(request.getActualScore())
                .notes(request.getNotes())
                .build();

        ExamMilestone saved = repository.save(entity);
        return ExamMilestoneResponse.fromEntity(saved);
    }

    public ExamMilestoneResponse updateMilestone(Long id, ExamMilestoneRequest request) {
        ExamMilestone milestone = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Exam milestone not found with id: " + id));

        milestone.setExamCode(request.getExamCode());
        milestone.setTitle(request.getTitle());
        milestone.setTargetDate(request.getTargetDate());
        if (request.getStatus() != null) {
            milestone.setStatus(request.getStatus());
        }
        milestone.setTargetScore(request.getTargetScore());
        milestone.setActualScore(request.getActualScore());
        milestone.setNotes(request.getNotes());

        ExamMilestone updated = repository.save(milestone);
        return ExamMilestoneResponse.fromEntity(updated);
    }

    public void deleteMilestone(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Exam milestone not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
