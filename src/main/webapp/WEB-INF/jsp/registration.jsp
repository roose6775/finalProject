<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Registration</title>
</head>
<body>
<form method="post" action="registration">
  <div style="width: 20%; height: 20%; margin: 0 auto;">
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
        <td>Nickname*</td>
        <td><input type="text" name="regNickname" value="" required/></td>
      </tr>
      <tr>
        <td>Password*</td>
        <td><input type="password" name="regPassword" value="" required/></td>
      </tr>
      <tr>
        <td>Repeat password*</td>
        <td><input type="password" name="regPasswordAgain" value="" required/></td>
      </tr>
      <tr>
        <td colspan="2">* - required fields</td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="Submit" /></td>
      </tr>
      <tr>
        <td colspan="2" style="font-size: 13px;">or Login <a href="login">here</a></td>
      </tr>
      </tbody>
    </table>
  </div>
</form>
</body>
</html>
