package com.platform.certtracker.dto;

import com.platform.certtracker.entity.MilestoneStatus;

import java.time.LocalDate;

public class ExamMilestoneRequest {
    private String examCode;
    private String title;
    private LocalDate targetDate;
    private MilestoneStatus status;
    private Integer targetScore;
    private Integer actualScore;
    private String notes;

    public ExamMilestoneRequest() {
    }

    public ExamMilestoneRequest(String examCode, String title, LocalDate targetDate, MilestoneStatus status,
                                Integer targetScore, Integer actualScore, String notes) {
        this.examCode = examCode;
        this.title = title;
        this.targetDate = targetDate;
        this.status = status;
        this.targetScore = targetScore;
        this.actualScore = actualScore;
        this.notes = notes;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String examCode;
        private String title;
        private LocalDate targetDate;
        private MilestoneStatus status;
        private Integer targetScore;
        private Integer actualScore;
        private String notes;

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

        public ExamMilestoneRequest build() {
            return new ExamMilestoneRequest(examCode, title, targetDate, status, targetScore, actualScore, notes);
        }
    }
}
