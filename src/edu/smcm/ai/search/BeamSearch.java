package edu.smcm.ai.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <P>
 * An implementation of the Beam Search.
 * </P>
 *
 * <P>
 * There is no pseudocode for the local beam search in "Artificial Intelligence:
 * A Modern Approach" (Third Edition) by Russell and Norvig. However, it is well
 * described on pp. 125-126. The description is as follows:
 * </P>
 * 
 * <BLOCKQUOTE> The <B>local beam search</B> algorithm keeps track of <I>k</I>
 * states rather than just one. It begins with <I>k</I> randomly generated
 * states. At each step, all the successors of all <I>k</I> states are
 * generated. If any one is a goal, the algorithm halts. Otherwise, it selects
 * the <I>k</I> best from the complete list and repeats. </BLOCKQUOTE>
 */
public class BeamSearch extends LocalSearch {

	/**
	 * The length of the beam.
	 */
	private int beam_length;

	/**
	 * A constructor.
	 * 
	 * @param beam_length
	 *            length of the beam
	 */
	public BeamSearch(int beam_length) {
		this.beam_length = beam_length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.LocalSearch#search(edu.smcm.ai.search.State)
	 */
	@Override
	public State search(State start) {
//		return start;
		
		states_evaluated = 0;
		ArrayList<LocalSearchState> states = new ArrayList<LocalSearchState>(beam_length);

		for (int i = 0; i < beam_length; i++){
			states.add(((LocalSearchState) start).random());
		}
		Collections.sort(states);
		Collections.reverse(states);
		ArrayList<LocalSearchState> successors = new ArrayList<LocalSearchState>();
		do {
			successors.clear();
			for (LocalSearchState s : states){
				for (Action a : s.actions()){
					LocalSearchState temp = s.result(a);
					successors.add(temp);
					states_evaluated++;
				}
			}
			
			Collections.sort(successors);
			Collections.reverse(successors);
			
			//System.out.println("successor: " + successors.get(0).utility() + ", current: " + states.get(0).utility());
			if (successors.get(0).utility() < states.get(0).utility()){
				return states.get(0);
			}
			
			states.clear();
			
			for (int i = 0; i < beam_length; i++){
				states.add(successors.get(i));
			}
			
		} while (true);
	}
}
