public class Landebahneintrag
{
    private int nummer;
    private double laenge;
    private boolean besetzt;

    public Landebahneintrag(int nummerNeu, double laengeNeu) {
        nummer = nummerNeu;
        laenge = laengeNeu;
        besetzt = false;
    }

    public boolean gibFreigabe() {
        return besetzt;
    }
  
    public void gibDaten() {
        System.out.println("Nummer: "+nummer+" Laenge: "+laenge);
    }
}
