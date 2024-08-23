import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static List<Flow> getFlowsPerMonth(int month) {
        List<Flow> flows = new ArrayList<>();

        File file;
        Scanner scanner;

        if (month < 10) {
            file = new File("src/main/resources/m.20210" + month + ".csv");
        } else {
            file = new File("src/main/resources/m.2021" + month + ".csv");
        }
        try {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("item")) {
                    continue;
                }

                String[] arrayOfData = line.split(",");
                Flow flow = new Flow(arrayOfData[0], Boolean.parseBoolean(arrayOfData[1]),
                        Integer.parseInt(arrayOfData[2]), Integer.parseInt(arrayOfData[3]));
                flows.add(flow);
            }
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
        scanner.close();
        return flows;
    }

    public static List<MonthlyReport> getYearlyReport() {
        List<MonthlyReport> monthlyReports = new ArrayList<>();
        Scanner scanner = null;

        File file = new File("src/main/resources/y.2021.csv");

        try {
            scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNext()) {

                String line1 = scanner.nextLine();
                String line2 = scanner.nextLine();

                MonthlyReport monthlyReport = getReportFromLines(line1, line2);
                monthlyReports.add(monthlyReport);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        scanner.close();
        return monthlyReports;

    }

    private static MonthlyReport getReportFromLines(String line1, String line2) {
        String[] dataArray1 = line1.split(",");
        String[] dataArray2 = line2.split(",");

        MonthlyReport monthlyReport;

        if (Boolean.parseBoolean(dataArray1[2])) {
            monthlyReport = new MonthlyReport(Integer.parseInt(dataArray1[0]),
                    Integer.parseInt(dataArray2[1]), Integer.parseInt(dataArray1[1]));
        } else {
            monthlyReport = new MonthlyReport(Integer.parseInt(dataArray1[0]),
                    Integer.parseInt(dataArray1[1]), Integer.parseInt(dataArray2[1]));
        }
        return monthlyReport;
    }
}
