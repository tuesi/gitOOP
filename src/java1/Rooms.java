/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tadas
 */
public class Rooms 
{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    Date systemTime = new Date();
    
    private String roomName;
    private String Status;
    private Date TakenUntil;

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Date getTakenUntil() {
        return TakenUntil;
    }

    public void setTakenUntil(Date TakenUntil) {
        this.TakenUntil = TakenUntil;
    }
    
      public String toString() {
        String toRet = this.roomName+"!!@!!"+this.Status+"!!@!!"+sdf.format(TakenUntil);
        return toRet;
    }
    
    public Rooms(String data)
    {
        TimeSkipper skipper = new TimeSkipper(systemTime);
        String[] splitData = data.split("!!@!!");
        this.roomName = splitData[0];
        if(splitData[1].equals("Not Taken: ") || splitData[2] == (sdf.format(skipper.getTime()).toString()))
        {
            Status = "Not Taken: ";
            this.TakenUntil = new Date();
        }
        else
        {
            try {
                Status = "Taken until: ";
                this.TakenUntil = sdf.parse(splitData[2]);
            } catch (ParseException ex) {
                System.out.println("Somthing whent wrong");
            }
        }
    }
    
}
