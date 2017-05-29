package br.pucrs.algoritmos.line;

import br.pucrs.algoritmos.lists.ListArrayOfPages;

/**
 * Created by Rodrigo on 21/05/2017.
 */
public class Word {
    private String word;
    private ListArrayOfPages pages;
    private Integer previousPageNumber;

    public Word(String word) {
        this.word = word;
        this.pages = new ListArrayOfPages();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ListArrayOfPages getPages() {
        return this.pages;
    }

    public Integer getPreviousPageNumber() {
        return previousPageNumber;
    }

    public void setPreviousPageNumber(Integer previousPageNumber) {
        this.previousPageNumber = previousPageNumber;
    }

    public void addPage(Page page) {
        pages.add(page);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(word.concat(":") + pages.toString());

        return builder.toString();
    }
}
