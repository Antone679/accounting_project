package util;

import controller.ReportController;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleAsker {
    public static void chooseFunction() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int number;

        do {

            System.out.println("Выберите действие и введите порядковую цифру: \n" +
                    "1. Считать все месячные отчеты.\n" +
                    "2. Считать годовой отчет.\n" +
                    "3. Сверить отчеты.\n" +
                    "4. Вывести информацию о всех месячных отчетах.\n" +
                    "5. Вывести информацию о годовом отчете.\n" +
                    "6. Выход из программы");


            while (!scanner.hasNextInt()) {
                System.out.println("Введено неправильное число, попробуйте еще раз!");
                scanner.next(); // Сбрасываем неверный ввод
            }

          number = scanner.nextInt();

            switch (number) {
                case 1:
                    ReportController.getAllMonthlyReportsAsList();
                    break;
                case 2:
                    ReportController.createYearlyReport();
                    break;
                case 3: ReportController.compareYearToMonthReports();
                    break;
                case 4: ReportController.printInfoAllMonths();
                    break;
                case 5: ReportController.printYearlyReport();
                break;
                case 6:
                    System.out.println("Завершение работы программы!");
                    break;
                default:
                    System.out.println("Введено неправильное число, попробуйте еще раз!");
                    System.out.println();
                    break;
            }
        } while (number != 6);
        scanner.close();
    }
}
