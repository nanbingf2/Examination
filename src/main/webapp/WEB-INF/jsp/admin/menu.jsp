<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="showCourse">课程管理<span class="badge pull-right">${sessionScope.courseCount}</span></a></li>
        <li><a href="showStudent">学生管理<span class="badge pull-right">${sessionScope.studentCount}</span></a></li>
        <li><a href="showTeacher">教师管理<span class="badge pull-right">${sessionScope.teacherCount}</span></a></li>
        <%--<li><a href="resetPassword">账号密码重置<sapn class="glyphicon glyphicon-repeat pull-right" /></a></li>--%>
        <li><a href="editPassword">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="${pageContext.request.contextPath}/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>