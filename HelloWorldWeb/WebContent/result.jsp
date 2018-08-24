<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Result Page</title>
		<link rel="stylesheet" type="text/css" href="css.css">
	</head>
		<body>
			<div id="result">
				<c:out value="${dateTime}"></c:out>
			</div>
			<div id="result2">
				<c:out value="System Properties at Server:"></c:out>
			</div>
			<div id="result3">
				<c:forEach items="${systemProperties}" var="property" varStatus="varStatus">
					<p id=propertyItem>
						<c:out value="${varStatus.count}. ${property}"></c:out>
					</p>
				</c:forEach>
			</div>
		</body>
</html>
