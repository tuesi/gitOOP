/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java1;

import java.util.ArrayList;
import java.util.Calendar;
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
    private List<TakenRoom> rooms;
    private Date time;

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
    
    public List<TakenRoom> getRoom()
    {
        return rooms;
    }
    
    public void setRooms(List<TakenRoom> rooms)
    {
        this.rooms = rooms;
    }
    
    
    
    public User(String data) {
        books = new ArrayList<>();
        rooms = new ArrayList<>();
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
            if(userData[i].equals("room"))
            {
                for(int y=i+1;y<userData.length;y+=2)
                {
                    String roomData = userData[y]+"!!@!!"+userData[y+1];
                    rooms.add(new TakenRoom(roomData));
                    if(userData[y+1].equals("room"))
                    {
                        y++;
                    }
                }
                break;
            }
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
        for (TakenRoom room : rooms)
        {
            toRet += "!!@!!room";
            toRet += room.toString();
        }
        return toRet;
    }
    
    public void getAllBooks() {
        for (TakenBook book : books) {
            System.out.println(book.getIsbn() + ": " + book.getTakenAt());
        }
    }
    
     public void getAllRooms() {
        for (TakenRoom room : rooms) {
            System.out.println(room.getRoomNo() + ": " + room.getTakenUntil());
        }
    }

    public void addBook(Book get) {
        books.add(new TakenBook(get.getName(), new Date()));
    }
    
    public void returnBook(int num){
        books.remove(num);
    }
    
    
    public void addRoom(Rooms get, int dayDifference)
    {
        this.time = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, dayDifference);
        time = c.getTime();
        
        get.setStatus("Taken until: ");
        get.setTakenUntil(time);
        rooms.add(new TakenRoom(get.getRoomName(), time));
    }
    

    void updateDebt(int dayDifference) {
        for (TakenBook book : books) {
            TimeSkipper skipper = new TimeSkipper(book.getTakenAt());
            skipper.setToNow();
            int thisDiff = skipper.getDayDifference() + dayDifference;
            this.price += thisDiff * 0.2;
        }
    }
}
