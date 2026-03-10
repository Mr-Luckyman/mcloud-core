package ru.mentee.power.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mentee.power.spring.entity.VisitCounter;
import ru.mentee.power.spring.repository.VisitCounterRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitCounterRepository visitCounterRepository;

    @Transactional
    public Long incrementAndGetCount() {
        Optional<VisitCounter> counterOpt = visitCounterRepository.findById(1L);

        VisitCounter counter;
        if (counterOpt.isPresent()) {
            counter = counterOpt.get();
            counter.setCount(counter.getCount() + 1);
        } else {
            counter = new VisitCounter();
            counter.setId(1L);
            counter.setCount(1L);
        }
        VisitCounter savedCounter = visitCounterRepository.save(counter);

        return savedCounter.getCount();
    }
}
