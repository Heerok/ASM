package digitalaxom.asm.view;

import digitalaxom.asm.db.Article;

/**
 * Created by Heerok on 19-11-2016.
 */
public class ArticleDTO {
    public String author;
    public String name;
    public String filePath;
    public boolean active;
    public String language;
    public String articleString;
    public Long id;

    public ArticleDTO(Article a) {
        this.id = a.getId();
        this.author = a.getAuthor();
        this.name = a.getName();
        this.filePath = a.getFilePath();
        this.active = a.isActive();
        this.language = a.getLanguage();
        this.articleString = a.getArticleString();
    }
}
