package Model;

        import java.sql.*;
        import java.util.ArrayList;

public class DatabaseReaderByThreads extends Thread{

    private int numberOfLines;
    private int start;
    private ArrayList<Integer> employees;

    public DatabaseReaderByThreads(int numberOfLines, int start, ArrayList<Integer> employees) {
        this.numberOfLines = numberOfLines;
        this.start = start;
        this.employees = employees;
    }

    //Declare an static int so we can add the salary of the employees from every thread
    public static int income;

    @Override
    public void run() {
        super.run();
        try {
            //Set the end array position for every thread
            int end = (start + numberOfLines - 1);
            String myURL = "jdbc:mysql://localhost/bbdd_psp_1";
            Connection conn = DriverManager.getConnection(myURL,"DAM2020_PSP","DAM2020_PSP");
            //Create a Statement from the connection
            Statement statement = conn.createStatement();
            //Insert the data for every record assigned to this thread
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `empleados` WHERE `ID` BETWEEN " + employees.get(start) + " AND " + employees.get(end));
            while(resultSet.next()){
                //Add the salary to the static variable
                income += resultSet.getInt("INGRESOS");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
