<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="len" value="${fn:length(profileName)}"></c:set>
<c:set var="filetype" value="${fn:toUpperCase(fn:substring(profileName, len-4, len))}"></c:set>

<c:choose>
	<c:when test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.GIF') or (filetype eq '.PNG')}">
		<img class="profile-img" alt="" src="<c:url value='resources/img/${profileName}'/>">
	</c:when>
	<c:otherwise>
		<img class="profile-img" alt="" src="<c:url value='resources/img/user.png'/>">
	</c:otherwise>
</c:choose>
