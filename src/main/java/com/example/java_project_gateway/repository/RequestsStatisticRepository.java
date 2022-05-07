package com.example.java_project_gateway.repository;

import com.example.java_project_gateway.entity.RequestsStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsStatisticRepository extends JpaRepository<RequestsStatistic, Long> {
    public RequestsStatistic findByRequestId(String requestId);

}
