package com.mv.fp7.data.model;

public class Book {
    private int id;
    private String title;
    private String serie;
    private String author;
    private int year;
    private int cover;

    private static int sIdIncrementer = 0;

    public Book(int id, int year, int cover, String title, String serie, String author) {
        sIdIncrementer++;

        this.id = id;
        this.title = title;
        this.serie = serie;
        this.author = author;
        this.year = year;
        this.cover = cover;
    }

    public Book(int year, int cover, String title, String serie, String author){
        sIdIncrementer++;

        this.id = sIdIncrementer;
        this.title = title;
        this.serie = serie;
        this.author = author;
        this.year = year;
        this.cover = cover;
    }

    public Book() {
        sIdIncrementer++;

        this.id = sIdIncrementer;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSerie() {
        return serie;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getCover() {
        return cover;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }
}
