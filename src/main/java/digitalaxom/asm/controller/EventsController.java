package digitalaxom.asm.controller;

import digitalaxom.asm.db.Events;
import digitalaxom.asm.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Heerok on 04-10-2016.
 */
@RestController
@RequestMapping(value = "/admin/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = "/findAll")
    public List<Events> findAll(){
        return eventsService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody Events e){
        eventsService.save(e);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void save(@RequestParam Long id){
        eventsService.delete(id);
    }

}
