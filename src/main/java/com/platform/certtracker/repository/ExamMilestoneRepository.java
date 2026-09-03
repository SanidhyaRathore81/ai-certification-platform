package com.platform.certtracker.repository;

import com.platform.certtracker.entity.ExamMilestone;
import com.platform.certtracker.entity.MilestoneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamMilestoneRepository extends JpaRepository<ExamMilestone, Long> {

    Optional<ExamMilestone> findByExamCode(String examCode);

    List<ExamMilestone> findByStatus(MilestoneStatus status);
}
