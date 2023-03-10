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

package pathfinder.scriptTestRunner;
import graph.Graph;
import pathfinder.Dijkstra;
import pathfinder.datastructures.Path;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {



    // Leave this constructor public
    private final Map<String, Graph<String, Double>> pathfinderMap = new HashMap<>();
    public PrintWriter output;
    public BufferedReader input;
    public PathfinderTestDriver(Reader r, Writer w) {
        // TODO: Implement this, reading commands from `r` and writing output to `w`.
        // See GraphTestDriver as an example.
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    // Leave this method public
    public void runTests() throws IOException {
        // TODO: Implement this.
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
            switch (command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
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
        } catch (Exception e) {
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

            pathfinderMap.put(graphName, new Graph<String, Double>());
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

            Graph<String,Double> graph = pathfinderMap.get(graphName);
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
            Double label = Double.valueOf(edgeLabel);
            Graph<String, Double> graph = pathfinderMap.get(graphName);
            graph.addEdge(parentName, childName, label);
            output.println("added edge " + edgeLabel + "00 from " + parentName +
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

            Graph<String, Double> graph = pathfinderMap.get(graphName);
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
        Graph<String, Double> graph = pathfinderMap.get(graphName);
        String node = parentName;
        Set<String> nodes = graph.listNodes();
        String temp = "the children of " + parentName + " in " + graphName + " are:";
        for (String n : nodes) {
            if (n.equals(parentName)) {
                node = n;
            }
        }
        Set<Graph.Edge> edges = new TreeSet<Graph.Edge>(new Comparator<Graph.Edge>() {
            @Override
            public int compare(Graph.Edge o1, Graph.Edge o2) {
                if (o1.getChild().equals(o2.getChild())) {
                    return ((String)o1.getEdgeLabel()).compareTo((String)o2.getEdgeLabel());
                }
                return ((String)o1.getChild()).compareTo((String)o2.getChild());
            }
        });
        edges.addAll(graph.listNodeEdges(node));
        for (Graph.Edge e : edges) {
            temp += " " + e.getChild() + "(" + e.getEdgeLabel() + ")";
        }
        output.println(temp);
    }
    private void findPath(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new CommandException("Bad arguments to FindPath: " + arguments);
        }
        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        findPath(graphName, parentName, childName);
    }

    private void findPath(String graphName, String parentName, String childName) {
        Graph<String, Double> graph = pathfinderMap.get(graphName);
        if ((!(graph.listNodes().contains(parentName)) &&
                (!graph.listNodes().contains(childName)))) {
            output.println("unknown: " + parentName);
            output.println("unknown: " + childName);
        } else if ((!graph.listNodes().contains((childName)))) {
            output.println("unknown: " + childName);
        } else if (!(graph.listNodes().contains((parentName)))) {
            output.println("unknown: " + parentName);
        }
            if (graph.listNodes().contains(parentName) &&
                    graph.listNodes().contains(childName)) {
                String result = "path from " + parentName + " to " + childName + ":";
                Path<String> smallest = Dijkstra.findPath(graph, parentName, childName);
                for (Path<String>.Segment b : smallest) {
                    result += "\n" + b.getStart() + " to " + b.getEnd() +" with weight " + b.getCost() + "00";
                }
                output.println(result + "\n" + "total cost: " + smallest.getCost() + "00");
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
