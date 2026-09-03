package com.platform.certtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.certtracker.config.SecurityConfig;
import com.platform.certtracker.dto.ExamMilestoneRequest;
import com.platform.certtracker.dto.ExamMilestoneResponse;
import com.platform.certtracker.entity.MilestoneStatus;
import com.platform.certtracker.service.ExamMilestoneService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExamMilestoneController.class)
@Import(SecurityConfig.class)
class ExamMilestoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExamMilestoneService service;

    @Test
    @DisplayName("GET /api/v1/milestones should return all milestones")
    void testGetAllMilestones() throws Exception {
        ExamMilestoneResponse response = ExamMilestoneResponse.builder()
                .id(1L)
                .examCode("CCDV-F")
                .title("Claude Certified Developer - Foundations")
                .targetDate(LocalDate.of(2026, 9, 29))
                .status(MilestoneStatus.IN_PROGRESS)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(service.getAllMilestones()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/milestones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].examCode").value("CCDV-F"))
                .andExpect(jsonPath("$[0].title").value("Claude Certified Developer - Foundations"));
    }

    @Test
    @DisplayName("POST /api/v1/milestones should create a milestone")
    void testCreateMilestone() throws Exception {
        ExamMilestoneRequest request = ExamMilestoneRequest.builder()
                .examCode("CCAR-F")
                .title("Claude Certified Architect - Foundations")
                .targetDate(LocalDate.of(2026, 10, 19))
                .status(MilestoneStatus.PLANNED)
                .build();

        ExamMilestoneResponse response = ExamMilestoneResponse.builder()
                .id(2L)
                .examCode("CCAR-F")
                .title("Claude Certified Architect - Foundations")
                .targetDate(LocalDate.of(2026, 10, 19))
                .status(MilestoneStatus.PLANNED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(service.createMilestone(any(ExamMilestoneRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/milestones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.examCode").value("CCAR-F"));
    }

    @Test
    @DisplayName("DELETE /api/v1/milestones/{id} should return no content")
    void testDeleteMilestone() throws Exception {
        doNothing().when(service).deleteMilestone(1L);

        mockMvc.perform(delete("/api/v1/milestones/1"))
                .andExpect(status().isNoContent());
    }
}
