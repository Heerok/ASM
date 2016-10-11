package digitalaxom.asm.controller;

import digitalaxom.asm.db.Events;
import digitalaxom.asm.db.SattrasList;
import digitalaxom.asm.service.EventsService;
import digitalaxom.asm.service.SattraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Heerok on 04-10-2016.
 */
@RestController
@RequestMapping(value = "/admin/sattra")
public class SattraController {

    @Autowired
    private SattraService sattraService;

    @RequestMapping(value = "/findAll")
    public List<SattrasList> findAll(){
        return sattraService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody SattrasList e){
        sattraService.save(e);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void save(@RequestParam Long id){
        sattraService.delete(id);
    }

}
