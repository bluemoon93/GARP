<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Detalhes da Conta</title>
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

            <div class="page-header">
                <h1>Detalhes da Conta</h1>
            </div>
            <div class="row-fluid">

                <div class="span4">
                    <div class="well">
                        <h:form>
                            <h4>Detalhes</h4>
                            <h:panelGrid columns="2" styleClass="table table-hover">
                                <h:outputText value="eMail:"/>
                                <h:outputText value="#{candidato.candidato.candidatoPK.email}" title="Email" />
                                <h:outputText value="Nome:"/>
                                <h:outputText value="#{candidato.candidato.nome}" title="Nome" />
                                <h:outputText value="Data Nascimento:"/>
                                <h:outputText value="#{candidato.candidato.dataNasc}" title="DataNasc" >
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                                <h:outputText value="Telemovel:"/>
                                <h:outputText value="#{candidato.candidato.telemovel}" title="Telemovel" />

                                <h:commandButton styleClass="btn btn-primary" action="#{candidato.editarCandydate}" value="Editar">
                                    <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}" />
                                </h:commandButton>

                                <h:commandButton styleClass="btn" action="#{candidato.removeCandy}" value="Apagar Conta" onclick="return popup()" >
                                    <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}" />
                                </h:commandButton>
                                <h:commandButton styleClass="btn" action="#{passWorker.changePassPageCandy}" value="Alterar Password">
                                    <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}" />
                                </h:commandButton>
                            </h:panelGrid> 
                        </h:form>
                    </div>
                </div>
            </div>   
        </body>
    </html>
</f:view>
