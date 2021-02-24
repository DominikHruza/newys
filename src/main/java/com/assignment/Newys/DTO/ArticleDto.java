package com.assignment.Newys.DTO;

import com.assignment.Newys.models.NewsArticle;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ArticleDto {
    private Long id;
    private String header;
    private String content;
    private String username;
    private String createdAt;

    public ArticleDto(Long id, String header, String content, String username, String date) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.username = username;
        this.createdAt = date;
    }

    public static ArticleDto ConvertToDto(NewsArticle article){
        return new ArticleDto(
                article.getId(),
                article.getHeader(),
                article.getContent(),
                article.getUser().getUsername(),
                article.getCreatedAt()
        );
    }

}
