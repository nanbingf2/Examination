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
					    	<h1 class="col-md-5">已选该课程学生名单</h1>
						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>学号</th>
									<th>姓名</th>
									<th>分数</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
								<c:forEach items="${selectedCourseList}" var="item">
									<tr>
										<td>${item.studentid}</td>
										<td>${item.studentname}</td>
										<c:if test="${!item.over}">
											<td>未打分</td>
											<td>
												<button class="btn btn-default btn-xs btn-info" onClick="location.href='mark?studentid=${item.studentid}&courseid=${courseid}'">打分</button>
											</td>
										</c:if>
										<c:if test="${item.over}">
											<td>${item.mark}</td>
											<td>已打分</td>
										</c:if>
									</tr>
								</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
						<c:if test="${Page != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="showGrade?page=${Page.upPageNo}&courseid=${courseid}">&laquo;上一页</a></li>
									<li class="active"><a href="">${Page.currentPage}</a></li>
									<c:if test="${Page.currentPage+1 <= Page.totalPage}">
										<li><a href="showGrade?page=${Page.currentPage+1}&courseid=${courseid}">${Page.currentPage+1}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+2 <= Page.totalPage}">
										<li><a href="showGrade?page=${Page.currentPage+2}&courseid=${courseid}">${Page.currentPage+2}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+3 <= Page.totalPage}">
										<li><a href="showGrade?page=${Page.currentPage+3}&courseid=${courseid}">${Page.currentPage+3}</a></li>
									</c:if>
									<c:if test="${Page.currentPage+4 <= Page.totalPage}">
										<li><a href="showGrade?page=${Page.currentPage+4}&courseid=${courseid}">${Page.currentPage+4}</a></li>
									</c:if>
									<li><a href="showGrade?page=${Page.totalPage}&courseid=${courseid}">最后一页&raquo;</a></li>
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