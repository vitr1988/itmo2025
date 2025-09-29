package ru.itmo.jpa.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.itmo.jpa.dto.EventDto;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;

    public void sendEvent(EventDto eventDto) {
        kafkaTemplate.send("itmo.topic_test", new Random().nextInt(10) + "", eventDto);
    }
}
