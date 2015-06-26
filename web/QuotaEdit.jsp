<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editar Pagamento Quota</title>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap-datetimepicker.min.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.min.js"></script>
            <link href="/GASSv12/faces/bootstrap-datetimepicker.min.css" rel="stylesheet">
            <%--
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
             <link href="/GASSv7FDS/faces/bootstrap.js" rel="stylesheet">
     <link href="/GASSv7FDS/faces/bootstrap.min.js" rel="stylesheet">
            
            --%>
            <!-- Le styles -->
            <link href="/GASSv120/faces/bootstrap.css" rel="stylesheet">
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
                function popupGuardar() {
                    var x = confirm("Tem a certeza que pretende guardar as alterações efectuadas?");
                    return x;
                }
                function popup() {
                    var x = confirm("Tem a certeza que não pretende guardar as alterações efectuadas?");
                    return x;
                }
                function popupApagar() {
                    var x = confirm("Tem a certeza que pretende eliminar definitivamente este pagamento?");
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
            
            <h1>Editar Pagamento de Quota</h1>
            <section id="forms">
                <h:form styleClass="form-horizontal well">

                    <fieldset>
                        
                        <div class="control-group">
                            <label class="control-label" for="input01">Ano da quota</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="ano" value="#{pagamentoquota.pagamentoquota.pagamentoquotaPK.ano}" title="Ano" required="true" requiredMessage="The ano field is required." />     
                            </div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label" for="input01">Data do Pagamento (dd/MM/yyyy)</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="dataPag" value="#{pagamentoquota.pagamentoquota.dataPag}" title="Data Pagamento" required="true" requiredMessage="The dataPag field is required." >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:inputText>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Quantia</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="quantia" value="#{pagamentoquota.pagamentoquota.quantia}" title="Quantia" required="true" requiredMessage="The quantia field is required." />
                            </div>
                        </div>              
                        <div class="control-group">
                            <label class="control-label" for="input01">Data do Recibo (dd/MM/yyyy)</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="dataRecibo" value="#{pagamentoquota.pagamentoquota.dataRecibo}" title="Data Recibo" >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:inputText>
                            </div>
                        </div>    
                        <div class="control-group">
                            <label class="control-label" for="input01">Número do Recibo</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="nrRecibo" value="#{pagamentoquota.pagamentoquota.nrRecibo}" title="Nmr Recibo" required ="true" requiredMessage = "Nmr Recibo field is required." />
                            </div>
                        </div>   
                    </fieldset>
                    <h4>Dados do sócio</h4>
                    <h:panelGrid columns="8" styleClass="table table-hover">

                        <h:outputText value="Sócio"/>
                        <h:outputText value="#{pagamentoquota.pagamentoquota.socio.candidato.nome}"/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value="Número de Sócio"/>
                        <h:outputText value="#{pagamentoquota.pagamentoquota.socio.nrSocio}"/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value="Email"/>
                        <h:outputText value="#{pagamentoquota.pagamentoquota.socio.candidato.candidatoPK.email}"/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                    </h:panelGrid>
                    <div class="form-actions">
                        <h:commandButton styleClass="btn btn-primary" action="#{pagamentoquota.editQuota}" value="Guardar" onclick="return popupGuardar()">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandButton>
                        &nbsp
                        <h:commandButton styleClass="btn" action="#{pagamentoquota.listQuota}" value="Cancelar" immediate="true" onclick="return popup()">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandButton>
                        &nbsp
                        <h:commandLink styleClass="btn" value="Apagar" action="#{pagamentoquota.removeQuota}" onclick="return popupApagar()">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </div>
                </h:form>
            </section>
        </body>
    </html>
</f:view>