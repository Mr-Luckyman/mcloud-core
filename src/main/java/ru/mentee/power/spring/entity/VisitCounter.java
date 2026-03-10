package ru.mentee.power.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "visits")
@Data
public class VisitCounter {
    @Id
    private Long id = 1L;

    private Long count;

}
