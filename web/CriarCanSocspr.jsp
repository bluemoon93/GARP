<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Inscrição</title>
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
            <script>
                function popupBotao() {
                    var x = confirm("Tem a certeza que pretende eliminar os botões selecionados?");
                    return x;
                }
            </script>
            <script>
                function popupMetodo() {
                    var x = confirm("Tem a certeza que pretende eliminar os métodos de passagem selecionados?");
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
                        <h:form >

                            <h:panelGrid columns="2" styleClass="panel">
                                <h:outputText value="Email"/>
                                <h:inputText styleClass="form-signin input-block-level" id="email" value="#{ocupaWorker.email}" title="Email" required="true" requiredMessage="O campo email é obrigatório." />
                                <h:outputText value="Palavra Passe "/>
                                <h:inputSecret styleClass="form-signin input-block-level" id="palavraPasse" value="#{ocupaWorker.password}" title="PalavraPasse" required="true" requiredMessage="O campo palavra passe é obrigatório." />
                                <h:outputText value="Confirme a Palavra Passe"/>
                                <h:inputSecret styleClass="form-signin input-block-level" id="palavraPasse1" value="#{stringWorker.pw}" title="PalavraPasse1" required="true" requiredMessage="O campo de confirmação de palavra passe é obrigatório." />
                                <h:outputText value="Nome"/>
                                <h:inputText styleClass="form-signin input-block-level" id="nome" value="#{ocupaWorker.nome}" title="Nome" />
                                <h:outputText value="Data de Nascimento (dd/MM/yyyy)"/>
                                <h:inputText styleClass="form-signin input-block-level" id="dataNasc" value="#{ocupaWorker.dataNasc}" title="DataNasc" >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:inputText>
                                <h:outputText value="Telemóvel"/>
                                <h:inputText styleClass="form-signin input-block-level" id="telemovel" value="#{ocupaWorker.telefone}" title="Telemovel" />

                                <h:outputText value="NIF "/>
                                <h:inputText styleClass="form-signin input-block-level" id="nif" value="#{ocupaWorker.nif}" title="Nif" required="true" requiredMessage="O campo NIF é obrigatório." />
                                
                                <h:outputText value="Tipo: "/>
                                <h:selectOneMenu  id="tipo2" value="#{ocupaWorker.tipo}" title="Associacao" required="true" requiredMessage="O campo tipo de sócio é obrigatório." >
                                    <f:selectItems value="#{ocupaWorker.tipoItemsAvailableSelectOne2(1111)}"/>
                                </h:selectOneMenu>
                    

                            </h:panelGrid>
                            <br/>
                            <h:commandButton styleClass="btn btn-primary" action="#{socio.createGass2(1111)}" value="Registar Conta"/>
                        </h:form>
                    </div>
                </div>
            </div>
        </body>
    </html>
</f:view>
