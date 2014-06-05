/**
 * 
 */
package com.pedroalmir.visualgraph.aco.model.solution;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.visualgraph.aco.model.Ant;
import com.pedroalmir.visualgraph.aco.model.graph.Node;
import com.pedroalmir.visualgraph.aco.model.problem.AntConfiguration;

/**
 * Solution
 * @author Pedro Almir
 */
public class Solution implements Comparable<Solution>{
	/** Results */
	private Ant theBestAnt;
	private double cost;
	/** Execution time */
	private long executionTime;
	/** Configuration */
	private AntConfiguration antConfiguration;
	/** */
	private double betterSolution;
	private double worstSolution;
	private double average;
	private double standardDeviation;
	private String path;
	/** */
	private List<IterationSolution> iterationSolutions;
	
	/**
	 * @param antConfiguration
	 * @param cost
	 * @param executionTime
	 */
	public Solution(AntConfiguration antConfiguration, double cost, long executionTime) {
		super();
		this.cost = cost;
		this.antConfiguration = antConfiguration;
		this.executionTime = executionTime;
		this.iterationSolutions = new LinkedList<IterationSolution>();
	}
	
	/**
	 * Default constructor
	 */
	public Solution() {
		this.iterationSolutions = new LinkedList<IterationSolution>();
	}
	
	/**
	 * Get the best iteration
	 * @return solution
	 */
	public IterationSolution getBetterIterarion(){
		IterationSolution better = null;
		double betterSolution = Double.MAX_VALUE;
		for(IterationSolution it : this.iterationSolutions){
			if(it.getBetterSolution() < betterSolution){
				betterSolution = it.getBetterSolution();
				better = it;
			}
		}
		return better;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Analise do Comportamento do Algoritmo: " + this.antConfiguration.getAlgorithmName() + "\n");
		buffer.append("Nós selecionados: " + this.printPath(this.theBestAnt) + "\n");
		buffer.append("Custo Total: " + String.format("%.4f", this.cost));
		return buffer.toString();
	}
	
	/**
	 */
	public List<Object[]> printThis(){
    	List<Object[]> data = new ArrayList<Object[]>();
		String[] emptyLine = new String[]{""};
		/* Default */
		data.add(new String[]{"Algorithm: " + this.antConfiguration.getAlgorithmName()});
		data.add(emptyLine);
		
		if(this.antConfiguration != null){
			data.add(new Object[]{"Number of Ants", this.antConfiguration.getNumAgents(), "Q", Double.valueOf(String.format("%.4f", this.antConfiguration.getQ()).replace(',', '.'))});
			data.add(new Object[]{"Alpha", Double.valueOf(String.format("%.2f", this.antConfiguration.getAlpha()).replace(',', '.')), "Beta", Double.valueOf(String.format("%.2f", this.antConfiguration.getBeta()).replace(',', '.'))});
			data.add(new Object[]{"Initial Pheromone", Double.valueOf(String.format("%.2f", this.antConfiguration.getInitialPheromone()).replace(',', '.')), "Pheromone Persistence", Double.valueOf(String.format("%.2f", this.antConfiguration.getPheromonePersistence()).replace(',', '.'))});
			data.add(new Object[]{"Iterations", this.antConfiguration.getMaxIterations(), "Executions", this.antConfiguration.getMaxExecutions()});
			data.add(emptyLine);
			data.add(new String[]{"Path", this.printPath(this.theBestAnt), "Execution Time", "[" + new SimpleDateFormat("mm:ss:SSS").format(this.executionTime) + "]"});
			data.add(new Object[]{"Cost", Double.valueOf(String.format("%.4f", this.cost).replace(',', '.')), "Execution Time (ms)", this.executionTime});
			
			data.add(emptyLine);
			
			int count = 1;
			Object[] header = new Object[this.iterationSolutions.size()+1];
			Object[] betterSolution = new Object[this.iterationSolutions.size()+1];
			Object[] worstSolution = new Object[this.iterationSolutions.size()+1];
			Object[] average = new Object[this.iterationSolutions.size()+1];
			Object[] standardDeviation = new Object[this.iterationSolutions.size()+1];
			Object[] elitism = new Object[this.iterationSolutions.size()+1];
			
			header[0] 			 = "";
			betterSolution[0] 	 = "BetterSolution";
			worstSolution[0] 	 = "WorstSolution";
			average[0] 			 = "Average";
			standardDeviation[0] = "StandardDeviation";
			elitism[0] 			 = "Elitism";

			for(IterationSolution it : this.iterationSolutions){
				header[count] 			 = "Iteration " + count;
				betterSolution[count] 	 = Double.valueOf(String.format("%.4f", it.getBetterSolution()).replace(',', '.'));
				worstSolution[count] 	 = Double.valueOf(String.format("%.4f", it.getWorstSolution()).replace(',', '.'));
				average[count] 			 = Double.valueOf(String.format("%.4f", it.getAverage()).replace(',', '.'));
				standardDeviation[count] = Double.valueOf(String.format("%.4f", it.getStandardDeviation()).replace(',', '.'));
				elitism[count] 			 = Double.valueOf(String.format("%.4f", it.getTheBest()).replace(',', '.'));
				count++;
			}
			
			data.add(header);
			data.add(betterSolution);
			data.add(worstSolution);
			data.add(average);
			data.add(standardDeviation);
			data.add(elitism);
		}
		return data;
	}
	
	
	/**
	 * @param theBestAnt
	 * @return
	 */
	private String printPath(Ant theBestAnt) {
		ArrayList<String> path = new ArrayList<String>();
		for(Node node : theBestAnt.getTabuList()){
			path.add(node.getInformations().get("id").toString());
		}
		return Arrays.toString(path.toArray());
	}

