package digitalaxom.asm.service;

import digitalaxom.asm.Repository.SattraRepository;
import digitalaxom.asm.db.SattrasList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Heerok on 11-10-2016.
 */
@Service
public class SattraService {

    @Autowired
    private SattraRepository sattraRepository;

    public List<SattrasList> findAll(){
        return sattraRepository.findAll();
    }

    public SattrasList save(SattrasList e){
        return sattraRepository.save(e);
    }

    public void delete(Long id){
        sattraRepository.delete(id);
    }

}
