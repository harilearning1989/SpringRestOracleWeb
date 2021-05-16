<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
#login-box {
    width: 300px;
    padding: 20px;
    margin: 100px auto;
    background: #fff;
    -webkit-border-radius: 2px;
    -moz-border-radius: 2px;
    border: 1px solid #000;
}
</style>
</head>
<body align="center">
    </br></br></br></br></br></br></br>

<div id="login-box" align="center">
 <c:if test="${param.error != null}">
    	<div style="color:red; font-weight: bold; margin: 30px 0px;">Invalid username and password.</div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div style="color:red; font-weight: bold; margin: 30px 0px;">You have been logged out.</div>
    </c:if>
    <c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>
	<form action="#" action="/login" method="post">
		<table>
        	<tr>
        		<td>UserName:</td>
        		<td><input type='text' name='username' placeholder="Username" required></td>
        	</tr>
        	<tr>
        		<td>Password:</td>
        		<td><input type='password' name='password' placeholder="Password" required/></td>
        	</tr>
        	<tr>
                 <td>Remember me:</td>
                 <td><input type="checkbox" name="remember" /></td>
            </tr>
        	<tr>
        		<td colspan='2'><input name="Submit" type="submit"
        			value="submit" /></td>
        	</tr>
        </table>
	</form>
	</div>
</body>
</html>