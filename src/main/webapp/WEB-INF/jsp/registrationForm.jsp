<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Registration</title>
</head>
<body>
<form method="post" action="">
  <div style="width: 50px; height: 50px; margin: 0 auto;">
    <table border="0" style="text-align:center;">
      <thead>
      <tr>
        <th colspan="2">Registration Form</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>Name</td>
        <td><input type="text" name="regName" value="" /></td>
      </tr>
      <tr>
        <td>Nickname</td>
        <td><input type="text" name="regNickname" value="" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="regPassword" value="" /></td>
      </tr>
      <tr>
        <td>Write password again</td>
        <td><input type="password" name="regPasswordAgain" value="" /></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="Submit" /></td>
      </tr>
      <tr>
        <td colspan="2">or <a href="login.jsp">Login Here</a></td>
      </tr>
      </tbody>
    </table>
  </div>
</form>
</body>
</html>
