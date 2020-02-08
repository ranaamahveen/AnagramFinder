package junit;


import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class AnagramTest {
	Anagram angrm = new Anagram();
	@Test
	public void testMatchWords() throws FileNotFoundException {
		File f = new File("src/main/java/dictionary.txt");
		String filename = f.getAbsolutePath();
		String[] arguements = new String[1];
		arguements[0] = filename;
		Scanner sc = new Scanner(System.in);
		String wrd = sc.next();
		System.out.println("here");
		System.out.println("word: "+wrd);
		angrm.matchWords(arguements[0]);
	}

	@Test
	public void testMain() {
		File f = new File("src/main/java/dictionary.txt");
		String filename = f.getAbsolutePath();
		String[] arguements = new String[1];
		arguements[0] = filename;
		Anagram.main(arguements);
	}
	
	@Test
	public void testAnagramFind() throws FileNotFoundException {
		File f = new File("src/main/java/dictionary.txt");
		String filename = f.getAbsolutePath();
		String[] arguements = new String[1];
		arguements[0] = filename;
		Scanner in = new Scanner(System.in);
		angrm.anagramFind(filename, in);
		String result = angrm.getAnagramSearchOutput();
		Assert.assertEquals(result,"Word not found in dictionary");
	}
	@Test
	public void testAnagramResult1() {
		int count = 0;
		String userInp = null;
		String anagramList = "Hello world";
		angrm.anagramResult(userInp, count, anagramList);
		String result = angrm.getAnagramSearchOutput();
		Assert.assertEquals(result,"Word not found in dictionary");
	}
	
	@Test
	public void testAnagramResult2() {
		int count = 2;
		String userInp = null;
		String anagramList = "Hello, world";
		angrm.anagramResult(userInp, count, anagramList);
		String result = angrm.getAnagramSearchOutput();
		Assert.assertEquals(result,"Anagrams found in dictionary");
	}
	
	

}
