package com.matiaskobold.proyectopp6.model;

public class SteamNews {
    private String title;
    private String contents;
    private String author;
    private String url;

    public SteamNews(String title, String contents, String author, String url) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
