<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/include/base.jsp" %>

<%

String title = getTitle(request.getParameter("title"));

%>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title><%= title %></title>
    
    <!-- Bootstrap Core CSS -->
    <link href="<%= basePath %>frame/assets/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= basePath %>frame/assets/sco/css/scojs.css" rel="stylesheet">
    <link href="<%= basePath %>frame/assets/sco/css/sco.message.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%= basePath %>frame/assets/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="<%= basePath %>frame/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= basePath %>frame/css/sb-admin-2.css" rel="stylesheet">
    
    <link href="<%= basePath %>frame/assets/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    
    <link href="<%= basePath %>frame/assets/messenger/css/messenger.css" rel="stylesheet">
    <link href="<%= basePath %>frame/assets/messenger/css/messenger-theme-future.css" rel="stylesheet">
    
    <link href="<%= basePath %>frame/assets/datetimepicker/css/datetimepicker.css" rel="stylesheet">
    

    <!-- Morris Charts CSS
    <link href="<%= basePath %>frame/assets/morrisjs/morris.css" rel="stylesheet"> -->

    <!-- Custom Fonts -->
    <link href="<%= basePath %>frame/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    
    <!-- Theme Style -->
    <link href="<%= basePath %>frame/css/theme.css" rel="stylesheet">
    
    <!-- Custom Style -->
    <link href="<%= requestPath %>css/style.css" rel="stylesheet">
    
    
    <!-- jQuery -->
    <script src="<%= basePath %>frame/assets/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= basePath %>frame/assets/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="<%= basePath %>frame/assets/sco/js/sco.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%= basePath %>frame/assets/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript
    <script src="<%= basePath %>frame/assets/raphael/raphael-min.js"></script>
    <script src="<%= basePath %>frame/assets/morrisjs/morris.js"></script>
    <script src="<%= basePath %>frame/js/morris-data.js"></script> -->

    <script src="<%= basePath %>frame/assets/validate/validator.min.js"></script>
    
    <script src="<%= basePath %>frame/assets/messenger/js/messenger.min.js"></script>
    <script src="<%= basePath %>frame/assets/messenger/js/messenger-theme-future.js"></script>
    
    <script src="<%= basePath %>frame/assets/bootstrap-table/bootstrap-table-all.js"></script>
    <script src="<%= basePath %>frame/assets/bootstrap-table/bootstrap-table-locale-all.min.js"></script>
    
    <script src="<%= basePath %>frame/assets/bootbox/bootbox.js"></script>
    
    <script src="<%= basePath %>frame/assets/datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="<%= basePath %>frame/assets/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    
    <script src="<%= basePath %>frame/assets/jquery-pagination-plugin/jquery.twbsPagination.min.js"></script>
    


    <!-- Custom Theme JavaScript -->
    <script src="<%= basePath %>frame/js/sb-admin-2.js"></script>
    <script src="<%= basePath %>frame/js/main.jsp"></script>
    
    <!-- Custom JavaScript -->
    <script src="<%= requestPath %>js/main.js"></script>