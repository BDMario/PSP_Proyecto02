package Model;

import java.sql.*;

public class DatabaseReader{

    private static int income;

    public void getIncomeSequentially(){
        try {
            long startOfReading = System.currentTimeMillis();
            String myURL = "jdbc:mysql://localhost/bbdd_psp_1";
            Connection conn = DriverManager.getConnection(myURL,"root","");
            // create a Statement from the connection
            Statement statement = conn.createStatement();
            // insert the data for every record assigned to this thread
            ResultSet resultSet = statement.executeQuery("SELECT `INGRESOS` FROM `empleados`");
            while(resultSet.next()){
                income += resultSet.getInt("INGRESOS");
            }
            conn.close();
            long endOfReading = System.currentTimeMillis();
            long readingTime = endOfReading - startOfReading;
            System.out.println("Total income: " + income);
            System.out.println("Read sequentially into " + readingTime + " milliseconds.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
