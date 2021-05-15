package com.dstrube.ksu_csis;

/*
From ~/java:

javac -d bin com/dstrube/ksu-csis/HelloWorld1.java
java -cp bin com.dstrube.ksu_csis.HelloWorld1

*/

// Hello World version --- Swing
// Created: Fri Jan 29 19:58:47 1999
// Modified: 13:14, 12/30/00

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;


public class HelloWorld1 extends JFrame
{

    public static void main( String args[] )
    {
        HelloWorld1 c = new HelloWorld1();
    }
    
    private HelloWorld1()
    {
        // Create the application window (aka Frame)
        super("HelloWorld Example II");
        // how big
        setSize(450,400);
        // Change the background
        setBackground(Color.lightGray);
        // Let 'em see it
        setVisible(true);
        // This is needed to make the close window button
        //   on the frame work.
        // addWindowListener(new LocalWindowAdapter());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp1 = new GradientPaint(
                0,200,Color.blue, 450,200,Color.green);
        g2d.setPaint(gp1);
        g.fillOval(0,0,450,400);
        Font f = new Font("sansserif", Font.BOLD, 72);
        g.setFont(f);
        GradientPaint gp2 = new GradientPaint(
                0,0,Color.red, 50,50, Color.white, true);
        g2d.setPaint(gp2);
        g.drawString("Hello World!", 20,200);
    }


}
