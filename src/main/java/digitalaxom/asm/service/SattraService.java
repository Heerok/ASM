package digitalaxom.asm.service;

import digitalaxom.asm.Repository.ArticleRepository;
import digitalaxom.asm.Repository.SattraRepository;
import digitalaxom.asm.db.Article;
import digitalaxom.asm.db.SattrasList;
import digitalaxom.asm.view.SattraDTO;
import digitalaxom.asm.view.SattrasListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Heerok on 11-10-2016.
 */
@Service
public class SattraService {

    @Autowired
    private SattraRepository sattraRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<SattrasList> findAll(){
        return sattraRepository.findAll();
    }

    public SattrasList save(SattrasListDTO e){
        SattrasList sattrasList;
        if(e.getId()!=null)
            sattrasList = sattraRepository.findOne(e.getId());
        else
            sattrasList = new SattrasList();
        sattrasList.setDistrict(e.getDistrict());
        sattrasList.setContactDetails(e.getContactDetails());
        sattrasList.setPhoneNo(e.getPhoneNo());
        sattrasList.setSattraName(e.getSattraName());
        sattrasList.setState(e.getState());
        sattrasList.setSattradhikarName(e.getSattradhikarName());
        return sattraRepository.save(sattrasList);
    }

    public void delete(Long id){
        sattraRepository.delete(id);
    }

    public boolean existArticle(Long articleId){
        Article article = articleRepository.findOne(articleId);
        return sattraRepository.findByArticlesIn(article)!=null;
    }

    public boolean addArticle(Long articleId,Long sattraId){
        try{
            SattrasList sattrasList = sattraRepository.findOne(sattraId);
            Set<Article> articles = sattrasList.getArticles();
            articles.add(articleRepository.findOne(articleId));
            sattrasList.setArticles(articles);
            sattraRepository.save(sattrasList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteArticle(Long sattraId, Long articleId){
        try{
            SattrasList sattrasList = sattraRepository.findOne(sattraId);
            Set<Article> articles = sattrasList.getArticles();
            articles.remove(articleRepository.findOne(articleId));
            sattrasList.setArticles(articles);
            sattraRepository.save(sattrasList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
