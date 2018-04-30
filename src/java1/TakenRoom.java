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
public class TakenRoom {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    
    private String roomNo;
    private Date takenUntil;
    
    
    TakenRoom(String name, Date date)
    {
        roomNo = name;
        takenUntil = date;
        System.out.println(name);
        System.out.println(date);
    }
    
    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Date getTakenUntil() {
        return takenUntil;
    }

    public void setTakenUntil(Date takenUntil) {
        this.takenUntil = takenUntil;
    }
    
    
    
    public TakenRoom(){}
    
    public TakenRoom(String rooms)
    {
        System.out.println("name");
        String[] data = rooms.split("!!@!!");
        this.roomNo = data[0];
        try
        {
            this.takenUntil = sdf.parse(data[1]);
        }
        catch(Exception e)
        {
            this.takenUntil = new Date();
        }
    }
    
    public String toString()
    {
        return "!!@!!"+this.roomNo+"!!@!!"+sdf.format(takenUntil);
    }
}
