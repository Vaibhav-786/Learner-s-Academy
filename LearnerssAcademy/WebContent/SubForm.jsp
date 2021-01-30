<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1>ASSIGN CLASSES FOR SUBJECTS</h1>
        <h2>
            <a href="/new">Assign Class</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Assigned Classes</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${sub != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${sub == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${sub != null}">
                        Edit 
                    </c:if>
                    <c:if test="${sub == null}">
                        Add New 
                    </c:if>
                </h2>
            </caption>
                <c:if test="${sub != null}">
                    <input type="hidden" name="id" value="<c:out value='${sub.id}' />" />
                </c:if>           
            <tr>
                <th>Subject: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${sub.subname}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Class: </th>
                <td>
                    <input type="text" name="author" size="45"
                            value="<c:out value='${sub.classname}' />"
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