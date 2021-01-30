<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1>Teachers Management</h1>
        <h2>
            <a href="/new">Add New Teacher</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Teachers</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${teacher != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${teacher == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${teacher != null}">
                        Edit Teacher
                    </c:if>
                    <c:if test="${teacher == null}">
                        Add New Teacher
                    </c:if>
                </h2>
            </caption>
                <c:if test="${teacher != null}">
                    <input type="hidden" name="id" value="<c:out value='${teacher.id}' />" />
                </c:if>           
            <tr>
                <th>Teacher Name: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${teacher.teachername}' />"
                        />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
        
        <br><br>
		<a href="logout">Home</a>
		<br><br>
		<a href="logout">Logout</a>
    </div>   
</body>
</html>