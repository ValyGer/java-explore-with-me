package ru.practicum.stats.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.stats.HitDto;
import ru.practicum.stats.model.Hit;

@Mapper(componentModel = "spring")
public interface HitMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "app", ignore = true)
    @Mapping(target = "timestamp", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Hit toHit(HitDto hitDto);
}
