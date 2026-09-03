package com.platform.certtracker.service;

import com.platform.certtracker.dto.ExamMilestoneRequest;
import com.platform.certtracker.dto.ExamMilestoneResponse;
import com.platform.certtracker.entity.ExamMilestone;
import com.platform.certtracker.entity.MilestoneStatus;
import com.platform.certtracker.repository.ExamMilestoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamMilestoneServiceTest {

    @Mock
    private ExamMilestoneRepository repository;

    @InjectMocks
    private ExamMilestoneService service;

    private ExamMilestone milestone;
    private ExamMilestoneRequest request;

    @BeforeEach
    void setUp() {
        milestone = ExamMilestone.builder()
                .id(1L)
                .examCode("CCDV-F")
                .title("Claude Certified Developer - Foundations")
                .targetDate(LocalDate.of(2026, 9, 29))
                .status(MilestoneStatus.IN_PROGRESS)
                .targetScore(850)
                .actualScore(null)
                .notes("Focus on Tool Use & Prompt Caching")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        request = ExamMilestoneRequest.builder()
                .examCode("CCDV-F")
                .title("Claude Certified Developer - Foundations")
                .targetDate(LocalDate.of(2026, 9, 29))
                .status(MilestoneStatus.IN_PROGRESS)
                .targetScore(850)
                .notes("Focus on Tool Use & Prompt Caching")
                .build();
    }

    @Test
    @DisplayName("Should retrieve all exam milestones")
    void testGetAllMilestones() {
        when(repository.findAll()).thenReturn(List.of(milestone));

        List<ExamMilestoneResponse> result = service.getAllMilestones();

        assertEquals(1, result.size());
        assertEquals("CCDV-F", result.get(0).getExamCode());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find milestone by ID")
    void testGetMilestoneByIdSuccess() {
        when(repository.findById(1L)).thenReturn(Optional.of(milestone));

        ExamMilestoneResponse result = service.getMilestoneById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("CCDV-F", result.getExamCode());
    }

    @Test
    @DisplayName("Should throw when milestone ID not found")
    void testGetMilestoneByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.getMilestoneById(99L));
    }

    @Test
    @DisplayName("Should create milestone successfully")
    void testCreateMilestone() {
        when(repository.save(any(ExamMilestone.class))).thenReturn(milestone);

        ExamMilestoneResponse created = service.createMilestone(request);

        assertNotNull(created);
        assertEquals("CCDV-F", created.getExamCode());
        verify(repository, times(1)).save(any(ExamMilestone.class));
    }

    @Test
    @DisplayName("Should update existing milestone")
    void testUpdateMilestone() {
        when(repository.findById(1L)).thenReturn(Optional.of(milestone));
        when(repository.save(any(ExamMilestone.class))).thenReturn(milestone);

        request.setActualScore(900);
        request.setStatus(MilestoneStatus.COMPLETED);

        ExamMilestoneResponse updated = service.updateMilestone(1L, request);

        assertNotNull(updated);
        verify(repository, times(1)).save(milestone);
    }

    @Test
    @DisplayName("Should delete milestone by ID")
    void testDeleteMilestoneSuccess() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deleteMilestone(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw when deleting non-existent milestone")
    void testDeleteMilestoneNotFound() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> service.deleteMilestone(99L));
        verify(repository, never()).deleteById(anyLong());
    }
}
