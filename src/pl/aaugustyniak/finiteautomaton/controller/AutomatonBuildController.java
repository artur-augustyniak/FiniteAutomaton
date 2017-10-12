/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import pl.aaugustyniak.finiteautomaton.model.Automaton;
import pl.aaugustyniak.finiteautomaton.model.AutomatonFactory;
import pl.aaugustyniak.finiteautomaton.parser.StructParser;

/**
 *
 * @author Artur Augustyniak
 */
public class AutomatonBuildController {

    public static Automaton buildAutomatonFromFile(String filename) throws FileNotFoundException, IOException {
        Automaton a = null;
        try {
            a = new AutomatonFactory(new StructParser(filename)).buildAutomaton();
        } catch (FileNotFoundException ex) {
            throw new IOException(filename);
        } catch (IOException ex) {
            throw new IOException(filename);
        }
        return a;
    }
}
