public class Abschluss extends Listenelement
{
    public Abschluss() {
    }
    
    public Listenelement gibNaechster() {
        return null;
    }
    
    public Listenelement einfuegen(int n,double l) {
        return new Landebahn(n,l,this);
    }
        
    public int durchzaehlen() {
        return 0;
    }
}
