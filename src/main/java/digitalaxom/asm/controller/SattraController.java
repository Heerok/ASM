package digitalaxom.asm.controller;

import digitalaxom.asm.db.SattrasList;
import digitalaxom.asm.service.SattraService;
import digitalaxom.asm.view.JsonResponse;
import digitalaxom.asm.view.SattrasListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Heerok on 04-10-2016.
 */
@RestController
@RequestMapping(value = "/admin/sattra")
public class SattraController {

    @Autowired
    private SattraService sattraService;

    @RequestMapping(value = "/findAll")
    public List<SattrasListDTO> findAll(){
        return toDTO(sattraService.findAll());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody SattrasListDTO e){
        sattraService.save(e);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void save(@RequestParam Long id){
        sattraService.delete(id);
    }

    @RequestMapping(value = "/addArticle")
    public JsonResponse addArticle(@RequestParam Long sattraId, @RequestParam Long articleId){
        if(sattraService.existArticle(articleId))
            return new JsonResponse(false,"Article already added");

        sattraService.addArticle(articleId,sattraId);
        return new JsonResponse(true,"Article added successfully");
    }

    @RequestMapping(value = "/deleteArticle")
    public JsonResponse deleteArticle(@RequestParam Long sattraId, @RequestParam Long articleId){

        if(!sattraService.deleteArticle(sattraId,articleId))
            return new JsonResponse(false,"Error while removing article");

        return new JsonResponse(true,"Article removed successfully");
    }

    private List<SattrasListDTO> toDTO(List<SattrasList> list){
        return list.stream().map(SattrasListDTO::new).collect(Collectors.toList());
    }

}
