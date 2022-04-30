package com.example.reactive.dto;


import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//@Document
public class News {

    public News() {
    }

    public News(String content, Timestamp createAt) {
        this.content = content;
        if (createAt != null) {
            this.createAt = createAt;
        } else {
            this.createAt = Timestamp.valueOf(LocalDateTime.now());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    private String id;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    private Timestamp createAt = Timestamp.valueOf(LocalDateTime.now());
}
