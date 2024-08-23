public class Flow {
    private String Name;
    private boolean isExpense;
    private int quantity;
    private int sumOfOne;
    private int sum;

    public Flow(String name, boolean isExpense, int quantity, int sumOfOne) {
        Name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.sum = quantity * sumOfOne;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public void setSumOfOne(int sumOfOne) {
        this.sumOfOne = sumOfOne;
    }

    public int getSum() {
        return sum;
    }


    @Override
    public String toString() {
        return "Flow{" +
                "Name='" + Name + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", sumOfOne=" + sumOfOne +
                ", sum=" + sum +
                '}';
    }
}
