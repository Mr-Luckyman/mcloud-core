package ru.mentee.power.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mentee.power.spring.entity.VisitCounter;

public interface VisitCounterRepository extends JpaRepository<VisitCounter, Long> {
}
