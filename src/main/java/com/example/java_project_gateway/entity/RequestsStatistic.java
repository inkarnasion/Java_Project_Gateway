package com.example.java_project_gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests_statistic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestsStatistic extends BaseEntity {
    private String serviceName;

    private String requestId;

    private LocalDateTime time;

    private String clientId;
}
