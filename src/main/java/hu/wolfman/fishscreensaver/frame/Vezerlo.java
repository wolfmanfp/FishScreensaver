package hu.wolfman.fishscreensaver.frame;

import hu.wolfman.fishscreensaver.util.MathUtil;
import hu.wolfman.fishscreensaver.util.ImageUtil;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
        int akvariumSzelesseg = rajzPanel.getWidth();
        int akvariumMagassag = rajzPanel.getHeight();
        int vizMagassag = (int) (akvariumMagassag * 0.2);
        long ido = MathUtil.random(ALSO_IDO, FELSO_IDO);
        double kx = MathUtil.random(akvariumSzelesseg - hal.getKepSzelesseg());
        double ky = MathUtil.random(akvariumMagassag - hal.getKepMagassag(),
                vizMagassag);

        double XLepeskoz = (Math.random() < 0.5) ? 1.0 : -1.0;
        double YLepeskoz = (Math.random() < 0.5) ? 0.5 : -0.5;

        hal.beallit(kx, ky, true, XLepeskoz, YLepeskoz,
                akvariumSzelesseg, akvariumMagassag, this, ido);
        halak.add(hal);
        indit(hal);
    }

    public void vizbeRak(int szam) {
        Hal hal;
        int sorszam;
        int meret;
        for (int i = 0; i < szam; i++) {
            meret = MathUtil.random(ALSO_KEPMERET, FELSO_KEPMERET);
            sorszam = MathUtil.random(KEP_SZAM);
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
            kep = ImageUtil.getImage("/kepek/hal" + i + ".png");
            kepLista.add(kep);
        }
    }

}


