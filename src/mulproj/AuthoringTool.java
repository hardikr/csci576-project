package mulproj;


import java.awt.image.*;
import java.io.*;


public class AuthoringTool {
   
   private static createVideoGUI gui = new createVideoGUI();
   
   private static final int WIDTH = 352;
   private static final int HEIGHT = 288;
   private static final int FPS = 20;
   
   
   public static BufferedImage img1 = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
   public static BufferedImage img2 = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
   public static byte[] bytesSrc;
   public static byte[] bytesDest;
   public static long NUMFRAMES_VID1;
  
   
   public static void main(String[] args) throws FileNotFoundException, IOException {

    String fileName;// = args[0];
    
//    fileName = "C:\\Users\\Hardik\\Documents\\vdo1.rgb";
    
    // Setup Video 1
    gui.setupVideo1(img1);
    gui.setupVideo2(img2);
  }
   
  public static void videoLoaded(String fileName, createVideoGUI.vidType type) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        InputStream is = new FileInputStream(file);

        long len = file.length();
      
        int offset = 0;
        int numRead = 0;

        long numFrames = file.length()/(3*WIDTH*HEIGHT);
        System.out.println(numFrames);
        NUMFRAMES_VID1 = numFrames;
        
        switch(type) {
            case SOURCE: bytesSrc = null;
                         bytesSrc = new byte[(int)len];
                         while (offset < bytesSrc.length && (numRead=is.read(bytesSrc, offset, bytesSrc.length-offset)) >= 0) {
                            offset += numRead;
                         }
                         gui.setVideo1Loaded();
                         break;
            case DEST:   bytesDest = null;
                         bytesDest = new byte[(int)len];
                         while (offset < bytesDest.length && (numRead=is.read(bytesDest, offset, bytesDest.length-offset)) >= 0) {
                            offset += numRead;
                         }
                         gui.setVideo2Loaded();
                         break;
            default:     break;
        }
  }
  
  public static void showFrameSrc(int curFrame) {
    //gui.setupVideo1(img1);
    System.out.println("curFrame: "+ curFrame);
    int ind=0;
    for(int y = 0; y < HEIGHT; y++){
        for(int x = 0; x < WIDTH; x++){
            byte r = bytesSrc[ind + 3*curFrame*HEIGHT*WIDTH];
            byte g = bytesSrc[ind+HEIGHT*WIDTH + 3*curFrame*HEIGHT*WIDTH];
            byte b = bytesSrc[ind+HEIGHT*WIDTH*2 + 3*curFrame*HEIGHT*WIDTH]; 

            int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
            // int pix = ((a << 24) + (r << 16) + (g << 8) + b);

            img1.setRGB(x,y,pix);
            ind++;
        }
    }
    gui.repaint(createVideoGUI.vidType.SOURCE);
  }
  
  public static void showFrameDest(int curFrame) {
    //gui.setupVideo1(img1);
    System.out.println("curFrame: "+ curFrame);
    int ind=0;
    for(int y = 0; y < HEIGHT; y++){
        for(int x = 0; x < WIDTH; x++){
            byte r = bytesDest[ind + 3*curFrame*HEIGHT*WIDTH];
            byte g = bytesDest[ind+HEIGHT*WIDTH + 3*curFrame*HEIGHT*WIDTH];
            byte b = bytesDest[ind+HEIGHT*WIDTH*2 + 3*curFrame*HEIGHT*WIDTH]; 

            int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
            // int pix = ((a << 24) + (r << 16) + (g << 8) + b);

            img2.setRGB(x,y,pix);
            ind++;
        }
    }
    gui.repaint(createVideoGUI.vidType.DEST);
  }
  
  public static void playVideo1(String fileName) {
      System.out.println("FILENAME: "+fileName);
      gui.setupVideo1(img1);
      try {
        File file = new File(fileName);
        InputStream is = new FileInputStream(file);

        long len = file.length();
        bytesSrc = new byte[(int)len];

        int offset = 0;
        int numRead = 0;

        long numFrames = file.length()/(3*WIDTH*HEIGHT);
        System.out.println(numFrames);
        NUMFRAMES_VID1 = numFrames;

        while (offset < bytesSrc.length && (numRead=is.read(bytesSrc, offset, bytesSrc.length-offset)) >= 0) {
            offset += numRead;
        }	

        int ind = 0;
        int curFrame = 0;

        System.out.println(bytesSrc.length);

        while(curFrame < numFrames) {
            System.out.println(curFrame);
            //setSliderPosition
            ind=0;
            for(int y = 0; y < HEIGHT; y++){
                for(int x = 0; x < WIDTH; x++){
                    byte a = 0;

                    byte r = bytesSrc[ind + 3*curFrame*HEIGHT*WIDTH];
                    byte g = bytesSrc[ind+HEIGHT*WIDTH + 3*curFrame*HEIGHT*WIDTH];
                    byte b = bytesSrc[ind+HEIGHT*WIDTH*2 + 3*curFrame*HEIGHT*WIDTH]; 

                    int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
                    // int pix = ((a << 24) + (r << 16) + (g << 8) + b);

                    img1.setRGB(x,y,pix);
                    ind++;
                }
            }
            gui.repaint(createVideoGUI.vidType.SOURCE);
//            curFrame = (curFrame==numFrames-1) ? 0 : curFrame+1; // loop
            curFrame++;
            //Thread.sleep(1000/FPS);
        }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } //catch (InterruptedException e) {
      //e.printStackTrace();
    //}
  }
   
}
