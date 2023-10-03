<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>formulario</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h2>Registrate</h2>
				<form:form method="Post" modelAttribute="nuevoUsuario" action="/registro">
					<div>
						<form:label path="nombre">nombre</form:label>
						<form:input path="nombre" class="form-control"/>
						<form:errors path="nombre" class="text-danger"/>
					</div>
					<div>
						<form:label path="apellido">apellido</form:label>
						<form:input path="apellido" class="form-control"/>
						<form:errors path="apellido" class="text-danger"/>
					</div>
					<div>
						<form:label path="email">email</form:label>
						<form:input path="email" class="form-control"/>
						<form:errors path="email" class="text-danger"/>
					</div>
					<div>
						<form:label path="password">contraseña</form:label>
						<form:password path="password" class="form-control"/>
						<form:errors path="password" class="text-danger"/>
					</div>
					<div>
						<form:label path="confirmPassword">confirmar contraseña</form:label>
						<form:password path="confirmPassword" class="form-control"/>
						<form:errors path="confirmPassword" class="text-danger"/>
					</div>
					<input type="submit" class="btn btn-success mt-3" value="Registrarme">	
				</form:form>
			</div>
			<div class="col-6">
				<h2>Inicia sesion</h2>
				<p class="text-danger">${error_login}</p>
				<form action="/login" method="post">
					<div>
						<label>E-mail</label>
						<input type="email" class="form-control" name="email">
					</div>
					<div>
						<label>contraseña</label>
						<input type="password" class="form-control" name="password">
					</div>
					<input type="submit" class="btn btn-info mt-3" value="Inicia Sesion">
				</form>
			</div>
		</div>
	</div>
</body>
</html>