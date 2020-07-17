<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>P�gina inicial</title>
<link rel="shortcut icon" type="image/x-icon" href="img/ico.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<script src="js\jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="bootstrap\css\bootstrap.min.css" />
<script src="bootstrap\js\bootstrap.min.js"></script>
<link rel='stylesheet' href='csss.css' />
<script>
	$(document).ready(function () {
    	setTimeout(function () {
        $('.wrapper').addClass('loaded');
        }, 3000);
    });
    jQuery(function () {
    	$(window).load(function () {
        	$('.wrapper').removeClass('preload');
        });

    });
</script>
</head>
<body>
	<% 	String whois = (session.getAttribute("user") == null) ? "nobody" : session.getAttribute("user").toString(); 
		String result = request.getParameter("result");
	%>
	
	<% 	if(result != null){
			if(result.equals("right")){
	%>
			<div class="alert alert-danger" align="center">
				Parab�ns, evento cadastro com sucesso!
			</div>
	<%			
			}
		}
	%>
	<% if(!whois.equals("nobody")){ %>
	<form method="POST" action="process">
    	<input type="hidden" name="action" value="auth" />
    	<input type="hidden" name="log" value="false" />
    	<button class="container-fluid col-2 btn btn-outline-light" style="color: white; font-size: 2vw;" type="submit">Sair</button>
    </form>
    <a class="container-fluid col-2 btn btn-outline-light" style="color: white; font-size: 2vw;" href="profile.jsp">Ver perfil</a>
    
    <section id="sectioncards">
        <header id="headercards" style="text-align: center;">
			
            <div id="carouseldiv">
                <img class="d-block w-100" src="img/logoBranco.svg" max-height="900px">
            </div>

            <h6 style="color: #fff; font-size: 25px; line-height: 1.2; text-align: center;">Para come�ar a utilizar
                escolha uma das duas op��es abaixo.</h6>
        </header>
        <div class="container" style="padding-top: 3rem; margin-top: 3rem;">
            <div class="box">
                <div class="imgBx">
                    <img src="img/terno.jpg">
                </div>
                <div class="content" style="font-weight: bold;">
                    <h3 style="font-weight: bold;">Criar Eventos</h3><br>
                    <p>O EventCreator permite que o usu�rio crie campenatos sejam eles em grupo ou individuais, o
                        chaviamento e sorteio de partidas fica por nossa conta.</p>
                    <div style="border-bottom: 5rem solid transparent;"></div>
                    <button class="back"><a style="color: white; cursor: pointer; border: none; text-decoration: none;" href="cadEvent.jsp">CRIAR UM EVENTO</a></button>

                </div>
            </div>
            <div class="box">
                <div class="imgBx">
                    <img src="img/friends123.jpg">
                </div>
                <div class="content">
                    <h3 style="font-weight: bold;">Participe de um evento</h3><br>
                    <p>Veja os eventos disponiveis e participe do evento que mais chamou sua aten��o.</p>
                    <div style="border-bottom: 5rem solid transparent;"></div>
                    <button class="back"><a style="color: white; cursor: pointer;text-decoration: none;"
                            href="partEvent.jsp">PARTICIPAR DE EVENTO</a></button>
                </div>
            </div>
        </div>
    </section>

    <section>
        <div id="carouseldiv">
            <div id="carousel">
                <div class="carousel-inner img-responsive">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="img/tduo..jpg" max-height="900px">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer class="footer" style="background-color:white; padding-top: 5rem;">
        <div class="container">
            <div class="row">
                <img class="d-block w-100" src="img/logot.svg" max-height="900px">
                <div class="col-sm-12 col-md-6" style="margin-right: 25%; padding-top: 5rem;">
                    <h6 style="font-weight: bold; color: #ff1040;">Sobre</h6>
                    <p class="text-justify;" style="color: #ff1040;">O sistema Event Creator funciona da seguinte maneira. O usu�rio ir� se
                        cadastrar e ter� sua pr�pria conta, visualizando os tipos de torneio e o n�mero de participantes
                        do grupo para a realiza��o deste evento, poder� tamb�m monitorar a situa��o de seu time, se est�
                        sendo uma equipe imbat�vel, mediana ou perdedora. E os novos jogos que ocorrer� futuramente.</p>
                </div>
                
                <div style="border-bottom: 400px solid transparent; "></div>
                <button class="backfooter"><a style="color: #ff1040; cursor: pointer;text-decoration: none; padding-top: 5rem;"
                    href="#sectioncards">Voltar ao topo</a></button>
            </div>
        </div>
    </footer>
    <%}else{ %>
    	String urlP = "ca4f395fba8a.ngrok.io";
    	<script>alert("Voc� precisa estar logado para acessar essa p�gina.");
    			window.location.href = "http://localhost:8080/EventCreator/login.jsp";
    	</script>
    <%} %>
</body>
</html>