<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title></title>
<link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
<link rel="stylesheet" href="<%=basePath%>css/admin.css">
<script src="<%=basePath%>js/jquery.js"></script>
<script src="<%=basePath%>js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder">上传文件信息修改</strong></div>
<div class="panel admin-panel margin-top" id="add">
  <div class="body-content">
    <form method="post" class="form-x" action="/CEPACMS/Upload/update.do">
    <div class="form-group">
            <div class="label">
              <label>上传文件名称：</label>
            </div>
            <div class="field">
              <input type="text" class="input w50" name="uploadName" value="${upload.uploadName}"  />
              <div class="tips"></div>
            </div>
          </div>
      <div class="form-group">
        <input type="hidden" name="uploadID" value="${upload.uploadID}" id="uploadID"/>
        <div class="label">
          <label>上传者名称：</label>
        </div>
        <div class="field">
          <select name="umId">
            <option value=0>---请选择---</option>
            <c:forEach var="ume" items="${mList}">
              <c:choose>
                <c:when test="${ume.mId==upload.umId}">
                  <option value="${ume.mId}" selected="selected">${ume.mName}</option>
                </c:when>
                <c:otherwise>
                  <option value="${ume.mId}">${ume.mName}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 修改 </button>
        </div>
      </div>
    </form>
  </div>
</div>
</div>
</body></html>