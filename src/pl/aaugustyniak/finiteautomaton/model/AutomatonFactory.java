/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import pl.aaugustyniak.finiteautomaton.model.State.StateType;
import pl.aaugustyniak.finiteautomaton.parser.StructParser;

/**
 *
 * @author Artur Augustyniak
 */
public class AutomatonFactory {

    private StructParser pa;
    private Alphabet alphabet;
    private StateSet ss;

    public AutomatonFactory(StructParser p) {
        this.pa = p;
        buildAlphabet();
        buildStates();
        addTransitions();
    }

    private void buildAlphabet() {
        this.alphabet = new Alphabet();
        for (String s : this.pa.getAlphabet()) {
            alphabet.addSymbol(new Symbol(s.toString()));
        }
    }

    private void buildStates() {
        this.ss = new StateSet();
        for (Map.Entry<String, String> entry : this.pa.getStates().entrySet()) {
            ss.addState(new State(entry.getKey(), StateType.valueOf(entry.getValue())));
        }
    }

    private void addTransitions() {
        State cState;
        for (Map.Entry<String, LinkedHashMap<String, ArrayList>> entry : this.pa.getTransitions().entrySet()) {
            cState = this.ss.getByName(entry.getKey());
            for (Map.Entry<String, ArrayList> t : entry.getValue().entrySet()) {

                for (Object resName : t.getValue()) {
                    cState.addResulting(this.alphabet.getByContentString(t.getKey()), this.ss.getByName(resName.toString()));
                }

            }
        }
    }

    public Automaton buildAutomaton() {
        return new Automaton(this.alphabet, this.ss);
    }
}
