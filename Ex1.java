import java.util.*;
import java.util.function.UnaryOperator;

class Individual {
    private double fitness;
    private double[] chromosome;
    private int chromosome_length;

//    public Individual(){
//        fitness = -100.0;
//        chromosome = new double[20];
//        chromosome_length = 20;
//    }

    public Individual() {
        fitness = Double.MAX_VALUE;
        this.chromosome_length = Exercise_1.CHROMOSOME_LENGTH;
        chromosome = new double[chromosome_length];
        create_chromosome();
    }

    private void create_chromosome(){
        for(int j = 0; j < chromosome_length; j++){
            chromosome[j] = Math.random()*Math.round(5.12*(Math.random() - Math.random()));
        }
    }

    public void calculate_fitness(){
        fitness = Assess.getTest1(chromosome);
    }

    public void mutate(double probability){
        for (int i = 0; i < chromosome_length; i++) {
            if (Math.random() < probability){
                chromosome[i] = chromosome[i] - Math.random();
            }
        }
    }

    public double getFitness() {
        return fitness;
    }

    public double[] getChromosome() {
        return chromosome;
    }
}

class Population {
    private ArrayList<Individual> population_list;
    private int population_size;

    public Population(int population_size){
        population_list = new ArrayList<>();
        this.population_size = population_size;
        //generate_population();
    }

    void generate_population(){
        for (int i = 0; i < population_size; i++) {
            population_list.add(new Individual());
        }
    }

    void calculate_fitness(){
        for (int i = 0; i < population_size; i++) {
            population_list.get(i).calculate_fitness();
        }
    }
    
    void evolve(){
        ArrayList<Individual> new_population_list = new ArrayList<>();
        for (int i = 0; i < population_size; i++) {
            Individual parent_x = tournament_selection();
            Individual parent_y = tournament_selection();
            Individual child = meiosis(parent_x, parent_y);
            child.mutate(Exercise_1.MUTATION_RATE);
            new_population_list.add(child);
        }
        population_list.clear();
        population_list.addAll(new_population_list);
    }

    Individual get_fittest_individual(){
        Individual fittest_individual = new Individual();
        for (int i = 0; i < population_size; i++) {
            if(fittest_individual.getFitness() > population_list.get(i).getFitness()){
                fittest_individual = population_list.get(i);
            }
        }
        return fittest_individual;
    }

    Individual tournament_selection(){
        int k = Exercise_1.TOURNAMENT_K;
        Random random = new Random();
        Population tournament_population = new Population(k);
        for (int i = 0; i < k ; i++) {
            int random_index = random.nextInt(population_size);
            tournament_population.population_list.add(population_list.get(random_index));
        }
        tournament_population.calculate_fitness();
        return tournament_population.get_fittest_individual();
    }

    Individual meiosis(Individual partner_x, Individual partner_y){
        Random random = new Random();
        Individual child = new Individual();
        int chromosome_length = Exercise_1.CHROMOSOME_LENGTH;

        int crossover_point = random.nextInt(chromosome_length);
        for (int i = 0; i < chromosome_length; i++) {
            if (crossover_point < i){ child.getChromosome()[i] = partner_y.getChromosome()[i];}
            else { child.getChromosome()[i] = partner_x.getChromosome()[i];}
        }
        return child;
    }
}

public class Exercise_1 {
    static final int POPULATION_SIZE = 100;
    static final int CHROMOSOME_LENGTH = 20;
    static final int TOURNAMENT_K = 5;
    static final double  MUTATION_RATE = 0.015;



    public Exercise_1(){


    }
    public double[] run(){



//        1) Randomly initialize populations p
        Population population = new Population(POPULATION_SIZE);
        population.generate_population();

//        2) Determine fitness of population
        population.calculate_fitness();

//        3) Untill convergence repeat:
//          a) Select parents from population
//          b) Crossover and generate new population
//          c) Perform mutation on new population
//          d) Calculate fitness for new population
        Individual fittest = population.get_fittest_individual();
        Individual temp = new Individual();

        int generation = 0;
        while ((fittest.getFitness() > 0.05)){
            if (temp.getFitness() == fittest.getFitness() && generation > 500)
                break;

            population.evolve();
            population.calculate_fitness();
            temp = fittest;
            fittest = population.get_fittest_individual();
            generation++;
        }
        return fittest.getChromosome();
    }

//    public void test(){
//        for (int i = 0; i < 1; i++) {
//            // Generate the initial population
//            Population population = new Population(POPULATION_SIZE);
//            population.generate_population();
//
//            // Compute fitness
//            population.calculate_fitness();
//
//            Individual fittest = population.get_fittest_individual();
//            Individual temp = new Individual();
//
//            System.out.println("Best Individual Start: \t" + fittest.getFitness());
//            int generation = 0;
//            while ((fittest.getFitness() > 0.05)){
//                if (temp.getFitness() == fittest.getFitness() && generation > 500)
//                    break;
//
//                population.evolve();
//                population.calculate_fitness();
//                temp = fittest;
//                fittest = population.get_fittest_individual();
//                generation++;
//            }
//
//            System.out.println("Best Individual End: \t" + fittest.getFitness());
//            System.out.println("generation: \t" + generation);
//
//
////            Individual parent2 = population.tournament_selection();
////            Individual get_best_individual = population.get_fittest_individual();
////
////            System.out.println("Parent 1 (Best Individual): \t" + get_best_individual.getFitness());
////            System.out.println("Parent 2: \t\t\t\t\t\t" + parent2.getFitness());
////
////            Individual child = population.meiosis(parent2, get_best_individual);
////            child.calculate_fitness();
////
////            System.out.println("Child before mutation: \t\t\t" + child.getFitness());
////
////            child.mutate(0.1);
////            child.calculate_fitness();
////
////            System.out.println("Child after mutation: \t\t\t" + child.getFitness());
////            //System.out.println("Best fit: " + population.get_best_individual().getFitness());
//            System.out.println("\n--------------------------------------\n");
//        }
//    }
}