<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<a class="navbar-brand" href="#">Improving Education for At-risk
				Students</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse"></div>
		<!--/.nav-collapse -->
	</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<br>
			<br>
	<h1>
		Student Details for ${studentDetail.childid}	
	</h1>
	
	<h2>
		Academic Assessments
	</h2>
	
	<h3>
		Reading
	</h3>
	
	<table class=table>
		<tr>
			<td>Kindergarten</td>
			<td>${studentDetail.c1r4rtsc}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>${studentDetail.c3r4rtsc}</td>
		</tr>
		<tr>
			<td>Third Grade</td>
			<td>${studentDetail.c5r4rtsc}</td>
		</tr>
		<tr>
			<td>Fifth Grade</td>
			<td>${studentDetail.c6r4rtsc}</td>
		</tr>
		<tr>
			<td>Eighth Grade</td>
			<td>${studentDetail.c7r4rtsc}</td>
		</tr>
	</table>
	
	<h3>
		Mathematics
	</h3>
	
	<table class=table>
		<tr>
			<td>Kindergarten</td>
			<td>${studentDetail.c1r4mtsc}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>${studentDetail.c3r4mtsc}</td>
		</tr>
		<tr>
			<td>Third Grade</td>
			<td>${studentDetail.c5r4mtsc}</td>
		</tr>
		<tr>
			<td>Fifth Grade</td>
			<td>${studentDetail.c6r4mtsc}</td>
		</tr>
		<tr>
			<td>Eighth Grade</td>
			<td>${studentDetail.c7r4mtsc}</td>
		</tr>
	</table>
	
	<h3>
		Science
	</h3>
	
	<table class=table>
		<tr>
			<td>Third Grade</td>
			<td>${studentDetail.c5r2stsc}</td>
		</tr>
		<tr>
			<td>Fifth Grade</td>
			<td>${studentDetail.c6r2stsc}</td>
		</tr>
		<tr>
			<td>Eighth Grade</td>
			<td>${studentDetail.c7r2stsc}</td>
		</tr>
	</table>
	
	<h2>
		Teacher Interviews
	</h2>
		
	<table class=table>
		<tr>
			<td>Kindergarten</td>
			<td>How often do the children in your class(es) do the following activities? q19a. Go to the school library or media center.</td>
			<td>${studentDetail.a2gotoli}</td>
		</tr>
		<tr>
			<td>Kindergarten</td>
			<td>How often do the children in your class(es) do the following activities? q19b. Borrow materials from the library or media center.</td>
			<td>${studentDetail.a2borrow}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>How often do the children in your class do the following activities? q46a. Go to the school library or media center.</td>
			<td>${studentDetail.a4gotoli}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>How often do the children in your class(es) do the following activities? q38b. Borrow materials from the library or media center.</td>
			<td>${studentDetail.a4kborro}</td>
		</tr>
		<tr>
			<td>Third Grade</td>
			<td>How often do the children in your class do the following activities? q45a. Go to the school library or media center.</td>
			<td>${studentDetail.a5gotoli}</td>
		</tr>
		<tr>
			<td>Third Grade</td>
			<td>How often do the children in your class do the following activities? q45b. Borrow materials from the library or media center.</td>
			<td>${studentDetail.a5borrow}</td>
		</tr>
		<tr>
			<td>Fifth Grade</td>
			<td>How often do the children in this reading class do the following activities? q22a. Go to the school library or media center.</td>
			<td>${studentDetail.g6gotoli}</td>
		</tr>
		<tr>
			<td>Fifth Grade</td>
			<td>How often do the children in this reading class do the following activities? q22b. Borrow materials from the library or media center.</td>
			<td>${studentDetail.g6borrow}</td>
		</tr>
	</table>
	
	<h2>
		Parent Interviews
	</h2>
	
	<table class=table>
		<tr>
			<td>Kindergarten</td>
			<td>About how many children's books {does {CHILD} have/are} in your home now, including library books? Please only include books that are for children.</td>
			<td>${studentDetail.p1chlboo}</td>
		</tr>
		<tr>
			<td>Kindergarten</td>
			<td>In the past month, that is, since {MONTH} {DAY}, has anyone in your family done the following things with {CHILD}? Visited a library?</td>
			<td>${studentDetail.p2librar}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>Did {he/she} participate in any story hours at the library?</td>
			<td>${studentDetail.p3sthlib}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>Does {CHILD} have {his/her} own library card?</td>
			<td>${studentDetail.p4clbcrd}</td>
		</tr>
		<tr>
			<td>Third Grade</td>
			<td>Do you have a library card?</td>
			<td>${studentDetail.p5rlbcrd}</td>
		</tr>
		<tr>
			<td>Fifth Grade</td>
			<td>About how many children's books does {CHILD} have in your home now, including library books? Please only include books that are for children.</td>
			<td>${studentDetail.p6chlboo}</td>
		</tr>
	</table>
	
	<h2>
		School Facilities
	</h2>
	
	<table class=table>
		<tr>
			<td>Kindergarten</td>
			<td>q1.For each of the facilities that are available to students and that you are able to observe, rate the condition and environmental factors. q1c_a library - available</td>
			<td>${studentDetail.k2q1c_a}</td>
		</tr>
		<tr>
			<td>Kindergarten</td>
			<td>q1.For each of the facilities that are available to students and that you are able to observe, rate the condition and environmental factors. q1c_c library - space/size</td>
			<td>${studentDetail.k2q1c_c}</td>
		</tr>
		<tr>
			<td>Kindergarten</td>
			<td>q1.For each of the facilities that are available to students and that you are able to observe, rate the condition and environmental factors. q1c_f library - physical condition (ceiling, walls, floors, etc.)</td>
			<td>${studentDetail.k2q1c_f}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>q1. For each of the facilities that are available to students and that you are able to observe, rate the condition and environmental factors. q1a_c. library - available</td>
			<td>${studentDetail.k4q1a_c}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>q2. For each of the facilities that are available to students and that you are able to observe, rate the condition and environmental factors. q2a_c. library - space/size</td>
			<td>${studentDetail.k4q2a_c}</td>
		</tr>
		<tr>
			<td>First Grade</td>
			<td>q2. For each of the facilities that are available to students and that you are able to observe, rate the condition and environmental factors. q2d_c. library - physical condition (ceiling, walls, floors, etc.)</td>
			<td>${studentDetail.k4q2d_c}</td>
		</tr>
	</table>
	</div></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>