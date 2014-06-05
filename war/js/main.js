/** Data definition */
var nodes = []; var viewNodes = []; var edges = []; var maxI = 0;
var bestResult = Number.MAX_VALUE; var bestPath = []; var storePosition = false;

/** Parse data to JSON */
function parseJSON(data) {
    return window.JSON && window.JSON.parse ? window.JSON.parse(data) : (new Function("return " + data))(); 
}

/** Create nodes and edges */
function createNodesAndEdges(matrix, initialNode){
	nodes = []; edges = [];
	
	/* Create an array with nodes */
	for(var i in matrix){
		var idNode = parseInt(i) + 1;
		if(idNode == initialNode){
			nodes.push({id: idNode, label: idNode + "", color: '#8B0000', fontColor: '#FFFFFF'});
		}else{
			nodes.push({id: idNode, label: idNode + ""});
		}
	}
	
	/* Create an array with edges */
	for(var i in matrix){
		var idNodeFrom = parseInt(i) + 1;
		for(var j in matrix[i]){
			var idNodeTo = parseInt(j) + 1;
			if(matrix[i][j] != 0 && j >= i){
				var x = Math.random();
				edges.push({from: idNodeFrom, to: idNodeTo, label: matrix[i][j], pheromone: 0.0});	
			}
		}
	}
}

/** Plot graph in container */
function plotGraph(nodes, edges, container){
	if(viewNodes.length == 0){
		viewNodes = nodes;
	}
	
	/* Create a graph */
  	var data = {
    	//nodes: nodes,
    	nodes: viewNodes,
    	edges: edges,
  	};
  	/* Options */
 	var options = {
 		width:  '100%',
 		height: '470px',
 		 physics: {
 	        barnesHut: {
 	            enabled: true,
 	            gravitationalConstant: -11650,
 	            centralGravity: 0.1,
 	            springLength: 300,
 	            springConstant: 0.04,
 	            damping: 0.09
 	        }
 	    }
 	}
 	
 	var graph = new vis.Graph(container, data, options);
  	if(!storePosition){
  		storePosition = true;
	 	graph.storePosition();
	 	viewNodes = [];
		for(var key in graph.nodes){
	 		viewNodes.push(graph.nodes[key]);
		}
  	}
}

/** Redraw the graph */
function redraw(result){
	var edgesMap = new Object();
	$.map(result.edges, function(value, key){
		edgesMap[value.id] = value;
	});
	
	for(var i in result.solution){
		var key = result.solution[i]['id'];
		var pheronome = result.solution[i]['pheromone'];
		
		edgesMap[key]['value'] = pheronome;
		edgesMap[key]['color'] = '#006400';
	}
	
	edges = [];
	for(var key in edgesMap){
		edges.push(edgesMap[key]);
	}
	plotGraph(result.nodes, edges, document.getElementById('mygraph'));
}

/** Print the best result */
function printTheBestResult(){
	var edgesMap = new Object();
	$.map(edges, function(value, key){
		edgesMap[value.id] = value;
	});
	
	for(var i in bestPath){
		var key = bestPath[i]['id'];
		edgesMap[key]['color'] = '#006400';
	}
	
	edges = [];
	for(var key in edgesMap){
		edges.push(edgesMap[key]);
	}
	plotGraph(nodes, edges, document.getElementById('mygraph'));
}

/** Send Request */
function sendRequest(runAllIterations){
	var json = {
		/* Ant system configuration */
		alpha: parseJSON($('#alpha').val()),
		beta: parseJSON($('#beta').val()),
		initialPheromone: parseJSON($('#initialPheromone').val()),
		pheromonePersistence: parseJSON($('#pheromonePersistence').val()),
		numAgents: parseJSON($('#numAgents').val()),
		maxIterations: parseJSON($('#maxIterations').val()),
		q: parseJSON($('#q').val()),
		strategy: $('#strategy').val(),
		initialNode: parseJSON($('#firstNode').val()),
		nodes: nodes,
		edges: edges,
		actualIteration: parseJSON($('#actualIteration').val()),
		solution: []
	};
	json = JSON.stringify(json);
	
	$.ajax({
		url: '/executeAntSystem',
		type: 'POST',
		data: {json: json, runAll: runAllIterations},
		beforeSend: function(){
			/* Active the loader */
		}, success: function(data, textStatus, jqXHR){
			/* Parse result to JSON */
			var result = parseJSON(data);
			maxI = result.maxIterations;
			
			/* Atualizando o campo que guarda a iteração atual */
			$('#actualIteration').attr('value', result.actualIteration);
			
			/* Desabilitando o botão para geração do grafo */
			$('#createGraph').attr('disabled', 'disabled');
			
			/* Habilitando novos controles: reset and finish */
			$('#resetSystem').removeAttr('disabled');
			$('#finishSystem').removeAttr('disabled');
			
			/* Atualizando o label do panel de controle */
			$('#labelControl').text('Controls - Iteration ' + result.actualIteration + ' of ' + result.maxIterations);
			$('#visualizationPanel').text('Visualization - The best result found in this iteration was ' + result.iterationSolution);
			
			/* Store the best result */
			if(result.iterationSolution < bestResult){
				bestResult = result.iterationSolution;
				bestPath = result.solution;
			}
			
			/* Just for debug */
			console.log(result.actualIteration, ' - ', result.maxIterations);
			
			if(result.actualIteration == result.maxIterations){
				/* Desabilitando botão de execução e alterando o texto do botão 'Finish' para 'Result'*/
				$('#runAntSystem').attr('disabled', 'disabled');
				$('#finishSystem').html('<span class="glyphicon glyphicon-ok"></span> Result');
				
				/* Just for debug */
				console.log('The best result: ' + bestResult, 'Path: ' + bestPath)
				
				$('#visualizationPanel').text('Visualization - The best result found was ' + bestResult + '.');
				printTheBestResult();
			}else{
				/* Atualizando botão 'Run Ant System' to 'Next Iteration' */
				$('#runAntSystem').html('<span class="glyphicon glyphicon-step-forward"></span> Next Iteration');
			}
			
			/* Redraw the graphical */
			redraw(result);
		}, error: function(jqXHR, textStatus, errorThrown){
			console.log(textStatus);
		}, complete: function(jqXHR, textStatus){
			/* Disable the loader*/
		}
	});
}

$(document).ready(function() {
	/* Graph container */
	var container = document.getElementById('mygraph');
	
	/* Create Graph button */
	$('#createGraph').click(function(){
		var matrix = parseJSON($('#matrix').val());
		var initialNode = parseJSON($('#firstNode').val());
		/* Create nodes and edges */
		createNodesAndEdges(matrix, initialNode);
		/* Plot graph in container */
		plotGraph(nodes, edges, container);
		/* Enable Run Algorithm button */
		$('#runAntSystem').removeAttr('disabled');
	});
	
	/* Reset system and reload page */
	$('#resetSystem').click(function(){
		location.reload();
	});
	
	/* Finish Algorithm */
	$('#finishSystem').click(function(){
		console.log(parseJSON($('#actualIteration').val()), maxI);
		if(parseJSON($('#actualIteration').val()) == maxI){
			$('#visualizationPanel').text('Visualization - The best result found was ' + bestResult);
			printTheBestResult();
		}else{
			sendRequest(true);
		}
	});
	
	/* Run Algorithm button */
	$('#runAntSystem').click(function(){
		sendRequest(false);
	});			  	
});