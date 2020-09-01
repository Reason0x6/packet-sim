import java.util.*;

public class Packet{
    private double createdTime;
    private String msg;
    private int id;

    Packet(double in, int num, String msg){
        id = num;
        createdTime = in;
        this.msg = msg;
    }

    public String getMSG(){
        return msg;
    }

    public int getID(){
        return id;
    }

    //adds the delays to the timeS
    public void updateTime(double add){
        createdTime += add;
    }

    public double getCreatedTime(){
        return createdTime;
    }
}
