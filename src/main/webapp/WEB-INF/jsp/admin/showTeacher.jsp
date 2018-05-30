<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>教师信息显示</title>

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

	<!-- 中间主体 --><jsp:include page="top.jsp"></jsp:include>
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">教师名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="showTeacher" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入姓名" name="teachername">
									<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>
								</div>
							</form>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='addTeacher'">
								添加教师信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>教师编号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>出生年份</th>
									<th>学历</th>
									<th>职称</th>
									<th>入职年份</th>
									<th>学院</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${teacherList}" var="item">
								<tr>
									<td>${item.teacherid}</td>
									<td>${item.teachername}</td>
									<td>${item.sex}</td>
									<td><fmt:formatDate value="${item.birthday}" dateStyle="medium" /></td>
									<td>${item.degree}</td>
									<td>${item.title}</td>
									<td><fmt:formatDate value="${item.grade}" dateStyle="medium" /></td>
									<td>${item.collegeName}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='editTeacher?id=${item.teacherid}'">修改</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onClick="confirmd(${item.teacherid})">删除</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onClick="resetPassword(${item.teacherid})">重置密码</button>
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
									<li><a href="showTeacher?page=${Page.upPageNo}&teachername=${teachername}">&laquo;上一页</a></li>
									<li class="active"><a href="">${Page.currentPage}</a></li>
									<c:if test="${Page.currentPage+1 <= Page.totalPage}">
										<li><a href="showTeacher?page=${Page.currentPage+1}&teachername=${teachername}">${Page.currentPage+1}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+2 <= Page.totalPage}">
										<li><a href="showTeacher?page=${Page.currentPage+2}&teachername=${teachername}">${Page.currentPage+2}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+3 <= Page.totalPage}">
										<li><a href="showTeacher?page=${Page.currentPage+3}&teachername=${teachername}">${Page.currentPage+3}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+4 <= Page.totalPage}">
										<li><a href="showTeacher?page=${Page.currentPage+4}&teachername=${teachername}">${Page.currentPage+4}</a></li>
									</c:if>
									<li><a href="showTeacher?page=${Page.totalPage}&teachername=${teachername}">最后一页&raquo;</a></li>
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
		$("#nav li:nth-child(3)").addClass("active")
		<!-- 翻页操作 -->
		<c:if test="${Page != null}">
			if (${Page.currentPage} == ${Page.totalPage}) {
				$(".pagination li:last-child").addClass("disabled")
			};
			if (${Page.currentPage} == ${1}) {
				$(".pagination li:nth-child(1)").addClass("disabled")
			};
		</c:if>

		<!-- 删除操作 -->
		function confirmd(id) {
			var msg = "您真的确定要删除吗？！";
			if (confirm(msg)==true){
				location.href="deleteTeacher?id="+id;
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

		<!-- 搜索操作 -->
        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>