import java.util.ArrayList;
import java.util.Random;
/**
 * Chromosome class has list of items to show genetic algorithm
 * Each chromosome hae set of items
 * Randomly determined the includion
 * Class suitable for crossover, mutation and fitness calculation methods
 * It extends arrayList of item and it impliments comparable interface
 */
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private static Random rng = new Random(); // Random number generator
    /**
     * Default constructor for Chromosome
     */
    public Chromosome() {
        // constructor without parameter
    }
    //Constructor overloading
    /**
     * Constructor to initiate the set of items
     * @Param ArrayList Item, for the inclusion of chromosome
     */
    public Chromosome(ArrayList<Item> items) {
        for (Item item : items) {
            Item newItem = new Item(item);
            newItem.setIncluded(rng.nextBoolean()); // Randomly include items passed in to this Chromosome
            this.add(newItem);
        }
    }
    /**
     * create child chromosome with crossover
     * @param other the other parent to do crossover with
     * @return Chromosome after crossover will be returned
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome child = new Chromosome();

        // There are 7 genes, represented by the 7 items in the Chromosome
        for (int i = 0; i < 7; i++) { // Assuming each chromosome has exactly 7 items
            int randomNumber = rng.nextInt(10) + 1; // Random number between 1 and 10

            if (randomNumber <= 5) {
                // Use Parent 1's gene (included value)
                child.add(new Item(this.get(i))); // Copy the item to child chromosome
                child.get(i).setIncluded(this.get(i).isIncluded()); // Set inclusion from Parent 1
            } else {
                // Use Parent 2's gene (included value)
                child.add(new Item(other.get(i))); //Copy the item
                child.get(i).setIncluded(other.get(i).isIncluded()); // Set inclusion from Parent 2
            }
        }
        return child;
    }
    /**
     * Mutate the chromosome by reversing the inclusion
     * 10% of the time mutation occurs
     */
    public void mutate() {
        for (Item item : this) {
            if (rng.nextInt(10) == 0) {
                item.setIncluded(!item.isIncluded()); // reverse included state
            }
        }
    }
    /**
     * Calculate fitness of the chromosome
     * Combined weight is less than or equal to 10 are included
     * combined weight more than 10 are excluded
     * @return fitness of the chromosome
     */
    public int getFitness() {
        double totalWeight = 0;
        int totalValue = 0;
        for (Item item : this) {
            if (item.isIncluded()) {
                totalWeight += item.getWeight();
                totalValue += item.getValue();
            }
        }
        return totalWeight > 10 ? 0 : totalValue; // Return fitness 0 if total weight is more than 10
    }
    /**
     * Override standard compareTo to compare two chromosomes
     *@param Other chromosome to compare with
     * @return return integer value
     */
    @Override
    public int compareTo(Chromosome other) {
        //Returns -1 if this chromosome’s =itness is greater than the other’s fitness, +1 if
        //this chromosome’s fitness is less than the other one’s, and 0 if their fitness is the same
        return Integer.compare(other.getFitness(), this.getFitness());
    }
    /**
     *override the toString to format the object to return specified text format
     *  @return  string value
     */
    @Override
    public String toString() {
        // Display Name, weight and values of all items in the format
        // Stringbuilder is used taking reference to java guidelines from: https://www.geeksforgeeks.org/
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            if (item.isIncluded()) {
                sb.append(item.toString()).append(", ");
            }
        }
        sb.append("Fitness: ").append(getFitness());
        return sb.toString();
    }
}
