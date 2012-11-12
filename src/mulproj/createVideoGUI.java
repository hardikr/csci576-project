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
    
    public JFrame frame;
    public JLabel video1Label;
    public JMenuBar menuBar;
    public JMenu fileMenu;
    public JMenuItem openMenuItem;
    public JFileChooser jFileChooser;
    public JButton resetBtn;
    public JSlider jSlider1;
    public String video1URL;
    
    public createVideoGUI() {
        frame = new JFrame();
        video1Label = new JLabel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        openMenuItem = new JMenuItem();
        jFileChooser = new JFileChooser();
        resetBtn = new JButton();
        jSlider1 = new JSlider();
        
        initialGUISetup();
        setupMenu();
        setupButtons();
        setLayout();
        setupSlider1();
    }
    
    
    private void initialGUISetup() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
    
    private void setLayout() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(video1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(resetBtn)))
                .addContainerGap(468, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(video1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(resetBtn)
                .addContainerGap(102, Short.MAX_VALUE))
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
    
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider source = (JSlider)evt.getSource();
        if (!source.getValueIsAdjusting()) {
            System.out.println(source.getValue());
            AuthoringTool.showFrame(source.getValue());
        }
    }
    
    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        jSlider1.setValue(0);
    }
   
   
    
    private void setupMenu() {
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openMenuItemActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(createVideoGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(createVideoGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }
    
    private void setSliderRange(int frames) {
        jSlider1.setMinimum(0);
        jSlider1.setMaximum(frames);
        jSlider1.setValue(0);
    }
    
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException {                                             
        int returnVal = jFileChooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            //java.io.File file = jFileChooser.getSelectedFile();
            video1URL = jFileChooser.getSelectedFile().toString(); //file.toString();
            AuthoringTool.videoLoaded(video1URL);
            setSliderRange((int)AuthoringTool.NUMFRAMES_VID1-1);
        }
    }                                            
    
    
    public void repaint() {
        frame.repaint();
        video1Label.repaint();    
    }
    
    public void setupVideo1(BufferedImage img) {
        video1Label.setIcon(new ImageIcon(img));

    }

   
    
}
