package br.pucrs.algoritmos.lists;

import br.pucrs.algoritmos.line.Word;

public class LinkedListOfWords {

    // Classe interna Node
    private class Node {

        public Word element;
        public Node next;

        public Node(Word element) {
            this.element = element;
            next = null;
        }
    }

    // Referência para o primeiro elemento da lista encadeada.
    private Node head;
    // Referência para o último elemento da lista encadeada.
    private Node tail;
    // Contador para a quantidade de elementos que a lista contem.
    private int count;

    private String frequentlyWord;
    private Integer frequentlyWordOccurrencesCount;

    /**
     * Construtor da lista
     */
    public LinkedListOfWords() {
        head = null;
        tail = null;
        count = 0;
        this.frequentlyWordOccurrencesCount = 0;
    }

    /**
     * Adiciona um elemento ao final da lista
     *
     * @param word elemento a ser adicionado ao final da lista
     */
    public void add(Word word) {
        Node aux = new Node(word);
        Node ant = head;

        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }

    public void addIncreasingOrder(Word word) {
        if (count == 0) {
            add(word);
        } else if (head.element.getWord().compareTo(word.getWord()) > 0) { // insercao no inicio
            add(0, word);
        } else if (tail.element.getWord().compareTo(word.getWord()) < 0) { // insercao no final
            add(word);
        } else { // insercao no meio - procurar a posicao
            Node ant = head;
            Node aux = head.next;
            Node n = new Node(word);
            while (aux != null) {
                if (aux.element.getWord().compareTo(word.getWord()) > 0) {
                    n.next = aux;
                    ant.next = n;
                    count++;
                    break;
                }
                ant = aux;
                aux = aux.next;
            }
        }

    }

    /**
     * Insere um elemento em uma determinada posicao da lista
     *
     * @param index a posicao da lista onde o elemento sera inserido
     * @param word  elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(Integer index, Word word) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node n = new Node(word);
        if (index == 0) { //insere no inicio
            n.next = head;
            head = n;
            if (tail == null) {
                tail = n;
            }
        } else if (index == count) { // insere no final
            tail.next = n;
            tail = n;
        } else { // insere no meio
            Node aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            n.next = aux.next;
            aux.next = n;
        }
        count++;
    }

    /**
     * Retorna o elemento de uma determinada posicao da lista
     *
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Word get(Integer index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux.element);
    }

    /**
     * Substitui o elemento armanzenado em uma determinada posicao da lista pelo
     * elemento indicado
     *
     * @param index a posicao da lista
     * @param word  o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posicao da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Word set(Integer index, Word word) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        Word tmp = aux.element;
        aux.element = word;
        return tmp;
    }

    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente
     *
     * @param word o elemento a ser removido
     * @return true se a lista contem o elemento especificado
     */
    public boolean remove(Word word) {
        if (word == null) {
            return false;
        }
        if (count == 0) {
            return false;
        }

        if (head.element.equals(word)) { // remocao do primeiro
            head = head.next;
            if (count == 1) { // se havia so um elemento na lista
                tail = null;
            }
            count--;
            return true;
        }

        Node ant = head;
        Node aux = head.next;

        for (int i = 1; i < count; i++) {
            if (aux.element.equals(word)) {
                if (aux == tail) { // remocao do ultimo
                    tail = ant;
                    tail.next = null;
                } else { // remocao do meio
                    ant.next = aux.next;
                }
                count--;
                return true;
            }
            ant = ant.next;
            aux = aux.next;
        }

        return false;
    }

    /**
     * Retorna true se a lista nao contem elementos
     *
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista
     *
     * @return o numero de elementos da lista
     */
    public Integer size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Remove o elemento de uma determinada posicao da lista
     *
     * @param index a posicao da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Word removeByIndex(Integer index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        Node aux = head;
        if (index == 0) {
            if (tail == head) // se tiver apenas um elemento
            {
                tail = null;
            }
            head = head.next;
            count--;
            return aux.element;
        }
        int c = 0;
        while (c < index - 1) {
            aux = aux.next;
            c++;
        }
        Word word = aux.next.element;
        if (tail == aux.next) {
            tail = aux;
        }
        aux.next = aux.next.next;
        count--;
        return word;
    }

    /**
     * Retorna o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     *
     * @param word o elemento a ser buscado
     * @return o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public Integer indexOf(Word word) {
        int index = 0;
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(word)) {
                return (index);
            }
            aux = aux.next;
            index++;
        }
        return -1;
    }

    public Word getWord(String element) {
        int index = 0;
        Node aux = head;
        while (aux != null) {
            if (aux.element.getWord().equals(element)) {
                return aux.element;
            }
            aux = aux.next;
            index++;
        }
        return null;
    }

    /**
     * Retorna true se a lista contem o elemento especificado
     *
     * @param word o elemento a ser testado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(Word word) {
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(word)) {
                return (true);
            }
            aux = aux.next;
        }

        return false;
    }

    public Integer getFrequentlyWordOccurrencesCount() {
        return frequentlyWordOccurrencesCount;
    }

    public String getFrequentlyWord() {
        return frequentlyWord;
    }

    public void setFrequentlyWord(String frequentlyWord) {
        this.frequentlyWord = frequentlyWord;
    }

    public void setFrequentlyWordOccurrencesCount(Integer frequentlyWordOccurrencesCount) {
        this.frequentlyWordOccurrencesCount = frequentlyWordOccurrencesCount;
    }

}
