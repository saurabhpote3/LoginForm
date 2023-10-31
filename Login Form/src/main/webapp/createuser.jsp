<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<div style="width: 500px; margin: auto; padding-top: 20px"
		class="border border-primary p-5 mt-5">
		<h2 style="text-align: center">Sign Up</h2>
		<form action="CreateUserServlet" method="post">
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Name</label> <input
					type="text" class="form-control" id="exampleInputName" name="name">
			</div>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Email
					address</label> <input name="email" type="email" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">We'll never share your
					email with anyone else.</div>
			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Password</label>
				<input type="password" class="form-control"
					id="exampleInputPassword1" name="password">
			</div>
			<a href="login_form.jsp" class="link-primary mb-3"
				style="display: block">if you have a account already then log in</a>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>