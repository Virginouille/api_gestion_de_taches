package com.api.gestiondetache.dto;

import com.api.gestiondetache.model.TaskStatuts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    private String title;
    private TaskStatuts statut;
    private Long assigneeId;
    private List<Long> userIds;
}
