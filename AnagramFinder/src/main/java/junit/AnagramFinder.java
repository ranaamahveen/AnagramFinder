package junit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AnagramFinder {
	private static long lStartTime;
	private long lEndTime;
	private long output;
	private Map<String, List<String>> filteredWords = null;
	private String dict = null;
	private String anagramSearchOutput = null;

	public String getAnagramSearchOutput() {
		return anagramSearchOutput;
	}
	public void setAnagramSearchOutput(String anagramSearchOutput) {
		this.anagramSearchOutput = anagramSearchOutput;
	}
	
	public Map<String, List<String>> getFilteredWords() throws FileNotFoundException {
		if (filteredWords == null) {
			loadDictionary();
		}
		return filteredWords;
	}
	public void setFilteredWords(Map<String, List<String>> filteredWords) {
		this.filteredWords = filteredWords;
	}
	public void anagramFind(String dictionary) throws FileNotFoundException {
		
		try {
			dict = dictionary;
			loadDictionary();
			anagramMatch();
		} catch(Exception e) {
			System.out.println("Exception found: "+e);
		}finally {
			System.exit(0);
		}	
	}
	public void loadDictionary() {
		Scanner s = null;
		try {
			System.out.println("Welcome to the Anagram Finder\n"+"-----------------------------");
			s = new Scanner(new File(dict));
			lEndTime = System.nanoTime();
			output =  TimeUnit.NANOSECONDS.toMicros((lEndTime - lStartTime));
			System.out.println("Dictionary loaded in "+output+"μs");
			
			Map<String, List<String>> filteredWords = new HashMap<String, List<String>>();
			//mapping all the anagrams for each word and storing using hashmap
			while (s.hasNext()) {
				String str = s.next();
			    char[] temp = str.toCharArray();
			    Arrays.sort(temp);
			    String key = new String(temp).toLowerCase();
			    if (filteredWords.get(key) != null) {
			    	filteredWords.get(key).add(str.toLowerCase());
			    } else {
			        ArrayList<String> anagramList = new ArrayList<String>();
			        anagramList.add(str);
			        filteredWords.put(key, anagramList);
			    }
			}
			setFilteredWords(filteredWords);
		} catch (FileNotFoundException e) {
			System.out.println("Exception found while loading: "+e);
		} finally {
			s.close();
		}
	}
	public void anagramMatch() {
		Scanner input = null;
		try {
			System.out.println("Enter the word");
			//Reading user input and printing out the anagrams is there are any
			input = new Scanner(System.in);
			String str = input.next();
			String st = str;
			lStartTime = System.nanoTime();
			while(!st.equalsIgnoreCase("exit")) {
				int count = 0;
			    String anagramString = "";
			    char[] key = str.toCharArray();
			    Arrays.sort(key);
			    str = new String(key).toLowerCase();
			    filteredWords = getFilteredWords();
			    if (!filteredWords.containsKey(str)) {
			    	lEndTime = System.nanoTime();
			    	output =  TimeUnit.NANOSECONDS.toMicros((lEndTime - lStartTime));
			    	setAnagramSearchOutput("No anagrams found ");
			        System.out.print("No anagrams found for " +st+" in "+output+"μs \n");
			    } else if (filteredWords.get(str).size() != 0) {
			        for (String p : filteredWords.get(str)) {
			        	if(count!=0) {
			        		anagramString += (", ");
			        	}
			        	count++;
			        	anagramString += p;
			        }
			        lEndTime = System.nanoTime();
			        output =  TimeUnit.NANOSECONDS.toMicros((lEndTime - lStartTime));
			        setAnagramSearchOutput( "Anagrams found in dictionary");
			        System.out.print(count+" Anagrams found for "+st+" in "+output+"μs \n");
			        System.out.println(anagramString);
			    }
				System.out.println("\nEnter the word");
				str = input.next();
			    st = str;
			    lStartTime = System.nanoTime();
			}
		} catch (Exception e) {
			System.out.println("File not found while matching: " +e);
		} finally {
			input.close();
		}
	}
    public static void main(String[] args){
    	lStartTime = System.nanoTime();
    	try {
    		String dict = args[0];
    		AnagramFinder anagram = new AnagramFinder();
    		anagram.anagramFind(dict);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "+e);
		} finally {
			System.exit(0);
		}
    }
    
}