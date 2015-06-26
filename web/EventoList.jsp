<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Eventos</title>
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
                .space{
                    margin-left: 20px;
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
                    var x = confirm("Tem a certeza que quer eliminar os eventos selecionados?");
                    return x;
                }
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
                <h1>Lista de todos os eventos</h1>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <div class="well">
                        <h:form>
                            &nbsp 
                            <h:commandButton styleClass="btn" action="#{evento.selectAllEventos}" rendered="#{not empty evento.eventoItems}" value="Selecionar Todos"  />    
                            &nbsp  

                            <h:commandButton styleClass="btn" action="#{evento.cleanAllEventos}" rendered="#{not empty evento.eventoItems}" value="Limpar Todos"  /> 
                            &nbsp  

                            <h:commandButton styleClass="btn" value="Apagar" action="#{evento.backEndDeleteEventos}" onclick="return popup()">
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                            </h:commandButton>
                            &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 

                            <h:commandButton styleClass="btn btn-primary" action="#{evento.createSetupGASS}" value="Criar evento"/>
                            <br />
                            <br />

                            <br /> <br/>
                            <h:dataTable styleClass="table table-hover" value="#{evento.eventoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText escape="false" value="&nbsp;"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox id="Select2" value="#{item.checkBox}" title="Enviar Email2" />
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Título"/>
                                    </f:facet>
                                    <h:commandLink styleClass="command" style="color: #000" value="#{item.titulo}" action="#{evento.detailSetupGASS}">
                                        <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Data do Evento"/>
                                    </f:facet>
                                    <h:outputText value="#{item.dataEvento}">
                                        <f:convertDateTime pattern="dd/MM/yyyy" />
                                    </h:outputText>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Localização"/>
                                    </f:facet>
                                    <h:commandLink styleClass="command" style="color: #000" value="#{item.localizacao}" action="#{evento.detailSetupGASS}">
                                        <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Data de Criação"/>
                                    </f:facet>
                                    <h:outputText value="#{item.dataCriacao}">
                                        <f:convertDateTime pattern="dd/MM/yyyy" />
                                    </h:outputText>
                                </h:column>


                                <h:column>

                                    <h:outputText value=" "/>


                                    <h:outputText value=" "/>
                                    <h:commandLink styleClass="command" value="Inscrições" action="#{evento.listParticipaGASS}">
                                        <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>

                                    </h:commandLink>
                                </h:column>
                            </h:dataTable>

                        </h:form>
                    </div>
                    <h:form styleClass="space">
                        <h:commandButton styleClass="btn" value="Voltar à Homepage" action="#{candidato.checkCargo}" immediate="true"/>
                    </h:form>
                </div>
            </div>
        </body>
    </html>
</f:view>
