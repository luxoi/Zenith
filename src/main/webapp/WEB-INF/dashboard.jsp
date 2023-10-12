<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title><!--Css-->
<link rel="stylesheet" type="text/css" href="/css/style.css">
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
                                       	 <a href="" > <span>Click aqui para Empezar</span> </a>
										</div>
                </div>
        
        </div>
        
    </section>

    <script src="/jsproyecto/script.js"></script>
</body>
</html>