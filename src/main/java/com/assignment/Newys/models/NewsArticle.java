package com.assignment.Newys.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public NewsArticle(String header, String content, String createdAt) {
        this.content = content;
        this.createdAt = createdAt;
        this.header = header;
    }
}
