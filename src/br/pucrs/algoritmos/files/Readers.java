package br.pucrs.algoritmos.files;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import br.pucrs.algoritmos.lists.LinkedListOfWords;
import br.pucrs.algoritmos.lists.ListArrayOfString;
import br.pucrs.algoritmos.line.Page;
import br.pucrs.algoritmos.line.Word;

public class Readers {
    private static final String STANDARD_PATH = "files";
    private static final String STOP_WORDS_FILE = STANDARD_PATH + "/stopwords.txt";
    private static final String TEST_FILE = STANDARD_PATH + "/teste-words.txt";

    private Float countLetters;
    private Float countStopWords;
    private Integer bookLines = 0;
    private String fileChosen;
    private ListArrayOfString textPages;

    public Readers() {
        this.countStopWords = 0.0F;
        this.countLetters = 0.0F;
        textPages = new ListArrayOfString();
    }

    private void readStopWordsToList(ListArrayOfString list) {
        Path path = Paths.get(STOP_WORDS_FILE);

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
            while (sc.hasNext()) {
                list.add(sc.next().trim().toLowerCase());
                countStopWords++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readBookWordsToList(LinkedListOfWords list, String fileName) {
        //Path path = Paths.get(TEST_FILE);
        Path path = Paths.get(STANDARD_PATH.concat("/").concat(fileName));
        fileChosen = fileName;

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
            String line;
            int pageNumber = 1;
            String word;
            StringBuilder bookInStringBuilder = new StringBuilder();
            ListArrayOfString stopWordsList = new ListArrayOfString();
            readStopWordsToList(stopWordsList);
            StringBuilder builder = new StringBuilder();

            while (sc.hasNext()) {
                line = sc.nextLine();
                Scanner scTwo = new Scanner(line);
                scTwo.useDelimiter("[ ]");
                builder.append(line)
                        .append("\n\n");

                bookLines++;
                bookInStringBuilder.append(line);

                if (bookLines >= 40 && bookLines % 40 == 0) {
                    pageNumber = bookLines / 40;
                    textPages.add(builder.toString());
                    builder = new StringBuilder();
                }

                while (scTwo.hasNext()) {
                    word = scTwo.next().replaceAll("[^a-zA-Z]", "").trim().toLowerCase();
                    countLetters++;


                    if (!word.isEmpty() && !stopWordsList.contains(word)) {
                        Word currentWord = list.getWord(word);

                        if (Objects.nonNull(currentWord) && currentWord.getPreviousPageNumber() != null) {

                            if (currentWord.getPreviousPageNumber().equals(pageNumber)) {
                                Page page = currentWord.getPages().get(pageNumber);
                                page.sumOneQuantity();
                                if (page.getQuantity() > list.getFrequentlyWordOccurrencesCount()) {
                                    list.setFrequentlyWordOccurrencesCount(page.getQuantity());
                                    list.setFrequentlyWord(word);
                                }
                            } else {
                                Page page = new Page(pageNumber);
                                page.sumOneQuantity();
                                currentWord.addPage(page);
                                currentWord.setPreviousPageNumber(page.getPageNumber());
                            }
                        } else {
                            Page page = new Page(pageNumber);
                            page.sumOneQuantity();
                            Word wordObject = new Word(word);
                            wordObject.addPage(page);
                            wordObject.setPreviousPageNumber(pageNumber);
                            list.addIncreasingOrder(wordObject);
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void searchWord(LinkedListOfWords listOfWords, String word) {
        Boolean validPage = false;
        int pageRequested = 0;
        LinkedListOfWords newList = new LinkedListOfWords();
        Scanner sc = new Scanner(System.in);
        Word foundWord = null;
        Page pageFound = null;

        for (int i = 0; i < listOfWords.size(); i++) {
            foundWord = listOfWords.get(i);
            if (foundWord.getWord().equals(word.trim().toLowerCase())) {
                break;
            }
        }
        System.out.println(foundWord.toString());
        System.out.println("\n\n\n");

        while (!validPage) {
            try {
                System.out.println("Informe o número da página que deseja ver:");
                pageRequested = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Texto informado não corresponde a uma página.");
                System.out.println("Digite somente números.");
            }

            if (pageRequested != 0) {
                pageFound = foundWord.getPages().get(pageRequested);
                if (Objects.nonNull(pageFound)) {
                    validPage = true;
                } else {
                    System.out.println("Página informada não existe!");
                }
            }
        }

        String pageText = textPages.get(pageRequested);
        String wordRequested = foundWord.getWord();

        System.out.println(pageText.toLowerCase().replaceAll(wordRequested, "[" + wordRequested + "]"));
    }


    public String getStopWordsPercentage() {
        System.out.println(countStopWords);
        System.out.println(countLetters);
        Float total = (countStopWords * 100) / countLetters;
        DecimalFormat format = new DecimalFormat("#0.00");
        
        return format.format(total)
                .toString()
                .concat("%");
    }
}
