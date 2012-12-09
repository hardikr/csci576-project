/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mulproj;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hardik
 */
public class createVideoGUI {
    /* GLOBAL CONSTANTS */
    public static enum vidType {
        SOURCE, DEST
    };
    
    /* GLOBAL VARIABLES */
    public JFrame frame;
    public JLabel video1Label;
    public JLabel video2Label;
    public JLabel vid1FrameNumLabel;
    public JLabel vid2FrameNumLabel;
    public JMenuBar menuBar;
    public JMenu fileMenu;
    public JMenuItem openVideo1MenuItem;
    public JMenuItem openVideo2MenuItem;
    public JFileChooser jFileChooser;
    public JButton connectVidBtn;
    public JButton saveFileBtn;
    public JLabel statusLabel;
    public JSlider jSlider1;
    public JSlider jSlider2;
    public String video1URL;
    public String video2URL;
    public JLabel hyperlinkLabel;
    public JComboBox hyperlinkDropdown;
    public JButton addHyperlinkBtn;
    public JTextField hyperlinkName;
    public Hyperlink[] hyperlinkArr;
    public int numHyperlinks = 0;
    public int curHyperlinkNum = -1;
    public Boolean video1Loaded = false;
    public Boolean video2Loaded = false;
    public int vid1FrameNum;
    public int vid2FrameNum;
    
    public createVideoGUI() {
        frame = new JFrame();
        video1Label = new JLabel();
        video2Label = new JLabel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        openVideo1MenuItem = new JMenuItem();
        openVideo2MenuItem = new JMenuItem();
        jFileChooser = new JFileChooser();
        connectVidBtn = new JButton();
        saveFileBtn = new JButton();
        statusLabel = new JLabel();
        jSlider1 = new JSlider();
        jSlider2 = new JSlider();
        vid1FrameNumLabel = new JLabel();
        vid2FrameNumLabel = new JLabel();
        hyperlinkLabel = new JLabel();
        hyperlinkDropdown = new JComboBox();
        addHyperlinkBtn = new JButton();
        hyperlinkName = new JTextField();
        hyperlinkArr = new Hyperlink[20];
        
        initialGUISetup();
        setupMenu();
        setupButtons();
        setLayout();
        setupSlider1();
        setupSlider2();
        setupHyperlinkDropdown();
    }
    
    
    private void initialGUISetup() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        hyperlinkLabel.setText("Hyperlink List");
        hyperlinkName.setText("hyperlink_name");
        statusLabel.setText("Status: Ready");
    }
    
    private void setLayout() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(vid1FrameNumLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vid2FrameNumLabel)
                .addGap(307, 307, 307))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(video1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(video2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hyperlinkDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hyperlinkLabel)
                            .addComponent(addHyperlinkBtn)
                            .addComponent(hyperlinkName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(connectVidBtn)
                        .addGap(141, 141, 141)
                        .addComponent(saveFileBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(video1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(video2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vid2FrameNumLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(vid1FrameNumLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(addHyperlinkBtn)
                        .addGap(32, 32, 32)
                        .addComponent(hyperlinkLabel)
                        .addGap(18, 18, 18)
                        .addComponent(hyperlinkDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hyperlinkName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(connectVidBtn)
                .addComponent(saveFileBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(statusLabel)
                .addContainerGap())
        );
        frame.pack();
    }
    
    private void setupButtons() {
        connectVidBtn.setText("Connect Videos");
        connectVidBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                connectVidBtnMouseClicked(evt);
            }
        });
        
        addHyperlinkBtn.setText("Create New Link");
        addHyperlinkBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addHyperlinkBtnMouseClicked(evt);
            }
        });
        
        saveFileBtn.setText("Save File");
        saveFileBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveFileBtnMouseClicked(evt);
            }
        });
    }
    
    private void setupSlider1() {
        jSlider1.setValue(0);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
    }
    
    private void setupSlider2() {
        jSlider2.setValue(0);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });
    }
    
    private void setupHyperlinkDropdown() {
        hyperlinkDropdown.setModel(new javax.swing.DefaultComboBoxModel());
        hyperlinkDropdown.addItem("None");
        hyperlinkDropdown.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hlDropdownActionPerformed(evt);
            }
        });
    }
    
    private void hlDropdownActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JComboBox cb = (JComboBox)evt.getSource();
        String newSelection;
        newSelection = String.valueOf(cb.getSelectedItem());
        System.out.println("New selection: "+newSelection);
        if(newSelection.equals("None")) {
            System.out.println("Calling remove from dropdown:none");
            removeHyperlink(-1);
            hyperlinkName.setText(" ");
        } else {
            // user has selected a previously created link
            System.out.println("Calling remove from dropdown:else");
            removeHyperlink(Integer.valueOf(newSelection));
            drawLink(Integer.valueOf(newSelection));
            hyperlinkName.setText(hyperlinkArr[curHyperlinkNum].name);
        }
    }
    
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider source = (JSlider)evt.getSource();
//        System.out.println("Slider 1 changed!!");
        if (!source.getValueIsAdjusting()) {
            //System.out.println(source.getValue());
            vid1FrameNumLabel.setText("Frame: "+source.getValue());
            vid1FrameNum = source.getValue();
            AuthoringTool.showFrameSrc(source.getValue());
        }
    }
    
    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider source = (JSlider)evt.getSource();
