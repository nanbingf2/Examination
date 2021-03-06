<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>课程信息显示</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">课程列表</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="showCourse" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入课程名" name="coursename" value="${coursename}">
									<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>
								</div>
							</form>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>课程号</th>
									<th>课程名称</th>
									<th>授课老师编号</th>
									<th>上课时间</th>
									<th>上课地点</th>
									<th>周数</th>
									<th>课程类型</th>
									<th>学分</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${courseList}" var="item">
								<tr>
									<td>${item.courseid}</td>
									<td>${item.coursename}</td>
									<td>${item.teacherid}</td>
									<td>${item.coursetime}</td>
									<td>${item.classroom}</td>
									<td>${item.courseweek}</td>
									<td>${item.coursetype}</td>
									<td>${item.score}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='stuSelectedCourse?id=${item.courseid}'">选课</button>
										<!--弹出框-->
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
						<c:if test="${Page != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="showCourse?page=${Page.upPageNo}&coursename=${coursename}">&laquo;上一页</a></li>
									<li class="active"><a href="">${Page.currentPage}</a></li>
									<c:if test="${Page.currentPage+1 <= Page.totalPage}">
										<li><a href="showCourse?page=${Page.currentPage+1}&coursename=${coursename}">${Page.currentPage+1}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+2 <= Page.totalPage}">
										<li><a href="showCourse?page=${Page.currentPage+2}&coursename=${coursename}">${Page.currentPage+2}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+3 <= Page.totalPage}">
										<li><a href="showCourse?page=${Page.currentPage+3}&coursename=${coursename}">${Page.currentPage+3}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+4 <= Page.totalPage}">
										<li><a href="showCourse?page=${Page.currentPage+4}&coursename=${coursename}">${Page.currentPage+4}</a></li>
									</c:if>
									<li><a href="showCourse?page=${Page.totalPage}&coursename=${coursename}">最后一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
				    </div>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(1)").addClass("active")
		<c:if test="${Page != null}">
		if (${Page.currentPage} == ${Page.totalPage}) {
			$(".pagination li:last-child").addClass("disabled")
		};

		if (${Page.currentPage} == ${1}) {
			$(".pagination li:nth-child(1)").addClass("disabled")
		};
		</c:if>

        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>