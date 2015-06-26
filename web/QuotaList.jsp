<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ page import="java.io.*,java.util.*" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listar Pagamentos Quotas </title>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.min.js"></script>
            <%--
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
             <link href="/GASSv7FDS/faces/bootstrap.js" rel="stylesheet">
     <link href="/GASSv7FDS/faces/bootstrap.min.js" rel="stylesheet">
            
            --%>
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
                    var x = confirm("Tem a certeza?");
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
                <h1>Listar Pagamentos Quotas</h1>
            </div>

            <br />

            <h:form>
                <div class="span8">
                    &nbsp
                    <br/>
                    <br/>
                    <h:panelGroup>

                        &nbsp
                        <h:commandButton styleClass="btn btn-primary" value="Todos os anos" action="#{pagamentoquota.findAll}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandButton>

                        &nbsp <%-- este comando da um espacamento entre variaveis--%>


                        <h:dataTable styleClass="tabelaEsc" value="#{pagamentoquota.listaAnos}" var="item" >    
                            <h:column>
                                <tr>
                                    <h:commandButton styleClass="btn btn-info" action="#{pagamentoquota.filtrar}" value="#{item}">
                                        <f:param name="jsfcrud.currentAno" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.intC].jsfcrud_invoke}" />
                                    </h:commandButton>
                                </tr>
                                &nbsp
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </div>
                <div class="span4"></div>
                <div class="span4">
                    <div class="well">
                        <h:outputText value="Caso pretenda fazer o download da tabela dos anos selecionados, carregue com o botão direito e escolha \"Guardar Link Como\" " />
                        <h:outputLink value="quotas.csv">aqui</h:outputLink> 
                        </div>
                    </div>
            </h:form>
            <br/><br/><br/><br/>
            <div class="row-fluid"> 

                <div class="well">
                    <h:form styleClass="table table-hover">
                        <h:panelGroup styleClass="table table-hover">
                            <h:dataTable value="#{pagamentoquota.pagamentoquotaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Ano da quota"/>
                                    </f:facet>
                                    <h:commandLink styleClass="link" value="#{item.pagamentoquotaPK.ano}" style="color: #000" action="#{pagamentoquota.detalhesQuota}">
                                        <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>

                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Quantia"/>
                                    </f:facet>

                                    <h:commandLink styleClass="link" value="#{item.quantia}" style="color: #000" action="#{pagamentoquota.detalhesQuota}">
                                        <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                                    </h:commandLink> 
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Número de Recibo"/>
                                    </f:facet>
                                    <h:commandLink styleClass="link" value="#{item.nrRecibo}" style="color: #000" action="#{pagamentoquota.detalhesQuota}">
                                        <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>

                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Sócio"/>
                                    </f:facet>

                                    <h:commandLink styleClass="link" value="#{item.socio.candidato.nome}" style="color: #000" action="#{socio.socPayList}">
                                        <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item.socio][socio.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Número de Sócio"/>
                                    </f:facet>
                                    <h:commandLink styleClass="link" value="#{item.socio.nrSocio}" style="color: #000" action="#{socio.socPayList}">
                                        <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item.socio][socio.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>
                                </h:column>
                            </h:dataTable> 
                        </h:panelGroup>   
                    </h:form>
                </div>

                <h:form styleClass="space">
                    <h:commandButton  styleClass="btn" action="#{socio.homeSecretariado}" value="Voltar"/>
                </h:form>
            </div>
        </body>
    </html>
</f:view>
