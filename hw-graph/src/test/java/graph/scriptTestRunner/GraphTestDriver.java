/*
 * Copyright (C) 2022 Kevin Zatloukal and James Wilcox.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Autumn Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package graph.scriptTestRunner;

import java.io.*;
import java.util.*;

import graph.*;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph.
 **/
public class GraphTestDriver {

    // ***************************
    // ***  JUnit Test Driver  ***
    // ***************************

    /**
     * String -> Graph: maps the names of graphs to the actual graph
     **/
    // TODO for the student: Uncomment and parameterize the next line correctly:
    private final Map<String, Graph> graphs = new HashMap<String, Graph>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new GraphTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public GraphTestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @throws IOException if the input or output sources encounter an IOException
     * @spec.effects Executes the commands read from the input and writes results to the output
     **/
    // Leave this method public
    public void runTests() throws IOException {
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) ||
               (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while(st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch(command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch(Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }

    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        // TODO Insert your code here.

        graphs.put(graphName, new Graph());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        // TODO Insert your code here.

         Graph graph = graphs.get(graphName);
         graph.addNode(nodeName);
         output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         String edgeLabel) {
        // TODO Insert your code here.

         Graph graph = graphs.get(graphName);
         graph.addNode(parentName);
         graph.addNode(childName);
         graph.addEdge(parentName, childName, edgeLabel);
         output.println("added edge " + edgeLabel + " from " + parentName +
                 " to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        // TODO Insert your code here.

        Graph graph = graphs.get(graphName);
        Set<String> nodeSet = new TreeSet<String>(graph.listNodes());
        String list = graphName + " contains:";
        for (String node : nodeSet){
            list += " " + node;
        }
        output.println(list);
    }

    private void listChildren(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        // TODO Insert your code here.
//        Queue<Edge> queue = new LinkedList<Edge>();
//        Set<Edge> visited = new HashSet<Edge>();
//        for(Edge children : graph.listChildren(parentName)){
//            queue.add(children);
//        }
//        while (!queue.isEmpty()) {
//            Edge e = queue.remove();
//            if (!visited.contains(e)) {
//                visited.add(e);
//                list += " " + e.getChild() + "(" + e.getEdgeLabel() + ")";
//                for (Edge child : graph.listChildren(e.getChild())) {
//                    if (!visited.contains(child)) {
//                        queue.add(child);
//                    }
//                }
//            }
//
//        }
//        output.println(list);
//   }
//        Graph graph = graphs.get(graphName);
//        String list = "the children of " + parentName + " in " + graphName + " are:";
//
//        TreeSet<Edge> edgeSet = new TreeSet<Edge>(graph.listChildren(parentName));
//        for(Edge e : edgeSet){
//                list += " " + e.getChild() + "(" + e.getEdgeLabel() + ")";
//        }
//
//        output.println(list);
        Graph graph = graphs.get(graphName);
        String list = ("the children of " + parentName + " in " + graphName + " are:");
        List<String> nodes = new ArrayList<>(graph.listChildren(parentName));
        Collections.sort(nodes);
        if (nodes.size() != 0) {
            for (int i = 0; i < nodes.size(); i++) {
                if (i + 1 == nodes.size()) {
                    String atNode = nodes.get(i);
                    list += " " + atNode + "(" + graph.getLabel(parentName, atNode) + ")";
                    output.println(list);
                } else if (i + 1 != nodes.size()) {
                    list += " " + nodes.get(i) + "(" + graph.getLabel(parentName, nodes.get(i)) + ")";
                    output.print(list);

                }
            }
        } else {
            output.println();
        }
    }


        /**
         * This exception results when the input file cannot be parsed properly
         **/
        static class CommandException extends RuntimeException {

            public CommandException() {
                super();
            }

            public CommandException(String s) {
                super(s);
            }

            public static final long serialVersionUID = 3495;
        }
}
