package ru.practicum.ewm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StatClient extends BaseClient {

    @Autowired
    public StatClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + ""))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> getStats(String start, String end, List<String> uris, boolean unique) {
        StringBuilder uri = new StringBuilder();
        for (String u : uris) {
            uri.append("&uris=").append(u);
        }
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "unique", unique);
        log.info("Выполнение запрос на получение статистики через модуль client");
        return get("/stats?start={start}&end={end}" + uri + "&unique={unique}", parameters);
    }

    public ResponseEntity<Object> addHit(EndpointHit endpointHit) {
        log.info("Выполнение запрос на добавление события вызова сервиса в статистику через модуль client");
        return post("/hit", endpointHit);
    }
}
