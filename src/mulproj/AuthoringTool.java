package mulproj;


import java.awt.image.*;
import java.io.*;


public class AuthoringTool {
   
   private static createVideoGUI gui = new createVideoGUI();
   
   private static final int WIDTH = 352;
   private static final int HEIGHT = 288;
   private static final int FPS = 20;
   
   
   public static BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
   public static byte[] bytes;
   public static long NUMFRAMES_VID1;
  
   
   public static void main(String[] args) throws FileNotFoundException, IOException {

    String fileName;// = args[0];
    
//    fileName = "C:\\Users\\Hardik\\Documents\\vdo1.rgb";
    
    // Setup Video 1
    gui.setupVideo1(img);
    
  }
   
  public static void videoLoaded(String fileName) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        InputStream is = new FileInputStream(file);

        long len = file.length();
        bytes = new byte[(int)len];

        int offset = 0;
        int numRead = 0;

        long numFrames = file.length()/(3*WIDTH*HEIGHT);
        System.out.println(numFrames);
        NUMFRAMES_VID1 = numFrames;

        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
  }
  
  public static void showFrame(int curFrame) {
    //gui.setupVideo1(img);
    System.out.println("curFrame: "+ curFrame);
    int ind=0;
    for(int y = 0; y < HEIGHT; y++){
        for(int x = 0; x < WIDTH; x++){
            byte r = bytes[ind + 3*curFrame*HEIGHT*WIDTH];
            byte g = bytes[ind+HEIGHT*WIDTH + 3*curFrame*HEIGHT*WIDTH];
            byte b = bytes[ind+HEIGHT*WIDTH*2 + 3*curFrame*HEIGHT*WIDTH]; 

            int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
            // int pix = ((a << 24) + (r << 16) + (g << 8) + b);

            img.setRGB(x,y,pix);
            ind++;
        }
    }
    gui.repaint();
  }
  
  public static void playVideo1(String fileName) {
      System.out.println("FILENAME: "+fileName);
      gui.setupVideo1(img);
      try {
        File file = new File(fileName);
        InputStream is = new FileInputStream(file);

        long len = file.length();
        bytes = new byte[(int)len];

        int offset = 0;
        int numRead = 0;

        long numFrames = file.length()/(3*WIDTH*HEIGHT);
        System.out.println(numFrames);
        NUMFRAMES_VID1 = numFrames;

        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }	

        int ind = 0;
        int curFrame = 0;

        System.out.println(bytes.length);

        while(curFrame < numFrames) {
            System.out.println(curFrame);
            //setSliderPosition
            ind=0;
            for(int y = 0; y < HEIGHT; y++){
                for(int x = 0; x < WIDTH; x++){
                    byte a = 0;

                    byte r = bytes[ind + 3*curFrame*HEIGHT*WIDTH];
                    byte g = bytes[ind+HEIGHT*WIDTH + 3*curFrame*HEIGHT*WIDTH];
                    byte b = bytes[ind+HEIGHT*WIDTH*2 + 3*curFrame*HEIGHT*WIDTH]; 

                    int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
                    // int pix = ((a << 24) + (r << 16) + (g << 8) + b);

                    img.setRGB(x,y,pix);
                    ind++;
                }
            }
            gui.repaint();
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
