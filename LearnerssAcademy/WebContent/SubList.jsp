<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1> ASSIGN CLASSES FOR SUBJECTS</h1>
        <h2>
            <a href="/new">Assign Class</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Assigned Classes</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Assigned Classes</h2></caption>
            <tr>
                <th>ID</th>
                <th>Subject</th>
                <th>Class</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="sub" items="${listSub}">
                <tr>
                    <td><c:out value="${sub.id}" /></td>
                    <td><c:out value="${sub.subname}" /></td>
                    <td><c:out value="${sub.classname}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${sub.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${sub.id}' />">Delete</a>                     
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