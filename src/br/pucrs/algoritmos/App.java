package br.pucrs.algoritmos;

import br.pucrs.algoritmos.files.Readers;
import br.pucrs.algoritmos.lists.LinkedListOfString;
import br.pucrs.algoritmos.lists.LinkedListOfWord;
import br.pucrs.algoritmos.lists.ListArrayOfString;
import br.pucrs.algoritmos.lists.ListArrayOfWords;
import br.pucrs.algoritmos.word.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Rodrigo on 21/05/2017.
 */
public class App {

    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please, tell me what is book that you want to read:");
//        String file = sc.nextLine().toLowerCase().trim();
//
//        LinkedListOfString listOfStopWords = readStopWordsFromFile();
//        LinkedListOfWord linesOfWords = null;
//
//        while (Objects.isNull(linesOfWords = readWordsFromBook(listOfStopWords, file))) {
//            System.out.println("File not exists!\n\n");
//            System.out.println("Please, tell me again what is book that you want to read:");
//            file = sc.nextLine().toLowerCase().trim();
//        }
//
//        for(int i = 0; i < linesOfWords.size();i++){
//            System.out.println(linesOfWords.get(i));
//        }

    	ListArrayOfString stopWordsList = new ListArrayOfString();
    	ListArrayOfWords wordsList = new ListArrayOfWords();
    	Readers readers = new Readers();
    	
    	readers.readBookWordsToList(wordsList);
    	
    	for(int i = 0; i < wordsList.size(); i++){
    		System.out.println(wordsList.get(i).getWord());
    	}

    }

    private static LinkedListOfString readStopWordsFromFile() {
        LinkedListOfString listOfStopWords = new LinkedListOfString();
        Path path = Paths.get("files/stopwords.txt");
        /**
         * Reading the stop words file
         **/
        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(path, Charset.forName("utf8")))) {
            String stopWord;

            while (Objects.nonNull(stopWord = reader.readLine())) {
                listOfStopWords.add(stopWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfStopWords;
    }

    private static LinkedListOfWord readWordsFromBook(LinkedListOfString listOfStopWords, String fileName) {
        Path path = Paths.get("files/".concat(fileName).concat(".txt"));
        LinkedListOfWord linesOfWords = new LinkedListOfWord();
        System.out.println("1");
        /**
         * Reading the book words
         **/
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
            System.out.println("2");
            String word = null;

            while (sc.hasNextLine()) {
                ListArrayOfString wordsPerLine = new ListArrayOfString();
                Scanner sc2 = new Scanner(sc.nextLine());
                sc2.useDelimiter("[ \n]");
                while (sc.hasNext()) {
                    word = sc.next().trim().toLowerCase().replaceAll("[^\\p{Alpha}\\p{Digit}]+","");

                    if (!listOfStopWords.contains(word)) {
                        wordsPerLine.add(word);
                    }
                }
                linesOfWords.add(wordsPerLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesOfWords;
    }
}