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

    private String header;

    @Column(columnDefinition="text")
    private String content;

    private String createdAt;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "likedArticles")
    private Set<User> likes = new HashSet<>();

    @ManyToMany(mappedBy = "articles")
    private Set<UserGroup> groups = new HashSet<>();

    public NewsArticle(String header, String content, String createdAt) {
        this.content = content;
        this.createdAt = createdAt;
        this.header = header;
    }

    public boolean addLike(User user){
        return likes.add(user);
    }

    public boolean addGroup(UserGroup group){
        return groups.add(group);
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
