<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1>CLASS REPORT</h1>
        <h2>
            <a href="/new">Add New Record</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Records</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${record != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${record == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${record != null}">
                        Edit Record
                    </c:if>
                    <c:if test="${record == null}">
                        Add New Record
                    </c:if>
                </h2>
            </caption>
                <c:if test="${record != null}">
                    <input type="hidden" name="id" value="<c:out value='${record.id}' />" />
                </c:if>           
            <tr>
                <th>Class: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${record.classname}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Subject: </th>
                <td>
                    <input type="text" name="author" size="45"
                            value="<c:out value='${record.subjectname}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Teacher: </th>
                <td>
                    <input type="text" name="price" size="5"
                            value="<c:out value='${record.teachername}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Student: </th>
                <td>
                    <input type="text" name="price" size="5"
                            value="<c:out value='${record.studentname}' />"
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