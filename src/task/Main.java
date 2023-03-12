package task;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		Traslator tr = new Traslator();
		
		tr.addTranslation("spring", "весна");
		tr.addTranslation("came", "прийшла");
		tr.addTranslation("my", "мій");
		tr.addTranslation("cat", "кіт");
		tr.addTranslation("sings", "співає");		
		tr.addTranslation("summer", "літо");
		tr.addTranslation("next", "далі");
		
		File vocabularyCsv = new File("Vocabulary.txt");
		
		tr.fillVocabularyWithWordsFromFile(vocabularyCsv);
		tr.addPairsOfWordsToVocabulary();
		tr.addPairsOfWordsToVocabulary();
		tr.savePairsOfWordsToVocabulary(vocabularyCsv);
		
		File englishIn = new File("English.in.txt");
		File ukrainianOut = new File("Ukrainian.out.txt");
		
		tr.getTranslationAndPutInFile(englishIn, ukrainianOut);
		
		System.out.println();
		System.out.println("Переклад: ");
		System.out.println(tr.getTextInUkrainian());
		System.out.println("записано в файл " + ukrainianOut.getAbsolutePath());
				
	}

}
