import javax.swing.JOptionPane;

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
//* * // Implements the logic of the ship,captain,crew game
//* *
//* Date Created 10/08/2016
//* *
//* Saved in: PP02*
//* *
//*********************************************************************

public class RunDice {

	/**
	 * @param args
	 */
	
	private static boolean six;
	private static boolean five;
	private static boolean four;
	private static boolean gameOn = true;       //True = play
	private static boolean takeRisk = false;
	private static Dice[] diceArray;
	
	private static int numFound = 0;     //654 found , how many? 
	private static int rollNum = 0;   //Which  Round?  
	private static int numRolls = 5;      // Rolls /Round
	private static int gameScore = 0;  
	private static int insertIndex = 0; 
	
//	static int[][] testGame = new int[][]{
//		{6,5,4,2,3},
//		{6,5,0,0,0},
//		{2,4,0,0,0}
//	};
//		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//Initialize
		initDie();
		
	//Call roll for 1st round	
		roll();
		
	//Call calcScore for 1st round
		calculateScore();
	
	//check if gameOn
		if(gameOn){
			//Call roll for 2nd round
			 roll();
			//Call calcScore for 2nd round
			 calculateScore();
		}
			
	//check if gameOn	
		if(gameOn){
	//Call roll for 3rd round
			 roll();
	//Call FinalScore	
			 finalScore();
		}
	}

	
	public static void initDie(){
		
		// Create Dice Instances to save the rolled dices
		diceArray = new Dice[5];
		
		for(int i = 0;i < diceArray.length;i++){
			diceArray[i] = new Dice();
		}
	}
	
	public static void roll(){
		
		int throwDice = 0;        // random Dice thrown
		int indexArray = 0;       // index where the dice is placed in the array returned after insertion
		insertIndex = 0;          //reset before each round to start from the first element
		
		resetArray(); // set all the numbers to 0 for a new round
		
		//increment the round count
		  rollNum ++;
		//Check if the last round = 3
		  if(rollNum == 3){
			  gameOn = false; 
			  
		  }
		  
		
		for(int rollNo = 0;rollNo < numRolls;rollNo++){
			
			throwDice =(int)(Math.random() * (6 - 1 + 1) + 1);
			//throwDice = testGame[rollNum-1][rollNo];
			
			//returns the index where the dice is inserted in the array
			indexArray = addDice(throwDice);
			
			//To set the flag if a 6, 5 ,4 is found
			if(throwDice == 6 && six == false && five == false && four == false && numFound <= 3 && takeRisk == false ){
				six = true;
				numFound ++;
				diceArray[indexArray-1].setKeep(true);
				
			}
			else if(throwDice == 5 && six == true && five == false && four == false && numFound <= 3 && takeRisk == false){
				five = true;
				numFound ++;
				diceArray[indexArray-1].setKeep(true);
			}
			else if(throwDice == 4 && six == true && five == true && four == false && numFound <= 3 && takeRisk == false){
				four = true;
				numFound ++;
				diceArray[indexArray-1].setKeep(true);
			}
								
		}//End for
		
		
		//update the number of Dice throws for next round
		numRolls = 5 - numFound;
		//Display after each round
		printArray();
	}
	
	public static int calculateScore(){
		
		int choice=0;
		
		if(!complete() && !gameOn){     //not found and end the game
			gameScore = 0;
			JOptionPane.showMessageDialog(null, "You have Lost the game!. Your Score in round " + rollNum+ " is  " + gameScore);
			return gameScore;
		}
		else if(complete() && !gameOn){ //found and end the game
			gameScore = runScore();
			JOptionPane.showMessageDialog(null, "Your Final Score in round " + rollNum+ " is " + gameScore);
			return gameScore;
		}
		else if(!complete() && gameOn){//not found and continue the game
			gameScore = runScore();
			JOptionPane.showMessageDialog(null, "You still have a chance to win!. Your Score in round " + rollNum + " is 0" );
			return gameScore;
		}
		else if(complete() && gameOn){// found and continue the game
			
			gameScore = runScore();
			JOptionPane.showMessageDialog(null, "Your Score in round " + rollNum + " is " + gameScore);
			choice = JOptionPane.showConfirmDialog(null, "Do you want to play for a better Score than " + gameScore +  "?","Play or Stop?", 0);
			
			if(choice == 0){
				//resetGame();
				gameScore = 0;
				takeRisk = true;
				//JOptionPane.showMessageDialog(null, "Your Score in round " + rollNum+ " is " + gameScore);
				return gameScore;
				
			}
			else if(choice != 0){
				gameOn = false;
				gameScore = runScore();
				JOptionPane.showMessageDialog(null, "You have won!. Your Final Score in round " + rollNum+ " is " + gameScore);
				return gameScore;
				
			}
		}
		
		return 0;
		
	}
	
    public static void finalScore(){
		
    	//stop the game
    	gameOn = false;
    	
    	calculateScore();
	}
    
    public static int addDice(int numDice){ // Add the thrown dice in the array
    	
    	
    //	int i = 0;
    	
    	//loop through to find non kept values and add
    //	for(i = 0;i < diceArray.length;i++){
    	//	if(!diceArray[insertIndex].getKeep()){
    			diceArray[insertIndex].setNumber(numDice);
    			insertIndex++;    //next place which is not a keep place
    			
    		//}
    	//}
    	
    	return insertIndex;
    	
    	
    }
	
    public static boolean complete(){
    	
    	//to check if 6,5,4 were found in sequence
    	if(six && five && four)
    		return true;
    	else
    		return false;
    			
    	
    }
	
    public static int runScore(){
    	    	
    	int finScore = 0;
    	
    	//loop through to find non kept values and add to get the score for that roll
    	for(int i = 0;i < diceArray.length;i++){
    		if(!diceArray[i].getKeep()){
    			finScore = finScore + diceArray[i].getNumber();    			
    		}
    	}
    	
    	return finScore;
    }
    
    public static void resetGame(){
    	
    	//reset flags
    	six =false;
    	five =false;
    	four =false;
    	gameOn = true;       //True = play
    	
    	//reset counters
    	numFound = 0;     //654 found , how many?     	
    	numRolls = 5;      // Rolls /Round
    	gameScore = 0;      
    	
    	
    	
    	
    }
    
    public static void resetArray(){
    	
    	
    	//clear the array for the next round
    	for(int i = 0;i < diceArray.length;i++){    		
    			diceArray[i].setNumber(0);
    			diceArray[i].setKeep(false);  
    	}
    }

    public static void printArray(){
    	
    	//Display the rolled dices for each roll
    	String msg="";
    	
    	for(int i = 0;i < diceArray.length;i++){
    		if(diceArray[i].getNumber() != 0 )
    		msg = msg + diceArray[i].getNumber() + " ";
    			//System.out.print(diceArray[i].getNumber() + "@" + diceArray[i].getKeep() + "\t");
    			  
    	}
    	JOptionPane.showMessageDialog(null, msg);
    }
    
}
