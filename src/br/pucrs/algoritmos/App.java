package br.pucrs.algoritmos;

import br.pucrs.algoritmos.files.Readers;
import br.pucrs.algoritmos.lists.LinkedListOfWords;
import br.pucrs.algoritmos.lists.ListArrayOfInteger;
import br.pucrs.algoritmos.lists.ListArrayOfPages;
import br.pucrs.algoritmos.line.Page;

import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Rodrigo on 21/05/2017.
 */
public class App {
    private static Readers readers;
    private static LinkedListOfWords listOfWords;

    public static void main(String args[]) {
        readers = new Readers();
        listOfWords = new LinkedListOfWords();
        openFile();

        Integer option = menuOptions();
        doActionByOption(option);

    }

    public static void openFile() {
        String fileName = null;
        Boolean fileExists = false;
        Scanner sc = new Scanner(System.in);

        while (!fileExists) {
            if (Objects.nonNull(fileName)) {
                System.out.println("O arquivo solicitado, não existe!");
                System.out.println("\n");
            }
            System.out.println("Informe o nome do arquivo que deseja verificar as stopwords:");
            fileName = sc.nextLine();

            fileExists = readers.readBookWordsToList(listOfWords, fileName);
        }
    }

    public static Integer menuOptions() {
        Boolean correctOption = false;
        ListArrayOfInteger validOptions = new ListArrayOfInteger(5);
        validOptions.add(1);
        validOptions.add(2);
        validOptions.add(3);
        validOptions.add(4);
        validOptions.add(5);
        Integer option = null;
        Scanner sc = new Scanner(System.in);

        while (!correctOption) {
            if (Objects.nonNull(option)) {
                System.out.println("Opção inválida!");
                System.out.println("\n\n");
            }

            System.out.println("Por favor informe uma das opções abaixo:\n");
            System.out.println("1 - Exibir todo o índice remissivo (em ordem alfabética)");
            System.out.println("2 - Exibir o percentual de stopwords do texto (quanto % do texto é formado por stopwords)");
            System.out.println("3 - Encontrar a palavra mais frequente, isto é, com maior número de ocorrências");
            System.out.println("4 - Pesquisar palavra (o usuário informa uma palavra; o sistema mostra as páginas em que a palavra\n" +
                    "ocorre; na sequência, o usuário escolhe a página; o sistema exibe a página na tela, circundando a\n" +
                    "palavra informada com sinais de [ e ]);\n");
            System.out.println("5 - Encontrar página complexa (o sistema descobre e informa a página que contém o maior número de\n" +
                    "palavras indexadas, informando quantas são)");

            try {
                option = sc.nextInt();
            } catch (NumberFormatException e) {
                continue;
            }

            correctOption = validOptions.contains(option);
        }

        return option;
    }

    private static void doActionByOption(Integer option) {
        switch (option) {
            case 1:
                for (int i = 0; i < listOfWords.size(); i++) {
                    System.out.println(listOfWords.get(i).toString());
                }

                break;
            case 2:
                System.out.println(readers.getStopWordsPercentage());

                break;
            case 3:
                StringBuilder builder = new StringBuilder();
                builder.append("A palavra mais frequente é: ")
                        .append(listOfWords.getFrequentlyWord())
                        .append("\n\n")
                        .append("Com o total de: ")
                        .append(listOfWords.getFrequentlyWordOccurrencesCount())
                        .append(" ")
                        .append("ocorrências.");

                System.out.println(builder.toString());

                break;
            case 4:
                Scanner sc = new Scanner(System.in);
                System.out.println("Digite a palavra que deseja encontrar:");
                String word = sc.nextLine();
                readers.searchWord(listOfWords, word);

            case 5:
                Page page = null;
                Integer totalQuantity = 0;

                for (int i = 0; i < listOfWords.size(); i++) {
                    ListArrayOfPages pages = listOfWords.get(i).getPages();
                    for (int j = 0; j < pages.size(); j++) {

                        if (Objects.nonNull(pages.get(j)) && pages.get(j).getQuantity() > totalQuantity) {
                            totalQuantity = pages.get(j).getQuantity();
                            page = pages.get(j);
                        }
                    }
                }

                System.out.println(page);
        }
    }
}