<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="/css/bootstrap.css"      rel="stylesheet">
	<link href="/css/custom.css"      rel="stylesheet">
	<link href="/css/main.css"      rel="stylesheet">
</head>
<body>
<h1 align="center">Employees List</h1>
<table id="t02"  cellpadding="2">
<tr>
<th>
<a  href="/entry"><h2>Home Page:Entry New Employee</h2></a>

</th>

<th>

<a  align ="right" href="/delete"><h2>Delete All Employees</h2></a>
</th>
</tr>
</table>
<table >
<tr>


  <th>Page No : 
<c:forEach var="count" items="${pageCount}"  varStatus="theCount"> 
<a align="right" href="/viewemployeesalarys/${theCount.count}">${theCount.count}</a>   
</c:forEach>  
</th>

</tr>

</table>
   
<form:form  class="form-horizontal" >
<table id="t01" border="2" width="70%" cellpadding="2">
<tr><th>Id</th><th>First Name</th><th>Last Name</th>
<th>Salary</th></tr>  

   <c:forEach var="employeesalary" items="${list}"> 
   <tr>  
   <td>${employeesalary.id}</td>  
   <td>${employeesalary.firstName}</td> 
   <td>${employeesalary.lastName}</td>  
   <td>${employeesalary.salary}</td> 
    
   <td><a href="/editemployeesalary/${employeesalary.id}">Edit</a></td>  
   <td><a href="/deleteemployeesalary/${employeesalary.id}">Delete</a></td>  
   </tr>  
   </c:forEach> 
   
   
   </table>  
   <br/>
   
  
   </form:form>
</body>
</html>