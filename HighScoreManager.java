//package ScoreTracker;

import java.util.*;
import java.io.*;

public class HighScoreManager{
	public static void main(String[] args){}
	//Arraylist with scores from Score class
	private ArrayList<Score> scores;
	
	//Where the highscores are saved
	private static final String SCORE_FILE = "scores.dat";
	
	//In and output stream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	
	public HighScoreManager(){
		//Here the arraylist is initialized
		scores = new ArrayList<Score>();
	}
	//Returns arraylist with sorted scores
	public ArrayList<Score> getScores(){
		loadScoreFile();
		sort();
		return scores;
	}

	public Score getMaxScore(){
		loadScoreFile();
		sort();
		return scores.get(0);
	}

	//Creates a new object "comparator" from CompareScores class, and sorts the arraylist 
	private void sort(){
		CompareScores comparator = new CompareScores();
		Collections.sort(scores, comparator);
	}
	//Adds scores to the scorefile.
	//The scores in the scorefile is uploaded to the scores arraylist, new scores are added, and the scorefile is updated.
	public void addScore(String name, int score){
		loadScoreFile();
		scores.add(new Score(name, score));
		updateScoreFile();
	}
	//Loads the arraylist in the scorefile, and puts it in the scores array list.
	//The try-catch will avoid that your program crashes if something goes wrong with upload
	public void loadScoreFile(){
		try{
			inputStream = new ObjectInputStream(new FileInputStream(SCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject();
		}
		catch (FileNotFoundException e){
			System.out.println("[Load] FNF Error: " + e.getMessage());
		}
		catch (IOException e){
			System.out.println("[Load] IO Error: " + e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println("[Load] CNF Error: " + e.getMessage());
		}
		finally {
			try{
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			}
			catch (IOException e){
			System.out.println("[Load] IO Error: " + e.getMessage());
			}
		}
	}
	//Uploads the arraylist in the scorefile, and puts it back in the scorefile
	//The try-catch will avoid that your program crashes if something goes wrong with upload
	public void updateScoreFile(){
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(SCORE_FILE));
			outputStream.writeObject(scores);
		}
		catch (FileNotFoundException e){
			System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
		}
		catch (IOException e){
			System.out.println("[Update] IO Error: " + e.getMessage());
		}
		finally{
			try{
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			}
			catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}
	//How we display out highscores
	public String getHighscoreString() {
		String highscoreString = "";
		int max = 10;
		
		ArrayList<Score> scores;
		scores = getScores();
		
		int i = 0;
		int x = scores.size();
		if(x > max){
			x = max;
		}
		while (i < x){
			highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
			i++;
		}
		return highscoreString;
	}

	public Boolean highScoreEmpty() {
		loadScoreFile();
		if(scores.size() == 0)
			return false;
		return true;
	}
}