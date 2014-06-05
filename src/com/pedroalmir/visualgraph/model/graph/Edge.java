/**
 * 
 */
package com.pedroalmir.visualgraph.model.graph;

/**
 * @author Pedro Almir
 */
public class Edge {
	private String id;
	private int from;
	private int to;
	private String label;
	private double pheromone;
	
	/**
	 * Default constructor
	 */
	public Edge() {
		
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public int getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(int to) {
		this.to = to;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the pheromone
	 */
	public double getPheromone() {
		return pheromone;
	}
	/**
	 * @param pheromone the pheromone to set
	 */
	public void setPheromone(double pheromone) {
		this.pheromone = pheromone;
	}
}
