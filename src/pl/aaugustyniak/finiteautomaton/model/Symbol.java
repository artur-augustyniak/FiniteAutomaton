/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.model;

/**
 *
 * @author Artur Augustyniak
 */
public class Symbol {

    private Object content;

    public Symbol(Object o) {
        content = o;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {

        return this.content.toString();
    }
}
