<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>文件上传</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <script src="<%=basePath%>js/jquery.js"></script>
    <script src="<%=basePath%>js/pintuer.js"></script>
</head>
<body>
    <div class="panel admin-panel margin-top" id="add">
        <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 下载文件列表</strong></div>
        <div class="body-content">
            <form class="form-x">
                <div class="form-group">
                    <div class="label">
                        <label>核酸检测通知</label>
                    </div>
                    <div class="field">
                        <button class="button bg-green icon-align-justify">
                            <a href="${pageContext.request.contextPath }/DownloadIO?filename=核酸检测通知.docx">下载</a></button>
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label>第三针疫苗通知</label>
                    </div>
                    <div class="field">
                        <button class="button bg-green icon-align-justify">
                            <a href="${pageContext.request.contextPath }/DownloadIO?filename=第三针疫苗通知.docx">下载</a></button>
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label>关于最近封区通知</label>
                    </div>
                    <div class="field">
                        <button class="button bg-green icon-align-justify">
                            <a href="${pageContext.request.contextPath }/DownloadIO?filename=关于最近封区通知.docx">下载</a></button>
                        <div class="tips"></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>