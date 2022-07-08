<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${dList==null}">
  <c:redirect url="/Download/queryAll.do"></c:redirect>
</c:if>
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
  <script type="application/javascript">

    //全选
    function checkAll(obj){
        var chks = document.getElementsByName("chk");
        for (var i = 0; i < chks.length; i++) {
            chks[i].checked = obj.checked;
        }
    }

    //跳转页
    function page_onchange(obj){
        var value= obj.value();
        window.location.href="/Download/queryAll.do?currentPageIndex="+value;
    }

    //是否删除提示
    function delete_onclick(){
      var bln = confirm("是否删除该笔资料？");
      //直接返回
      return bln;
    }

    /* 上一页和下一页禁用操作 */
    $(function(){
      //首先获取当前页及尾页页码
      var pageIndex = "${pageUtil.currentPageIndex+1}";
      var totalPage = "${pageUtil.totalPageCount}";
      if(pageIndex == 1){//当前页为首页
        $("#pPage").css("pointer-events","none");
      }
      if(pageIndex == totalPage){//当前页为尾页
        $("#nPage").css("pointer-events","none");
      }
    })

    //多笔删除
    function del(){
      var form1 = document.getElementById("form1");
      form1.action = "/CEPACMS/Download/delete.do";
      form1.submit();
    }
  </script>
</head>
<body>
<div class="panel admin-panel">
  <form id="form1" role="form" name="form" action="/CEPACMS/Download/queryByKeyWords.do" method="post">
  <div class="panel-head"><strong class="icon-reorder">文件下载信息列表</strong></div>
  <div class="padding border-bottom">
    <ul class="search">
      <li>
        <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 添加内容</button>
        <button type="button" class="button border-green"><span class="icon-check"></span><input type="checkbox" onclick="checkAll(this)" name="chkAll"> 全选</button>
        <input type="text" placeholder="请输入搜索关键字" name="downloadName" id="downloadName" value="${param.downloadName}" class="input" style="width:250px; line-height:17px;display:inline-block" />
        <input type="submit" class="button border-main icon-search" value="搜索"/></li>
    </ul>
  </div>
  <table class="table table-hover text-center">
    <tr>
      <th>下载文件编号</th>
      <th>下载文件名称</th>
      <th>下载者名称</th>
      <c:if test="${role==1 }">
      <th>操作</th>
      </c:if>
    </tr>
  <c:forEach items="${dList}" var="dow">
    <tr>
      <td>
      <input name="chk" value="${dow.downloadID}" type="checkbox" class="checkbox">
          ${dow.downloadID}
      </td>
      <td>${dow.downloadName}</td>
      <td>${dow.mName}</td>
      <td><div class="button-group">
      <c:if test="${role==1 }">
      <a class="button border-main" href="/CEPACMS/Download/edit.do?downloadID=${dow.downloadID}"><span class="icon-edit"></span> 修改</a>
      <a onclick="return delete_onclick()" class="button border-red" href="/CEPACMS/Download/delete.do?downloadID=${dow.downloadID}"><span class="icon-trash-o"></span> 删除</a>
      </c:if>
      </div></td>
    </tr>
  </c:forEach>
    <tr>
      <td colspan="7">
        <div class="pagelist"> <a href="/CEPACMS/Download/queryAll.do?currentPageIndex=${pageUtil.currentPageIndex-1}" id="pPage">上一页</a>
          总共<span id="totalCount" style="color: red">${pageUtil.totalCount}</span>条数据，
          每页<span id="pageSize" style="color: red">${pageUtil.pageSize}</span>条数据，
          共<span id="pageCount" style="color: red">${pageUtil.totalPageCount}</span>页，
          当前是第<span id="currentPageIndex" style="color: red">${pageUtil.currentPageIndex+1}</span>页&nbsp;
          <a href="/CEPACMS/Download/queryAll.do?currentPageIndex=${pageUtil.currentPageIndex+1}" id="nPage">下一页</a>
          <select id="page" name="page" onchange="page_onchange(this)"><!-- this代表当前整个事件对象 ，事件源，就是当前整个element对象-->
            <c:forEach begin="0" end="${pageUtil.totalPageCount-1}" var="i">
              <!--什么时候被选中？只有i==当前页才被选中  -->
              <c:choose>
                <c:when test="${i == pageUtil.currentPageIndex}">
                  <option value="${i}" selected="selected">${i+1}</option>
                </c:when>
                <c:otherwise>
                  <option value="${i}">${i+1}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div></td>
    </tr>   
  </table>
  </form>
</div>

<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/CEPACMS/Download/add.do">
    <div class="form-group" id="f_1653901978475" onsubmit="return false;">
    	<div class="alert alert-blue">
        	<span class="close rotate-hover"></span>
        	<strong>提示：</strong>
        	点击下载进行下载各项通知文件
        </div>
    </div>
      <div class="form-group">
        <div class="label">
          <label>下载文件名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="downloadName" value=""  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>下载者：</label>
        </div>
        <div class="field">
          <select name="dmId">
            <option value=0>---请选择---</option>
            <c:forEach var="dme" items="${mList}">
                  <option value="${dme.mId}">${dme.mName}</option>
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
          <a class="button border-main" href="/CEPACMS/download/Download.jsp"><span class="icon-edit"></span> 下载</a>
        </div>
      </div>
     
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>