<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>学生信息显示</title>

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
					    	<h1 class="col-md-5">学生名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="showStudent" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入姓名" value="${studentname}" name="studentname">
									<span class="input-group-addon btn" id="sub">搜索</span>
								</div>
							</form>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='addStudent'">
								添加用户信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
					                <th>学号</th>
				  					<th>姓名</th>
				  					<th>性别</th>
				  					<th>出生年份</th>
				  					<th>入学时间</th>
				  					<th>学院</th>
				  					<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${studentList}" var="student">
								<tr>
									<td>${student.studentid}</td>
									<td>${student.studentname}</td>
									<td>${student.sex}</td>
									<td><fmt:formatDate value="${student.birthday}" dateStyle="medium" /></td>
									<td><fmt:formatDate value="${student.grade}" dateStyle="medium" /></td>
									<td>${student.collegeName}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='editStudent?id=${student.studentid}'">修改</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onClick="confirmd(${student.studentid})">删除</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onClick="resetPassword(${student.studentid})">重置密码</button>
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
									<li><a href="showStudent?page=${Page.upPageNo}&studentname=${studentname}">&laquo;上一页</a></li>
									<li class="active"><a href="">${Page.currentPage}</a></li>
									<c:if test="${Page.currentPage+1 <= Page.totalPage}">
										<li><a href="showStudent?page=${Page.currentPage+1}&studentname=${studentname}">${Page.currentPage+1}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+2 <= Page.totalPage}">
										<li><a href="showStudent?page=${Page.currentPage+2}&studentname=${studentname}">${Page.currentPage+2}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+3 <= Page.totalPage}">
										<li><a href="showStudent?page=${Page.currentPage+3}&studentname=${studentname}">${Page.currentPage+3}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+4 <= Page.totalPage}">
										<li><a href="showStudent?page=${Page.currentPage+4}&studentname=${studentname}">${Page.currentPage+4}</a></li>
									</c:if>
									<li><a href="showStudent?page=${Page.totalPage}&studentname=${studentname}">最后一页&raquo;</a></li>
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
		$("#nav li:nth-child(2)").addClass("active");

		function confirmd(id) {
			var msg = "您真的确定要删除吗？！";
			if (confirm(msg)==true){
				location.href="deleteStudent?id="+id;
			}else{
				return false;
			}
		};

		<!-- 重置密码 -->
		function resetPassword(id) {
			var msg = "您真的确定要重置密码吗？！";
			if (confirm(msg)==true){
				location.href="resetPassword?id="+id;
			}else{
				return false;
			}
		};

		<!-- 搜索功能 -->
        $("#sub").click(function () {
            $("#form1").submit();
        });

        <c:if test="${Page != null}">
			if (${Page.currentPage} == ${Page.totalPage}) {
				$(".pagination li:last-child").addClass("disabled")
			};

			if (${Page.currentPage} == ${1}) {
				$(".pagination li:nth-child(1)").addClass("disabled")
			};
        </c:if>
	</script>
</html>
