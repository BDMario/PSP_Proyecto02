import Model.DatabaseReader;
import Model.DatabaseReaderByThreads;
import Model.GetNumberOfLines;

import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) {
        new DatabaseReader().getIncomeSequentially();

        ArrayList<Integer> employees = new GetNumberOfLines().GetNumberOfLines();

        int range = employees.size() / 5;
        int rest = employees.size() % 5;
        int start = 0;

        Thread th;

        long startOfReading = System.currentTimeMillis();
        for (int x = 0; x < 5; x++) {
            if (rest != 0 && x < rest) {
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
        long endOfReading = System.currentTimeMillis();
        long readingTime = endOfReading - startOfReading;
        System.out.println("Total income from thread reading: " + DatabaseReaderByThreads.income + " in " + readingTime + " milliseconds");
    }
}
