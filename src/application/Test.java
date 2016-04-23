package application;

import ff.physics.AirResistancePhysics;
import ff.physics.AirResistancePhysics.DragMode;

/**
 * Created by Deyv on 19/04/2016.
 */
public class Test {
    public static void main(String[] args) {
        AirResistancePhysics arp = new AirResistancePhysics(0, 50, 1, 0.01, 0.1, -9.8, DragMode.SQUARE);
        //AirResistancePhysics narp = new AirResistancePhysics(0, 50, 1, 0, 0.1, 9.8, DragMode.LINEAR);

        while(arp.getHeight()>=0 || arp.getTime()==0){
            //System.out.println(arp.toString());
            arp.calculateNextStep();
        }

        System.out.println(arp.getAscendTime());
        System.out.println(arp.getDescendTime());
        /*
        System.out.println("Second case: ");
        while(arp.getHeight()>=0 || narp.getTime()==0){

            System.out.println(narp.toString());
            narp.calculateNextStep();
        }
        */

    }
}
