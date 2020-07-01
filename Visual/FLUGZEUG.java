package Visual;

public class FLUGZEUG {

    private Rechteck rechterFleugel, linkerFluegel, rumpf;
    private int rumpfLaenge, rumpfBreite, fluegelLaenge, fluegelBreite;
    private String[] farben;


    public FLUGZEUG(int x, int y) {
        //Initialiserung der Maße
        rumpfLaenge = 40;
        rumpfBreite = 200;
        fluegelLaenge = 70;
        fluegelBreite = 25;

        //Einstellen der Farben
        farben = new String[3];
        farben[0] = "rot";
        farben[1] = "blau";
        farben[2] = "lila";

        //Flugzeug Erzeugen
        flugzeugErzeugen(x, y);
    }

    private int wuerfeln() {
        return (int) Math.ceil(Math.random() * 3) - 1;
    }

    private void flugzeugErzeugen(int x, int y) {
        int i = wuerfeln();
        rumpf = new Rechteck(40, 200, x, y, farben[i]);
        rechterFleugel =  new Rechteck(70, 25, x+40, y+80, farben[i]);
        linkerFluegel = new Rechteck(70, 25, x-70, y+80, farben[i]);
    }


}