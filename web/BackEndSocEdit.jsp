<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editar detalhes</title>
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

            <script type="text/javascript">
                /*
                 * jQuery UI Datepicker: Disable Specific Dates
                 * http://salman-w.blogspot.com/2013/01/jquery-ui-datepicker-examples.html
                 */
                $(function() {
                    var date1 = new Date;
                    $(".dateP").datepicker({
                        beforeShowDay: function(date) {
                            return [date > date1, ""];
                        }
                    });

                    $(".dateP").datepicker('option', 'dateFormat', 'dd/mm/yy');
                });
            </script>
            <script>
                function popupConfirm() {
                    var x = confirm("Tem certeza que pretende guardar as alterações?");
                    return x;
                }
            </script>

            <script>
                function popupCancel() {
                    var x = confirm("Alterações não serão guardadas. Tem a certeza que pretende continuar?");
                    return x;
                }
            </script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editar Sócio</h1>

            <section id="forms">
                <h:form styleClass="form-horizontal well">
                    <fieldset>

                        <legend>Características do sócio</legend>

                        <div class="control-group">
                            <label class="control-label" for="input01">Número de Sócio</label>
                            <div class="controls">
                                <h:outputText styleClass="form-signin input-block-level" id="nrSocio" value="#{socio.socio.nrSocio}" title="NrSocio" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Nome</label>
                            <div class="controls">
                                <h:inputText  styleClass="form-signin input-block-level" id="Nome" value="#{socio.socio.candidato.nome}" title="Nome" required="true" requiredMessage="O campo \"Nome\" é obrigatório." />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Email</label>
                            <div class="controls">
                                <h:outputText id="email" value="#{socio.socio.candidato.candidatoPK.email}" title="Email" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">NIF</label>
                            <div class="controls">
                                <h:outputText  value="#{socio.socio.socioPK.nif}" title="Nif"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" for="input01">Telemóvel</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="Telemovel" value="#{socio.socio.candidato.telemovel}" title="Telemovel" required="true" requiredMessage="O campo \"Telemovel\" é obrigatório." />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Estado</label>
                            <div class="controls">
                                <h:selectOneMenu id="estado" value="#{socio.socio.estado}" title="Estado" required="true" requiredMessage="The estado field is required." >
                                    <f:selectItems value="#{estado.estadoAssocItemsAvailableSelectOne}"/>
                                </h:selectOneMenu>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Tipo de sócio</label>
                            <div class="controls">
                                <h:selectOneMenu id="tiposocio" value="#{socio.socio.tiposocio}" title="TipoSocio" required="true" requiredMessage="The estado field is required." >
                                    <f:selectItems value="#{tiposocio.tiposocioAssocItemsAvailableSelectOne}"/>
                                </h:selectOneMenu>
                            </div>
                        </div>

                        <div class="form-actions">
                            <h:commandButton styleClass="btn btn-primary" action="#{socio.editSoss}" value="Guardar" onclick="return popupConfirm()">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                             </h:commandButton>
                            &nbsp 
                            <h:commandButton styleClass="btn" action="#{socio.detailSocioSetup}" value="Cancelar" immediate="true" onclick="return popupCancel()">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            </h:commandButton>
                        </div>
                    </fieldset>
                </h:form>
            </section>   
        </body>
    </html>
</f:view>