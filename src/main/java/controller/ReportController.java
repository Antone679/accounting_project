package controller;

import entity.MonthlyReport;
import entity.YearlyReport;
import util.CSVReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReportController {
    public static MonthlyReport createMonthlyReport(int month) throws IOException {
        return new MonthlyReport(month, CSVReader.getFlowsPerMonth(month));
    }

    public static List<MonthlyReport> getAllMonthlyReportsAsList() throws IOException {
        List<MonthlyReport> monthlyReports = new ArrayList<>();
        for (int i = 1; i < countCSVFiles(); i++) {
            monthlyReports.add(new MonthlyReport(i, CSVReader.getFlowsPerMonth(i)));
        }
        return monthlyReports;
    }

    public static void printInfoAllMonths() throws IOException {
        for (MonthlyReport mr : getAllMonthlyReportsAsList()) {
            System.out.println(mr.toString());
        }
    }

    public static boolean compareYearToMonthReports() throws IOException {
        List<MonthlyReport> listFromMonthMethod = getAllMonthlyReportsAsList();
        List<MonthlyReport> listFromYearMethod = createYearlyReport().getMonthlyReportList();
        boolean isEquals = true;
        for (int i = 0; i < listFromMonthMethod.size(); i++) {
            if (listFromMonthMethod.get(i).getIncome() != listFromYearMethod.get(i).getIncome()
                    || listFromMonthMethod.get(i).getExpense() != listFromYearMethod.get(i).getExpense()) {
                isEquals = false;
                System.out.println("Расхождение: " + Month.of(i + 1) + "\n" +
                        "прибыль за месяц " + listFromMonthMethod.get(i).getIncome() + "\n" +
                        "прибыль по годовому " + listFromYearMethod.get(i).getIncome() + "\n" +
                        "расходы за месяц " + listFromMonthMethod.get(i).getExpense() + "\n" +
                        "расходы по годовому " + listFromYearMethod.get(i).getExpense());
                System.out.println();
            }
        }
        return isEquals;
    }

    public static YearlyReport createYearlyReport() {
        return new YearlyReport(CSVReader.getYearlyReport());
    }

    public static void printYearlyReport() {
        YearlyReport yearlyReport = createYearlyReport();
        System.out.println(yearlyReport);
        yearlyReport.getDifferenceBetweenIncomeAndExpensePerMonth();

    }

    private static int countCSVFiles() throws IOException {
        String directoryPath = "src/main/resources";
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            return (int) paths
                    .filter(Files::isRegularFile) // Оставляем только файлы
                    .filter(path -> path.toString().endsWith(".csv")) // Фильтруем по расширению
                    .count(); // Подсчитываем количество
        }
    }
}
