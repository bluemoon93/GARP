<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Inscrições</title>
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
                    var x = confirm("Tem a certeza que pretende eliminar os participantes selecionados?");
                    return x;
                }
                function popupRegisto() {
                    var x = confirm("Tem a certeza que pretende registar os pagamentos de todos os participantes selecionados?");
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
                <h1><h:outputText value="Inscrições do #{evento.evento.titulo}"/></h1>
            </div>

            <h:form>

                &nbsp  &nbsp
                <h:commandButton styleClass="btn btn-primary" value="Emitir recibos" action="#{evento.listarEventosRecibos}">
                    <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                    <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                    <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                    <f:param name="jsfcrud.relatedController" value="evento" />
                    <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController" />
                </h:commandButton>
                &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
                <h:commandButton styleClass="btn btn-primary" value="Registar pagamentos" action="#{evento.registarPagamentosEventos}" onclick="return popupRegisto()">
                    <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                    <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                    <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                    <f:param name="jsfcrud.relatedController" value="evento" />
                    <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController" />
                </h:commandButton>
                <br/><br/>
                &nbsp  &nbsp
                <h:commandButton styleClass="btn" value="Apagar" action="#{evento.backEndDeleteParticipante}" onclick="return popup()">
                    <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                    <%--                 <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                                      <f:param name="jsfcrud.relatedController" value="evento" />
                                      <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController" />
                    --%>         
                </h:commandButton>


                <br /> <br />
                &nbsp &nbsp &nbsp
                <h:commandButton styleClass="btn" action="#{evento.selectAllParticipantes}" rendered="#{not empty evento.evento.participaCollection}" value="Selecionar Todos"  />    
                &nbsp  

                <h:commandButton styleClass="btn" action="#{evento.cleanAllParticipantes}" rendered="#{not empty evento.evento.participaCollection}" value="Limpar Todos"  /> 
                <br /> <br />
                <div class="row-fluid">
                    <div class="span4"> 
                        <div class="well">
                            <h4>Participantes inscritos</h4>
                            <h:panelGrid styleClass="table table-hover">
                                <h:outputText rendered="#{empty evento.evento.participaCollection}" value="Não existem inscrições."/>
                                <h:dataTable value="#{evento.evento.participaCollection}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                             rendered="#{not empty evento.evento.participaCollection}">

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText escape="false" value="&nbsp;"/>
                                        </f:facet>
                                        <h:selectBooleanCheckbox id="Select2" value="#{item.checkBox}" title="Enviar Email2" />
                                    </h:column>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Email"/>
                                        </f:facet>
                                        <h:commandLink styleClass="link" value="#{item.participaPK.email}" action="#{participa.editSetupGASS}">

                                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                                            <%--               <f:param name="jsfcrud.relatedController" value="evento" />
                                                           <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController" />
                                                         <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                                            --%>           
                                        </h:commandLink>
                                    </h:column>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Quantia"/>
                                        </f:facet>
                                        <h:outputText value="#{item.valorAPagar} €"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Já pagou"/>
                                        </f:facet>
                                        <h:outputText value="sim" rendered="#{item.pagou == true}" />
                                        <%--    
<h:outputText value="sim" rendered="#{item.pagou == true && participa.valorRecibo == 0}" >
                                        <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                                    </h:outputText>
<h:outputText value="sim, --" rendered="#{item.pagou == true && participa.valorRecibo == null}" />
                                    <h:outputText value="sim, #{participa.valorRecibo}" rendered="#{item.pagou == true && participa.valorRecibo != null}" />
                                        --%>                     <h:selectBooleanCheckbox id="Select3" value="#{item.pagou}" rendered="#{item.pagou == false}" title="Enviar Email3" />
                                    </h:column>
                                </h:dataTable>
                            </h:panelGrid>
                        </div>
                    </div>
                    <div class="span4">
                        <div class="well">
                            <h4>Adicionar inscrição</h4>
                            <h:form>
                                <h:inputHidden id="validateCreateField" validator="#{participa.validateCreate}" value="value"/>

                                <h:panelGrid columns="2" styleClass="table table-hover">

                                    <h:outputText value="Email"/>
                                    <h:inputText styleClass="form-signin input-block-level" id="email" value="#{participa.participa.participaPK.email}" title="Email"/>

                                    <h:outputText value="Quantia (€)"/>
                                    <h:inputText styleClass="form-signin input-block-level" id="valorAPagar" value="#{participa.participa.valorAPagar}" title="ValorAPagar"/>

                                </h:panelGrid>

                                <h:commandButton styleClass="btn btn-primary" action="#{participa.createGASS}" value="Criar" />
                            </div>
                        </div>
                    </div>

                </h:form>
            </h:form>
        </body>
    </html>
</f:view>