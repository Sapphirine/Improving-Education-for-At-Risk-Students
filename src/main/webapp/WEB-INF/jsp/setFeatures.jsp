<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Student Tracker</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Improving Education for At-risk Students</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse"></div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
		<br><br>
			<h1>Set Student Features</h1>
			<form:form commandName="feature" id="form1">
				<div class="form-group">
					<label>Student Name</label>
					<form:input class="form-control" path="name" />
				</div>
				<div class="form-group">
					<label>Kindergarten Reading</label>
					<form:input class="form-control" path="kReading" />
				</div>
				<div class="form-group">
					<label>Kindergarten Math</label>
					<form:input class="form-control" path="kMath" />
				</div>
				<div class="form-group">
					<label>First Grade Reading</label>
					<form:input class="form-control" path="fReading" />
				</div>
				<div class="form-group">
					<label>First Grade Math</label>
					<form:input class="form-control" path="fMath" />
				</div>
				<div class="form-group">
					<label>Third Grade Reading</label>
					<form:input class="form-control" path="tReading" />
				</div>
				<div class="form-group">
					<label>Third Grade Math</label>
					<form:input class="form-control" path="tMath" />
				</div>
				<div class="form-group">
					<label>Third Grade Science</label>
					<form:input class="form-control" path="tScience" />
				</div>
			</form:form>
			<button type="submit" form="form1" value="Enter Features" class="btn btn-primary">Enter Features</button>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
