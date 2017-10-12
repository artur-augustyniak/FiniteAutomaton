/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Artur Augustyniak
 */
public class Word implements Queue<Symbol> {

    private LinkedList<Symbol> q;
    private Alphabet alphabet;
    private String incoming;

    public Word(String incoming, Alphabet a) throws Exception {

        this.alphabet = a;
        this.incoming = incoming;
        q = new LinkedList<Symbol>();
        Symbol current;

        for (char c : this.incoming.toCharArray()) {

            current = this.alphabet.getByContentString(Character.toString(c));
            if (current == null) {
                throw new Exception("sdf");
            }
            q.add(current);
        }
    }

    @Override
    public boolean add(Symbol e) {
        return q.add(e);
    }

    @Override
    public boolean offer(Symbol e) {
        return q.offer(e);
    }

    @Override
    public Symbol remove() {
        return q.remove();
    }

    @Override
    public Symbol poll() {
        return q.poll();
    }

    @Override
    public Symbol element() {
        return q.element();
    }

    @Override
    public Symbol peek() {
        return q.peek();
    }

    @Override
    public int size() {
        return q.size();
    }

    @Override
    public boolean isEmpty() {
        return q.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return q.contains(o);
    }

    @Override
    public Iterator<Symbol> iterator() {
        return q.iterator();
    }

    @Override
    public Object[] toArray() {
        return q.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return q.toArray(ts);
    }

    @Override
    public boolean remove(Object o) {
        return q.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        return q.containsAll(clctn);
    }

    @Override
    public boolean addAll(Collection<? extends Symbol> clctn) {
        return q.addAll(clctn);
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        return q.removeAll(clctn);
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        return q.retainAll(clctn);
    }

    @Override
    public void clear() {
        q.clear();
    }
}
