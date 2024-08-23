import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyReport {
    private int month;
    private int income;
    private int expense;
    private Map<String, Integer> highestEarn = new HashMap<>();
    private Map<String, Integer> highestExpense = new HashMap<>();
    private List<Flow> flows;

    public MonthlyReport(int month, List<Flow> flows) {
        this.month = month;
        this.flows = flows;
        setIncome();
        setExpense();
        setHighestEarn();
        setHighestExpense();
    }

    public MonthlyReport(int month, int income, int expense) {
        this.month = month;
        this.income = income;
        this.expense = expense;
    }

    public int getMonth() {
        return month;
    }

    private void setMonth(int month) {
        this.month = month;
    }

    public int getIncome() {
        return income;
    }

    ///////////////////////////
    private void setIncome() {
        this.income = flows.stream().filter(a -> !a.isExpense())
                .map(Flow::getSum).reduce(0, Integer::sum);
    }

    /////////////////////////////
    public int getExpense() {
        return expense;
    }

    private void setExpense() {
        this.expense = flows.stream().filter(Flow::isExpense)
                .map(Flow::getSum).reduce(0, Integer::sum);
    }

    public List<Flow> getFlows() {
        return flows;
    }

    private void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    public Map<String, Integer> getHighestEarn() {
        return highestEarn;
    }

    ////////////////////
    private void setHighestEarn() {
        Map<String, Integer> earnMap = new HashMap<>();

        flows.stream().filter(flow -> !flow.isExpense())
                .forEach(flow -> earnMap.put(flow.getName(),
                        earnMap.getOrDefault(flow.getName(), 0) + flow.getSum()));

        if (!earnMap.isEmpty()) {
            int maxValue = earnMap.values().stream().max(Integer::compareTo).orElse(0);
            for (Map.Entry<String, Integer> entry : earnMap.entrySet()) {
                if (entry.getValue() == maxValue) {
                    highestEarn.put(entry.getKey(), maxValue);
                }
            }
        } else {
            highestExpense.put("No earnings", 0);
        }
    }

    //////////////////////
    public Map<String, Integer> getHighestExpense() {
        return highestExpense;
    }

    private void setHighestExpense() {
        Map<String, Integer> expenseMap = new HashMap<>();

        flows.stream()
                .filter(Flow::isExpense)
                .forEach(flow -> expenseMap.put(flow.getName(),
                        expenseMap.getOrDefault(flow.getName(), 0) + flow.getSum()));

        if (!expenseMap.isEmpty()) {
            // Находим максимальное значение и заполняем highestExpense
            int maxValue = expenseMap.values().stream().max(Integer::compareTo).orElse(0);
            for (Map.Entry<String, Integer> entry : expenseMap.entrySet()) {
                if (entry.getValue() == maxValue) {
                    highestExpense.put(entry.getKey(), maxValue);
                }
            }
        } else {
            highestExpense.put("No expenses", 0);
        }
    }

    private String mapPrint(Map<String, Integer> map) {
        String line = null;
        for (Map.Entry<String, Integer> tmp : map.entrySet()) {
            line = tmp.getKey() + " - " + tmp.getValue();
        }
        return line;
    }

    @Override
    public String toString() {

        return
                Month.of(month) + "\n" +
                        "ДОХОД " + income + "\n" +
                        "РАСХОД " + expense + "\n" +
                        "БОЛЬШИЙ ЗАРАБОТОК " + mapPrint(highestEarn) + "\n" +
                        "БОЛЬШИЙ РАСХОД " + mapPrint(highestExpense) + "\n" +
                        "___________________________________________________________";
    }
}
