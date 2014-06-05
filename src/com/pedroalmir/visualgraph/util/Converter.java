/**
 * 
 */
package com.pedroalmir.visualgraph.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.pedroalmir.visualgraph.aco.AntSystemControllerTSP;
import com.pedroalmir.visualgraph.aco.model.enums.StrategyAS;
import com.pedroalmir.visualgraph.aco.model.graph.Edge;
import com.pedroalmir.visualgraph.aco.model.graph.Graph;
import com.pedroalmir.visualgraph.aco.model.graph.Node;
import com.pedroalmir.visualgraph.aco.model.problem.AntConfiguration;
import com.pedroalmir.visualgraph.aco.model.solution.IterationSolution;
import com.pedroalmir.visualgraph.model.AntSystemExecutionModel;

/**
 * @author Pedro Almir
 *
 */
public class Converter {
	
	/**
	 * @param executionModel
	 * @param controllerTSP
	 * @param iterationSolution
	 * @return
	 */
	public static AntSystemExecutionModel convertAntSystemController(AntSystemExecutionModel executionModel, AntSystemControllerTSP controllerTSP, IterationSolution iterationSolution){
		/* Edges */
		HashMap<String, com.pedroalmir.visualgraph.model.graph.Edge> vMap = new HashMap<String, com.pedroalmir.visualgraph.model.graph.Edge>();
		for(com.pedroalmir.visualgraph.model.graph.Edge vEdge : executionModel.getEdges()){
			double pheromone = 0.0; int count = 0;
			for(Edge e : controllerTSP.getGraph().getEdges()){
				if(e.getViewID().equals(vEdge.getId())){
					pheromone += e.getPheromone();
					count++;
				}
				if(count == 2){
					break;
				}
			}
			vEdge.setPheromone(Double.valueOf(String.format("%.2f", pheromone).replace(",", ".")));
			vMap.put(vEdge.getId(), vEdge);
		}
		
		if(executionModel.getSolution() == null){
			executionModel.setSolution(new ArrayList<com.pedroalmir.visualgraph.model.graph.Edge>());
		}
		
		/* Iteration Solution */
		for(Edge edge : iterationSolution.getTheBestAnt().getEdgeList()){
			executionModel.getSolution().add(vMap.get(edge.getViewID()));
		}
		
		executionModel.setIterationSolution(iterationSolution.getBetterSolution());
		executionModel.setActualIteration(controllerTSP.getActualIteration());
		return executionModel;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	public static AntSystemControllerTSP convertAntSystemExecutionModel(AntSystemExecutionModel executionModel){
		StrategyAS strategy = null;
		if(executionModel.getStrategy() == "ant_cycle"){
			strategy = StrategyAS.ANT_CYCLE;
		}else if(executionModel.getStrategy() == "ant_density"){
			strategy = StrategyAS.ANT_DENSITY;
		}else if(executionModel.getStrategy() == "ant_quantity"){
			strategy = StrategyAS.ANT_QUANTITY;
		}else{
			strategy = StrategyAS.ANT_CYCLE;
		}
		/* Ant System Configuration */
		AntConfiguration config = new AntConfiguration("Visual Ant System", executionModel.getAlpha(), executionModel.getBeta(), 
				executionModel.getQ(), executionModel.getPheromonePersistence(), executionModel.getInitialPheromone(), executionModel.getNumAgents(), 
				executionModel.getMaxIterations(), strategy);
		/* Nodes */
		HashMap<Integer, Node> mapOfNodes = new HashMap<Integer, Node>();
		LinkedList<Node> nodes = new LinkedList<Node>();
		
		for(com.pedroalmir.visualgraph.model.graph.Node node : executionModel.getNodes()){
			Node newNode = new Node(node.getId(), node.getLabel());
			nodes.add(newNode);
			mapOfNodes.put(node.getId(), newNode);
		}
		
		/* Edges */
		LinkedList<Edge> edges = new LinkedList<Edge>();
		long globalCount = 1;
		for(com.pedroalmir.visualgraph.model.graph.Edge edge : executionModel.getEdges()){
			edges.add(new Edge(globalCount++, edge.getId(), mapOfNodes.get(edge.getFrom()), mapOfNodes.get(edge.getTo()), Double.valueOf(edge.getLabel()), edge.getPheromone()/2));
			edges.add(new Edge(globalCount++, edge.getId(), mapOfNodes.get(edge.getTo()), mapOfNodes.get(edge.getFrom()), Double.valueOf(edge.getLabel()), edge.getPheromone()/2));
		}
		
		/* Graph */
		Graph graph = new Graph(1l, "Visual Ant System Graph", nodes, edges);
		Node initialNode = mapOfNodes.get(executionModel.getInitialNode());
		AntSystemControllerTSP antSystem = new AntSystemControllerTSP(graph, initialNode, config, executionModel.getActualIteration());
		return antSystem;
	}
}
