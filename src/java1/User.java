/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tadas
 */
public class User {
    private String username;
    private String password;
    private boolean isStudent;
    private float price;
    private List<TakenBook> books;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<TakenBook> getBooks() {
        return books;
    }

    public void setBooks(List<TakenBook> books) {
        this.books = books;
    }
    
    
    
    
    public User(String data) {
        books = new ArrayList<>();
        String[] userData = data.split("!!@!!");
        this.username = userData[0];
        this.password = userData[1];
        if (userData[2].equals("nostud")) {
            this.isStudent = false;
            this.price = Float.parseFloat(userData[3]);
        }
        else {
            this.isStudent = true;
            this.price = 0f;
        }
        for (int i=4;i<userData.length;i+=2) {
            String bookData = userData[i]+"!!@!!"+userData[i+1];
            books.add(new TakenBook(bookData));
        }
    }
    
     public String toString() {
        String toRet = this.username+"!!@!!"+this.password+"!!@!!";
        if (isStudent)
            toRet += "stud!!@!!0";
        else
            toRet += "nostud!!@!!"+this.price;
        for (TakenBook book : books) {
            toRet += book.toString();
        }
        return toRet;
    }
    
    public void getAllBooks() {
        for (TakenBook book : books) {
            System.out.println(book.getIsbn() + ": " + book.getTakenAt());
        }
    }

    public void addBook(Book get) {
        books.add(new TakenBook(get.getName(), new Date()));
    }
    
    public void returnBook(int num){
        books.remove(num);
    }
}
