/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.model;

import java.util.HashSet;

/**
 * Klasa reprezentująca zbiór stanów
 *
 * @author Artur Augustyniak
 */
public class StateSet {

    HashSet<State> states;
    boolean isInput = false;

    public boolean getIsInput() {
        return isInput;
    }

    public void setIsInput(boolean isInput) {
        this.isInput = isInput;
    }

    public StateSet() {
        this.states = new HashSet<State>();

    }

    public StateSet addState(State s) {
        this.states.add(s);
        return this;
    }

    public HashSet<State> getStates() {

        return states;
    }

    public State.StateType getStateSetType() {

        State.StateType decission;
        if (this.getIsInput()) {
            decission = State.StateType.INPUT;
        } else {
            decission = State.StateType.EMPTY;
        }

        for (State s : this.getStates()) {
            if (s.getsType().getMask() < decission.getMask() && this.getIsInput()) {
                decission = s.getsType();
                break;
            } else if (s.getsType().equals(State.StateType.COMBINED) && !this.getIsInput()) {
                decission = State.StateType.ACCEPT;
                break;
            } else if (s.getsType().equals(State.StateType.INPUT) && !this.getIsInput()) {
                decission = State.StateType.REJECT;

            } else {
                decission = s.getsType();
            }

        }
        return decission;
    }

    public StateSet getResultingForSymbol(Symbol a) {
        StateSet result = new StateSet();
        for (State s : this.states) {
            StateSet haveRsulting = s.getResulting().get(a);
            if (haveRsulting != null) {
                result.getStates().addAll(haveRsulting.getStates());
            }
        }
        return result;
    }

    public String getMetric() {
        String metric = new String();
        for (State s : this.states) {
            metric = metric + s.getName();
        }

        if (metric.isEmpty()) {
            metric = "[]";
        }

        return metric;
    }

    public State getByName(String name) {
        for (State s : this.getStates()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
