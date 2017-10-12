/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.model;

import java.util.HashMap;

/**
 * Klasa reprezentująca unikalny stan
 *
 * @author Artur Augustyniak
 */
public class State {

    public enum StateType {

        COMBINED(1), INPUT(2), ACCEPT(3), REJECT(4), EMPTY(5);
        private final int mask;

        private StateType(int mask) {
            this.mask = mask;
        }

        public int getMask() {
            return mask;
        }
    }
    private String name;
    private StateType sType;
    HashMap<Symbol, StateSet> resulting;

    /**
     * Nazwy stanów dla wizualizacji
     *
     * @param name
     */
    public State(String name, StateType sType) {
        this.name = name;
        this.sType = sType;
    }

    public void setResulting(HashMap<Symbol, StateSet> resulting) {
        this.resulting = resulting;
    }

    public void addResulting(Symbol s, State st) {
        if (this.resulting == null) {
            this.resulting = new HashMap<Symbol, StateSet>();
        }
        StateSet newResulting;
        newResulting = this.resulting.get(s);
        if (newResulting != null) {
            newResulting.getStates().add(st);
        } else {
            newResulting = new StateSet();
            newResulting.getStates().add(st);
            this.resulting.put(s, newResulting);
        }
    }

    public HashMap<Symbol, StateSet> getResulting() {
        return resulting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateType getsType() {
        return sType;
    }

    public void setsType(StateType sType) {
        this.sType = sType;
    }
}
