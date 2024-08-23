import java.time.Month;
import java.util.List;

public class YearlyReport {

    private int year;
    private int income;
    private int expense;
    private List<MonthlyReport> monthlyReportList;

    public YearlyReport(List<MonthlyReport> monthlyReportList) {
        this.monthlyReportList = monthlyReportList;
        setYear();
        setIncome();
        setExpense();
    }

    public int getYear() {
        return year;
    }

    private void setYear() {
        this.year = 2021;
    }

    public int getIncome() {
        return income;
    }

    private void setIncome() {
        this.income = monthlyReportList.stream().map(MonthlyReport::getIncome)
                .reduce(0, Integer::sum);
    }

    public int getExpense() {
        return expense;
    }

    private void setExpense() {
        this.expense = monthlyReportList.stream().map(MonthlyReport::getExpense)
                .reduce(0, Integer::sum);
    }

    public List<MonthlyReport> getMonthlyReportList() {
        return monthlyReportList;
    }

    private int getAverageExpense() {
        return monthlyReportList.stream().map(MonthlyReport::getExpense)
                .reduce(0, Integer::sum) / monthlyReportList.size();
    }

    private int getAverageIncome() {
        return monthlyReportList.stream().map(MonthlyReport::getIncome)
                .reduce(0, Integer::sum) / monthlyReportList.size();
    }

    public void getDifferenceBetweenIncomeAndExpensePerMonth() {
        for (int i = 1; i < monthlyReportList.size() + 1; i++) {
            System.out.println(Month.of(i));
            int result = monthlyReportList.get(i-1).getIncome() - monthlyReportList.get(i-1).getExpense();
            System.out.println("Разность доходов и расходов: " + result);
            System.out.println("_____________________________________________");
        }
    }

    @Override
    public String toString() {
        return
                "ГОД: " + year + "\n" +
                "Средний расход за все месяцы в году: " + getAverageExpense() + "\n" +
                "Средний доход за все месяцы в году: " + getAverageIncome();
    }

}
