package com.assignment.Newys.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class NewsArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String header;

    @Column(columnDefinition="text")
    String content;

    String createdAt;

    @ManyToOne
    User user;

    @ManyToMany(mappedBy = "likedArticles")
    Set<User> likes = new HashSet<>();

    public NewsArticle(String header, String content, String createdAt) {
        this.content = content;
        this.createdAt = createdAt;
        this.header = header;
    }

    public boolean addLike(User user){
        return likes.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsArticle)) return false;

        NewsArticle article = (NewsArticle) o;

        return getId().equals(article.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
