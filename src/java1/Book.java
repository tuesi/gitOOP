/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tadas
 */
public class Book {
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private String name;
    private String author;
    private String publisher;
    private Date releaseDate;
    private String description;
    private BookGenre genre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }
    
    public Book(String data) {
        String[] splitData = data.split("!!@!!");
        this.name = splitData[0];
        this.author = splitData[1];
        this.publisher = splitData[2];
        try {
            this.releaseDate = sdf.parse(splitData[3]);
        } catch (Exception e) {
            this.releaseDate = new Date();
        }
        this.description = splitData[4];
        this.genre = BookGenre.valueOf(splitData[5]);
    }
}
