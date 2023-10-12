<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title><!--Css-->
<link rel="stylesheet" type="text/css" href="/cssformulario/style.css">
<!--boxicons-->
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

</head>
<body>
    <nav class="sidebar close">
         <header>
            <div class="image-text">
                <span class="image">
                    <img src="/images/LOL_Logo.webp" alt="logo">
                </span>

                <div class="text header-text">
                    <span class="name">Ligoleyen</span>
                    <span class="profession">Luxoi</span>
                </div>
            </div>
            <i class='bx bx-chevron-right toggle'></i>
        </header>

        <div class="menu-bar">


            <div class="menu">
                        <li class="search-box">                  
                                <i class='bx bx-search-alt-2 icon' ></i>
                                <input type="search" placeholder="Search">
                        </li>


                    <ul class="menu-links">
                        <li class="nav-link">
                            <a href="/dashboard">
                                <i class='bx bx-home-alt icon' ></i>
                                <span class="text nav-text">Dashboard</span>
                            </a>
                        </li>
                    </ul>
                    <li class="nav-link">
                        <a href="/proyectos">
                            <i class='bx bxs-popsicle icon' ></i>
                            <span class="text nav-text">Proyects</span>
                        </a>
                    </li>
                  
                    <li class="nav-link">
                        <a href="#">
                            <i class='bx bx-wallet icon' ></i>
                            <span class="text nav-text">Premiun</span>
                        </a>
                    </li>
            
            </div>

            <div class="bottom-content">
                <li class="nav-link">
                    <a href="/logout">
                        <i class='bx bx-log-out icon' ></i>
                        <span class="text nav-text">Logout</span>
                    </a>
                </li>

                <li class="mode">
                <div class="moon-sun">
                    <i class='bx bx-moon icon moon' ></i>
                    <i class='bx bx-sun icon sun' ></i>
                </div>
                <span class="mode-text text">Dark mode</span>

                <div class="toggle-switch">
                    <span class="switch"></span>
                </div>
                </li>
            </div>
        </div>
    </nav>

    <section class="home">
        <div class="text">Crea tu Proyecto</div>
        <div class="form">
           <form:form action="/crearProyecto" method="POST" modelAttribute="nuevoProyecto">
           
            		<div>
            		
						<form:label path="plantilla">Plantilla</form:label>
						<form:select path="plantilla">
							<form:option value="estudiante">Estudiante</form:option>
							<form:option value="gimnasio">Gimnasio</form:option>
							<form:option value="pyme">PYME</form:option>
							<form:option value="custom">Custom</form:option>
						</form:select>
					
					</div>
		
		            <div>
						<form:label path="titulo">Titulo del proyecto</form:label>
						<form:input path="titulo" class="form-control"/>
						<form:errors path="titulo" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="descripcion">Descripcion del proyecto</form:label>
						<form:textarea path="descripcion" class="form-control"/>
						<form:errors path="descripcion" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="fecha">Fecha de entrega</form:label>
						<form:input type="date" path="fecha" class="form-control"/>
						<form:errors path="fecha" class="text-danger"></form:errors>
					</div>
		               <form:hidden path="creador" value="${usuario.id}"/>
                <button type="submit">Enviar</button>
           
           </form:form>
        </div>
    </section>

    <script type="text/javascript" src="/jsproyecto/script.js"></script>
</body>
</html>