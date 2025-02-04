import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
/**
 * GeneticAlgorithm class is suppose to solve basic bin packing problem implementing the genetic algorithm
 * Problem is: maximum value of items that a fittest person take if one can take maximum of 10 pound weight only
 * It reads the items from the file, initilize, crossover, mutate over multiple generation to find fittest individual
 */
public class GeneticAlgorithm {
    private static Random rng = new Random(); // Declare rng as a static variable
    /**
     * Read the data from a file. Each line contain Name, weight and value separated by comma & space
     * @param filename name of the file containing items data
     * @return array of items object taken out from the file
     * @throws FileNotFoundException if the file specified in the file cannot be found
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(", ");
            String name = parts[0];
            double weight = Double.parseDouble(parts[1]);
            int value = Integer.parseInt(parts[2]);
            items.add(new Item(name, weight, value));
        }
        scanner.close();
        return items;
    }
    /**
     * Initilize population of chromosome randomly
     * @param items list of Item objects to use while creating chromosomes
     * @param populationSize number of chromosome to generate
     * @return arraylist of Chromosome objects representing initial population
     *
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        ArrayList<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Chromosome(items));
        }
        return population;
    }
    /**
     *
     * @param args arguement not used
     * @throws FileNotFoundException if the file items.txt
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Item> items = readData("items.txt");
        ArrayList<Chromosome> population = initializePopulation(items, 10);// set initial population to be 10

        for (int generation = 0; generation < 20; generation++) { // repeat 20 times
            ArrayList<Chromosome> nextGeneration = new ArrayList<>(population);
            Collections.sort(nextGeneration);

            // Add the top individuals to the next generation
            population.clear();
            population.addAll(nextGeneration.subList(0, 10));//adding current population to next generation

            // Crossover to create new individuals
            while (population.size() < 10) {
                Chromosome parent1 = nextGeneration.get(rng.nextInt(nextGeneration.size()));// first parent chosen for the generation
                Chromosome parent2 = nextGeneration.get(rng.nextInt(nextGeneration.size()));// second parent
                Chromosome child = parent1.crossover(parent2);
                population.add(child);
            }

            // Mutate 10% of the population
            for (int i = 0; i < population.size() * 0.1; i++) {
                population.get(rng.nextInt(population.size())).mutate();
            }

            // Sort population by fitness
            Collections.sort(population);
        }

        // Display fittest individual first position of arrayList, to the console
        System.out.println("Fittest Individual:");
        System.out.println(population.get(0).toString());
    }
}
