package digitalaxom.asm.db;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Heerok on 15-10-2016.
 */
@Entity
public class Article extends BaseModel{

    private String author;
    private String name;
    private String filePath;
    private boolean active;
    private String language;
    @Column(columnDefinition = "varchar(3000)")
    private String articleString;

    public String getArticleString() {
        return articleString;
    }

    public void setArticleString(String articleString) {
        this.articleString = articleString;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
