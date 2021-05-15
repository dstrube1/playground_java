package com.dstrube.ksu_csis;

/*
From ~/java:

javac -d bin com/dstrube/ksu-csis/HelloApplet.java
java -cp bin com.dstrube.ksu_csis.HelloApplet

*/

// Hello World --- Applet version
// Created: Fri Jan 29 19:58:47 1999
// Modified: 23:03, 07/31/01

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("deprecation")
//@SuppressWarnings({"deprecation", "deprecated", "unchecked"})
public class HelloApplet extends Applet
{

    public static void main( String args[] )
    {
    	//Deprecated
    }
    public void init()
    {
        // Change the background
        setBackground(Color.lightGray);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp1 = new GradientPaint(
                0,200,Color.blue, 450,200,Color.green);
        g2d.setPaint(gp1);
        g.fillOval(0,0,500,400);
        Font f = new Font("sansserif", Font.BOLD, 72);
        g.setFont(f);
        GradientPaint gp2 = new GradientPaint(
                0,0,Color.red, 50,50, Color.white, true);
        g2d.setPaint(gp2);
        g.drawString("Hello Applet!", 20,200);
    }


}
