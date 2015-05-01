<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="resources/images/iddd.ico" >
    <title>《实现领域驱动设计》销售排名</title>
</head>

<body>
    <c:out value="${info}"/><p>
	<c:forEach var="record" items="${records}">
        <c:out value="${record.date}  "/>:<c:out value="《${record.bookName}》"/>在<c:out value="【${record.category}】"/>分类中排第<c:out value="${record.rank}"/>名<p>
    </c:forEach>
</body>
</html>