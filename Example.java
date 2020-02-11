
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

class Ex2Chromosome {
	private boolean[] sol2_chromosome;
	private double sol2_fitness;

		public Ex2Chromosome() {
			sol2_fitness = 5000000;
			sol2_chromosome = new boolean[100];
			make_sol2_chromosome();
		}

		public void make_sol2_chromosome() {
			for(int i=0; i < sol2_chromosome.length; i++) {
				sol2_chromosome[i] = (Math.random() > 0.5);
			}
		}

		public boolean[] get_sol2_chromosome() {
			return sol2_chromosome;
		}

		public double get_sol2_fitness() {
			return sol2_fitness;
		}

		public void calc_sol2_fitness() {
			sol2_fitness = Assess.getTest2(sol2_chromosome);
		}

		public void mutation(double prob) {
			for (int i=0; i < sol2_chromosome.length; i++){
				if(Math.random() < prob) {
					if(sol2_chromosome[i] = true) {
						sol2_chromosome[i] = false;
					} else {
						sol2_chromosome[i] = true;
					}
				}
			}
		}
}

class Ex2InitialPopulation {
	private int sol2_pop_size;
	private ArrayList<Ex2Chromosome> sol2_population;

	public Ex2InitialPopulation(int sol2_pop_size) {
	sol2_population = new ArrayList<>();
	this.sol2_pop_size = sol2_pop_size;
	}

	public void sol2_make_population() {
		for(int i = 0; i < sol2_pop_size; i++) {
			sol2_population.add(new sol2_chromosome());
		}
	}
	public void calc_sol2_fitness() {
		for (int i=0; i< sol2_pop_size; i++) {
			sol2_population.get(i).calc_sol2_fitness();
		}
	}
	public Ex2Chromosome sol2_get_fittest() {
		Ex2Chromosome sol2_fittest = new Ex2Chromosome();
		for(int i=0; i < sol2_pop_size; i++) {
			if(sol2_fittest.get_sol2_fitness() > sol2_population.get(i).get_sol2_fitness()) {
				sol2_fittest = sol2_population.get(i);
			}
		} return sol2_fittest;
	}
	public Ex2Chromosome sol2_selection() {
		int k = 3;
		Ex2InitialPopulation new_sol2_population = new Ex2InitialPopulation(k);
		Random rand = new Random();
		for(int i = 0; i < k; i++) {
			int index = rand.nextInt(sol2_pop_size);
			new_sol2_population.sol2_population.add(sol2_population.get(index));
		}
		new_sol2_population.calc_sol2_fitness();
		return new_sol2_population.sol2_get_fittest();
	}
	public void sol2_modify(double probability) {
		ArrayList<Ex2Chromosome> sol2_populationList = new ArrayList<>();
		for(int i = 0; i < sol2_pop_size; i++) {
			Ex2Chromosome parentA = sol2_selection();
			Ex2Chromosome parentB = sol2_selection();
			Ex2Chromosome child = sol2_crossover(parentA, parentB);
			child.mutation(probability);
			new_sol2_population.add(child);
		}
		sol2_population.clear();
		sol2_population.addAll(new_sol2_population);
	}

	public Ex2Chromosome sol2_crossover(Ex2Chromosome a, Ex2Chromosome b) {
		int crossPoint1 = new Random().nextInt(49);
		int crossPoint2 = new Random().nextInt(98 + 1) + 49;
		Ex2Chromosome temp = new Ex2Chromosome();
		for (int i = 0; i < 50; i++) {
			if (crossPoint1 < i) {
				temp.get_sol2_chromosome()[i] = a.get_sol2_chromosome()[i];
			} else {
				temp.get_sol2_chromosome()[i] = b.get_sol2_chromosome()[i];
			}
		}
		for (int i = 49; i < 99; i++) {
			if (crossPoint2 < i) {
				temp.get_sol2_chromosome()[i] = a.get_sol2_chromosome()[i];
			} else {
				temp.get_sol2_chromosome()[i] = b.get_sol2_chromosome()[i];
			}
		}
		return temp;
	}

}

class Ex2 {

}


class Ex1 {

