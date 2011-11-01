<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="j_spring_security_check">
		<table>
			<tr>
				<td>Login : </td>
				<td><input type="text" name="j_username" /></td>
			</tr>
			<tr>
				<td>Password : </td>
				<td><input type="password" name="j_password" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" name="Envoyez" />
					<input type="reset" name="Annuler" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>