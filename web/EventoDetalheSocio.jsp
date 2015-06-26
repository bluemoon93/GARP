<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Detalhes do Evento</title>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
               <script type="text/javascript" src="/GASSv12/faces/bootstrap.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.min.js"></script>
 
            <!-- Le styles -->
            <link href="/GASSv12/faces/bootstrap.css" rel="stylesheet">
            <style type="text/css">
                body {
                    padding-top: 60px;
                    padding-bottom: 40px;
                }

                .form-signin {
                    max-width: 300px;
                    padding: 19px 29px 29px;
                    margin: 0 auto 20px;
                    background-color: #fff;
                    border: 1px solid #e5e5e5;
                    -webkit-border-radius: 5px;
                    -moz-border-radius: 5px;
                    border-radius: 5px;
                    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                    box-shadow: 0 1px 2px rgba(0,0,0,.05);
                }
                .form-signin .form-signin-heading,
                .form-signin .checkbox {
                    margin-bottom: 10px;
                }
                .form-signin input[type="text"],
                .form-signin input[type="password"] {
                    font-size: 16px;
                    height: auto;
                    margin-bottom: 15px;
                    padding: 7px 9px;
                }

            </style>
            <link href="/GASSv12/faces/bootstrap-responsive.css" rel="stylesheet">
            <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
            <!--[if lt IE 9]>
              <script src="../assets/js/html5shiv.js"></script>
            <![endif]-->

            <!-- Fav and touch icons -->
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
            <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
            <link rel="shortcut icon" href="../assets/ico/favicon.png">


            <script>
                $('.dropdown-toggle').dropdown();

                function popup() {
                    var x = confirm("Tem a certeza que quer eliminar o evento?");
                    return x;
                }

                function popupEliminar() {
                    var x = confirm("Tem a certeza que pretende retirar a sua inscrição do evento?");
                    return x;
                }
            </script>

            <script>
                $(function() {
                    var date1 = new Date;
                    $(".date").datepicker({
                        beforeShowDay: function(date) {
                            return [date > date1, ""];
                        }
                    });

                    $(".date").datepicker('option', 'dateFormat');
                });
            </script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>

            <div class="navbar navbar-inverse navbar-fixed-top">
                <div  class="navbar-inner">
                    <div class="container-fluid">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                        </a>

                        <div class="btn-group pull-right">
                            <h:form>            
                                <h:commandButton styleClass="btn"  value="Logout" action="#{socio.logout()}"></h:commandButton>
                            </h:form>
                        </div>

                        <p class="navbar-text pull-right" style="margin-right: 40px">
                            Logged in as <h:outputText value="#{candidato.pessoaLogin.nome}"/>
                        </p>
                        <h:form>
                            <div style="background: #000" class="nav-collapse">
                                <ul class="nav">

                                    <li><h:commandLink  value="#{associacao.associacao.nome}" action="#{socio.homeSecretariado()}"/></li>
                                    <li><h:commandLink  value="GASS" action="#{associacao.gassHome}"/></li>
                                    <li><a href="Contactos.jsp">Contactos</a></li> 
                                    <li><a href="BackEndHome1.jsp">Home</a></li>

                                    </li>
                                </ul>
                            </div>
                        </h:form>
                        <div style="background: #000" class="nav-collapse pull-right">
                            <%--<h:form>                                
                                <% beanWorker.setPage(request.getHeader("Referer")); %>
                                <a href="<%=request.getHeader("Referer")%>" >Voltar</a>
                            </h:form>--%>
                        </div>

                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>

            <div class="page-header">
                <h1>Detalhes do Evento</h1>
            </div>

            <div class="row-fluid">

                <div class="span4">
                    <div class="well">
                        <h:form>
                            <h4>Detalhes</h4>
                            <h:panelGrid columns="2" styleClass="table table-hover">
                                <h:outputText value="Título"/>
                                <h:outputText value="#{evento.evento.titulo}" title="Titulo" />
                                <h:outputText value="Data do Evento"/>
                                <h:outputText value="#{evento.evento.dataEvento}" title="DataEvento" >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:outputText>
                                <h:outputText value="Localização"/>
                                <h:outputText value="#{evento.evento.localizacao}" title="Localizacao" />
                                <a id="pq" name="organization" href="#conteudo">Conteúdo</a>
                                <h:outputText value=""/>
                                <h:outputText value="Data de Criação"/>
                                <h:outputText value="#{evento.evento.dataCriacao}" title="DataCriacao" >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:outputText>
                                <h:outputText value="Preço"/>
                                <h:outputText value="#{evento.evento.quantia}" title="Preco" />
                            </h:panelGrid>
                        </h:form>
                    </div>
                </div>

                <h:form>
                    <div class="span4">
                        <div class="well">   
                            <h4>Estado da participação no evento</h4>
                            <h:commandButton styleClass="btn btn-warning" action="#{participa.participarEvento}" value="Participar no evento #{evento.evento.titulo}" rendered="#{participa.jaParticipa() == false}" immediate="true">
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                            </h:commandButton>
                            <h:outputText value="Encontra-se inscrito no evento." rendered="#{participa.jaParticipa() == true}"/>
                            <br/>
                            <h:outputText rendered="#{participa.participa.pagou == false && participa.jaParticipa() == true}" value="O pagamento da sua inscrição ainda não foi registado."/>
                            <h:outputText value="O pagamento da sua inscrição já está registado." rendered="#{participa.participa.pagou == true && participa.jaParticipa() == true}"/>
                            <br/>
                            <h:commandButton value="Retirar Inscrição" action="#{participa.participanteRetirarInscricao}" rendered="#{participa.jaParticipa() == true}" onclick="return popupEliminar()"/>
                        </div>
                    </div>
                </h:form>
            </div>
            <div class="row-fluid">
                <div class="well">
                    <h:form id="conteudo">
                        <h5>Conteúdo</h5>
                        <h:outputText value="#{evento.evento.conteudo}" title="Conteudo" />
                    </h:form>
                </div>
            </div>
        </body>
    </html>
</f:view>
