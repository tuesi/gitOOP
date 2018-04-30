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
public class TakenBook {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    
    private String isbn;
    private Date takenAt;

    TakenBook(String name, Date date) {
        isbn = name;
        takenAt = date;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }
    
    
    
    public TakenBook(){}
    
    public TakenBook(String book) {
        // split string
        
        String[] data = book.split("!!@!!");
        this.isbn = data[0];
        try {
        this.takenAt = sdf.parse(data[1]);
        }
        catch (Exception e) {
            this.takenAt = new Date();
        }
    }
    
    public String toString() {
        return "!!@!!"+this.isbn+"!!@!!"+sdf.format(takenAt);
    }
}
