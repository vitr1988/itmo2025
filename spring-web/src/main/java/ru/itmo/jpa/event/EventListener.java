package ru.itmo.jpa.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.itmo.jpa.dto.EventDto;

@Slf4j
@Component
public class EventListener {

    @KafkaListener(topics = "itmo.topic_test")
    public void onEvent(EventDto event) {
        log.info("Received event {}", event);
    }
}
