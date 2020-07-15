public class Landebahn extends Listenelement
{
    private Listenelement naechster;
    private Landebahneintrag landebahndaten;
    
    public Landebahn(int n,double l,Listenelement next) {
        landebahndaten = new Landebahneintrag(n,l);
        naechster = next;
    }
    
    public Listenelement einfuegen(int n, double l) {
        naechster = naechster.einfuegen(n,l);
        return this;
    }
    
    public int durchzaehlen() {
        return naechster.durchzaehlen() +1;
    }
    
    public Listenelement gibNaechster() {
        return naechster;
    }
}
