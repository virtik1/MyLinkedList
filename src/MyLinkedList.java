import java.util.NoSuchElementException;

public class MyLinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E> previous;
    private int size;

    public MyLinkedList() {
        size = 0;
        first = null;
        last = null;
        previous = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        Node<E> tmp = first;
        while (tmp != null) {
            if (tmp.getT() == o) {
                return true;
            }
            tmp = tmp.getNext();
        }
        return false;
    }

    public boolean add(E t) {
        Node<E> tmp = new Node<>(t);
        if (first == null) {
            first = tmp;
            previous = first;
            last = first;
            size ++;
            return true;
        }
        if (size() == 1) {
            last = tmp;
            previous.setNext(last);
            last.setPrevious(previous);
            size ++;
            return true;
        }
        else {
            previous = last;
            last = tmp;
            last.setPrevious(previous);
            previous.setNext(last);
            size ++;
            return true;
        }
    }

    public boolean add(E t, int index) {
        if (index == 0) {
            addFirst(t);
            return true;
        }
        if (index >= size() - 1) {
            addLast(t);
            return true;
        }
        Node<E> tmp = new Node<>(t);
        Node<E> next = getByIndex(index);
        Node<E> preceding = next.getPrevious();
        preceding.setNext(tmp);
        next.setPrevious(tmp);
        tmp.setNext(next);
        tmp.setPrevious(preceding);
        return false;
    }

    public boolean addLast(E t) {
        add(t);
        return true;
    }

    public boolean addFirst(E t) {
        Node<E> tmp = new Node<>(t);
        if (first == null) {
            first = tmp;
            previous = first;
            last = first;
            size ++;
            return true;
        }
        if (size() == 1) {
            tmp.setNext(first);
            first.setPrevious(tmp);
            first = tmp;
            previous = first;
            size ++;
            return true;
        }
        else {
            tmp.setNext(first);
            first.setPrevious(tmp);
            first = tmp;
            size ++;
            return true;
        }
    }

    private Node<E> getByIndex(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            return null;
        }
        if (index <= (size() - 1) / 2) {
            Node<E> tmp = first;
            for (int i = 1; i <= index; i ++) {
                tmp = tmp.getNext();
            }
            return tmp;
        }
        else {
            Node<E> tmp = last;
            for (int i = size() - 1; i > index; i --) {
                tmp = tmp.getPrevious();
            }
            return tmp;
        }
    }

    public int indexOf(Object o) {
        if (isEmpty()) {
            return -1;
        }
        Node<E> tmp = first;
        for (int i = 0; i < size(); i ++) {
            if (o == tmp.getT()) {
                return i;
            }
            tmp = tmp.getNext();
        }
        return -1;
    }

    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size() == 1) {
            E t = first.getT();
            first = null;
            last = null;
            previous = null;
            size --;
            return t;
        }
        else {
            Node<E> tmp = first;
            E t = tmp.getT();
            first = first.getNext();
            first.setPrevious(null);
            tmp = null;
            size --;
            return t;
        }
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        if (size() == 1) {
            E t = first.getT();
            first = null;
            last = null;
            previous = null;
            size --;
            return t;
        }
        else {
            Node<E> tmp = first;
            E t = tmp.getT();
            first = first.getNext();
            first.setPrevious(null);
            tmp = null;
            size --;
            return t;
        }
    }

    public E pollFirst() {
        return poll();
    }

    public E removeFirst() {
        return remove();
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size() == 1) {
            E t = first.getT();
            removeFirst();
            return t;
        }
        else {
            Node<E> tmp = last;
            E t = last.getT();
            last = previous;
            previous = previous.getPrevious();
            last.setNext(null);
            tmp = null;
            size --;
            return t;
        }
    }

    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        if (size() == 1) {
            E t = first.getT();
            removeFirst();
            return t;
        }
        else {
            Node<E> tmp = last;
            E t = last.getT();
            last = previous;
            previous = previous.getPrevious();
            last.setNext(null);
            tmp = null;
            size --;
            return t;
        }
    }

    public boolean remove(Object o) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first.getT() == o) {
            removeFirst();
            return true;
        }
        if (last.getT() == o) {
            removeLast();
            return true;
        }
        Node<E> tmp = first.getNext();
        while (tmp != null) {
            if (tmp.getT() == o) {
                Node<E> next = tmp.getNext();
                Node<E> preceding = tmp.getPrevious();
                preceding.setNext(next);
                next.setPrevious(preceding);
                tmp = null;
                return true;
            }
            tmp = tmp.getNext();
        }
        throw new NoSuchElementException();
    }

    public boolean remove(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            throw new NoSuchElementException();
        }
        if (index == 1) {
            removeFirst();
            return true;
        }
        if (index == size() - 1) {
            removeLast();
            return true;
        }
        else {
            Node<E> tmp = getByIndex(index);
            Node<E> next = tmp.getNext();
            Node<E> preceding = tmp.getPrevious();
            next.setPrevious(preceding);
            preceding.setNext(next);
            tmp = null;
            size --;
            return true;
        }
    }

    public E get(int index) {
        if(isEmpty() || index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> tmp = getByIndex(index);
        return tmp.getT();
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.getT();
    }

    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.getT();
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.getT();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return last.getT();
    }

    public void clear() {
        Node<E> tmp;
        Node<E> it = first;
        while (it != null) {
            tmp = it;
            it = it.getNext();
            tmp = null;
        }
    }

    public E set(int index, E t) {
        Node<E> tmp = getByIndex(index);
        E oldT = tmp.getT();
        tmp.setT(t);
        return oldT;
    }
}