		public static double[] run() {
			boolean flag = false;
			double variance = 0.5;
			double probability = 0.025;
			InitializePopulation population = new InitializePopulation(500);

			int generation = 0;
			population.make_population();

		//	tempList.add(fittest);
		/**
			InitializePopulation population2 = new InitializePopulation(500);
			population2.make_population();
			Chromosome secondFittest = population2.getFittest();
			Chromosome tempy2 = new Chromosome();
			int generation2 = 0;


*/
			Chromosome fittest = population.getFittest();
			Chromosome tempy = new Chromosome();

			double x = 0;
			while(fittest.getFitness() != x) {
				Random dblRd = new Random();
				if (tempy.getFitness() == fittest.getFitness() && generation > 1000) {
					flag = true;
					break;
				}
				if (fittest.getFitness() < x ) {
				variance = dblRd.nextDouble(); //adding some varience to the mutation
				}
				population.modify(probability, variance);
				population.calculate_fitness();
				tempy = fittest;
				fittest = population.getFittest();
				generation++;
			}
			/**

			if (flag == true) { //second population experimentation

				while(secondFittest.getFitness() != x) {
					Random dblRd = new Random();
					variance = dblRd.nextDouble();
					if(tempy2.getFitness() == secondFittest.getFitness() && generation2 > 500) {
						flag = false;
						break;
					}
				population2.modify(probability, variance);
				population2.calculate_fitness();
				tempy2 = secondFittest;
				generation2++;
				}


			}

			if(fittest.getFitness() < secondFittest.getFitness()) {
				return fittest.getChromosome();
			}
			else {
				return secondFittest.getChromosome();
			}
*/
return fittest.getChromosome();
		}
	}

	//===================GA classes ===========================

	class Chromosome {

		private double[] chromosome;
		private double fitness;
		public int chromoLength = 20;

		public Chromosome() {
			fitness = 500000;
			chromosome = new double[chromoLength];
			makeChromosome();
		}
		public double getFitness() { //obtain fitness of a given chromosome
			return fitness;
		}
		public void makeChromosome() { //building set of 20 doubles
			for (int i = 0; i < chromoLength; i++) {
				chromosome[i] = Math.random() * Math.round(5.12 * (Math.random() - Math.random()));
			}
		}

		public double[] getChromosome() { //get a given Chromosome
			return chromosome;
		}



		public void mutation(double probability, double varience){
			for (int i = 0; i < 20; i++) {
					if (Math.random() < probability){
							chromosome[i] = chromosome[i] - Math.random() + varience; //chosen a random number to minus from the gene and + 0.5 on
					}
			}
	}

public void calculate_fitness() {
	fitness = Assess.getTest1(chromosome);
	}

}



class InitializePopulation {

	private int pop_size;
	private ArrayList<Chromosome> pop_list;

	public InitializePopulation(int pop_size) {
		pop_list = new ArrayList<>();
		this.pop_size = pop_size;
	}

	public void make_population() {
		for (int i = 0; i < pop_size; i++) {
			pop_list.add(new Chromosome());
		}
	}

	public void calculate_fitness() {
		for (int i = 0; i < pop_size; i++) {
			pop_list.get(i).calculate_fitness();
		}
	}

	public Chromosome getFittest() { //find fittest and assign to new
		Chromosome fittest = new Chromosome();
		for (int i = 0; i < pop_size; i++) {
			if(fittest.getFitness() > pop_list.get(i).getFitness()) {
				fittest = pop_list.get(i);
			}
		} return fittest;
	}

public	Chromosome tournSelect() {
		int k = 3;
		InitializePopulation tourn_pop = new InitializePopulation(k);
		Random rand = new Random();
		for(int i = 0; i < k; i++) {
			int rand_index = rand.nextInt(pop_size); //random number between 0 and pop size
			tourn_pop.pop_list.add(pop_list.get(rand_index));
		}
		tourn_pop.calculate_fitness();
		return tourn_pop.getFittest();
	}

	public void modify(double probability, double variance) {

		ArrayList<Chromosome> new_pop_list = new ArrayList<>();
		//Random ran = new Random();
		for (int i = 0; i < pop_size; i++) {
		Chromosome parentA = tournSelect();
		Chromosome parentB = tournSelect();
		Chromosome child = crossover(parentA, parentB);
	//	double nxtRan = ran.nextDouble();
		child.mutation(probability, variance);
		new_pop_list.add(child);

		}
		pop_list.clear();
//		for (int i = 0; i < new_pop_list.size(); i++) {
	//		pop_list.add(new_pop_list.get(i));
		//}
		pop_list.addAll(new_pop_list);

	}


	public Chromosome crossover(Chromosome a, Chromosome b) {
		int crossoverPoint = new Random().nextInt(20); //random crossover
		Chromosome temp = new Chromosome();
		for (int i = 0; i < 20; i++) {
				if (crossoverPoint < i) {
						temp.getChromosome()[i] = a.getChromosome()[i];
				} else {
				temp.getChromosome()[i] = b.getChromosome()[i];
			}
		}
		return temp;
	}

}
