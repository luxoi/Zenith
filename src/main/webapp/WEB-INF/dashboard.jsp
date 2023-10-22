<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title><!--Css-->
<link rel="stylesheet" type="text/css" href="/css/style.css?1">
<!--boxicons-->
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

</head>
<body>
	<div class="container">
    	<nav class="sidebar close">
         	<header>
            	<div class="image-text">
                	<span class="image">
                   	<a href="/dashboard"><img src="/imagesproyecto/logo.png" alt="logo"></a> 
                	</span>

                	<div class="text header-text">
                    	<span class="name">Zenith</span>
                    	<span class="profession">Bienvenido!</span>
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
				            	<i class='bx bx-home-alt icon'></i>
				            	<span class="text nav-text">Dashboard</span>
				        	</a>
				    	</li>
				    	</ul>
				    
				    
				    	<li class="nav-link">
				        	<a href="/proyectos">
				            	<i class='bx bxs-folder icon'></i>
				        	    	<span class="text nav-text">Proyectos</span>
				        	</a>
				       
				    	</li>   
				     	<ul class="nav-link" >
				     
				            	<c:forEach items="${proyectos}" var="proyecto">
				                	<li class="sub-menu-link">
				                    	<a href="/proyectos/${proyecto.id}">
				                        	<i class='bx bxs-memory-card icon'></i>
				                        	<span class="text sub-menu-text">${proyecto.titulo}</span>
				                    	</a>
				                	</li>
				            	</c:forEach>
				        	</ul>
				    
				    
				    
                    	<li class="nav-link">
                        	<a href="/premiun">
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
        	<div class="text">
            	<h1></h1>
                	<div class="principal">
                    	<span><h1>Bienvenido a nuestro gestor de proyectos</h1></span>
                    	<p>Aprende a organizarte de manera muy facil y didactica con nosotros, podras hacer seguimiento a tus tareas y diferentes proyectos en equipo</p>
                    	<img src="/images/wewewe.png" class="org">
                	</div>
                	<div class="principalderecha">
                		<h3>Si es tu primera vez en la pagina, puedes empezar creando un proyecto nuevo!</h3>
                 	<div class="boton">
	             		<a href="/crear/proyectos" > <span>Crear proyecto</span> </a>
					</div>
                	</div>	
        
        	</div>
        
    	</section>
    </div>
    <div class="footer">
    	<div class="subFooter">
    		<h4 class="tituloFooter">SOBRE NOSOTROS</h4>
    		<img class="imageFooter" src="/imagesproyecto/logo.png" alt="logo">
    		<p class="textoFooter">En Zenith Organización, estamos comprometidos a proporcionarte una plataforma de gestión de tareas simple y versátil que simplifica la forma en que te organizas, colaboras y logras tus metas. Diseñada para satisfacer las necesidades de todo tipo de público, nuestra plataforma combina la potencia de las herramientas de organización con una interfaz amigable y accesible.</p>
    	</div>
    	<div class="subFooter">
    		<h4 class="tituloFooter">NUESTRA MISIÓN</h4>
    		<p class="textoFooter">Queremos ayudarte a ser más productivo, a mantenerte enfocado en tus objetivos y a colaborar de manera eficaz con tu equipo. En Zenith, creemos que la gestión de tareas no debería ser complicada y que la organización debería estar al alcance de todos.</p>
    		<h4 class="tituloFooter">ÚNETE A ZENITH</h4>
    		<p class="textoFooter">Únete a la creciente comunidad de usuarios de Zenith Organización y descubre una nueva forma de organizarte y alcanzar tus metas. Tanto si eres un profesional ocupado, un estudiante con muchas tareas o simplemente alguien que busca simplificar su vida, Zenith tiene algo que ofrecer.</p>
    	</div>
    	<div class="subFooter">
    		<h4 class="tituloFooter">CONTÁCTANOS</h4>
    		<div class="Footerimages">
    			<img class="contactoFooterImages" alt="Twitter" src="/imagesproyecto/twitter.webp">
    			<img class="contactoFooterImages" alt="Instagram" src="/imagesproyecto/instagram.webp">
    			<img class="contactoFooterImages" alt="Github" src="/imagesproyecto/github.png">
    			<img class="contactoFooterImages" alt="Facebook" src="/imagesproyecto/facebook.png">
    		</div>
    		<div class="footerContacto">
    			<img class="contactoFooterImages" alt="locacion" src="/imagesproyecto/location.png">
    			<p class="textoFooter">:  Maipú 714, Concepción, Chile</p>
    		</div>
    		<div class="footerContacto">
    			<img class="contactoFooterImages" alt="celular" src="/imagesproyecto/cell.png">
    			<p class="textoFooter">:  +56973550175</p>
    		</div>
    		<div class="footerContacto">
    			<img class="contactoFooterImages" alt="email" src="/imagesproyecto/email.png">
    			<p class="textoFooter">:  zenithorganization@gmail.com</p>
    		</div>
    	</div>
    
    </div>
    <script src="/jsproyecto/script.js"></script>
</body>
</html>