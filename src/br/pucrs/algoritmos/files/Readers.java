package br.pucrs.algoritmos.files;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import br.pucrs.algoritmos.lists.ListArrayOfString;
import br.pucrs.algoritmos.lists.ListArrayOfWords;
import br.pucrs.algoritmos.word.Word;

public class Readers {
	private static final String STOP_WORDS_FILE = "files/stopwords.txt";
	private static final String BOOK_WORDS_FILE = "files/alice.txt";
	private Integer countLetters;
	private Integer countStopWords;
	private Integer bookLines = 0;

	public static void readStopWordsToList(ListArrayOfString list) {
		Path path = Paths.get(STOP_WORDS_FILE);

		try(Scanner sc = new Scanner(Files.newBufferedReader(path,Charset.forName("utf8")))){
			while(sc.hasNext()) {
				list.add(sc.next());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readBookWordsToList(ListArrayOfWords list) {
		Path path = Paths.get(BOOK_WORDS_FILE);

		try(Scanner sc = new Scanner(Files.newBufferedReader(path,Charset.forName("utf8")))){
			String line;
			Integer page;
			String word;

			while(sc.hasNext()) {
				line = sc.nextLine();
				Scanner scTwo = new Scanner(line);
				scTwo.useDelimiter("[ \n]");
				bookLines++;

				while (scTwo.hasNext()) {
					word = scTwo.next().replaceAll("[^a-zA-Z]", "");

					if(bookLines >= 40 && bookLines % 40 == 0){
						page = bookLines / 40;
					}else{
						page = 1;
					}

					Word wordObject = new Word(word);
					wordObject.addPage(page);
					list.add(wordObject);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
