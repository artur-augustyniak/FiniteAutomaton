/*
 * Copyright (c) 2013 Artur Augustyniak
 * 
 */
package pl.aaugustyniak.finiteautomaton.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import pl.aaugustyniak.finiteautomaton.model.State.StateType;

/**
 * Uogólniona klasa automatu skończonego Konstrukcja Klasy State i StateSet
 * wykazuje równoważność NFA i wynikającego ze zbiorów osiągalnych stanów DFA
 *
 * @author Artur Augustyniak
 */
public class Automaton {

    private Alphabet alphabet;
    private StateSet states;
    private HashMap<StateSet, HashMap<Symbol, StateSet>> transitionsPowerSet;
    private Path lastPath;
    private StateSet currentPointer;

    private StateSet getInputState() {
        StateSet inpSt = new StateSet();
        inpSt.setIsInput(true);
        for (State s : this.states.getStates()) {
            if (s.getsType() == StateType.COMBINED || s.getsType() == StateType.INPUT) {
                inpSt.addState(s);
            }
        }
        return inpSt;
    }

    private StateType simonSays(StateSet states) {

        StateType decission = State.StateType.REJECT;
        for (State s : states.getStates()) {
            if (s.getsType().equals(State.StateType.ACCEPT) || s.getsType().equals(State.StateType.COMBINED)) {
                decission = State.StateType.ACCEPT;
            }
        }
        return decission;
    }

    public Automaton(Alphabet alphabet, StateSet ss) {
        this.alphabet = alphabet;
        this.states = ss;
    }

    public StateType processWord(Word w) {
        StateSet activeStates = getInputState();
        this.lastPath = new Path();
        for (Symbol element : w) {

            activeStates = activeStates.getResultingForSymbol(element);
            this.lastPath.addJump(element, activeStates);

        }
        return simonSays(activeStates);
    }

    public HashMap<StateSet, HashMap<Symbol, StateSet>> getTransitionsPowerSet() {

        transitionsPowerSet = new HashMap<StateSet, HashMap<Symbol, StateSet>>();
        Queue<StateSet> Q = new LinkedList<StateSet>();
        Q.add(getInputState());

        StateSet M, O;
        HashMap<Symbol, StateSet> destination;
        HashSet<String> uniqGuard = new HashSet<String>();
        while (!Q.isEmpty()) {
            M = Q.remove();
            destination = new HashMap<Symbol, StateSet>();
            for (Symbol symb : this.alphabet.getAlphabet()) {
                O = M.getResultingForSymbol(symb);
                destination.put(symb, O);
                if (uniqGuard.add(O.getMetric())) {
                    Q.add(O);
                }
            }
            this.transitionsPowerSet.put(M, destination);
        }

        return this.transitionsPowerSet;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public StateSet getStates() {
        return states;
    }

    public Path getLastPath() {
        return lastPath;
    }
}
