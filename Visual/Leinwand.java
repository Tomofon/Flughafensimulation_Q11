package Visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Leinwand ist eine Klasse, die einfache Zeichenoperationen auf einer
 * leinwandartigen Zeichenflaeche ermaeglicht. Sie ist eine vereinfachte Version
 * der Klasse Canvas (englisch faer Leinwand) des JDK und wurde speziell faer das
 * Projekt "Figuren" geschrieben.
 * 
 * 
 * @author: Bruce Quig
 * @author: Michael Kaelling (mik)
 * @author: Axel Schmolitzky
 * 
 * @version: 2006.03.30
 */
public class Leinwand implements ActionListener{
    // Hinweis: Die Implementierung dieser Klasse (insbesondere die
    // Verwaltung der Farben und Identitaeten der Figuren) ist etwas
    // komplizierter als notwendig. Dies ist absichtlich so, weil damit
    // die Schnittstellen und Exemplarvariablen der Figuren-Klassen
    // faer den Lernanspruch dieses Projekts einfacher und klarer
    // sein kaennen.

    private static Leinwand leinwandSingleton;

    /**
     * Fabrikmethode, die eine Referenz auf das einzige Exemplar dieser Klasse
     * zuraeckliefert. Wenn es von einer Klasse nur genau ein Exemplar gibt, wird
     * dieses als 'Singleton' bezeichnet.
     */
    public static Leinwand gibLeinwand() {
        if (leinwandSingleton == null) {
            leinwandSingleton = new Leinwand("Flughafensimulation", 1920, 1080,
                    Color.green);
        }
        leinwandSingleton.setzeSichtbarkeit(true);
        return leinwandSingleton;
    }

    // ----- Exemplarvariablen -----

    private JFrame fenster;

    private Zeichenflaeche zeichenflaeche;

    private Graphics2D graphic;

    private Color hintergrundfarbe;

    private Image leinwandImage;

    private List<Object> figuren;

    private Map<Object, ShapeMitFarbe> figurZuShape;
    
    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Image star;
    private Timer timer;
    private int x, y;
    
    /**
     * Erzeuge eine Leinwand.
     * 
     * @param titel
     *            Titel, der im Rahmen der Leinwand angezeigt wird
     * @param breite
     *            die gewaenschte Breite der Leinwand
     * @param hoehe
     *            die gewaenschte Haehe der Leinwand
     * @param grundfarbe
     *            die Hintergrundfarbe der Leinwand
     */
    private Leinwand(String titel, int breite, int hoehe, Color grundfarbe) {
        fenster = new JFrame();
        zeichenflaeche = new Zeichenflaeche();
        fenster.setContentPane(zeichenflaeche);
        fenster.setTitle(titel);
        zeichenflaeche.setPreferredSize(new Dimension(breite, hoehe));
        hintergrundfarbe = grundfarbe;
        fenster.pack();
        figuren = new ArrayList<Object>();
        figurZuShape = new HashMap<Object, ShapeMitFarbe>();
        timer = new Timer(DELAY, this);
        //timer.start();
    }

