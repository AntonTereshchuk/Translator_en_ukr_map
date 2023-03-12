package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Traslator {
	
	private Map<String , String> translationMap = new HashMap<>();
	private String textInUkrainian = "";
	
	public Traslator(Map<String, String> translationMap) {
		super();
		this.translationMap = translationMap;
	}
			
	public Traslator() {
		super();
	}
	
	public Map<String, String> getTranslationMap() {
		return translationMap;
	}

	public void setTranslationMap(Map<String, String> translationMap) {
		this.translationMap = translationMap;
	}

	public String getTextInUkrainian() {
		return textInUkrainian;
	}

	public void setTextInUkrainian(String textInUkrainian) {
		this.textInUkrainian = textInUkrainian;
	}

	public void addTranslation(String englishWord, String ukrainianWord) {
		translationMap.put(englishWord, ukrainianWord);		
	}
	
	public void fillVocabularyWithWordsFromFile(File vocabularyCsv) {
		
		String englishWord;
		String ukrainianWord;
		String[] words; 
		
		if (vocabularyCsv.exists() && !vocabularyCsv.isDirectory()) {
			
			try (Scanner sc = new Scanner(vocabularyCsv)) {
				
				for (; sc.hasNextLine();) {
					
					words = sc.nextLine().split(" ");
					englishWord = words[0];
					ukrainianWord = words[1];
					
					if (translationMap.get(englishWord) == null) {
						translationMap.put(englishWord, ukrainianWord);
					}
										
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public void addPairsOfWordsToVocabulary() {
		
		String englishWord;
		String ukrainianWord = "";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter english word: ");
		englishWord = sc.nextLine();
		System.out.println("Enter ukrainian translation: ");
		ukrainianWord = sc.nextLine();
		
		translationMap.put(englishWord, ukrainianWord);
		
	}
	
	public void savePairsOfWordsToVocabulary(File vocabularyCsv) {
							
		try(PrintWriter pw = new PrintWriter(vocabularyCsv)) {
			for (String key : translationMap.keySet()) {
				pw.println(key + " " + translationMap.get(key));
		    }			
		}
		catch(IOException e) {
			e.printStackTrace();
		};
		
	}
	
	public void getTranslationAndPutInFile(File englishIn, File ukrainianOut) {
	
		translateAText(englishIn);
		createFileWithTranslation(ukrainianOut);
		
	}
	
	public void translateAText(File englishIn) {
		
		String[] words;
		String wordInUkrainian;
				
		try (Scanner sc = new Scanner(englishIn)) {
						
			for (; sc.hasNextLine();) {
				
				words = sc.nextLine().split(" ");
				
				for (int i = 0; i < words.length; i++) {
					wordInUkrainian = translationMap.get(words[i]);
					if (wordInUkrainian != null) {
						textInUkrainian += wordInUkrainian + " ";
					} else {
						textInUkrainian += words[i];
					};
				}
				
				textInUkrainian += System.lineSeparator();
													
			}
								
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
				
	}
	
	public void createFileWithTranslation(File ukrainianOut) {
		
		try(PrintWriter pw = new PrintWriter(ukrainianOut)) {
							
		    pw.println(textInUkrainian);
		}
		catch(IOException e) {
			e.printStackTrace();
		};			
		
	}

	@Override
	public String toString() {
		return "Traslator [translationMap=" + translationMap + ", textInUkrainian=" + textInUkrainian + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(textInUkrainian, translationMap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Traslator other = (Traslator) obj;
		return Objects.equals(textInUkrainian, other.textInUkrainian)
				&& Objects.equals(translationMap, other.translationMap);
	}
		
}
