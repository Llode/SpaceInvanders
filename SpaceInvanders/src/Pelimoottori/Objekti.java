/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

import java.awt.Image;

/**
 * Kaikkien kentällä liikkuvien objektien yliluokka.
 *
 * @author Larppa
 */
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

    /**
     * Objekti katoaa kentältä.
     */
    public void die() {
        spriteNakyy = false;
    }

    /**
     * @return Kertoo, onko objekti näkyvissä.
     */
    public boolean isVisible() {
        return spriteNakyy;
    }

    /**
     * Asettaa objektin näkyväksi.
     *
     * @param spriteNakyy
     */
    protected void setVisible(boolean spriteNakyy) {
        this.spriteNakyy = spriteNakyy;
    }

    /**
     * Asettaa objektille kvuan.
     *
     * @param sprite Objektin kuva.
     */
    public void setImage(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * @return Noutaa objektin kuvan.
     */
    public Image getImage() {
        return sprite;
    }

    /**
     * Objektin paikka koordinaatistossa.
     *
     * @param x Leveyssuunnassa.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Objektin paikka koordinaatistossa.
     *
     * @param y Korkeussuunnassa.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Koordinaattigetteri
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Koordinaattigetteri
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Objekti tuhoutuu.
     *
     * @param kuolee tosi, jos objekti tuhoutuu.
     */
    public void setKuolee(boolean kuolee) {
        this.kuolee = kuolee;
    }

    /**
     * @return kertoo mikäli objekti tuhoutui..
     */
    public boolean Kuoleeko() {
        return this.kuolee;
    }
}