	/**
	 * @return the executionTime
	 */
	public long getExecutionTime() {
		return executionTime;
	}
	/**
	 * @param executionTime the executionTime to set
	 */
	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
	
	/**
	 * @return the iterationSolutions
	 */
	public List<IterationSolution> getIterationSolutions() {
		return iterationSolutions;
	}

	/**
	 * @param iterationSolutions the iterationSolutions to set
	 */
	public void setIterationSolutions(List<IterationSolution> iterationSolutions) {
		this.iterationSolutions = iterationSolutions;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(Solution o) {
		return new Double(o.getCost()).compareTo(new Double(this.cost));
	}

	/**
	 * @return the theBestAnt
	 */
	public Ant getTheBestAnt() {
		return theBestAnt;
	}

	/**
	 * @param theBestAnt the theBestAnt to set
	 */
	public void setTheBestAnt(Ant theBestAnt) {
		this.theBestAnt = theBestAnt;
	}

	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * @param average the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}

	/**
	 * @return the standardDeviation
	 */
	public double getStandardDeviation() {
		return standardDeviation;
	}

	/**
	 * @param standardDeviation the standardDeviation to set
	 */
	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	/**
	 * @return the antConfiguration
	 */
	public AntConfiguration getAntConfiguration() {
		return antConfiguration;
	}

	/**
	 * @param antConfiguration the antConfiguration to set
	 */
	public void setAntConfiguration(AntConfiguration antConfiguration) {
		this.antConfiguration = antConfiguration;
	}

	/**
	 * @return the betterSolution
	 */
	public double getBetterSolution() {
		return betterSolution;
	}

	/**
	 * @param betterSolution the betterSolution to set
	 */
	public void setBetterSolution(double betterSolution) {
		this.betterSolution = betterSolution;
	}

	/**
	 * @return the worstSolution
	 */
	public double getWorstSolution() {
		return worstSolution;
	}

	/**
	 * @param worstSolution the worstSolution to set
	 */
	public void setWorstSolution(double worstSolution) {
		this.worstSolution = worstSolution;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
}

