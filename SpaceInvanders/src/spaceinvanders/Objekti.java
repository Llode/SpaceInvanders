/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import java.awt.Image;

public class Objekti {

    private boolean spriteNakyy;
    private Image sprite;
    protected int x;
    protected int y;
    protected boolean kuolee;
    public int liike;

    public Objekti() {
        spriteNakyy = true;
    }

    public void die() {
        spriteNakyy = false;
    }

    public boolean isVisible() {
        return spriteNakyy;
    }

    protected void setVisible(boolean spriteNakyy) {
        this.spriteNakyy = spriteNakyy;
    }
/**
     * Asettaa objektille kvuan.
     * @param sprite Objektin kuva.
     */
    public void setImage(Image sprite) {
        this.sprite = sprite;
    }

    public Image getImage() {
        return sprite;
    }
/**
     * Objektin paikka koordinaatistossa.
     * @param x Leveyssuunnassa.
     */
    public void setX(int x) {
        this.x = x;
    }
/**
     * Objektin paikka koordinaatistossa.
     * @param y Korkeussuunnassa.
     */
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
/**
     * 
     * @param kuolee 
     */
    public void setKuolee(boolean kuolee) {
        this.kuolee = kuolee;
    }

    public boolean Kuolee() {
        return this.kuolee;
    }
}
