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

public class TestLocal {
	static long lStartTime;
	static long lEndTime;
	static long output;
	private Map<String, List<String>> filteredWords = null;
	private String _dictionary = null;

	public Map<String, List<String>> getFilteredWords() throws FileNotFoundException {
		if (filteredWords == null) {
			loadDictionary();
		}
		return filteredWords;
	}

	public void setFilteredWords(Map<String, List<String>> filteredWords) {
		this.filteredWords = filteredWords;
	}

	public TestLocal(String dictionary, String scenario) throws FileNotFoundException {
		try {
			_dictionary = dictionary;
			if("scenario2".equalsIgnoreCase(scenario)) {
				loadDictionary();
				anagramMatch();
			}else if("scenario1".equalsIgnoreCase(scenario)) {
				anagramMatch();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}

	}

	private void loadDictionary() throws FileNotFoundException {
		Scanner s = null;
		try {
			System.out.println("Welcome to the Anagram Finder\n" + "-----------------------------");
			s = new Scanner(new File(_dictionary));
			lEndTime = new Date().getTime();
			output = lEndTime - lStartTime;
			System.out.println("Dictionary loaded in " + output + " ms");

			// Reading the word list from Dictionary.txt
			List<String> words = new ArrayList<String>();
			String pattern = null;
			while (s.hasNext()) {
				pattern = s.next();
				if (pattern == null || "".equalsIgnoreCase(pattern.trim())) {
					continue;
				}
				words.add(pattern);
			}

			Map<String, List<String>> filteredWords = new HashMap<String, List<String>>();

			// mapping all the anagrams for each word and storing using hashmap
			for (String str : words) {
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	private void anagramMatch() {
		Scanner input = null;
		try {
			// Reading user input and printing out the anagrams if there are any
			System.out.println("Enter the word");
			input = new Scanner(System.in);
			String str = input.next();
			String st = str;
			while (!st.equalsIgnoreCase("exit")) {
				lStartTime = new Date().getTime();
				int count = 0;
				char[] key = str.toCharArray();
				Arrays.sort(key);
				str = new String(key).toLowerCase();
				filteredWords = getFilteredWords();
				if (!filteredWords.containsKey(str)) {
					System.out.print("word not found in the Dictionary");
					continue;
				}
				List<String> anagramMatchList = filteredWords.get(str);
				count = anagramMatchList.size();

				lEndTime = new Date().getTime();
				output = lEndTime - lStartTime;
				System.out.print(count + " Anagrams found for " + st + " in " + output + " ms \n");
				StringBuilder sb = new StringBuilder();
				for (String p : filteredWords.get(str)) {
					sb.append(p).append(", ");
				}
				System.out.println(sb.toString());

				System.out.println("\nEnter the word");
				input = new Scanner(System.in);
				str = input.next();
				st = str;
			}
		} catch (Exception e) {

		} finally {
			input.close();
		}
	}

	public static void main(String[] args) {
		lStartTime = new Date().getTime();
		try {
			String dic = args[0];
			
			new TestLocal(dic, "scenario2");
			
			new TestLocal(dic, "scenario1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
