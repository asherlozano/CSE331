package graph;


/**
 * An edge represents an immutable 2 nodes connected from the first node and ending at the second node
 * and attached is a label
 *
 * An Example of an Edge includes <A,B, Label>, <B,A, Label>,
 * <C,B, Label>, <D,C, Label>, <D, D, Label>.
 */
public class Edge {
    private String node1;
    private String node2;
    private String label;

    /**
     * The class edge holds 2 nodes and a name for the labeled edge
     *
     * @param node1 starting node
     * @param node2 ending node
     * @param label name of label
     */
    public Edge(String node1, String node2, String label) {
        if(node1 == null || node2 == null || label == null){
            throw new NullPointerException();
        }
        this.node1 = node1;
        this.node2 = node2;
        this.label = label;
    }

    /**
     * getNode1 is called and returns the starting node of the edge
     *
     * @returns the starting node
     */
    public String getNode1(){
        return node1;
    }
    /**
     * This method when called returns the ending node of the edge
     *
     * @returns the ending node
     */
    public String getNode2(){
        return node2;
    }

    /**
     * This method returns the label of the edge
     * @returns the label of the edge
     */

    public String getEdgeLabel(){
        return label;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Edge){
            if(node1.equals(((Edge)other).node1) && node2.equals(((Edge)other).node2)
                    && label.equals(((Edge)other).label)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return node1.hashCode() + node2.hashCode() + label.hashCode();
    }
}
