package PresentationLayer;

import DomainImplementatieLayer.Snelheid;
import DomainImplementatieLayer.Auto;
import InfrastructureLayer.Observer;
import InfrastructureLayer.Subject;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.*;

class DigitaleMeter extends javax.swing.JLabel implements Observer
{
    private Snelheid S;
    
    public DigitaleMeter (Snelheid s) { S=s; }
    
    public void update() { setText(String.valueOf(S.waarde())); }
}        

class AnalogeMeter extends JProgressBar implements Observer
{
    public static final int MAXSNELHEID = 300; // Maximaal 300 km/h mogelijk
    private Snelheid S;
    
    public AnalogeMeter (Snelheid s) { S=s; }
    
    public void update() { setValue((int)( (100.0/MAXSNELHEID)*(S.waarde() ))); }
}        

public class Applicatie extends javax.swing.JFrame {

    private DigitaleMeter  digitaleMeter;
    private AnalogeMeter   analogeMeter;
    private Auto A;
    
    public Applicatie() {
        initComponents();
        A = new Auto();
          
        Font font = new Font("SansSerif", Font.BOLD, 15);
        
        digitaleMeter = new DigitaleMeter(A.snelheid());
        digitaleMeter.setVisible(true);
        digitaleMeter.setSize(30,30);
        digitaleMeter.setBorder(new LineBorder(Color.black));
        digitaleMeter.setFont(font);
        digitaleMeter.setLocation(200, 40);
        digitaleMeter.setForeground(Color.blue);
        digitaleMeter.setBackground(Color.white);
        digitaleMeter.setOpaque(true);
        add(digitaleMeter);
        
        analogeMeter = new AnalogeMeter(A.snelheid());
        analogeMeter.setVisible(true);
        analogeMeter.setSize(200, 20);
        analogeMeter.setBorder(new LineBorder(Color.black));
        analogeMeter.setLocation(200, 150);
        analogeMeter.setForeground(Color.blue);
        analogeMeter.setBackground(Color.white);
        add(analogeMeter);

        A.snelheid().insertObserver(digitaleMeter);
        A.snelheid().insertObserver(analogeMeter);
        
        jLabel4.setText(String.valueOf(AnalogeMeter.MAXSNELHEID) + " km/h");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        VraagSnelheidButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(300, 300));

        jLabel1.setText("Digitale waarde:");

        jLabel2.setText("Analoge waarde:");

        jLabel3.setText("0 km/h");

        jLabel4.setText("jLabel4");

        jLabel5.setText("km/h");

        VraagSnelheidButton.setText("Vraag Snelheid");
        VraagSnelheidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VraagSnelheidButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(137, 137, 137)
                .addComponent(jLabel4)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VraagSnelheidButton)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel5)))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(90, 90, 90)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(VraagSnelheidButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VraagSnelheidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VraagSnelheidButtonActionPerformed
        //A.setGewensteSnelheid();
        A.vraagWaarde();
        A.snelheid().notifyObservers();
    }//GEN-LAST:event_VraagSnelheidButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Applicatie().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VraagSnelheidButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
