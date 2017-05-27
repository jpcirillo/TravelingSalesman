package edu.smcm.ai.search;

import java.util.List;
import java.util.Random;

//import edu.smcm.ai.travellingsalesman.TravellingSalesmanState;

/**
 * Implemementation of the Simulating Annealing Search. See "Artificial
 * Intelligence: A Modern Approach" (Third Edition) by Russell and Norvig p.
 * 126.
 */
public class SimulatedAnnealing extends LocalSearch {

	/**
	 * A random number generator.
	 */
	private static final Random oracle;

	/**
	 * The cooling schedule.
	 */
	private Schedule schedule;

	static {
		// TODO Remove seed for production code
		oracle = new Random(938456);
	}

	/**
	 * A constructor with a cooling schedule.
	 * 
	 * @param schedule cooling schedule
	 */
	public SimulatedAnnealing(Schedule schedule) {
		this.schedule = schedule;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.LocalSearch#search(edu.smcm.ai.search.State)
	 */
	@Override
	public State search(State start) {
		states_evaluated = 0;
		LocalSearchState current = (LocalSearchState) start;
		LocalSearchState next;
		double T;
		double deltaE;
		for (int t = 0; t < Integer.MAX_VALUE; t++){
			states_evaluated++;
			T = schedule.temperature(t);
			if (T == 0) return current;
			List<Action> actions = current.actions();
			next = current.result(actions.get(oracle.nextInt(actions.size())));
			deltaE = next.utility()-current.utility();
			if (deltaE > 0) current = next;
			else current = (oracle.nextDouble() < Math.exp(deltaE/T)) ? next : current; 
		}
		return null;
	}
}
