/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton;

import pl.aaugustyniak.finiteautomaton.view.MainWindow;

/**
 *
 * @author Artur Augustyniak
 */
public class FiniteAutomaton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow frame = new MainWindow();
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
