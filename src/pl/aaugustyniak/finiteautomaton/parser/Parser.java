/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author Artur Augustyniak
 */
public abstract class Parser {

    protected File yamlFile;
    InputStream yamlInput;

    public Parser(String file) throws FileNotFoundException {
        this.yamlFile = new File(file);
        this.yamlInput = new FileInputStream(this.yamlFile);
    }
}
