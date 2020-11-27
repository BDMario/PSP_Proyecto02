import Model.DatabaseReader;
import Model.DatabaseReaderByThreads;
import Model.GetNumberOfLines;

import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) {
        new DatabaseReader().getIncomeSequentially();

        ArrayList<Integer> employees = new GetNumberOfLines().GetLines(); //Calls a method that returns an arraylist with all employees ID's

        //Sets a range to use later, which we'll use to know how much lines we want to read for each thread
        int range = employees.size() / 5;
        //With the rest we'll know how many threads will have to read one more line than the default range
        int rest = employees.size() % 5;
        int start = 0;

        Thread th;
        //Got the initial time to know how long it took
        long startOfReading = System.currentTimeMillis();
        for (int x = 0; x < 5; x++) {
            //If we do now have rest or it is lesser than the number of loops that we did, it wont get through the condition so the range will be the default
            if (rest != 0 && x < rest) {
                //Create a new thread with range, when it starts and the arraylist of the employees as parameters
                th = new Thread(new DatabaseReaderByThreads(range + 1, start, employees), "th" + x);
                th.start();
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                start += range + 1;
            } else {
                th = new Thread(new DatabaseReaderByThreads(range, start, employees), "th" + x);
                th.start();
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                start += range;
            }
        }
        //Get the final time to calculate how long it took
        long endOfReading = System.currentTimeMillis();
        long readingTime = endOfReading - startOfReading;
        System.out.println("Total income from thread reading: " + DatabaseReaderByThreads.income + " in " + readingTime + " milliseconds");
    }
}
