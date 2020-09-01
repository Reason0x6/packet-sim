import java.util.*;

public class Node{
    
    private double proccessingD = 0.025;
    private double queuingD = 0.0016166;
    private double totalD;
    private String ID;
    private LinkedList<Connection> connections;
    private LinkedList<Packet> queue;
    private LinkedList<Packet> out;

    Node(double pd, double qd, String ID){
        proccessingD = pd;
        queuingD = qd;
        totalD = pd + qd;
        connections = new LinkedList<Connection>();
        queue = new LinkedList<Packet>();
        out = new LinkedList<Packet>();
        this.ID = ID;
    }

    //adds connections
    public void connect(Connection temp){
        connections.add(temp);
    }

    //gets the send request
    public boolean send(String rID, Packet msg){
        //finds related connection
        for(Connection x: connections){
            if(x.receiver.getID().equals(rID)){
                //adds the delay
                msg.updateTime(x.delay);
                //adds packet to output stream for printing
                out.add(msg);

                //send packet to recieving node
                x.receiver.receive(msg);
                
                return true;
            }
        }
        return false;
    }

    //gets recieve requesti
    public Boolean receive(Packet msg){
        //takes the queue size
        int size = queue.size();
        //adds msg to queue
        queue.add(msg);
        //returns queue size increase bool
        return size < queue.size();
    }

    public String getID(){
        return ID;
    }


    public void update(double time){

        //handles receiving packet messaging
        for(Packet x: out){
            if(x.getCreatedTime() < time){
                System.out.println("Packet Received @ " + x.getCreatedTime() + "ms" + "\t\tPacket ID: " + (x.getID()) + "\t\tRoute: " + x.getMSG());
                out.remove(x);
            }
        }

        //handles removal packet messaging
        for(Packet x: queue){
            if(x.getCreatedTime() + totalD < time){
                System.out.println("Packet Removed @ " + (x.getCreatedTime()+totalD )+ "ms" + "\t\t\tPacket ID: " + (x.getID())+ "\t\tRoute: " + x.getMSG());
                queue.remove(x);
            }
        }
    
    }



}
