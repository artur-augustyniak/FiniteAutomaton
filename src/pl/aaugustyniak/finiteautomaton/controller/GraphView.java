/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.finiteautomaton.controller;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.Color;
import java.awt.Paint;
import java.util.Map;
import javax.swing.JComponent;
import org.apache.commons.collections15.Transformer;
import pl.aaugustyniak.finiteautomaton.model.Automaton;
import pl.aaugustyniak.finiteautomaton.model.State;
import static pl.aaugustyniak.finiteautomaton.model.State.StateType.ACCEPT;
import static pl.aaugustyniak.finiteautomaton.model.State.StateType.COMBINED;
import static pl.aaugustyniak.finiteautomaton.model.State.StateType.EMPTY;
import static pl.aaugustyniak.finiteautomaton.model.State.StateType.INPUT;
import pl.aaugustyniak.finiteautomaton.model.StateSet;
import pl.aaugustyniak.finiteautomaton.model.Symbol;

/**
 *
 * @author Artur Augustyniak
 */
public class GraphView {

    Graph<State, Edge> g;

    private class Edge {

        public Edge(Long id, String label) {
            this.id = id;
            this.label = label;
        }
        private Long id;
        private String label;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    private class uniqEdgeTransformer implements Transformer<Edge, String> {

        @Override
        public String transform(Edge i) {
            return i.getLabel();
        }
    }

    private class stateTransformer implements Transformer<State, String> {

        @Override
        public String transform(State i) {
            return i.getName();
        }
    }

    private class stateTooltipTransformer implements Transformer<State, String> {

        @Override
        public String transform(State i) {
            switch (i.getsType()) {
                case COMBINED:
                    return "Węzeł wejściowy i akceptujący";
                case INPUT:
                    return "Węzeł wejściowy";
                case ACCEPT:
                    return "Węzeł akceptujący";
                case EMPTY:
                    return "Stan pusty";
                default:
                    return "Węzeł odrzucający";
            }
        }
    }

    private class edgeTooltipTransformer implements Transformer<Edge, String> {

        @Override
        public String transform(Edge i) {
            return i.getLabel();
        }
    }

    private class stateTypePaintTransformer implements Transformer<State, Paint> {

        @Override
        public Paint transform(State i) {
            switch (i.getsType()) {
                case COMBINED:
                    return Color.ORANGE;
                case INPUT:
                    return Color.CYAN;
                case ACCEPT:
                    return Color.GREEN;
                case EMPTY:
                    return Color.GRAY;
                default:
                    return Color.RED;
            }
        }
    }

    public GraphView(Automaton a) {
        g = new DirectedOrderedSparseMultigraph<State, Edge>();
        Long edgeId = 1L;
        for (State s : a.getStates().getStates()) {
            for (Map.Entry<Symbol, StateSet> entry : s.getResulting().entrySet()) {
                Symbol key = entry.getKey();
                StateSet val = entry.getValue();
                for (State rs : val.getStates()) {
                    g.addEdge(new Edge(edgeId, key.getContent().toString()), s, rs);
                    edgeId++;
                }
            }
        }
    }

    public JComponent getVisualizationServer() {
        Layout<State, Edge> layout;
        layout = new ISOMLayout(g);
        //layout.setSize(new Dimension(600, 600));
        VisualizationViewer<State, Edge> vv = new VisualizationViewer<State, Edge>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(new stateTypePaintTransformer());
        vv.getRenderContext().setVertexLabelTransformer(new stateTransformer());
        vv.getRenderContext().setEdgeLabelTransformer(new uniqEdgeTransformer());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        vv.getRenderContext().setLabelOffset(1);
        vv.setVertexToolTipTransformer(new stateTooltipTransformer());
        vv.setEdgeToolTipTransformer(new edgeTooltipTransformer());
        //vv.setPreferredSize(new Dimension(600, 600));
        return vv;
    }
}
