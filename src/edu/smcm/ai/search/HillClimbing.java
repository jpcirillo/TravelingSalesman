package edu.smcm.ai.search;

import java.util.*;

/**
 * Implementation of the Hill Climbing Search. See "Artificial Intelligence: A
 * Modern Approach" (Third Edition) by Russell and Norvig p. 122.
 */
public class HillClimbing extends LocalSearch {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.LocalSearch#search(edu.smcm.ai.search.State)
	 */
	@Override
	public State search(State start) {
		states_evaluated = 0;
		// TODO Implement Hill Climbing Search
		
		LocalSearchState current = (LocalSearchState) start;
		LocalSearchState neighbor;
		LocalSearchState temp;
		
		do {

			states_evaluated++;
			
			List<Action> actions = current.actions();
			
			neighbor = current.result(actions.get(0));
			
			for (Action thisOne : actions) {
				
				temp = current.result(thisOne);
				
				if (temp.utility() > neighbor.utility())
					neighbor = temp;
				
			}
			
			if (neighbor.utility() <= current.utility())
				return current;
			
			current = neighbor;
			
		} while (true);
		
	}
}