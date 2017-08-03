<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script>
</head>
<script type="text/javascript">
	function onSubmit() {
		alert(123);
		$("#signIn option:first").prop("selected", 'selected');
	}
</script>

<body>
	<select id="signIn">
		<option>1</option>
		<option>2</option>
		<option selected="selected">3</option>
		<option>4</option>
	</select>
	<button class="btn" onclick="onSubmit();">选择第一个</button>
</body>
</html>