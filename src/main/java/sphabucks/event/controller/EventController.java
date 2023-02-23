package sphabucks.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.event.model.Event;
import sphabucks.event.model.EventImage;
import sphabucks.event.repository.IEventImageRepository;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.service.IEventService;
import sphabucks.event.vo.RequestEventImage;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final IEventService iEventService;

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event){

        return iEventService.addEvent(event);
    }

    @GetMapping("/get/{id}")
    public Event getEvent(@PathVariable Long id){
        log.info("input id ? {}",id);
        return iEventService.getEvent(id);
    }

    @PostMapping("/addImage")
    public EventImage addEventImage(@RequestBody RequestEventImage requestEventImage) {

        return iEventService.addEventImage(requestEventImage);
    }


}
