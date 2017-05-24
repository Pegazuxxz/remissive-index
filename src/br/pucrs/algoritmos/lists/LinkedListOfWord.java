package br.pucrs.algoritmos.lists;

import br.pucrs.algoritmos.word.Word;

public class LinkedListOfWord{

    // Classe interna Node
    private class Node {

        public ListArrayOfString element;
        public Node next;

        public Node(ListArrayOfString elements) {
            this.element = elements;
            next = null;
        }
    }

    // Referência para o primeiro elemento da lista encadeada.
    private Node head;
    // Referência para o último elemento da lista encadeada.
    private Node tail;
    // Contador para a quantidade de elementos que a lista contem.
    private int count;

    /**
     * Construtor da lista
     */
    public LinkedListOfWord() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona um elemento ao final da lista
     *
     * @param elements elemento a ser adicionado ao final da lista
     */
    public void add(ListArrayOfString elements) {
        Node aux = new Node(elements);
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }

    /**
     * Insere um elemento em uma determinada posicao da lista
     *
     * @param index a posicao da lista onde o elemento sera inserido
     * @param elements elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(Integer index, ListArrayOfString elements) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node n = new Node(elements);
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
    public ListArrayOfString get(Integer index) {
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
     * @param elements o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posicao da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public ListArrayOfString set(Integer index, ListArrayOfString elements) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        ListArrayOfString tmp = aux.element;
        aux.element = elements;
        return tmp;

    }

    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente
     *
     * @param elements o elemento a ser removido
     * @return true se a lista contem o elemento especificado
     */
    public boolean remove(ListArrayOfString elements) {
        if (elements == null) {
            return false;
        }
        if (count == 0) {
            return false;
        }

        if (head.element.equals(elements)) { // remocao do primeiro
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
            if (aux.element.equals(elements)) {
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
    public ListArrayOfString removeByIndex(Integer index) {
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
        ListArrayOfString elements = aux.next.element;
        if (tail == aux.next) {
            tail = aux;
        }
        aux.next = aux.next.next;
        count--;
        return elements;
    }

    /**
     * Retorna o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     *
     * @param elements o elemento a ser buscado
     * @return o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public Integer indexOf(ListArrayOfString elements) {
        int index = 0;
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(elements)) {
                return (index);
            }
            aux = aux.next;
            index++;
        }
        return -1;
    }

    /**
     * Retorna true se a lista contem o elemento especificado
     *
     * @param elements o elemento a ser testado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(ListArrayOfString elements) {
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(elements)) {
                return (true);
            }
            aux = aux.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        Node aux = head;

        while (aux != null) {
            s.append(aux.element.toString());
            s.append("\n");
            aux = aux.next;
        }

        return s.toString();
    }

}
