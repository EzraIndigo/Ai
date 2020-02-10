
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

double[] tmp2;

boolean [] result_2 = new boolean [20];
		for(int i = 0; i < 20; i++) {
			result_2[i] = true;
		}

tmp2 = (Assess.getTest2(result_2));

System.out.println("weight = " + tmp2[0]);
System.out.println("utility = " + tmp2[1]);

	}

}
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
	//1.select - 2.crossover - 3.mutate

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
