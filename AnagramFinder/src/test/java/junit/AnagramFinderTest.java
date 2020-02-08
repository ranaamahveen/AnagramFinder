package junit;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import junit.framework.Assert;



public class AnagramFinderTest {
    private AnagramFinder ang =  new AnagramFinder();
	@SuppressWarnings({ "null", "deprecation" })
	@Test
	public void testGetFilteredWords() throws FileNotFoundException {
		
		//Assert.assertEquals(true, ang.getFilteredWords());
		Map<String, List<String>> filteredWords = new HashMap<String, List<String>>();
		List<String> wordList = new ArrayList<String>();
		wordList.add("word");
		filteredWords.put("dorw", wordList);
		ang.getFilteredWords();
		Assert.assertSame(filteredWords,ang.getFilteredWords());
	}

	@Test
	public void testSetFilteredWords() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<String, List<String>> filteredWords = new HashMap<String, List<String>>();
		List<String> wordList = new ArrayList<String>();
		wordList.add("word");
		filteredWords.put("dorw", wordList);
		ang.setFilteredWords(filteredWords);
		
		Assert.assertEquals();
	}

	@Test
	public void testAnagramFind() {
	}

	@Test
	public void testAnagramMatch() {
	}

	@Test
	public void testMain() {
		
	}

}
