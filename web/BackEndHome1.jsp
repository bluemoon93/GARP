
<%-- 
    Document   : BackEndHome1
    Created on : 16/Mai/2013, 19:14:30
    Author     : Tiago
--%>


<%@page import="entitiesGASS.jsf.SocioController"%>
<%@page import="entitiesGASS.jsf.beanWorker"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
    <html>
        <head>

            <meta charset="utf-8">

            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>ListaSecretariado</title>


            <!-- dropdown-->
            <script src="js/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>

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

            <h:form>
                <div class="masthead">
                    <h3  <h:outputText styleClass="muted" value="#{associacao.associacao.nome}"/></h3>

                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container">

                                <div class="nav-collapse collapse">

                                    <ul class="nav">
                                        <li><h:commandLink action="#{pagamentoquota.listQuota}" value="Quotas" rendered="#{ocupa.read == true}"/></li>
                                        &nbsp 
                                        <li><h:commandLink styleClass="link" action="#{pagamentoquota.getAllNullReceipt}" value="Pagamentos por processar" rendered="#{ocupa.read == true}" /></li>
                                        &nbsp 
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Eventos <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a><h:commandLink styleClass="link" action="#{evento.createSetupGASS}" value="Criar Evento"/></a></li>
                                                &nbsp 
                                                <li><a><h:commandLink styleClass="link" action="#{evento.listSetupGASS}" value="Ver Eventos"/></a></li>
                                            </ul>
                                        </li> 

                                         <li><h:commandLink styleClass="link"  value="Sócios" action="#{candidato.homeSecretariado()}"/> </li>

                                        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 

                                      <%--  <div class="nav nav-pills pull-right">
                                            <h:form>
                                                <h:commandButton styleClass="btn btn-primary btn-large"  value="Sócios" action="#{candidato.homeSecretariado()}"/>
                                            </h:form>
                                        </div>--%>
                                        <p></p>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.navbar -->
                </div>
            </h:form>

            <div class="row-fluid">

                <div class="span4">
                    <div class="well">
                        
                        <h:form>
                             <h4>Novos Pedidos de Sócios</h4>
                            <h:panelGroup> 
                                <h:dataTable styleClass="table table-hover" value="#{socio.iniciaisList}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                             rendered="#{not empty socio.iniciaisList}">        
                                    <h:column>

                                        <f:facet name="header"><center>
                                               
                                            </f:facet>
                                            <h:commandLink styleClass="link" value="#{item.candidato.nome}" action="#{socio.socPayList}">
                                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                            </h:commandLink>
                                        </h:column>
                                    </h:dataTable>
                                </h:panelGroup>
                            </h:form>
                    </div>
                </div>

                <div class="span4">
                    <div class="well">
                        <h4>Pagamentos por Processar</h4>
                        <h:form>
                            
                            <h:panelGroup> 
                                <h:dataTable styleClass="table table-hover" value="#{pagamentoquota.pagam}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                             rendered="#{not empty pagamentoquota.pagam}">        
                                    <h:column>
                                       
                                            <h:outputText value="#{item.socio.candidato.nome}, #{item.pagamentoquotaPK.ano}"/></center>
                                        </h:column>
                                    </h:dataTable>
                                </h:panelGroup>
                            <h:commandLink styleClass="btn btn-info" action="#{pagamentoquota.getAllNullReceipt}" value="Registar" rendered="#{not empty pagamentoquota.pagam && ocupa.read == true}"/>
                            
                        </h:form>
                    </div>
                </div>


              
            </div>

        </div>
    </div>
</body>
</html>
</f:view>