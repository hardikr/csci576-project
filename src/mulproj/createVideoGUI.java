/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mulproj;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
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
    public JLabel vid1FrameNum;
    public JLabel vid2FrameNum;
    public JMenuBar menuBar;
    public JMenu fileMenu;
    public JMenuItem openVideo1MenuItem;
    public JMenuItem openVideo2MenuItem;
    public JFileChooser jFileChooser;
    public JButton resetBtn;
    public JSlider jSlider1;
    public JSlider jSlider2;
    public String video1URL;
    public String video2URL;
    public JLabel hyperlinkLabel;
    public JComboBox hyperLinkDropdown;
    public JButton addHyperlinkBtn;
    
    public createVideoGUI() {
        frame = new JFrame();
        video1Label = new JLabel();
        video2Label = new JLabel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        openVideo1MenuItem = new JMenuItem();
        openVideo2MenuItem = new JMenuItem();
        jFileChooser = new JFileChooser();
        resetBtn = new JButton();
        jSlider1 = new JSlider();
        jSlider2 = new JSlider();
        vid1FrameNum = new JLabel();
        vid2FrameNum = new JLabel();
        hyperlinkLabel = new JLabel();
        hyperLinkDropdown = new JComboBox();
        addHyperlinkBtn = new JButton();
        
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
    }
    
    private void setLayout() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(vid1FrameNum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vid2FrameNum)
                .addGap(307, 307, 307))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(video1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(video2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hyperLinkDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hyperlinkLabel)
                            .addComponent(addHyperlinkBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(resetBtn)))
                .addContainerGap(55, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vid1FrameNum)
                            .addComponent(vid2FrameNum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(addHyperlinkBtn)
                        .addGap(32, 32, 32)
                        .addComponent(hyperlinkLabel)
                        .addGap(18, 18, 18)
                        .addComponent(hyperLinkDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(resetBtn)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        frame.pack();
    }
    
    private void setupButtons() {
        resetBtn.setText("Reset");
        resetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetBtnMouseClicked(evt);
            }
        });
        
        addHyperlinkBtn.setText("Create New Link");
        addHyperlinkBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addHyperlinkBtnMouseClicked(evt);
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
        hyperLinkDropdown.setModel(new javax.swing.DefaultComboBoxModel());
    }
    
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider source = (JSlider)evt.getSource();
        System.out.println("Slider 1 changed!!");
        if (!source.getValueIsAdjusting()) {
            //System.out.println(source.getValue());
            vid1FrameNum.setText("Frame: "+source.getValue());
            AuthoringTool.showFrameSrc(source.getValue());
        }
    }
    
    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider source = (JSlider)evt.getSource();
        System.out.println("Slider 2 changed!!");
        if (!source.getValueIsAdjusting()) {
            //System.out.println(source.getValue());
            vid2FrameNum.setText("Frame: "+source.getValue());
            AuthoringTool.showFrameDest(source.getValue());
        }
    }
    
    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        jSlider1.setValue(0);
    }
   
    private void addHyperlinkBtnMouseClicked(java.awt.event.MouseEvent evt) {
        
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

   
    
}
