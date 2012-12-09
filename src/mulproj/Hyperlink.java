/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mulproj;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Hardik
 */
public class Hyperlink {
    ResizeRectangle link;
    String name;
    int pos;
    ArrayList<Double> nwXc, nwYc, seXc, seYc;
    ArrayList<Integer> srcFrameNo;
    Integer destFrameNo;
    String destVidURL;
    Boolean connected;

    public Hyperlink() {
        this.nwYc = new ArrayList<Double>();
        this.nwXc = new ArrayList<Double>();
        this.seYc = new ArrayList<Double>();
        this.seXc = new ArrayList<Double>();
        this.connected = false;
        this.link = new ResizeRectangle(50,50,150,100);
        this.srcFrameNo = new ArrayList<Integer>();
        this.pos = -1;
    }
    
    public void registerCoords(int frame) {
        Boolean newFrame = false;
        if(pos == -1) {
            newFrame = true;
            pos = 0; // first link ever
        } else {
            // we need to find out if there is an existing link on this frame
            int oldpos = srcFrameNo.indexOf(frame);
            if(oldpos == -1) {
                newFrame = true;
                System.out.println(oldpos+" not found");
                // no previous link on this frame number
                pos++;
            } else {
                newFrame = false;
                System.out.println(oldpos+" found");
                pos = oldpos;
            }
        }
        if(newFrame) {
            nwXc.add(link.points[0].getCenterX());
            nwYc.add(link.points[0].getCenterY());
            seXc.add(link.points[1].getCenterX());
            seYc.add(link.points[1].getCenterY());
            // also add dummies to src and dest
            srcFrameNo.add(frame);
        } else {
            nwXc.set(pos,link.points[0].getCenterX());
            nwYc.set(pos,link.points[0].getCenterY());
            seXc.set(pos,link.points[1].getCenterX());
            seYc.set(pos,link.points[1].getCenterY());
        }
        connected = true;
    }
    
    public void printLinks() {
        System.out.println(destVidURL);
        System.out.println(name);
        System.out.println(destFrameNo);
        for(int i=0;i<=pos;i++) {
            System.out.println("nw: "+nwXc.get(i)+","+nwYc.get(i));
            System.out.println("se: "+seXc.get(i)+","+seYc.get(i));
            System.out.println("sf: "+srcFrameNo.get(i));
        }
    }
    
}
