package Visual;

import java.awt.geom.Ellipse2D;

/**
 * Ein Kreis, der manipuliert werden kann und sich selbst auf einer Leinwand
 * zeichnet.
 * 
 * @author Michael Kaelling und David J. Barnes
 * @version 2006.03.30
 */

public class Kreis {
	private int durchmesser;

	private int xPosition;

	private int yPosition;

	private String farbe;

	private boolean istSichtbar;

	/**
	 * Erzeuge einen neuen Kreis an einer Standardposition mit einer
	 * Standardfarbe.
	 */
	public Kreis(int d, int x, int y, String f) {
		durchmesser = d;
		xPosition = x;
		yPosition = y;
		farbe = f;
		istSichtbar = false;
		sichtbarMachen();
	}

	/**
	 * Mache diesen Kreis sichtbar. Wenn es bereits sichtbar ist, tue nichts.
	 */
	public void sichtbarMachen() {
		istSichtbar = true;
		zeichnen();
	}

	/**
	 * Mache diesen Kreis unsichtbar. Wenn es bereits unsichtbar ist, tue
	 * nichts.
	 */
	public void unsichtbarMachen() {
		loeschen();
		istSichtbar = false;
	}

	/**
	 * Bewege diesen Kreis einige Bildschirmpunkte nach rechts.
	 */
	public void nachRechtsBewegen() {
		horizontalBewegen(20);
	}

	/**
	 * Bewege diesen Kreis einige Bildschirmpunkte nach links.
	 */
	public void nachLinksBewegen() {
		horizontalBewegen(-20);
	}

	/**
	 * Bewege diesen Kreis einige Bildschirmpunkte nach oben.
	 */
	public void nachObenBewegen() {
		vertikalBewegen(-20);
	}

	/**
	 * Bewege diesen Kreis einige Bildschirmpunkte nach unten.
	 */
	public void nachUntenBewegen() {
		vertikalBewegen(20);
	}

	/**
	 * Bewege diesen Kreis horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void horizontalBewegen(int entfernung) {
		loeschen();
		xPosition += entfernung;
		zeichnen();
	}

	/**
	 * Bewege diesen Kreis vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void vertikalBewegen(int entfernung) {
		loeschen();
		yPosition += entfernung;
		zeichnen();
	}

	/**
	 * Bewege diesen Kreis langsam horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamHorizontalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			xPosition += delta;
			zeichnen();
		}
	}

	/**
	 * Bewege diesen Kreis langsam vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamVertikalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			yPosition += delta;
			zeichnen();
		}
	}

	/**
	 * aendere den Durchmesser dieses Kreises in 'neuerDurchmesser' (Angabe in
	 * Bildschirmpunkten). 'neuerDurchmesser' muss graeaeer gleich Null sein.
	 */
	public void groesseAendern(int neuerDurchmesser) {
		loeschen();
		durchmesser = neuerDurchmesser;
		zeichnen();
	}

	/**
	 * aendere die Farbe dieses Kreises in 'neueFarbe'. Gaeltige Angaben sind
	 * "rot", "gelb", "blau", "gruen", "lila" und "schwarz".
	 */
	public void farbeAendern(String neueFarbe) {
		farbe = neueFarbe;
		zeichnen();
	}

	/**
	 * Zeichne diesen Kreis mit seinen aktuellen Werten auf den Bildschirm.
	 */
	public void zeichnen() {
		if (istSichtbar) {
			Leinwand leinwand = Leinwand.gibLeinwand();
			leinwand.zeichne(this, farbe, new Ellipse2D.Double(xPosition,
					yPosition, durchmesser, durchmesser));
			leinwand.warte(10);
		}
	}

	/**
	 * Laesche diesen Kreis vom Bildschirm.
	 */
	public void loeschen() {
		if (istSichtbar) {
			Leinwand leinwand = Leinwand.gibLeinwand();
			leinwand.entferne(this);
		}
	}

}
