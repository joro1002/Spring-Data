import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Set home = new Set();
        home.setConnection("root", "root");
       // home.getNames();
        //home.getMinionNames();
        home.changeTownNames();
    }
}
