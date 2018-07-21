<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = (String)request.getContextPath();
	request.setAttribute("path", path);
%>
<c:set var="path" scope="request"><%= path %></c:set>
<link href="${path}/static/easyui/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path}/static/easyui/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${path}/static/easyui/js/themes/icon.css" />

<script type="text/javascript" src="${path}/static/easyui/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${path}/static/easyui/js/jquery.easyui.js"></script>
<script type="text/javascript" src='${path}/static/easyui/js/outlook2.js'></script>
