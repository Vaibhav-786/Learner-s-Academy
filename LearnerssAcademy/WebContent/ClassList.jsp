<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1>Classes Management</h1>
        <h2>
            <a href="/new">Add New Class</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Classes</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Classes</h2></caption>
            <tr>
                <th>ID</th>
                <th>Class Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="clas" items="${listClas}">
                <tr>
                    <td><c:out value="${clas.id}" /></td>
                    <td><c:out value="${clas.clasname}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${clas.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${clas.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <br><br>
		<a href="logout">Home</a>
		<br><br>
		<a href="logout">Logout</a>
    </div> 
      
</body>
</html>