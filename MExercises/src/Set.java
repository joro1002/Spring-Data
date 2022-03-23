import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Set {

    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/minions_db?serverTimezone=UTC&";

    private Connection connection;

    public void setConnection(String user, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

         connection = DriverManager
                .getConnection(CONNECTION_STRING , properties);
    }

    public void getNames() throws SQLException {
        String query = "SELECT v.name, COUNT(mv.minion_id) AS \"Count\"\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING Count > 15\n" +
                "ORDER BY Count DESC";

        PreparedStatement statement = connection
                .prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %d%n",
                    resultSet.getString(1),
                    resultSet.getInt(2));
        }
    }

    public void getMinionNames() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

       String villainName =  getEntityNameById(villainId, "villains");
       if (villainName == null){
           System.out.printf("No villain with ID %d exists in the database.", villainId);
           return;
       }
        System.out.printf("Villain: %s%n", villainName);

       String query = "SELECT m.name, m.age\n" +
               "FROM minions AS m\n" +
               "JOIN minions_villains mv on m.id = mv.minion_id\n" +
               "WHERE mv.villain_id = ?";

       PreparedStatement statement = connection.prepareStatement(query);
       statement.setInt(1, villainId);
       ResultSet resultSet = statement.executeQuery();

       int count = 1;
       while (resultSet.next()){
           System.out.printf("%d. %s %d%n", count++,
                   resultSet.getString("name"),
                   resultSet.getInt("age"));
       }

    }

    private String getEntityNameById(int entityId, String tableName) throws SQLException {
        String query = String.format("SELECT name FROM %s WHERE id = ?", tableName);

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setInt(1, entityId);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;
    }
}
