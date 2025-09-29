package ru.itmo.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.jpa.dto.EventDto;
import ru.itmo.jpa.event.EventProducer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventProducer eventProducer;

    @PostMapping
    public void sendEvent(@RequestBody EventDto eventDto) {
        eventProducer.sendEvent(eventDto);
    }
}
