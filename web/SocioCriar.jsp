<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Página de Inscrição</title>
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
                .bold{
                    font-size: 20px;
                    font-weight: bold;
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
                    var x = confirm("Tem a certeza?");
                    return x;
                }
            </script>
            <script>
                function popupApagar() {
                    var x = confirm("Tem a certeza que pretende eliminar o sócio?");
                    return x;
                }
            </script>
            <script>
                function popupQuotas() {
                    var x = confirm("Tem a certeza que pretende eliminar os pagamentos selecionados?");
                    return x;
                }
            </script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <div class="page-header">
                <h1> &nbsp Inscrição</h1>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <div class="well">
                        <h:form styleClass="jsfcrud_list_form"> 
                            <h:inputHidden id="validateCreateField" validator="#{socio.validateCreate}" value="value"/>
                            <h:panelGrid columns="2" styleClass="table table-hover" > 
                                <h:outputText value="Nif: *"/>
                                <h:inputText styleClass="form-signin input-block-level" id="nif" value="#{socioWorker.nif}" title="Nif" required="true" requiredMessage="The nif field is required." />
                                <h:outputText value="eMail: *"/>
                                <h:inputText styleClass="form-signin input-block-level" id="emailz" value="#{socioWorker.email}" title="Tipo" required="true" requiredMessage="The tipo field is required." />
                                <h:outputText value="Password: *"/>
                                <h:inputSecret styleClass="form-signin input-block-level" id="pass" value="#{socioWorker.password}" title="Pw" required="true" requiredMessage="The tipo field is required." />
                                <h:outputText value="Tipo: *"/>
                                <h:selectOneMenu id="tipo2" value="#{socioWorker.tipoSocio}" title="Associacao" required="true" requiredMessage="The tiposocio field is required." >
                                    <f:selectItems value="#{socioWorker.tipoItemsAvailableSelectOne(0)}"/>
                                </h:selectOneMenu>
                            </h:panelGrid>
                            <br/>
                            <h:outputText value="Campos marcados com * são obrigatórios."/>
                            <br />
                            <br/>
                            <h:commandButton styleClass="btn btn-primary" action="#{socio.createSoss(0)}" value="Inscrever como Sócio"/>
                            <br />
                            <br />
                        </h:form>
                    </div>
                </div>
            </div>
        </body>
    </html>
</f:view>