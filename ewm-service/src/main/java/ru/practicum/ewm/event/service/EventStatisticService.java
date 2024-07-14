package ru.practicum.ewm.event.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.practicum.stats.StatClient;
import ru.practicum.stats.ViewStats;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@Slf4j
@RequiredArgsConstructor
public class EventStatisticService {

   // private final StatClient statClient;
    private final Gson gson;

    public Map<Long, Long> getEventsViews(List<Long> eventsId) {
        Map<Long, Long> eventsViews = new HashMap<>();
        if (eventsId == null || eventsId.isEmpty()) {
            return eventsViews;
        }

        List<String> uris = new ArrayList<>();
        for (Long eventId : eventsId) {
            uris.add("EVENT_URI" + eventId);
        }

//        ResponseEntity<Object> response = statClient.getStats(
//                LocalDateTime.now().minusDays(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                uris,
//                true
//        );

        Object body = null;//response.getBody();
        if (body != null) {
            String json = gson.toJson(body);
            Type typeToken = new TypeToken<List<ViewStats>>() {
            }.getType();
            List<ViewStats> viewStats = new Gson().fromJson(json, typeToken);
            eventsViews = eventsId.stream().collect(Collectors.toMap(eventId -> eventId, eventId -> 0L, (a, b) -> b));

            if (!viewStats.isEmpty()) {
                for (ViewStats view : viewStats) {
                    eventsViews.put(Long.parseLong(view.getUri().split("/", 0)[2]), view.getHits());
                }
            }
        }
        return eventsViews;
    }
}