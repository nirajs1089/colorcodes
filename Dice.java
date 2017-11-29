/**
 * 
 */

/**
 * @author Nirajnitin.Shah15
 *
 */
//*********************************************************************
//* *
//* CIS611 Fall Session 2016*
//* *
//* Programming Project PP02 *
//* *
//* Class Description *
//* * // creates the structure of a Dice for the RunDice Class to use it.
//* *
//* Date Created 10/08/2016
//* *
//* Saved in: PP02*
//* *
//*********************************************************************
import java.util.Random;

public class Dice {

	/**
	 * @param args
	 */
	
	
	//declaration
	private int number;
	private boolean keep;
	
		
	public void Dice(){
		
		//initialize to the default for the variables
					 number=0;
					 keep=false;
	}

	public boolean getKeep(){
		return keep;
	}

	public void setKeep(boolean setStatus){ // if you get 6,5,4
		this.keep = setStatus;
	}

	public int getNumber(){ // Get the rolled dice
		return number;
	}

	public void setNumber(int setNumber){
		number = setNumber;
	}

	
}
