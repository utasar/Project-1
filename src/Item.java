public class Item {
    private final String name; // Item Name-A label for item
    private final double weight; // Item Weight in pound
    private final int value; //Item value since rounded to nearest dollar, it is integer
    private boolean included; //Indicates whether the item should be taken or not

    public Item(String name, double weight, int value) {// constructor Item with 3 parameter
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.included = false; // Initialize as not included
    }

    public Item(Item other) {// constructor for Item Class
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    public double getWeight() { // getter for weight
        return weight;
    }

    public int getValue() { // getter for value
        return value;
    }

    public boolean isIncluded() {// Check Inclusion
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included; // Setter for included field
    }

    @Override
    public String toString() {// Display the item in the given form
        return name + " (" + weight + " lbs, $" + value + ")";
    }
}
