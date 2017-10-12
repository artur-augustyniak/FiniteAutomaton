/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.model;

import java.util.HashSet;

/**
 *
 * @author Artur Augustyniak
 */
public class Alphabet {

    private HashSet<Symbol> alphabet;

    public Alphabet() {
        this.alphabet = new HashSet<Symbol>();
    }

    public Alphabet addSymbol(Symbol s) {
        this.alphabet.add(s);
        return this;
    }

    public HashSet<Symbol> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(HashSet<Symbol> alphabet) {
        this.alphabet = alphabet;
    }

    public Symbol getLike(Symbol s) {

        for (Symbol sym : this.alphabet) {
            if (sym.getContent().equals(s.getContent())) {
                return sym;
            }

        }

        return null;
    }

    public Symbol getByContentString(String tString) {
        for (Symbol s : this.alphabet) {
            if (s.getContent().toString().equals(tString)) {
                return s;
            }
        }
        return null;
    }
}
