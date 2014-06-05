package com.pedroalmir.visualgraph.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pedroalmir.visualgraph.aco.AntSystemControllerTSP;
import com.pedroalmir.visualgraph.aco.model.solution.IterationSolution;
import com.pedroalmir.visualgraph.aco.model.solution.Solution;
import com.pedroalmir.visualgraph.model.AntSystemExecutionModel;
import com.pedroalmir.visualgraph.util.Converter;

@SuppressWarnings("serial")
public class AntSystemServlet extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = request.getParameter("json");
		Boolean runAll = Boolean.valueOf(request.getParameter("runAll"));
		
		/* Parse JSON to Java Object */
		Gson gson = new Gson();
		AntSystemExecutionModel executionModel = gson.fromJson(json, AntSystemExecutionModel.class);
		AntSystemControllerTSP antSystemControllerTSP = Converter.convertAntSystemExecutionModel(executionModel);
		
		/* Execute the ant system */
		AntSystemExecutionModel newExecutionModel = null;
		if(runAll){
			Solution solution = antSystemControllerTSP.execute();
			newExecutionModel = Converter.convertAntSystemController(executionModel, antSystemControllerTSP, solution.getBetterIterarion());
		}else{
			IterationSolution iterationSolution = antSystemControllerTSP.executeAnIteration();
			newExecutionModel = Converter.convertAntSystemController(executionModel, antSystemControllerTSP, iterationSolution);
		}
		
		/* Set content type of the response so that jQuery knows what it can expect. */
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
	    /* Write response body. */
	    response.getWriter().write(gson.toJson(newExecutionModel));
	}
}
