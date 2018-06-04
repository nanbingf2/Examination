<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="showCourse">所有课程<span class="badge pull-right">${sessionScope.allCourseCount}</span></a></li>
        <li><a href="selectedCourse">已选课程<span class="badge pull-right">${sessionScope.selectCourseCount}</span></a></li>
        <li><a href="overCourse">已修课程<span class="badge pull-right">${sessionScope.finishedCourseCount}</span></a></li>
        <li><a href="editPassword">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="${pageContext.request.contextPath}/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>