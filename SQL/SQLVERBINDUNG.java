package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Date;

public class SQLVERBINDUNG {

    Connection connection;
    public boolean connectToMysql(String host, String database, String user, String passwd){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwd;
            connection = DriverManager.getConnection(connectionCommand);
            System.out.println("SQL-VERBUNDEN");
            return true;
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

}
