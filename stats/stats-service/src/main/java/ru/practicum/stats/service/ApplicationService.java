package ru.practicum.stats.service;

import ru.practicum.stats.model.Application;

import java.util.Optional;

public interface ApplicationService {
    Optional<Application> fineByName(String app);

    Optional<Application> add(String app);
}
