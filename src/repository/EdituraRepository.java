package repository;

import database.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EdituraRepository {

    private  static EdituraRepository edituraRepository;
    private EdituraRepository() {}
    public static EdituraRepository getInstance()
    {
        if (edituraRepository == null)
            edituraRepository = new EdituraRepository();
        return edituraRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS EDITURA " +
                "(idEditura int PRIMARY KEY AUTO_INCREMENT, " +
                "denumire varchar(100); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM EDITURA;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addEditura(String denumire) {
        String insertEdituraSql = "INSERT INTO EDITURA(denumire) VALUES(\""
                + denumire + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertEdituraSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayEditura()
    {
        String selectSql = "SELECT * FROM EDITURA;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Editura: " + resultSet.getString(2));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("Nu exista!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
