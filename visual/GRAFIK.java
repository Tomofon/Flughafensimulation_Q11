package Visual;

public class GRAFIK {
    private STARTBAHN startbahn;
    private FLUGZEUG[] flugzeuge;

    public GRAFIK() {
        startbahn = new STARTBAHN();
        flugzeuge = new FLUGZEUG[3];
        flugzeuge[0] = new FLUGZEUG(585, 75);
        flugzeuge[1] = new FLUGZEUG(940, 75);
        flugzeuge[2] = new FLUGZEUG(1275, 75);
    }
}