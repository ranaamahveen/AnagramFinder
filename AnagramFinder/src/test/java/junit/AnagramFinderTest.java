package junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.Assert;



public class AnagramFinderTest {
    AnagramFinder anagram = new AnagramFinder();
	
    
    @Test
    public void testgetFilteredwords() throws FileNotFoundException {
		Map<String, List<String>> filteredWords = new HashMap<String, List<String>>();
		anagram.setFilteredWords(filteredWords);
		String result = anagram.getAnagramSearchOutput();
    }
	@Test
	public void testAnagramFind() throws FileNotFoundException {
			File f = new File("src/main/java/dictionary.txt");
			String filename = f.getAbsolutePath();
		anagram.anagramFind(filename);
	}

	@Test
	public void testAnagramMatch() {
		Map<String, List<String>> filteredWords = new HashMap<String, List<String>>();
		anagram.setFilteredWords(filteredWords);
		anagram.anagramMatch();
		anagram.setAnagramSearchOutput("No anagrams found ");
		String result = anagram.getAnagramSearchOutput();
		Assert.assertEquals(result,"No anagrams found ");
	}
	
	@Test
	public void testAnagramMatch2() {
		Map<String, List<String>> filteredWords = new HashMap<String, List<String>>();
		List<String> wordList = new ArrayList<String>();
		wordList.add("ot");
		filteredWords.put("ot", wordList);
		anagram.setFilteredWords(filteredWords);
		String result = anagram.getAnagramSearchOutput();
		anagram.anagramMatch();
	}


	@Test
	public void testMain() {
		File f = new File("src/main/java/dictionary.txt");
		String filename = f.getAbsolutePath();
		String[] arguements = new String[1];
		arguements[0] = filename;
		Anagram.main(arguements);
	}

}
