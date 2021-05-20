<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Product</title>
</head>
<body style="background-color: lightblue;">
	<div align="center">
		<h1>Create New Product</h1>
		<br />
		<form:form method="post" action="save" commandName="employee">
			<table>
				<tr>
					<td>First Name :</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name :</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td>Company Name :</td>
					<td><form:input path="companyName" /></td>
				</tr>
				<tr>
					<td>Address :</td>
					<td><form:input path="address" /></td>
				</tr>
				<tr>
					<td>City :</td>
					<td><form:input path="city" /></td>
				</tr>
				<tr>
					<td>County :</td>
					<td><form:input path="county" /></td>
				</tr>
				<tr>
					<td>State :</td>
					<td><form:input path="state" /></td>
				</tr>
				<tr>
                	<td>Zip :</td>
                	<td><form:input path="zip" /></td>
                </tr>
				<tr>
					<td colspan="2"><input type="submit" value="Save" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>