//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.pucrs.algoritmos.lists;

public class ListArrayOfInteger implements List<Integer> {
    private static final int INITIAL_SIZE = 10;
    private Integer[] data;
    private int count;

    public ListArrayOfInteger() {
        this(10);
    }

    public ListArrayOfInteger(int tam) {
        if(tam <= 0) {
            tam = 10;
        }

        this.data = new Integer[tam];
        this.count = 0;
    }

    public void clear() {
        this.data = new Integer[10];
        this.count = 0;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public Integer size() {
        return Integer.valueOf(this.count);
    }

    public void add(Integer element) {
        if(this.count == this.data.length) {
            this.setCapacity(this.data.length * 2);
        }

        this.data[this.count] = element;
        ++this.count;
    }

    public void add(Integer index, Integer element) {
        if(index.intValue() >= 0 && index.intValue() <= this.size().intValue()) {
            if(this.count == this.data.length) {
                this.setCapacity(this.data.length * 2);
            }

            for(int i = this.count; i > index.intValue(); --i) {
                this.data[i] = this.data[i - 1];
            }

            this.data[index.intValue()] = element;
            ++this.count;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Integer removeByIndex(Integer index) {
        if(index.intValue() >= 0 && index.intValue() < this.size().intValue()) {
            int aux = this.data[index.intValue()].intValue();

            for(int i = index.intValue(); i < this.count - 1; ++i) {
                this.data[i] = this.data[i + 1];
            }

            this.data[this.count - 1] = null;
            --this.count;
            return Integer.valueOf(aux);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean remove(Integer element) {
        for(int i = 0; i < this.count; ++i) {
            if(element.equals(this.data[i])) {
                for(int j = i; j < this.count - 1; ++j) {
                    this.data[j] = this.data[j + 1];
                }

                this.data[this.count - 1] = null;
                --this.count;
                return true;
            }
        }

        return false;
    }

    public Integer get(Integer index) {
        if(index.intValue() >= 0 && index.intValue() < this.count) {
            return this.data[index.intValue()];
        } else {
            throw new IndexOutOfBoundsException("Index = " + index);
        }
    }

    public Integer set(Integer index, Integer element) {
        if(index.intValue() >= 0 && index.intValue() < this.count) {
            int item = this.data[index.intValue()].intValue();
            this.data[index.intValue()] = element;
            return Integer.valueOf(item);
        } else {
            throw new IndexOutOfBoundsException("Index = " + index);
        }
    }

    public boolean contains(Integer element) {
        for(int p = 0; p < this.count; ++p) {
            if(this.data[p].equals(element)) {
                return true;
            }
        }

        return false;
    }

    public Integer indexOf(Integer element) {
        for(int i = 0; i < this.count; ++i) {
            if(this.data[i].equals(element)) {
                return Integer.valueOf(i);
            }
        }

        return Integer.valueOf(-1);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < this.count; ++i) {
            s.append(this.data[i]);
            if(i != this.count - 1) {
                s.append(",");
            }
        }

        s.append("\n");
        return s.toString();
    }

    private void setCapacity(int newCapacity) {
        if(newCapacity != this.data.length) {
            Integer[] newData = new Integer[newCapacity];
            int min;
            if(this.data.length < newCapacity) {
                min = this.data.length;
            } else {
                min = newCapacity;
            }

            for(int i = 0; i < min; ++i) {
                newData[i] = this.data[i];
            }

            this.data = newData;
        }

    }
}
