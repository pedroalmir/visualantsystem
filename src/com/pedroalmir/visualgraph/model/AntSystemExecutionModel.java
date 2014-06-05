/**
 * 
 */
package com.pedroalmir.visualgraph.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.pedroalmir.visualgraph.model.graph.Edge;
import com.pedroalmir.visualgraph.model.graph.Node;

/**
 * @author Pedro Almir
 */
public class AntSystemExecutionModel {
	private double alpha;
	private double beta;
	private double initialPheromone;
	private double pheromonePersistence;
	private int numAgents;
	private int maxIterations;
	private double q;
	private String strategy;
	
	private int initialNode;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	private ArrayList<Edge> solution;
	
	private int actualIteration;
	private double iterationSolution;
	
	/**
	 * Default constructor
	 */
	public AntSystemExecutionModel() {
		
	}

	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	/**
	 * @return the beta
	 */
	public double getBeta() {
		return beta;
	}

	/**
	 * @param beta the beta to set
	 */
	public void setBeta(double beta) {
		this.beta = beta;
	}

	/**
	 * @return the initialPheromone
	 */
	public double getInitialPheromone() {
		return initialPheromone;
	}

	/**
	 * @param initialPheromone the initialPheromone to set
	 */
	public void setInitialPheromone(double initialPheromone) {
		this.initialPheromone = initialPheromone;
	}

	/**
	 * @return the pheromonePersistence
	 */
	public double getPheromonePersistence() {
		return pheromonePersistence;
	}

	/**
	 * @param pheromonePersistence the pheromonePersistence to set
	 */
	public void setPheromonePersistence(double pheromonePersistence) {
		this.pheromonePersistence = pheromonePersistence;
	}

	/**
	 * @return the numAgents
	 */
	public int getNumAgents() {
		return numAgents;
	}

	/**
	 * @param numAgents the numAgents to set
	 */
	public void setNumAgents(int numAgents) {
		this.numAgents = numAgents;
	}

	/**
	 * @return the maxIterations
	 */
	public int getMaxIterations() {
		return maxIterations;
	}

	/**
	 * @param maxIterations the maxIterations to set
	 */
	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	/**
	 * @return the q
	 */
	public double getQ() {
		return q;
	}

	/**
	 * @param q the q to set
	 */
	public void setQ(double q) {
		this.q = q;
	}

	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the initialNode
	 */
	public int getInitialNode() {
		return initialNode;
	}

	/**
	 * @param initialNode the initialNode to set
	 */
	public void setInitialNode(int initialNode) {
		this.initialNode = initialNode;
	}

	/**
	 * @return the nodes
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * @return the actualIteration
	 */
	public int getActualIteration() {
		return actualIteration;
	}

	/**
	 * @param actualIteration the actualIteration to set
	 */
	public void setActualIteration(int actualIteration) {
		this.actualIteration = actualIteration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	/**
	 * @return the solution
	 */
	public ArrayList<Edge> getSolution() {
		return solution;
	}

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(ArrayList<Edge> solution) {
		this.solution = solution;
	}

	/**
	 * @return the iterationSolution
	 */
	public double getIterationSolution() {
		return iterationSolution;
	}

	/**
	 * @param iterationSolution the iterationSolution to set
	 */
	public void setIterationSolution(double iterationSolution) {
		this.iterationSolution = iterationSolution;
	}
}
