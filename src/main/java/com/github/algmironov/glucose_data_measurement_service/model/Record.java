package com.github.algmironov.glucose_data_measurement_service.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private Float glucose;

    @ManyToOne
    private User user;
}
