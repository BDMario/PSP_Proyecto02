package Model;

import java.sql.*;

public class DatabaseReader{

    private static int income;

    public void getIncomeSequentially(){
        try {
            //Got the initial time to know how long it took
            long startOfReading = System.currentTimeMillis();
            String myURL = "jdbc:mysql://localhost/bbdd_psp_1";
            Connection conn = DriverManager.getConnection(myURL,"DAM2020_PSP","DAM2020_PSP");
            // create a Statement from the connection
            Statement statement = conn.createStatement();
            // insert the data for every record assigned to this thread
            ResultSet resultSet = statement.executeQuery("SELECT `INGRESOS` FROM `empleados`");
            while(resultSet.next()){
                //Add the salary to the variable
                income += resultSet.getInt("INGRESOS");
            }
            conn.close();
            //Set the final time to calculate how long it took
            long endOfReading = System.currentTimeMillis();
            long readingTime = endOfReading - startOfReading;
            System.out.println("Total income: " + income);
            System.out.println("Read sequentially into " + readingTime + " milliseconds.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
