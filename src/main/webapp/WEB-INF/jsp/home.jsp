<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Northwind-JPA</title>
    <link href="<c:url value="/resources/jquery-ui/1.10.3/themes/base/jquery.ui.all.css" />" rel="stylesheet" type="text/css"/>
    <!-- <link href="<c:url value="/resources/jquery-ui/1.10.3/themes/humanity/jquery-ui-1.10.3.custom.css" />" rel="stylesheet" type="text/css"/> -->
    <link href="<c:url value="/resources/jqgrid/4.5.4/css/ui.jqgrid.css" />" rel="stylesheet" type="text/css"/>

    <%-- JQuery --%>
    <script type="text/javascript" src="<c:url value="/resources/jquery/1.10.2/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery-ui/1.10.3/ui/jquery.ui.core.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery-ui/1.10.3/ui/jquery.ui.widget.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery-ui/1.10.3/ui/jquery.ui.tabs.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery-ui/1.10.3/ui/jquery.ui.dialog.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery-ui/1.10.3/ui/jquery.ui.button.js" />"></script>
    
    <%--JQGrid --%>
    <script type='text/javascript' src='<c:url value="/resources/jqgrid/4.5.4/i8n/grid.locale-en.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/resources/jqgrid/4.5.4/jquery.jqGrid.src.js"/>'></script>
        
    <%-- JTable --%>
    <%--<link href="/resources/jtable/2.3.1/themes/metro/blue/jtable.css" rel="stylesheet" type="text/css" />       
    <script type="text/javascript" src="/resources/jtable/2.3.1/jquery.jtable.js" ></script> --%>
    
    <%-- Customized javascripts for tables --%>
    <script type="text/javascript" src="/resources/js/util.js"></script>
    <script type="text/javascript" src="/resources/js/category.js"></script>
    <script type="text/javascript" src="/resources/js/customer.js"></script>
    
</head>
<body>
    <h1><a href="<c:url value="/" />">Northwind-JPA</a></h1>
    <div id="tabs">
	    <ul>
            <li><a href="#category">Category</a></li>
            <li><a href="#customer">Customer</a></li>
	    </ul>
        
	    <div id = "category">
	        <div id = 'categoryJqgrid'>
	            <table id = 'categoryGrid'></table>
	            <div id = 'categoryPager'></div>
	        </div>
	        
	        <div id='categoryMsgbox' title='' style='display:none'></div>
	    </div>
        
        <div id = "customer">
            <div id = 'customerJqgrid'>
                <table id = 'customerGrid'></table>
                <div id = 'customerPager'></div>
            </div>
            
            <div id='customerMsgbox' title='' style='display:none'></div>
        </div>
    
    </div>
    
    <script type="text/javascript">
        $(document).ready(function() {
            $("#tabs").tabs();

            // Append '#' to the window location so "Back" returns to the selected tab
            // after a redirect or a full page refresh (e.g. Views tab).

            // However, note this general disclaimer about going back to previous tabs: 
            // http://docs.jquery.com/UI/API/1.8/Tabs#Back_button_and_bookmarking

            $("#tabs").bind("tabsselect", function(event, ui) { window.location.hash = ui.tab.hash; });
            
            
        });
        
    </script>
    
</body>
</html>