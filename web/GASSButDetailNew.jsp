<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Criar Detalhe Botão</title>
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
            <link href="/GASSv12/faces/bootstrap-responsive.css" rel="stylesheet">       <!--[if lt IE 9]>
            <script src="../assets/js/html5shiv.js"></script>
            <![endif]-->
            <!-- Fav and touch icons -->
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
            <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
            <link rel="shortcut icon" href="../assets/ico/favicon.png">
            <script>
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
                <div class="navbar-inner">
                    <div class="container"> 
                        <a class="brand" href="#">Get started</a>
                        <div class="nav-collapse collapse">
                            <ul class="nav">
                                <li class="active"><a href="#">GASS</a></li>
                                <li><a href="#about">Ajuda</a></li>
                                <li><a href="#contact">Contactos</a></li>
                            </ul>
                        </div><!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <div class="page-header">
                <h1> &nbsp Criar Detalhe Botão</h1>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <div class="well">
                        <h:form > 
                            <h:inputHidden id="validateCreateField" validator="#{detalhebotao.validateCreate}" value="value"/>
                            <h:panelGrid columns="2" styleClass="table table-hover" > 
                                <h:outputText value="Nome"/>
                                <h:inputText styleClass="form-signin input-block-level" id="nome" value="#{buttonDetailWorker.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />
                                <h:outputText value="Tipo"/>
                                <h:selectOneMenu id="botao" value="#{buttonDetailWorker.tipo}" title="Botao" required="true" requiredMessage="The botao field is required." >
                                    <f:selectItems value="#{buttonDetailWorker.tipoItemsAvailableSelectOne}"/>
                                </h:selectOneMenu>
                                <h:outputText value="Conteudo (caso seja Caixa de Texto)"/>
                                <h:inputTextarea styleClass="form-signin input-block-level" id="conteudo" value="#{buttonDetailWorker.conteudo}" title="Conteudo" />
                            </h:panelGrid>
                            <br />
                            <h:commandButton styleClass="btn btn-primary" action="#{detalhebotao.createGass}" value="Criar"/>
                            <br />
                            <br />
                            <h:commandButton styleClass="btn" action="#{botao.detailGassSetup}" value="Cancelar" immediate="true">
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}"/>
                            </h:commandButton>
                        </h:form>
                    </div>
                </div>
            </div>
        </body>
    </html>
</f:view>