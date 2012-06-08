/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Pelimoottori.Asetukset;
import Pelimoottori.Pelimoottori;
import Kayttoliittymat.TAdapter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Larppa
 */
public class CanvasTesti extends JFrame implements Asetukset{
    private GameCanvas canvas;
    private Pelimoottori moottori;
    

    
   public GameMain(){
   moottori.GameInit();
   canvas = new GameCanvas();
   canvas.setPreferredSize(new Dimension(RuudunLeveys, RuudunKorkeus));
   this.setContentPane(canvas);
   
   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   this.pack();
   this.setTitle("ASD");
   this.setVisible(true);
    }
}
class GameCanvas extends JPanel implements Asetukset, KeyListener {
        public GameCanvas(){
        setFocusable(true);
        requestFocus();
        addKeyListener(new TAdapter());
    }
    @Override
        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            super.paintComponent(g2d);
            setBackground(Color.BLACK);
            
            gameDraw(g2d);
        }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void gameDraw(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}