package com.github.algmironov.glucose_data_measurement_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.algmironov.glucose_data_measurement_service.model.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

}
