/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screensaver;

import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hal extends Thread {

    private int kepSzelesseg, kepMagassag;
    private double kepX, kepY;
    private Image kep;
    private boolean fut;
    private double XLepeskoz, YLepeskoz;
    private int akvariumSzelesseg, akvariumMagassag;
    private Vezerlo vezerlo;
    private long ido;

    public Hal(Image kep, int kepSzelesseg, int kepMagassag) {
        this.kep = kep;
        this.kepSzelesseg = kepSzelesseg;
        this.kepMagassag = kepMagassag;
    }

    public void beallit(double kepX, double kepY, boolean fut, double XLepeskoz, 
            double YLepeskoz, int akvariumSzelesseg, int akvariumMagassag, 
            Vezerlo vezerlo, long ido) {
        this.kepX = kepX;
        this.kepY = kepY;
        this.fut = fut;
        this.XLepeskoz = XLepeskoz;
        this.YLepeskoz = YLepeskoz;
        this.akvariumSzelesseg = akvariumSzelesseg;
        this.akvariumMagassag = akvariumMagassag;
        this.vezerlo = vezerlo;
        this.ido = ido;
    }
    
    public void rajzol(Graphics g) {
        if (XLepeskoz < 0) {
            g.drawImage(kep, (int)kepX, (int)kepY, kepSzelesseg, kepMagassag, null);
        } else {
            g.drawImage(kep, (int)kepX+kepSzelesseg, (int)kepY, -kepSzelesseg, kepMagassag, null);
        }
    }

    @Override
    public void run() {
        while (fut) {
            mozdul();
            frissit();
            keseltet();
        }
    }

    private void mozdul() {
        kepX += XLepeskoz;
        kepY += YLepeskoz;
        if (kepX + kepSzelesseg >= akvariumSzelesseg || kepX <= 0) {
            XLepeskoz = -XLepeskoz;
        }
        if (kepY + kepMagassag >= akvariumMagassag || kepY <= 0) {
            YLepeskoz = -YLepeskoz;
        }
    }

    private void frissit() {
        vezerlo.frissit();
    }

    private void keseltet() {
        try {
            Thread.sleep(ido);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getKep() {
        return kep;
    }

    public void setKep(Image kep) {
        this.kep = kep;
    }

    public int getKepSzelesseg() {
        return kepSzelesseg;
    }

    public void setKepSzelesseg(int kepSzelesseg) {
        this.kepSzelesseg = kepSzelesseg;
    }

    public int getKepMagassag() {
        return kepMagassag;
    }

    public void setKepMagassag(int kepMagassag) {
        this.kepMagassag = kepMagassag;
    }

}
