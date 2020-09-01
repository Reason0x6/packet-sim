import java.util.*;
import java.math.BigDecimal;
import java.math.*;

public class Simulate{

    public static void main(String[] args){

            //Create network object
            Network test = new Network();

            //Create Nodes pros delay, q delay, ID
            Node one = new Node(1,1,"A");
            Node two = new Node(1,1,"B");
            Node three = new Node(1,2,"C");

            //Add Nodes to network
            test.addNode(one);
            test.addNode(two);
            test.addNode(three);

            //Connect Nodes -> send node, rec node, delay
            test.connect("A", "B", 4);
            test.connect("A", "C", 10);

            //Network running loop

            for(double i = 1; i < 100; i+=0.1){

                //do the rounding thing
                 i = BigDecimal.valueOf(i)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();

                //update current model
                test.update(i);

                //test.send(current time, sending node, receiving node, path desc)
                //send packet from a -> b every 12 seconds
                if(i%12.0 == 0)                test.send(i, "A","B", "A -> B");

                //send packet from a -> c every 33 seconds
                if(i%33.0 == 0)                test.send(i, "A","C", "A -> C");
                
                
            }
        }

        

}
