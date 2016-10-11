package digitalaxom.asm.service;

import digitalaxom.asm.Repository.EventsRepository;
import digitalaxom.asm.db.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Heerok on 04-10-2016.
 */
@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    public List<Events> findAll(){
        return eventsRepository.findAll();
    }

    public Events save(Events e){
        return eventsRepository.save(e);
    }

    public void delete(Long id){
        eventsRepository.delete(id);
    }

}
