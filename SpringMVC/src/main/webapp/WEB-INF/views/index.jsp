<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GradleNote</title>
</head>
<body>
<h1>Hello, Gradle Gradle!</h1>

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    name:<input name="name" type="text"><br>
    age:<input name="age" type="text"><br>
    address:<input name="address" type="text"><br>
    birthday:<input name="birthday" type="text"><br>
    pic:<input type="file" name="pic"><br>
    <input type="submit" value="submit"><br>
</form>
</body>
</html>