public class Warteschlange {
 
    private Listenelement erster;
    
    public Warteschlange() {
     erster = new Abschluss();   
    }
    
    public void einfuegen(int nummerNeu,double laengeNeu) {
        erster = erster.einfuegen(nummerNeu,laengeNeu);
    }
    
    public int gibAnzahl() {
        return erster.durchzaehlen();
    }
}
