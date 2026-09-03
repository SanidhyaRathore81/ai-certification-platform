package com.platform.certtracker.dto;

import com.platform.certtracker.entity.ExamMilestone;
import com.platform.certtracker.entity.MilestoneStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExamMilestoneResponse {
    private Long id;
    private String examCode;
    private String title;
    private LocalDate targetDate;
    private MilestoneStatus status;
    private Integer targetScore;
    private Integer actualScore;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ExamMilestoneResponse() {
    }

    public ExamMilestoneResponse(Long id, String examCode, String title, LocalDate targetDate, MilestoneStatus status,
                                 Integer targetScore, Integer actualScore, String notes,
                                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.examCode = examCode;
        this.title = title;
        this.targetDate = targetDate;
        this.status = status;
        this.targetScore = targetScore;
        this.actualScore = actualScore;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ExamMilestoneResponse fromEntity(ExamMilestone entity) {
        return builder()
                .id(entity.getId())
                .examCode(entity.getExamCode())
                .title(entity.getTitle())
                .targetDate(entity.getTargetDate())
                .status(entity.getStatus())
                .targetScore(entity.getTargetScore())
                .actualScore(entity.getActualScore())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public MilestoneStatus getStatus() {
        return status;
    }

    public void setStatus(MilestoneStatus status) {
        this.status = status;
    }

    public Integer getTargetScore() {
        return targetScore;
    }

    public void setTargetScore(Integer targetScore) {
        this.targetScore = targetScore;
    }

    public Integer getActualScore() {
        return actualScore;
    }

    public void setActualScore(Integer actualScore) {
        this.actualScore = actualScore;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String examCode;
        private String title;
        private LocalDate targetDate;
        private MilestoneStatus status;
        private Integer targetScore;
        private Integer actualScore;
        private String notes;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder examCode(String examCode) {
            this.examCode = examCode;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder targetDate(LocalDate targetDate) {
            this.targetDate = targetDate;
            return this;
        }

        public Builder status(MilestoneStatus status) {
            this.status = status;
            return this;
        }

        public Builder targetScore(Integer targetScore) {
            this.targetScore = targetScore;
            return this;
        }

        public Builder actualScore(Integer actualScore) {
            this.actualScore = actualScore;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ExamMilestoneResponse build() {
            return new ExamMilestoneResponse(id, examCode, title, targetDate, status, targetScore, actualScore, notes, createdAt, updatedAt);
        }
    }
}
