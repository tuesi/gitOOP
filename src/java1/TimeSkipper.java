/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java1;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author tadas
 */
public class TimeSkipper {
    private final Date startDate;
    private Date time;
    private Calendar cal;

    public TimeSkipper(Date startTime) {
        this.startDate = startTime;
        this.time = startTime;
        this.cal = Calendar.getInstance();
        cal.setTime(time);
    }
    
    public void setToNow() {
        this.time = new Date();
    }
    
    public void skipDays(int number) {
        cal.add(Calendar.DAY_OF_WEEK, number);
        time = cal.getTime();
    }
    public void skipWeeks(int number) {
        cal.add(Calendar.WEEK_OF_MONTH, number);
        time = cal.getTime();
    }
    public void skipMonths(int number) {
        cal.add(Calendar.MONTH, number);
        time = cal.getTime();
    }
    
    public int getDayDifference() {
        Long startMilis = startDate.getTime();
        Long currMilis = time.getTime();
        
        Long days = (currMilis - startMilis) / 86400000;
        return days.intValue();
    }
    
    public int getSkipNumber(String param) {
        System.out.println("Enter the desired number of " + param);
        
        Scanner inputScan = new Scanner(System.in);
        String input = inputScan.next();
        
        return Integer.parseInt(input);
    }
}
