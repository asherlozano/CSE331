package graph;

import java.util.Objects;

/**
 * An edge represents an immutable 2 nodes connected from the first node and ending at the second node
 * and attached is a label
 *
 * An Example of an Edge includes (A,B, Label), (B,A, Label),
 * (C,B, Label), (D,C, Label), (D, D, Label).
 */

     //AF: An Edge is e12 = e(parent, then child)
     // p = parent c = child and l = label. An Edge looks like this (p, c, l)

    //REP INV: parent != null child != null and label != null
public class Edge<N, D> {
    private N parent;
    private N child;
    private D label;

    /**
     * The class edge holds 2 nodes and a name for the labeled edge
     *
     * @param parent starting node
     * @param child ending node
     * @param label name of label
     */
    public Edge(N parent, N child, D label) {
        if(parent == null || child == null || label == null){
            throw new NullPointerException();
        }
        this.parent = parent;
        this.child = child;
        this.label = label;
    }

    /**
     * getParent is called and returns the starting node of the edge
     *
     * @return the starting node
     */
    public N getParent(){
        return parent;
    }
    /**
     * This method when called returns the ending node of the edge
     *
     * @return the child node
     */
    public N getChild(){
        return child;
    }

    /**
     * This method returns the label of the edge
     * @return the label of the edge
     */

    public D getEdgeLabel(){
        return label;
    }

    @Override
    /**
     * This method overrides the equals methods for edges
     * @return true or false if an edge is equal
     */
    public boolean equals(Object other){
        if(!(other instanceof Edge<?,?>)){
            return false;
        }
        Edge<?, ?> o = (Edge<?, ?>) other;
        return this.child.equals(o.child) && this.label.equals(o.label);
    }


    /**
     * This method returns the hashcode of an edge
     * @return the hashcode of an edge
     */
    @Override
    public int hashCode(){
        return Objects.hash(parent, child, label);
    }
}
