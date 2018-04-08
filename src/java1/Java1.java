
package java1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Java1 {

    public static void main(String[] args) 
    {
        Date systemTime = new Date();
        List<User> users = readUsers("read.txt");
        List<Book> books = readBooks("books.txt");
        User thisUser = null;
        
        while (thisUser == null) {
            //User name input
            System.out.println("Enter Username");
            Scanner inputScan = new Scanner(System.in);
            String input = inputScan.next();

            //User password input
            System.out.println("Enter Password");
            Scanner passScan = new Scanner(System.in);
            String pass = passScan.next();


            for (User user : users) {
                if (user.getUsername().equals(input) && user.getPassword().equals(pass))
                    thisUser = user;
            }
            if (thisUser == null) {
                System.out.println("No such user");
            }
            else {
                System.out.println("Hello " + thisUser.getUsername());
                thisUser.getAllBooks();
                for (User user : users) {
                if (user.getUsername().equals(thisUser.getUsername()))
                    user = thisUser;
                 }
                //updateFile(users, "read.txt");
            }
        }
        boolean wantsToExit = false;
        while (!wantsToExit) {
            System.out.println("1 - add book, 0 - exit, 2 - remove book, 3 - skip time, 4 - show debt");
            Scanner inputScan = new Scanner(System.in);
            String input = inputScan.next();
            
            switch (input) {
                case "1":
                    addBook(thisUser, books);
                    break;
                case "0":
                    wantsToExit = true;
                    break;
                case"2":
                    returnBook(thisUser);
                    break;
                case "3":
                    skipTime(thisUser, systemTime);
                    break;
                case "4":
                    showDebt(thisUser);
                    break;
                default:
                    System.out.println("Unrecognized command");
            }
        }
        
        updateFile(users, "read.txt");
        
    }
    
    public static String readString(String file)
    {
        String text = "";
        try
        {
            Scanner s = new Scanner(new File(file));
            while(s.hasNextLine())
            {
                text = text + s.nextLine();
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
            
        }
        
        return text;
    }
    
    public static List<User> readUsers(String file)
    {
        String text = "";
        try
        {
            List<User> users = new ArrayList<>();
            Scanner s = new Scanner(new File(file));
            while(s.hasNextLine())
            {
                users.add(new User(s.nextLine()));
            }

            return users;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
            
        }
        
        return Collections.emptyList();
    }
    
    public static void updateFile(List<User> users, String file) {
        try {
            FileWriter w = new FileWriter(new File(file), false);
            for (User user : users) {
                w.write(user.toString());
                w.write(System.getProperty( "line.separator" ));
            }
            w.close();
        } catch (Exception e) {
            System.out.println("file not found");
        }
    }

    private static void addBook(User thisUser, List<Book> books) {
        System.out.println("Available books:");
        int bookCode = 0;
        for (Book book : books) {
            System.out.println(bookCode + "  " + book.getReleaseDate() + " " + book.getAuthor() + " : " + book.getName() + " (" + book.getPublisher() + " " + book.getGenre() + ") " + book.getDescription());
            bookCode++;
        }
        
        System.out.println("Ënter the number of the book to take it");
        Scanner inputScan = new Scanner(System.in);
        String input = inputScan.next();
        int num = Integer.parseInt(input);
        if (num >= books.size())
            System.out.println("No such book");
        else {
            thisUser.addBook(books.get(num));
        }
    }

    private static List<Book> readBooks(String file) {
        try
        {
            List<Book> books = new ArrayList<>();
            Scanner s = new Scanner(new File(file));
            while(s.hasNextLine())
            {
                books.add(new Book(s.nextLine()));
            }

            return books;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
            
        }
        
        return Collections.emptyList();
    }

    private static void returnBook(User user) {
        System.out.println("Available books:");
        int bookCode = 0;
        for (TakenBook book : user.getBooks()) {
            System.out.println(bookCode + " " + book.getIsbn() + " " + book.getTakenAt());
            bookCode++;
        }
        
        System.out.println("Ënter the number of the book to return");
        Scanner inputScan = new Scanner(System.in);
        String input = inputScan.next();
        int num = Integer.parseInt(input);
        if (num >= user.getBooks().size())
            System.out.println("No such book");
        else {
            user.returnBook(num);
        }
    }

    private static void skipTime(User thisUser, Date systemTime) {
        TimeSkipper skipper = new TimeSkipper(systemTime);
        System.out.println("1 - skip days, 2 - skip weeks, 3 - skip months");
        Scanner inputScan = new Scanner(System.in);
        String input = inputScan.next();

        switch (input) {
            case "1":
                skipper.skipDays(skipper.getSkipNumber("days"));
                thisUser.updateDebt(skipper.getDayDifference());
                break;
            case"2":
                skipper.skipWeeks(skipper.getSkipNumber("weeks"));
                thisUser.updateDebt(skipper.getDayDifference());
                break;
            case "3":
                skipper.skipMonths(skipper.getSkipNumber("moths"));
                thisUser.updateDebt(skipper.getDayDifference());
                break;
            default:
                System.out.println("Unrecognized command");
        }
    }

    private static void showDebt(User thisUser) {
        System.out.println("Your debt is: " + thisUser.getPrice());
    }
    
}
