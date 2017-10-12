/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.model;

import java.util.LinkedList;

/**
 * Scieżka
 *
 * @author Artur Augustyniak
 */
public class Path {

    public class Jump {

        private Symbol symbol;
        private StateSet ss;

        public Jump(Symbol symbol, StateSet ss) {
            this.symbol = symbol;
            this.ss = ss;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        public void setSymbol(Symbol symbol) {
            this.symbol = symbol;
        }

        public StateSet getSs() {
            return ss;
        }

        public void setSs(StateSet ss) {
            this.ss = ss;
        }
    }
    private LinkedList<Jump> jumps;

    public Path() {
        this.jumps = new LinkedList<Jump>();
    }

    public Path addJump(Symbol symbol, StateSet ss) {
        this.jumps.add(new Jump(symbol, ss));
        return this;
    }

    public LinkedList<Jump> getJumps() {
        return jumps;
    }

    public StateSet getLastJump() {
        return this.jumps.getLast().ss;
    }
}
