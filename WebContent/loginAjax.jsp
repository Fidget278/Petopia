
<%@ page contentType="text/plain; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<c:if test="${requestScope.isSuccess == 0}">
{
	"isSuccess" : "${requestScope.isSuccess}",
	"failText" : "${requestScope.failText}"
}
</c:if>

<c:if test="${requestScope.isSuccess == 1}">

{
	"isSuccess" : "${requestScope.isSuccess}",
	"url" : "${pageContext.request.contextPath}/petopia.do"
}

</c:if> 
 




