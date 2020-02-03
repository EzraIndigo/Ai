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


		//System.out.println("These are the instructions of how to use the problem library.  Please make sure you follow the instructions carefully.");


		//System.out.println("For the first problem, you need to use Assess.getTest1(double, double).");

		//An example solution consists of an array  of doubles of size 50
		//Allowed values are between -5 and +5 (this is not actually checked, but no point in going beyond these values)
		//Lower fitness is better. The optimal fitness is 0 (i.e. no negative fitness values).

		//generate a sample solution like so:


		//get the fitness for a candidate solution in problem 1 like so
		//	 fit = Assess.getTest1(sol1);
//
//			for(int j = 0; j < solSize; j++){
//				System.out.print(sol1[j] + " ");
//			}
//			System.out.println("The fitness of your example Solution is: "+ fit);


//			for (int j = 0; j < solSize; j++) {
//				if(sol1[j] < 1) {
//					sol1[j] = -1;
//				}
//			}


//			fit = Assess.getTest1(sol1);

//			for(int j = 0; j < solSize; j++){
//				System.out.print(sol1[j] + " ");
//			}
//			System.out.println("The fitness of your example Solution is: "+ fit);


//			for (int j = 0; j < solSize; j++) {
//				sol1[j] = sol1[j] / 2;
//				}
//			for(int j = 0; j < solSize; j++){
//				System.out.print(sol1[j] + " ");
//			}
//			fit = Assess.getTest1(sol1);

//			System.out.println("The fitness of your example Solution is: "+ fit);
//
//			for (int j = 0; j < solSize; j++) {
//				sol1[j] = sol1[j] - 0.02;
//			}

//			for(int j = 0; j < solSize; j++){
//				System.out.print(sol1[j] + " ");
//			}
//			fit = Assess.getTest1(sol1);

//			System.out.println("The fitness of your example Solution is: "+ fit);

//			for (int j = 0; j < solSize; j++) {
//				sol1[j] = sol1[j] - 0.010101;
//			}

//			for(int j = 0; j < solSize; j++){
//				System.out.print(sol1[j] + " ");
//			}
//			fit = Assess.getTest1(sol1);

//			System.out.println("The fitness of your example Solution is: "+ fit);
//			System.out.println(" ");
//			System.out.println(" ");
//			System.out.println("Now let us turn to the second problem:");
//			System.out.println("A sample solution in this case is a boolean array of size 100.");
//			System.out.println("I now create a random sample solution and get the weight and utility:");


		//Creating a sample solution for the second problem
		//The higher the fitness, the better, but be careful of  the weight constraint!
		//	boolean[] sol2 = new boolean[100];
		//	for(int i=0;i< sol2.length; i++){
		//		sol2[i]= (Math.random()>0.5);
		//	}

		//Now checking the fitness of the candidate solution
		//	double[] tmp =(Assess.getTest2(sol2));

		//The index 0 of tmp gives the weight. Index 1 gives the utility
//			System.out.println("The weight is: " + tmp[0]);
//			System.out.println("The utility is: " + tmp[1]);

		//Once completed, your code must submit the results you generated, including your name and login:
		//Use and adapt  the function below:
		//	Assess.checkIn(name,login,sol1,sol2);

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

		public double getFitness() {
			fitness = Assess.getTest1(chromosome);
			return fitness;
		}

		public double[] getChromosome() {
			return chromosome;
		}

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

