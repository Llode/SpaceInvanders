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
    protected int liike;

    public Objekti() {
        spriteNakyy = true;
    }

    public void die() {
        spriteNakyy = false;
    }

    public boolean nakyvissa() {
        return spriteNakyy;
    }

    protected void setVisible(boolean spriteNakyy) {
        this.spriteNakyy = spriteNakyy;
    }

    public void setImage(Image sprite) {
        this.sprite = sprite;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setKuolee(boolean kuolee) {
        this.kuolee = kuolee;
    }

    public boolean kuolee() {
        return this.kuolee;
    }
}
