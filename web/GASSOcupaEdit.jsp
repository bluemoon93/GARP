<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editar Ocupa</title>
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
                <h1> &nbsp Editar Ocupa</h1>
            </div> 




            <div class="row-fluid">
                <div class="span4">
                    <div class="well">
                        <h:form styleClass="jsfcrud_list_form">  
                            <h:panelGrid columns="2" styleClass="table table-hover" > 
                                <h:outputText value="DataFim (dd/MM/yyyy)"/>
                                <h:inputText styleClass="form-signin input-block-level"  id="dataFim" value="#{ocupa.ocupa.dataFim}" title="DataFim" required="true" requiredMessage="The dataFim field is required." >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:inputText>
                                <h:outputText value="DataInicio (dd/MM/yyyy)"/>
                                <h:inputText styleClass="form-signin input-block-level"  id="dataInicio" value="#{ocupa.ocupa.dataInicio}" title="DataInicio" required="true" requiredMessage="The dataInicio field is required." >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:inputText>
                                <h:outputText value="Socio:"/>
                                <h:outputText value=" #{ocupa.ocupa.socio}" title="Socio" />
                                <h:outputText value="Cargo:"/>
                                <h:outputText value=" #{ocupa.ocupa.cargo}" title="Cargo" />
                                <h:commandButton styleClass="btn btn-primary" action="#{ocupa.editGass}" value="Guardar">
                                    <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
                                </h:commandButton>

                                <h:commandButton styleClass="btn" action="#{associacao.detailSetupGass}" value="Cancelar" immediate="true">
                                    <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                </h:commandButton>
                            </h:panelGrid>
                        </h:form>
                    </div>
                </div>
            </div>

        </body>
    </html>
</f:view>
