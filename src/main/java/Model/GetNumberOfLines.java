package Model;

import java.sql.*;
import java.util.ArrayList;

public class GetNumberOfLines {
    public ArrayList<Integer> GetLines(){
        //Create an arraylist of integers so we can save the IDs of the employees
        ArrayList<Integer> employees = new ArrayList<>();
        try {
            String myURL = "jdbc:mysql://localhost/bbdd_psp_1";
            Connection conn = DriverManager.getConnection(myURL,"root","");
            // create a Statement from the connection
            Statement statement = conn.createStatement();
            // insert the data for every record
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `empleados`");
            while(resultSet.next()){
                //Adding the id of each employee to the array list to return it later
                employees.add(resultSet.getInt("ID"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