    /**
     * Setze, ob diese Leinwand sichtbar sein soll oder nicht. Wenn die Leinwand
     * sichtbar gemacht wird, wird ihr Fenster in den Vordergrund geholt. Diese
     * Operation kann auch benutzt werden, um ein bereits sichtbares
     * Leinwandfenster in den Vordergrund (vor andere Fenster) zu holen.
     * 
     * @param sichtbar
     *            boolean faer die gewaenschte Sichtbarkeit: true faer sichtbar,
     *            false faer nicht sichtbar.
     */
    public void setzeSichtbarkeit(boolean sichtbar) {
        if (graphic == null) {
            // erstmaliger Aufruf: erzeuge das Bildschirm-Image und faelle
            // es mit der Hintergrundfarbe
            Dimension size = zeichenflaeche.getSize();
            leinwandImage = zeichenflaeche.createImage(size.width, size.height);
            graphic = (Graphics2D) leinwandImage.getGraphics();
            graphic.setColor(hintergrundfarbe);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        fenster.setVisible(sichtbar);
    }

    /**
     * Zeichne faer das gegebene Figur-Objekt eine Java-Figur (einen Shape) auf
     * die Leinwand.
     * 
     * @param figur
     *            das Figur-Objekt, faer das ein Shape gezeichnet werden soll
     * @param farbe
     *            die Farbe der Figur
     * @param shape
     *            ein Objekt der Klasse Shape, das tatsaechlich gezeichnet wird
     */
    public void zeichne(Object figur, String farbe, Shape shape) {
        figuren.remove(figur); // entfernen, falls schon eingetragen
        figuren.add(figur); // am Ende hinzufaegen
        figurZuShape.put(figur, new ShapeMitFarbe(shape, farbe));
        erneutZeichnen();
    }

    /**
     * Entferne die gegebene Figur von der Leinwand.
     * 
     * @param figur
     *            die Figur, deren Shape entfernt werden soll
     */
    public void entferne(Object figur) {
        figuren.remove(figur); // entfernen,falls schon eingetragen
        figurZuShape.remove(figur);
        erneutZeichnen();
    }

    /**
     * Setze die Zeichenfarbe der Leinwand.
     * 
     * @param farbname
     *            der Name der neuen Zeichenfarbe.
     */
    public void setzeZeichenfarbe(String farbname) {
        if (farbname.equals("rot")) {
            graphic.setColor(Color.red);
        } else if (farbname.equals("schwarz")) {
            graphic.setColor(Color.black);
        } else if (farbname.equals("blau")) {
            graphic.setColor(Color.blue);
        } else if (farbname.equals("gelb")) {
            graphic.setColor(Color.yellow);
        } else if (farbname.equals("gruen")) {
            graphic.setColor(Color.green);
        } else if (farbname.equals("lila")) {
            graphic.setColor(Color.magenta);
        } else if (farbname.equals("weiss")) {
            graphic.setColor(Color.white);
        } else if (farbname.equals("grau")) {
            graphic.setColor(Color.gray);
        } else {
            graphic.setColor(Color.black);
        }
    }

    /**
     * Warte faer die angegebenen Millisekunden. Mit dieser Operation wird eine
     * Verzaegerung definiert, die faer animierte Zeichnungen benutzt werden kann.
     * 
     * @param millisekunden
     *            die zu wartenden Millisekunden
     */
    public void warte(int millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (Exception e) {
            // Exception ignorieren
        }
    }

    /**
     * Zeichne erneut alle Figuren auf der Leinwand.
     */
    private void erneutZeichnen() {
        loeschen();
        for (Object figur : figuren) {
            figurZuShape.get(figur).draw(graphic);
        }
        zeichenflaeche.repaint();
    }
    
    public void actionPerformed(ActionEvent e) {

        x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }

        zeichenflaeche.repaint();
    }

    /**
     * Laesche die gesamte Leinwand.
     */
    private void loeschen() {
        Color original = graphic.getColor();
        graphic.setColor(hintergrundfarbe);
        Dimension size = zeichenflaeche.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }
    
    public void animation() {
    
    }

    /***************************************************************************
     * Interne Klasse Zeichenflaeche - die Klasse faer die GUI-Komponente, die
     * tatsaechlich im Leinwand-Fenster angezeigt wird. Diese Klasse definiert
     * ein JPanel mit der zusaetzlichen Maeglichkeit, das auf ihm gezeichnet Image
     * aufzufrischen (erneut zu zeichnen).
     */
    private class Zeichenflaeche extends JPanel {
        private static final long serialVersionUID = 20060330L;

        public void paint(Graphics g) {
            g.drawImage(leinwandImage, 0, 0, null);
        }
    }

    /***************************************************************************
     * Interne Klasse ShapeMitFarbe - Da die Klasse Shape des JDK nicht auch
     * eine Farbe mitverwalten kann, muss mit dieser Klasse die Verknaepfung
     * modelliert werden.
     */
    private class ShapeMitFarbe {
        private Shape shape;

        private String farbe;

        public ShapeMitFarbe(Shape shape, String farbe) {
            this.shape = shape;
            this.farbe = farbe;
        }

        public void draw(Graphics2D graphic) {
            setzeZeichenfarbe(farbe);
            graphic.fill(shape);
        }
    }

}
