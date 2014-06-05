<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<meta name="author" content="Pedro Almir">
    	<meta name="description" content="Ant Colony Optimization - TSP Problem">
    	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    	
		<!-- Bootstrap core CSS -->
	    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	    <!-- Custom styles for this template -->
	    <link href="${pageContext.request.contextPath}/css/sticky-footer-navbar.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/vis.css" rel="stylesheet" type="text/css" />
	
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
	    <!-- ========================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="${pageContext.request.contextPath}/js/vis.js"></script>
	    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/docs.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/main.js"></script>
	</head>
	<body>
		<!-- Fixed navbar -->
    	<div class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	           			<span class="sr-only">Toggle navigation</span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
          			</button>
          			<a class="navbar-brand" href="#">Ant Colony Optimization</a>
        		</div>
        		<div class="collapse navbar-collapse">
          			<ul class="nav navbar-nav">
            			<li class="active"><a href="#">Home</a></li>
            			<li><a href="#about">About</a></li>
            			<li><a href="#contact">Contact</a></li>
          			</ul>
        		</div><!--/.nav-collapse -->
      		</div>
    	</div>
    	<!-- Begin page content -->
    	<div class="container">
      		<div class="page-header">
        		<h2>Ant Colony Optimization - TSP Problem</h2>
      		</div>
      		<p class="lead">Pin a fixed-height footer to the bottom of the viewport in desktop browsers with this custom HTML and CSS. A fixed navbar has been added with <code>padding-top: 60px;</code> on the <code>body > .container</code>.</p>
      		<div class="row">
      			<div class="col-md-5">
      				<div class="panel panel-default">
						<div class="panel-heading">
					    	<h3 class="panel-title">Travelling salesman problem</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
					  			<div class="form-group">
							    	<label for="matrix">Matrix definition (Use JSON pattern)</label>
						    		<textarea class="form-control" rows="5" id="matrix" placeholder="Define here the matrix of problem">[[0, 7, 4, 3, 11, 1], [7, 0, 2, 8, 10, 8], [4, 2, 0, 9, 9, 3], [3, 8, 9, 0, 5, 4], [11, 10, 9, 5, 0, 3], [1, 8, 3, 4, 3, 0]]
						    		</textarea>
							  	</div>
							  	<div class="form-group">
							    	<label for="firstNode">First node</label>
							    	<input type="text" class="form-control" id="firstNode" placeholder="Define here the first node for TSP problem" value="2">
							  	</div>
					  		</form>
					  	</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
					    	<h3 class="panel-title">Ant Colony Optimization</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="alpha">Alpha</label>
								    	<input type="text" class="form-control" id="alpha" placeholder="Alpha value" value="1.0">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="beta">Beta</label>
								    	<input type="text" class="form-control" id="beta" placeholder="Beta value" value="2.0">
							    	</div>
							  	</div>
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="initialPheromone">Initial Pheromone Trails</label>
								    	<input type="text" class="form-control" id="initialPheromone" placeholder="Initial Pheromone Trails" value="0.1">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="pheromonePersistence">Pheromone Persistence</label>
								    	<input type="text" class="form-control" id="pheromonePersistence" placeholder="Pheromone Persistence" value="0.5">
							    	</div>
							  	</div>
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="numAgents">Number of Ants</label>
								    	<input type="text" class="form-control" id="numAgents" placeholder="Number of Ants" value="3">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="maxIterations">Number of Iterations</label>
								    	<input type="text" class="form-control" id="maxIterations" placeholder="Number of Iterations" value="10">
							    	</div>
							  	</div>
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="q">Q</label>
								    	<input type="text" class="form-control" id="q" placeholder="Q value" value="1.0">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="strategy">Strategy</label>
								    	<select class="form-control" id="strategy">
											  <option value="ant_cycle">Ant Cycle</option>
											  <option value="ant_density">Ant Density</option>
											  <option value="ant_quantity">Ant Quantity</option>
										</select>
							    	</div>
							  	</div>
					  		</form>
					  	</div>
					</div>
      			</div>
      			<div class="col-md-7">
      				<div class="panel panel-primary" style="height: 542px;">
						<div class="panel-heading">
					    	<h3 id="visualizationPanel" class="panel-title">Visualization</h3>
					  	</div>
					  	<div class="panel-body text-center">
					  		<div id="mygraph" style="height: 470px;"></div>
					  	</div>
					</div>
					<div class="panel panel-default" style="height: 120px;">
						<div class="panel-heading">
					    	<h3 id="labelControl" class="panel-title">Controls</h3>
					  	</div>
					  	<div class="panel-body">
					  		<input id="actualIteration" type="hidden" value="0">
							<button id="createGraph" type="button" class="btn btn-primary btn-lg" style="width: 29%;">
								<span class="glyphicon glyphicon-cog"></span> Create Graph
							</button>
							<button id="runAntSystem" type="button" class="btn btn-primary btn-lg" disabled="disabled" style="width: 29%;">
								<span class="glyphicon glyphicon-play"></span> Run Ant System
							</button>
							
							<button id="resetSystem" type="button" class="btn btn-warning btn-lg" disabled="disabled" style="width: 19%;">
								<span class="glyphicon glyphicon-refresh"></span> Reset
							</button>
							<button id="finishSystem" type="button" class="btn btn-success btn-lg" disabled="disabled" style="width: 19%;">
								<span class="glyphicon glyphicon-fast-forward"></span> Finish
							</button>
					  	</div>
					</div>
      			</div>
      		</div>
    	</div>
	</body>
</html>