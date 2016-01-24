<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<form method="post" action =""> <%-- INSERT CLASS FOR THIS JSP IN ACTION="" --%>
<div style="width: 50px; height: 50px; margin: 0 auto;">
  <table style="text-align:center;">
    <tbody>
    <tr>
      <td>Username</td>
      <td><input type="text" name="logNickname" value="" /></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="logPassword" /></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Login" /></td>
    </tr>
    <tr>
      <td colspan="2" style="font-size: 13px;">You can <a href="registrationForm.jsp">register</a> here</td>
    </tr>
    </tbody>
  </table>
</div>
</form>
</body>
</html>