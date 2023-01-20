package com.mv.fp9.data.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({
    "id",
    "titulo",
    "serie",
    "autor",
    "ano",
    "capa"
})
@JsonIgnoreProperties({
    "created_at",
    "updated_at"
})
public class Book {

    private int id;

    @JsonProperty("titulo")
    private String title;

    private String serie;

    @JsonProperty("autor")
    private String author;

    @JsonProperty("ano")
    private int year;

    @JsonProperty("capa")
    private String cover;


    public Book(int id, String title, String serie, String author, int year, String cover) {
        this.id = id;
        this.title = title;
        this.serie = serie;
        this.author = author;
        this.year = year;
        this.cover = cover;
    }


    public Book() {
    }

    public int getId() {
        return id;
    }

    @JsonProperty("titulo")
    public String getTitle() {
        return title;
    }

    public String getSerie() {
        return serie;
    }

    @JsonProperty("autor")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("ano")
    public int getYear() {
        return year;
    }

    @JsonProperty("capa")
    public String getCover() {
        return cover;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("titulo")
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @JsonProperty("autor")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("ano")
    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty("capa")
    public void setCover(String cover) {
        this.cover = cover;
    }

    public static Book parseJsonToBook(String jsonString) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, Book.class);

    }

//    Converter array de json para array de objetos
//    https://stackoverflow.com/questions/6349421/how-to-use-jackson-to-deserialise-an-array-of-objects
    public static Book[] parseJsonToBooks(String jsonString) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, Book[].class);

    }

    public static String convertBookToJson(Book book) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(book);
    }
}
