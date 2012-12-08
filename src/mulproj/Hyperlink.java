/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mulproj;

import java.util.Arrays;

/**
 *
 * @author Hardik
 */
public class Hyperlink {
    ResizeRectangle link;
    String name;
    int pos;
    double[] nwXc, nwYc;
    double[] seXc, seYc;
    Integer[] srcFrameNo;
    Integer[] destFrameNo;
    String destVidURL;
    Boolean connected;

    public Hyperlink() {
        this.connected = false;
        this.link = new ResizeRectangle(50,50,150,100);
        this.nwXc = new double[20];
        this.nwYc = new double[20];
        this.seXc = new double[20];
        this.seYc = new double[20];
        this.srcFrameNo = new Integer[20];
        this.destFrameNo = new Integer[20];
        this.pos = -1;
    }
    
    public void registerCoords(int frame) {
        if(pos == -1) {
            pos = 0; // first link ever
        } else {
            // we need to find out if there is an existing link on this frame
            int oldpos = Arrays.asList(srcFrameNo).indexOf(frame);
            if(oldpos == -1) {
                System.out.println(oldpos+" not found");
                // no previous link on this frame number
                pos++;
            } else {
                System.out.println(oldpos+" found");
                pos = oldpos;
            }
        }
        nwXc[pos] = link.points[0].getCenterX();
        nwYc[pos] = link.points[0].getCenterY();
        seXc[pos] = link.points[1].getCenterX();
        seYc[pos] = link.points[1].getCenterY();
        connected = true;
    }
    
    public void printLinks() {
        System.out.println(destVidURL);
        System.out.println(name);
        for(int i=0;i<=pos;i++) {
            System.out.println("nw: "+nwXc[i]+","+nwYc[i]);
            System.out.println("se: "+seXc[i]+","+seYc[i]);
            System.out.println("sf: "+srcFrameNo[i]);
            System.out.println("df: "+destFrameNo[i]);
        }
    }
    
}
