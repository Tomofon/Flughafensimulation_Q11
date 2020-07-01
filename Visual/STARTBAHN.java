package Visual;

public class STARTBAHN {
    private Rechteck[] bahnen;
    private Rechteck parkplatz;
    private Rechteck[] einfahrt;
    private Rechteck[] startbahnStreifenRechts, startbahnStreifenLinks;

    public STARTBAHN () {
        // Startbahnen
        bahnen = new Rechteck[2];
        //Linke Startbahn
        bahnen[0] = new Rechteck(210, 1080, 30, 0, "grau");
        //Rechte Startbahn
        bahnen[1] = new Rechteck(210, 1080, 1680, 0, "grau");

        //Parkplatz
        parkplatz = new Rechteck(1240, 540, 340, 0, "grau");

        //Einfahrten auf die Startbahn
        einfahrt = new Rechteck[2];
        //Linke Einfahrt
        einfahrt[0] = new Rechteck(100, 180, 240, 200, "grau");
        //Rechte Einfahrt
        einfahrt[1] = new Rechteck(100, 180, 1580, 200, "grau");

        //Startbahnstreifen erstellen
        startbahnStreifenRechts = new Rechteck[7];
        startbahnStreifenLinks = new Rechteck[7];
        //rechte startBahnstreifen
        for (int i = 0; i < 7; i++) {
            startbahnStreifenRechts[i] = new Rechteck(20, 72, 125, 144*i, "weiss");
        }
        //linke startBahnstreifen
        for (int i = 0; i < 7; i++) {
            startbahnStreifenLinks[i] = new Rechteck(20, 72, 1775, 144*i, "weiss");
        }
    }
}