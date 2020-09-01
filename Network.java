import java.util.*;

public class Network{
    private int packets;
    LinkedList<Node> nodes;

    Network(){
        nodes = new LinkedList<Node>();
        packets = 0;
    }

    //Creates the packet and sends the packet
    public boolean send(double time, String sNodeID, String rNodeID, String msg){

        //logs packet creation
        System.out.println("Packet Created & Sent @ " + time + "ms" + "\t\tPacket ID: " + (packets) + "\t\tRoute: " + msg);
        Packet temp = new Packet(time ,packets++, msg);

        //finds sending node
        for(Node x: nodes){
            if(x.getID().equals(sNodeID)){
                //creates send request
                x.send(rNodeID, temp);
                return true;
            }
        }
        return false;
    }

    public boolean connect(String aS, String bS, double delay){

        //Finds the 2 selected nodes
        try{
            Node temp1 = null, temp2 = null;
            for(Node a: nodes){
                if(a.getID().equals(aS)){
                    temp1 = a;
                }
                if(a.getID().equals(bS)){
                    temp2 = a;
                }
            }

            //Creates the connections
            Connection aC = new Connection(temp1, temp2, delay);
            Connection bC = new Connection(temp2, temp1, delay);

            //submits the connections
            temp1.connect(aC);
            temp2.connect(bC);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public void addNode(Node in){
        nodes.add(in);
    }

    public void update(double newT){
        for(Node x: nodes){
            x.update(newT);
        }
    }


}
