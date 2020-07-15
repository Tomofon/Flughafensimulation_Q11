public abstract class Listenelement
{
    private Listenelement neachster;
    
    abstract Listenelement gibNaechster();
    
    abstract Listenelement einfuegen(int nummerNeu,double laengeNeu);
    
    abstract int durchzaehlen();
}
