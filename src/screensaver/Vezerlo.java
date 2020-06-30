/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package screensaver;

import tools.MathTools;
import tools.ImageTools;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 *
 * @author hallgato
 */
public class Vezerlo {
    private static final int KEP_SZAM = 12;
    private static final long ALSO_IDO = 20;
    private static final long FELSO_IDO = 30;
    private static final int ALSO_KEPMERET = 50;
    private static final int FELSO_KEPMERET = 70;
    
    private RajzPanel rajzPanel;
    private final List<Image> kepLista = new ArrayList<>();
    private final List<Hal> halak = new CopyOnWriteArrayList<>();

    public Vezerlo() {
        kepFeltoltes();
    }

    public void setRajzPanel(RajzPanel rajzPanel) {
        this.rajzPanel = rajzPanel;
    }

    public void vizbeRak(Hal hal) {
        int akvariumSzelesseg = rajzPanel.getWidth(), 
            akvariumMagassag = rajzPanel.getHeight(),
            vizMagassag = (int)(akvariumMagassag*0.2);
        long ido = MathTools.random(ALSO_IDO, FELSO_IDO);
        double kx = MathTools.random(akvariumSzelesseg - hal.getKepSzelesseg());
        double ky = MathTools.random(akvariumMagassag - hal.getKepMagassag(), 
                vizMagassag);
        // Ez egy kis beégetés, azt adja meg, hogy a lépésköz
        // értéke +1 vagy -1 legyen. 
      
        double XLepeskoz = (Math.random()<0.5)? 1.0 : -1.0;
        double YLepeskoz = (Math.random()<0.5)? 0.5 : -0.5;
        
        // Ha nem ismeri ezt az írásmódot, akkor ez is jó:
        /*
          if(Math.random()< 0.5) XLepeskoz = 1;
          else XLepeskoz = -1;
         */
        
        hal.beallit(kx, ky, true, XLepeskoz, YLepeskoz, 
                akvariumSzelesseg, akvariumMagassag, this, ido);
        halak.add(hal);
        indit(hal);
    }
    
    public void vizbeRak (int szam) {
        Hal hal;
        int sorszam;
        int meret;
        for (int i = 0; i < szam; i++) {
            meret = MathTools.random(ALSO_KEPMERET, FELSO_KEPMERET);
            sorszam = MathTools.random(KEP_SZAM);
            hal = new Hal(kepLista.get(sorszam), meret, meret);
            vizbeRak(hal);
        }
    }

    public void frissit() {
        rajzPanel.repaint();
    }

    private void indit(Hal hal) {
        hal.start();
    }

    public void rajzol(Graphics g) {
        for (Hal hal : halak) {
            hal.rajzol(g);
        }
    }
    
    private void kepFeltoltes() {
        Image kep;    
        for (int i = 0; i < KEP_SZAM; i++) {
            kep = ImageTools.getImage("/kepek/hal" + i + ".png");
            kepLista.add(kep);            
        }
    }
    
}


