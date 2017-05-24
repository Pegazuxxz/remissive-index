package br.pucrs.algoritmos.word;
import br.pucrs.algoritmos.lists.ListArrayOfInteger;

/**
 * Created by Rodrigo on 21/05/2017.
 */
public class Word {
    private String word;
    private ListArrayOfInteger pages;

    public Word(String word){
        this.word = word;
        this.pages = pages;
        pages = new ListArrayOfInteger();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ListArrayOfInteger getPages(){
        return this.pages;
    }

    public void addPage(Integer page){
        pages.add(page);
    }
}
