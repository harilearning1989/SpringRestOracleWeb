<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
<script src="jquery/jquery-3.5.1.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<script src="js/custom.js"></script>
<script type="text/javascript" src="DataTables/datatables.min.js"></script>
<link href="css/custom.css" rel="stylesheet">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="DataTables/datatables.min.css" />
<script>
$(document).ready(function() {
    $('#example').DataTable( {
        responsive: true
    } );
} );
</script>
<style type="text/css">
table th:nth-child(3), td:nth-child(3) {
	display: none;
}
</style>
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<caption>Your todos are</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Todo 1</td>
					<td>10/12/2017</td>
					<td>No</td>
					<td><a class="btn btn-warning" href="/edit-todo">Edit Todo</a></td>
					<td><a class="btn btn-warning" href="/delete-todo">Delete
							Todo</a></td>
				</tr>
			</tbody>
		</table>
		<div>
			<a class="btn btn-default" href="/add-todo">Add a Todo</a>
		</div>
	</div>

	<table id="example" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Name</th>
				<th>Position</th>
				<th>Office</th>
				<th>Age</th>
				<th>Start date</th>
				<th>Salary</th>
			</tr>
		</thead>

		<tfoot>
			<tr>
				<th>Name</th>
				<th>Position</th>
				<th>Office</th>
				<th>Age</th>
				<th>Start date</th>
				<th>Salary</th>
			</tr>
		</tfoot>

		<tbody>
			<tr>
				<td>Tiger Nixon</td>
				<td>System Architect</a></td>
				<td>Edinburgh</a></td>
				<td>61</td>
				<td>2011/04/25</td>
				<td>$320,800</td>
			</tr>
			<tr>
				<td>Garrett Winters</td>
				<td>Accountant</td>
				<td>Tokyo</td>
				<td>63</td>
				<td>2011/07/25</td>
				<td>$170,750</td>
			</tr>
			<tr>
				<td>Ashton Cox</td>
				<td>Junior Technical Author</td>
				<td>San Francisco</td>
				<td>66</td>
				<td>2009/01/12</td>
				<td>$86,000</td>
			</tr>
			<tr>
				<td>Cedric Kelly</td>
				<td>Senior Javascript Developer</td>
				<td>Edinburgh</td>
				<td>22</td>
				<td>2012/03/29</td>
				<td>$433,060</td>
			</tr>
			<tr>
				<td>Airi Satou</td>
				<td>Accountant</td>
				<td>Tokyo</td>
				<td>33</td>
				<td>2008/11/28</td>
				<td>$162,700</td>
			</tr>
		</tbody>
	</table>
</body>
</html>