//        System.out.println("Slider 2 changed!!");
        if (!source.getValueIsAdjusting()) {
            //System.out.println(source.getValue());
            vid2FrameNumLabel.setText("Frame: "+source.getValue());
            vid2FrameNum = source.getValue();
            AuthoringTool.showFrameDest(source.getValue());
        }
    }
    
    private void connectVidBtnMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        if(!(video1Loaded && video2Loaded)) {
            statusLabel.setText("Please load both videos before connecting");
        }
        else if(numHyperlinks<=0) {
            statusLabel.setText("Please create a hyperlink first!!");
        }
        else {
            System.out.println("in connectvidclick, cur: "+curHyperlinkNum);
            hyperlinkArr[curHyperlinkNum].registerCoords(vid1FrameNum);
            hyperlinkArr[curHyperlinkNum].name = hyperlinkName.getText();
            hyperlinkArr[curHyperlinkNum].destVidURL = video2URL;
            (hyperlinkArr[curHyperlinkNum].srcFrameNo).set(hyperlinkArr[curHyperlinkNum].pos,vid1FrameNum);
            hyperlinkArr[curHyperlinkNum].destFrameNo = vid2FrameNum;
            hyperlinkArr[curHyperlinkNum].printLinks();
            
            // re-enable create-link button ??
            addHyperlinkBtn.setEnabled(true);
            
        }
    }
   
    private void addHyperlinkBtnMouseClicked(java.awt.event.MouseEvent evt) {
        if(addHyperlinkBtn.isEnabled()) {
            // check if already hyperlink is drawn ?
            if(curHyperlinkNum >= 0) {
                System.out.println("Calling remove from add-hl");
                removeHyperlink(-1);
            }
            
            // create the hyperlink
            curHyperlinkNum = numHyperlinks;
            System.out.println("in addhyperlinkclick, cur: "+curHyperlinkNum);
            hyperlinkArr[curHyperlinkNum] = new Hyperlink();
            hyperlinkArr[curHyperlinkNum].link = new ResizeRectangle(50,50,150,100);
            hyperlinkArr[curHyperlinkNum].link.setBounds(0, 0, 352, 288);
            hyperlinkArr[curHyperlinkNum].link.setOpaque(false);

            // show it
            video1Label.add(hyperlinkArr[curHyperlinkNum].link);
            video1Label.repaint();

            // gui updates
            hyperlinkDropdown.addItem(curHyperlinkNum);
            System.out.println("Choosing index : "+(curHyperlinkNum));
            hyperlinkDropdown.setSelectedItem(curHyperlinkNum);
            hyperlinkName.setText("hyperlink-"+curHyperlinkNum);
            addHyperlinkBtn.setEnabled(false);

            numHyperlinks++;
        }
    }
    
    private void removeHyperlink(int selection) {
        if(selection == -1) {
            video1Label.remove(hyperlinkArr[curHyperlinkNum].link);
            video1Label.repaint();
            hyperlinkDropdown.setSelectedItem("None");
        } else {
            if(curHyperlinkNum>=0) {
                // see if this is a switch rom link x to link y
                video1Label.remove(hyperlinkArr[curHyperlinkNum].link);
                video1Label.repaint();
            }
            hyperlinkDropdown.setSelectedItem(selection);
        }
        curHyperlinkNum = -1; // no hyperlink shown at this moment.
    }
    
    private void drawLink(int num) {
        // drawing link number num
        System.out.println("drawing link number "+num);
        
        // see if link is already drawn
        if(curHyperlinkNum >= 0) {
            System.out.println("Calling remove from add-hl");
            removeHyperlink(-1);
        }
        // set curHyperlinkNum
        curHyperlinkNum = num;
        
        // see if this link was connected
        if(hyperlinkArr[curHyperlinkNum].connected) {
            // draw the link
            System.out.println("Drawing connected: "+curHyperlinkNum);
            video1Label.add(hyperlinkArr[curHyperlinkNum].link);
            video1Label.repaint();
        } else {
            // draw generic link
            System.out.println("Drawing generic: "+curHyperlinkNum);
            video1Label.add(hyperlinkArr[curHyperlinkNum].link);
            video1Label.repaint();
        }
    }
    
    private void saveFileBtnMouseClicked(java.awt.event.MouseEvent evt) {
        try{
            // Create file 
            statusLabel.setText("Saving File....");
            FileWriter fstream = new FileWriter("C:\\Users\\Hardik\\Desktop\\out.txt");
            BufferedWriter out = new BufferedWriter(fstream,32768);
            
            System.out.println("numlinks: "+numHyperlinks);
            String[] origFile;
            String fileName;
            // iterate hyperlinkArr
            for(int i=0;i<numHyperlinks;i++) {
                origFile = hyperlinkArr[i].destVidURL.split("\\\\");
                fileName = origFile[origFile.length-1];
                out.write(i+" "+hyperlinkArr[i].name+" "+hyperlinkArr[i].srcFrameNo.size());
                out.newLine();
                out.write(fileName+" "+hyperlinkArr[i].destFrameNo);
                out.newLine();
                for(int j=0;j<hyperlinkArr[i].srcFrameNo.size();j++) {
                    out.write((hyperlinkArr[i].srcFrameNo).get(j)+" ");
                    out.write(hyperlinkArr[i].nwXc.get(j).intValue()+" "+hyperlinkArr[i].nwYc.get(j).intValue()+" ");
                    out.write(hyperlinkArr[i].seXc.get(j).intValue()+" "+hyperlinkArr[i].seYc.get(j).intValue());
                    out.newLine();
                }
            }
//            for(int i=0;i<numHyperlinks;i++) {
//                out.write(i+" "+hyperlinkArr[i].name+" "+hyperlinkArr[i].srcFrameNo.size()+"\n");
//                out.write(hyperlinkArr[i].destVidURL+" "+hyperlinkArr[i].destFrameNo+"\n");
//                for(int j=0;j<hyperlinkArr[i].srcFrameNo.size();j++) {
//                    out.write(hyperlinkArr[i].srcFrameNo.get(i)+" ");
//                    out.write(hyperlinkArr[i].nwXc.get(i)+" "+hyperlinkArr[i].nwYc.get(i)+" ");
//                    out.write(hyperlinkArr[i].seXc.get(i)+" "+hyperlinkArr[i].seYc.get(i)+"\n");
//                }
//            }
            out.close();
            statusLabel.setText("File Saved!");
        }
        catch (Exception e){
            System.err.println("File Write Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
   
    
    private void setupMenu() {
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openVideo1MenuItem.setMnemonic('s');
        openVideo1MenuItem.setText("Open Source Video");
        openVideo1MenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openVideo1ActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(createVideoGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(createVideoGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        openVideo2MenuItem.setMnemonic('d');
        openVideo2MenuItem.setText("Open Destination Video");
        openVideo2MenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openVideo2ActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(createVideoGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(createVideoGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        fileMenu.add(openVideo1MenuItem);
        fileMenu.add(openVideo2MenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }
    
    private void setSlider1Range(int frames) {
        jSlider1.setMinimum(0);
        jSlider1.setMaximum(frames);
        jSlider1.setValue(0);
    }
    
    
    private void setSlider2Range(int frames) {
        jSlider2.setMinimum(0);
        jSlider2.setMaximum(frames);
        jSlider2.setValue(0);
    }
    
    private void openVideo1ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException {                                             
        int returnVal = jFileChooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            //java.io.File file = jFileChooser.getSelectedFile();
            video1URL = jFileChooser.getSelectedFile().toString(); //file.toString();
            AuthoringTool.videoLoaded(video1URL,vidType.SOURCE);
            AuthoringTool.showFrameSrc(0);
            setSlider1Range((int)AuthoringTool.NUMFRAMES_VID1-1);
        }
    }                                            
    
     private void openVideo2ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException {                                             
        int returnVal = jFileChooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            //java.io.File file = jFileChooser.getSelectedFile();
            video2URL = jFileChooser.getSelectedFile().toString(); //file.toString();
            AuthoringTool.videoLoaded(video2URL,vidType.DEST);
            AuthoringTool.showFrameDest(0);
            setSlider2Range((int)AuthoringTool.NUMFRAMES_VID1-1);
        }
    }
    
    public void repaint(vidType type) {
        frame.repaint();
        switch(type) {
            case SOURCE:    video1Label.repaint();
                            break;
            case DEST:      video2Label.repaint();
                            break;
        }    
    }
    
    public void setupVideo1(BufferedImage img) {
        video1Label.setIcon(new ImageIcon(img));
    }
    
    public void setupVideo2(BufferedImage img) {
        video2Label.setIcon(new ImageIcon(img));
    }
    
    public void setVideo1Loaded() {
        video1Loaded = true;
        statusLabel.setText("Source Loaded! : File: "+video1URL);
    }
    
    public void setVideo2Loaded() {
        video2Loaded = true;
        statusLabel.setText("Destination Loaded! : File: "+video2URL);
    }

   
    
}