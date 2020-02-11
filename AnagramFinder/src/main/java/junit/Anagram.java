package junit;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Anagram {
	private static long lStartTime;
	private long lEndTime;
	private long output;
	private String anagramSearchOutput = null;
	
	
	public String getAnagramSearchOutput() {
		return anagramSearchOutput;
	}
	public void setAnagramSearchOutput(String anagramSearchOutput) {
		this.anagramSearchOutput = anagramSearchOutput;
	}
	public void matchWords(String dict) throws FileNotFoundException {
		try {
			System.out.println("Welcome to the Anagram Finder\n"+"-----------------------------");
	    	System.out.println("\nEnter the word");
			
	        //Reading user input and printing out the anagrams is there are any
			Scanner input = new Scanner(System.in);
			anagramFind(dict, input);
		} catch (Exception e) {
			System.out.println("Exception found "+ e);
		} 
	}
	/**
	 * @param dict
	 * @throws FileNotFoundException
	 */
	public void anagramFind(String dict, Scanner input) throws FileNotFoundException {
		Scanner s = null;
		try {
			String inputStr = input.next();
			String st = "";
			st = inputStr;
			while((inputStr != null && !inputStr.isEmpty()) && !inputStr.equalsIgnoreCase("exit")) {
				lStartTime = System.nanoTime();
			    char[] key = inputStr.toCharArray();
			    Arrays.sort(key);
			    inputStr = new String(key).toLowerCase();
			    int count = 0;
			    String anagramString = "";
			    s = new Scanner(new File(dict));
			    while(s.hasNext()){
			    	String eachWord = s.next();
			    	 char[] charEach = eachWord.toCharArray();
			         Arrays.sort(charEach);
			         String wordFile = new String(charEach).toLowerCase();
			         if(wordFile.equals(inputStr)) {
			        	 if(count!=0) {
			        		 anagramString += (", ");
			        	 }
			        	 count++;
			        	 anagramString +=eachWord;
			         }
			    }
			    lEndTime = System.nanoTime();
			    output =  TimeUnit.NANOSECONDS.toMillis((lEndTime - lStartTime));
			    anagramResult(st, count, anagramString);
			    System.out.println("\nEnter the word");
			    inputStr = input.next();
			    st = inputStr;
			    s.close();
			}
		}catch(Exception e) {
			System.out.println("anagramFind error "+e);
		}finally {
			
			input.close();
		}
		
	}
	/**
	 * @param st
	 * @param count
	 * @param anagramString
	 */
	public void anagramResult(String st, int count, String anagramString) {
		if(count==0) {
			setAnagramSearchOutput("No anagrams found");
			System.out.println("No anagrams found for "+st+" in " +output+"ms");
		}
		else if(count>0) {
			System.out.println(count+" Anagrams found for "+st+" in "+output+"ms");
			setAnagramSearchOutput( "Anagrams found in dictionary");
			System.out.println(anagramString);
		}
	}
    public static void main(String[] args){
    	lStartTime = System.nanoTime();
    	try {
    		String dict = args[0];
    		Anagram ang = new Anagram();
    		ang.matchWords(dict);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "+e);
			System.exit(0);
		}
    }
    
}
