package br.pucrs.algoritmos.lists;

/**
 * Created by Rodrigo on 21/05/2017.
 */
public interface List<T> {
    void add(T element);
    void add(Integer index, T element);
    boolean remove(T element);
    T removeByIndex(Integer index);
    void clear();
    boolean isEmpty();
    boolean contains(T element);
    Integer indexOf(T element);
    Integer size();
    T get(Integer index);
    T set(Integer index, T newElement);
}
