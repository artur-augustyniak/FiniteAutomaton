/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Artur Augustyniak
 */
public class StructParser extends Parser {

    private LinkedHashMap<String, Object> content;
    private ArrayList<String> alphabet = null;
    private LinkedHashMap<String, String> states = null;
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList>> transitions = null;

    public StructParser(String file) throws FileNotFoundException, IOException {
        super(file);

        Yaml yaml = new Yaml();
        this.content = (LinkedHashMap) yaml.load(this.yamlInput);

        for (Map.Entry<String, Object> entry : this.content.entrySet()) {

            if (entry.getKey().equals("alphabet")) {
                this.alphabet = (ArrayList<String>) entry.getValue();
            }
            if (entry.getKey().equals("states")) {
                this.states = (LinkedHashMap) entry.getValue();
            }
            if (entry.getKey().equals("transitions")) {
                this.transitions = (LinkedHashMap) entry.getValue();
            }
        }
        if (this.alphabet == null || this.states == null || this.transitions == null) {
            throw new IOException(file);
        }
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public LinkedHashMap<String, String> getStates() {
        return states;
    }

    public LinkedHashMap<String, LinkedHashMap<String, ArrayList>> getTransitions() {
        return transitions;
    }
}
