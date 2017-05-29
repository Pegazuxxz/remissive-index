package br.pucrs.algoritmos.lists;

import br.pucrs.algoritmos.line.Page;

public class ListArrayOfPages implements List<Page> {

    private static final int INITIAL_SIZE = 10;
    private Page[] data;
    private int count;

    /**
     * Construtor da lista
     */
    public ListArrayOfPages() {
        this(INITIAL_SIZE);
    }

    /**
     * Construtor da lista
     *
     * @param tam tamanho inicial a ser alocado para a lista
     */
    public ListArrayOfPages(int tam) {
        if (tam <= 0) {
            tam = INITIAL_SIZE;
        }
        data = new Page[tam];
        count = 0;
    }

    /**
     * Esvazia a lista
     */
    @Override
    public void clear() {
        data = new Page[INITIAL_SIZE];
        count = 0;
    }

    /**
     * Retorna true se a lista não contêm elementos
     *
     * @return true se a lista não contêm elementos
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna o número de elementos da lista
     *
     * @return o número de elementos da lista
     */
    @Override
    public Integer size() {
        return count;
    }

    /**
     * Adiciona um elemento ao final da lista
     *
     * @param element elemento a ser adicionado ao final da lista
     */
    @Override
    public void add(Page element) {
        if (count == data.length) {
            setCapacity(data.length * 2);
        }
        data[count] = element;
        count++;
    }

    /**
     * Insere um elemento em uma determinada posição da lista
     *
     * @param index   a posição da lista onde o elemento será inserido
     * @param element elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    @Override
    public void add(Integer index, Page element) {

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (count == data.length) {
            setCapacity(data.length * 2);
        }

        for (int i = count; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        count++;
    }

    /**
     * Remove o elemento de uma determinada posição da lista
     *
     * @param index a posição da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    @Override
    public Page removeByIndex(Integer index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Page aux = data[index];

        for (int i = index; i < count - 1; i++) {
            data[i] = data[i + 1];
        }

        data[count - 1] = null;
        count--;

        return aux;
    }

    /**
     * Remove a primeira ocorrência do elemento na lista, se estiver presente
     *
     * @param element o elemento a ser removido
     * @return true se a lista contém o elemento especificado
     */
    @Override
    public boolean remove(Page element) {

        for (int i = 0; i < count; i++) {

            if (element.equals(data[i])) {
                for (int j = i; j < count - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna o elemento de uma determinada posição da lista
     *
     * @param index a posição da lista
     * @return o elemento da posição especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    @Override
    public Page get(Integer index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Index = " + index);
        }
        return data[index];
    }

    public Page get(int pageNumber) {
        for (int i = 0; i < count; i++) {
            if (data[i].getPageNumber() == pageNumber) {
                return data[i];
            }
        }

        return null;
    }

    /**
     * Substitui o elemento armanzenado em uma determinada posição da lista pelo
     * elemento indicado
     *
     * @param index   a posição da lista
     * @param element o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posição da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    @Override
    public Page set(Integer index, Page element) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Index = " + index);
        }
        Page item = data[index];
        data[index] = element;
        return item;
    }

    /**
     * Retorna true se a lista contém o elemento especificado
     *
     * @param element o elemento a ser testado
     * @return true se a lista contém o elemento especificado
     */
    @Override
    public boolean contains(Page element) {
        for (int p = 0; p < count; p++) {
            if (data[p].getPageNumber().equals(element.getPageNumber())) {
                return true;
            }
        }
        // Neste ponto, não achou: retorna falso
        return false;
    }

    /**
     * Retorna o índice da primeira ocorrência do elemento na lista, ou -1 se a
     * lista não contém o elemento
     *
     * @param element o elemento a ser buscado
     * @return o índice da primeira ocorrência do elemento na lista, ou -1 se a
     * lista não contém o elemento
     */
    @Override
    public Integer indexOf(Page element) {
        // Procura elemento no array, se achar retorna
        for (int i = 0; i < count; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        // Neste ponto, não achou: retorna -1
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < count; i++) {
            s.append(data[i].toString());
        }
        s.append("\n");
        return s.toString();
    }

    private void setCapacity(int newCapacity) {
        if (newCapacity != data.length) {
            int min = 0;
            Page[] newData = new Page[newCapacity];
            if (data.length < newCapacity) {
                min = data.length;
            } else {
                min = newCapacity;
            }
            for (int i = 0; i < min; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

}
