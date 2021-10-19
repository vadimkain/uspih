package com.example.uspih.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле текст не может быть пустым") // Указываем, что поле текст не должно быть пустым
    @Length(max = 2048, message = "Слишком длинный текст!") // Указываем максимальный размер поля
    private String text;

    @Length(max = 255, message = "Слишком длинный текст!")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private com.example.uspih.domain.User author;

    private String filename;

    public Message() {
    }

    public Message(String text, String tag, com.example.uspih.domain.User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public com.example.uspih.domain.User getAuthor() {
        return author;
    }

    public void setAuthor(com.example.uspih.domain.User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}