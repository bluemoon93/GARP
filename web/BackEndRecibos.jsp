<%-- 
    Document   : BackEndRecibos
    Created on : 6/Mai/2013, 21:41:00
    Author     : Nuno
--%>
<%@page contentType="text/html"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

            <title>Pagamentos sem Recibo</title>
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
                <h1>Lista de Pagamentos sem recibo</h1>
            </div>

            <div class="row-fluid">
                <div class="span4">
                    <div class="well">            

                        <h:form styleClass="table table-hover">
                            &nbsp 
                            <h:commandButton styleClass="btn btn-warning" action="#{pagamentoquota.backToList}" value="Voltar" /> 
                            &nbsp

                            <h:commandButton styleClass="btn btn-primary" action="#{pagamentoquota.registPagam}" rendered="#{not empty pagamentoquota.noReceiptList}" value="Registar Pagamentos" /> 
                            &nbsp

                            <h:commandButton styleClass="btn" action="#{pagamentoquota.selectAll}" rendered="#{not empty pagamentoquota.noReceiptList}" value="Selecionar Todos"  /> 
                            &nbsp  

                            <h:commandButton styleClass="btn" action="#{pagamentoquota.cleanAll}" rendered="#{not empty pagamentoquota.noReceiptList}" value="Limpar Todos"  /> 
                            &nbsp  


                            <h:panelGroup> 
                                <br/>
                                &nbsp 
                                <h:outputText rendered="#{empty pagamentoquota.noReceiptList}" value="Não há pagamentos sem recibo"/><br> </br>
                                <h:dataTable value="#{pagamentoquota.noReceiptList}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                             rendered="#{not empty pagamentoquota.noReceiptList}">        

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText escape="false" value="&nbsp;"/>
                                        </f:facet>
                                        <h:selectBooleanCheckbox id="Select" value="#{item.checkBox}" title="Enviar Email" />
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Nome"/>
                                        </f:facet>
                                        <h:outputText value="#{item.nome}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Email"/>
                                        </f:facet>
                                        <h:outputText value="#{item.email}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="NIF"/>
                                        </f:facet>
                                        <h:outputText value="#{item.nif}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Data Pagamento"/>
                                        </f:facet>
                                        <h:outputText value="#{item.data_Pagam}">
                                            <f:convertDateTime pattern="dd/MM/yyyy" />
                                        </h:outputText>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Ano"/>
                                        </f:facet>
                                        <h:outputText value="#{item.ano}"/>
                                    </h:column>
                                </h:dataTable>
                            </h:panelGroup>
                        </h:form>
                    </div>
                    <h:form styleClass="space">
                        <h:commandButton styleClass="btn btn-warning" action="#{pagamentoquota.backToList}" value="Voltar" /> 
                    </h:form>
                </div>
            </div>
        </body>
    </html>
</f:view>