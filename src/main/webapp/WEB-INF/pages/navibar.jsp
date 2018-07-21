<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="navi"><%= (String)request.getSession().getAttribute("navibar") %></c:set>
<script type="text/javascript">
	var navi = '${navi}';
	var _menus = eval('('+navi+')');
</script>
