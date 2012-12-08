/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mulproj;

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
    int[] srcFrameNo;
    int[] destFrameNo;
    String destVidURL;

    public Hyperlink() {
        this.link = new ResizeRectangle();
        this.nwXc = new double[20];
        this.nwYc = new double[20];
        this.seXc = new double[20];
        this.seYc = new double[20];
        this.srcFrameNo = new int[20];
        this.destFrameNo = new int[20];
        this.pos = -1;
    }
    
    public void registerCoords() {
        pos++;
        nwXc[pos] = link.points[0].getCenterX();
        nwYc[pos] = link.points[0].getCenterY();
        seXc[pos] = link.points[1].getCenterX();
        seYc[pos] = link.points[1].getCenterY();
    }
    
    public void printLinks() {
        System.out.println(destVidURL);
        for(int i=0;i<=pos;i++) {
            System.out.println("nw: "+nwXc[i]+","+nwYc[i]);
            System.out.println("se: "+seXc[i]+","+seYc[i]);
            System.out.println("sf: "+srcFrameNo[i]);
            System.out.println("df: "+destFrameNo[i]);
        }
    }
    
}
