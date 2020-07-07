package SQL;

public class SQLZUGRIFF {

    SQLVERBINDUNG sqlverbindung;

    public SQLZUGRIFF() {
        sqlverbindung = new SQLVERBINDUNG();
        sqlverbindung.connectToMysql("127.0.0.1", "flugahfen_einträge", "root", "");
    }
}
