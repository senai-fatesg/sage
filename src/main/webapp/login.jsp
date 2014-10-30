<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Bem Vindo ao SAGE</title>

<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script>
	function focar() {
		document.getElementById("usuario").focus();
	}
</script>
</head>
<body>
	<form action="j_spring_security_check" method="post">
		<div class="wrapper">
			<div class="header">
				<div class="container">
					<div class="row branding">
						<div class="span6">
							<h1 class="pull-left">
								<a href="index.html"><strong>Sage</strong></a>
							</h1>
						</div>
					</div>
					<div class="row navigation">
						<div class="span12">
							<ul class="nav nav-tabs">
								<li><a href="menu1.jsp">Menu 1</a></li>
								<li><a href="menu2.jsp">Menu 2</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="container content">
				<div class="row">
					<div class="span8 leftContent">
						<h2>Sistema de Acompanhamento e Gerenciamento de Estágio</h2>
						<div class="row">
							<img
								src="${pageContext.request.contextPath}/imagem/fieg-senai.jpg"
								alt="Logo Senai" class="thumbnail" style="margin: 0 auto;" />
						</div>

					</div>

					<div class="span4 sidebar">
						<h2>
							<a href="#" class="btn btn-large btn-warning">Alguma ação</a>
						</h2>
						<div class="well quickSignupForm">
							<h3>Acesso ao Sistema</h3>
							<%
								if (request.getParameter("msg") != null) {
									out.print("<span style='color: red;font-weight: bold;'>Usuário ou senha incorretos</span>");
								}
							%>
							<label>Login</label> <input type="text" required id="usuario"
								placeholder="Login" name="j_username" class="span3" /> <label>Senha</label>
							<input name="j_password" placeholder="Senha" required
								type="password" class="span3" /> <input
								class="btn btn-large btn-primary btnSignup" type="submit"
								value="Entrar" />
						</div>
						<h3>Titulo caixa</h3>
						<p>Conteúdo caixa</p>
					</div>

				</div>
				<div class="row">
					<div class="span8">
						<ul class="breadcrumb">
							<li><a href="http://www.senaigo.com.br/portaldodocente"
								target="_blank">PORTAL DO DOCENTE</a> <span class="divider">/</span></li>
							<li><a href="http://www.senaigo.com.br/portaldoaluno"
								target="_blank">PORTAL DO ALUNO</a> <span class="divider">/</span></li>
							<li><a href="http://www.revistatecnologiaaplicada.com.br"
								target="_blank">REVISTA FATESG </a> <span class="divider">/</span></li>
							<li><a href="http://www.sistemafieg.org.br/intranet"
								target="_blank">INTRANET FIEG</a> <span class="divider">/</span></li>
							<li><a href="http://www.sistemafieg.org.br" target="_blank">FIEG</a>
								<span class="divider">/</span></li>
							<li><a href="http://www.sesigo.org.br" target="_blank">SESI</a>
								<span class="divider">/</span></li>
							<li><a href="http://www.senaigo.com.br" target="_blank">SENAI</a>
								<span class="divider">/</span></li>
							<li><a href="http://www.ielgo.com.br" target="_blank">IEL</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
</body>
</html>