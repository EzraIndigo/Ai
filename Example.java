//This is my example Solution
import java.lang.Math;
import java.util.*;


class Example {
	public static void main(String[] args) {
		//Do not delete/alter the next line
		long startT = System.currentTimeMillis();

		//Edit this according to your name and login
		String name = "Ezra Wilton";
		String login = "ew340";


		double[] result = Ex1.run();
	double fit =	Assess.getTest1(result);
	System.out.println(fit);
		//Do not delete or alter the next line
		long endT = System.currentTimeMillis();
		System.out.println("Total execution time was: " + ((endT - startT) / 1000.0) + " seconds");


	}

}
	class Chromosome {

		public double[] chromosome;
		public double fitness;
		public int chromoLength = 20;

		public Chromosome() {
			chromosome = new double[chromoLength];
			makeChromosome();
		}

		public void makeChromosome() { //building set of 20 doubles
			for (int i = 0; i < chromoLength; i++) {
				chromosome[i] = Math.random() * Math.round(5.12 * (Math.random() - Math.random()));
			}
		}

		public double[] getChromosome() { //get a given Chromosome
			return chromosome;
		}

	}

		public double getFitness() { //obtain fitness of a given chromosome
			fitness = Assess.getTest1(chromosome);
			return fitness;
		}




class InitializePopulation {

	public int pop_size;
	public ArrayList<Chromosome> pop_list;

	public InitializePopulation(int pop_size) {
		pop_list = new ArrayList<Chromosome>();
		this.pop_size = pop_size;
	}

	public void make_population() {
		for (int i = 0; i < pop_size; i++) {
			pop_list.add(new Chromosome());
			this.pop_size = pop_size;
		}
	}

	public void calcFitness() {
		for (int i = 0; i < pop_size; i++) {
			pop_list.get(i).getFitness();
		}
	}

	Chromosome getFittest() { //find fittest and assign to new
		Chromosome fittest = new Chromosome();
		for (int i = 0; i < pop_size; i++) {
			if(fittest.getFitness() > pop_list.get(i).getFitness()) {
				fittest = pop_list.get(i);
			}
		} return fittest;
	}
	//1.select - 2.crossover - 3.mutate

	Chromosome tournSelect() {
		int k = 10;
		InitializePopulation tourn_pop = new InitializePopulation(k);
		Random rand = new Random();
		for(int i = 0; i < k; i++) {
			int rand_index = rand.nextInt(pop_size); //random number between 0 and pop size
			double selection[] = pop_list.get(rand_index);
			tourn_pop.pop_list.add(selection);
		}
		tourn_pop.calcFitness();
		return tourn_pop.getFittest();
	}

	public void modify() {
		ArrayList<Chromosome> new_pop_list = new ArrayList<>();
		for (i = 0; i < pop_size; i++) {
		Chromosome parentA = tournSelect();
		Chromosome parentB = torunSelect();
		Chromosome child = crossover(parentA, parentB);

		}
	}

	Chromosome crossover(Chromosome a, Chromosome b) {
		int crossoverPoint = 9; //middle point crossover
		Chromosome temp = new Chromosome();
		for (int i = 0; i < 20; i++) {
				while (crossoverPoint < i) {
						temp.getChromosome()[i] = a.getChromosome()[i];
				}
				temp.getChromosome()[i] = b.getChromosome()[i];
		}
		return temp;
	}



	class Ex1 {
		public Ex1() {

		}
		public static double[] run() {

			InitializePopulation population = new InitializePopulation(100);
			population.make_population();
			population.calcFitness();
			Chromosome fittest = population.getFittest();


			return fittest.getChromosome();
		}
	}
