<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="login.title" /></title>
</head>
<body>
	<h1><fmt:message key="login.title" /></h1>
    <p>
       <fmt:message key="login.text" /><br /><br />
    </p>

	<form method="post" action="j_spring_security_check">
    <div id="container_login">
    	<div class="title"><fmt:message key="login.form.title" /></div>
    	<div class="content">
			<ul>
				<li>
					<span>
						<label><fmt:message key="login.form.login"/></label>
					</span>
					<input type="text" name="j_username" />
				</li>
				<li>
					<span>
						<label><fmt:message key="login.form.password"/></label>
					</span>
					<input type="password" name="j_password" />
				</li>
				<li>
					<input type="submit" value="<fmt:message key="login.form.submit"/>" />
				</li>
			</ul>
		</div>
	</div>
	</form>
</body>
</html>