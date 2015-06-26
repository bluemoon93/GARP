<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Detalhes do Participante</title>
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
                    var x = confirm("Tem a certeza que pretende guardar as alterações efectuadas?");
                    return x;
                }

                function popupApagar() {
                    var x = confirm("Tem a certeza que pretende eliminar definitavamente este participante do evento?");
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
                <h1>Editar dados do participante</h1>
            </div>
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
            <section id="forms">
                <h:form styleClass="form-horizontal well">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label" for="input01">Data de Inscrição (dd/MM/yyyy)</label>
                            <div class="controls">             
                                <h:inputText styleClass="form-signin input-block-level" id="dataInscricao" value="#{participa.participa.dataInscricao}" title="DataInscricao" required="true" requiredMessage="O campo data de inscrição é obrigatório." >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:inputText>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Quantia</label>
                            <div class="controls">   
                                <h:inputText styleClass="form-signin input-block-level" id="valorAPagar" value="#{participa.participa.valorAPagar}" title="ValorAPagar" required="true" requiredMessage="O campo quantia é obrigatório." />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Número de Recibo</label>
                            <div class="controls">  
                                <h:outputText value="#{participa.participa.pagamentoevento.nrRecibo}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Data de Recibo</label>
                            <div class="controls">  
                                <h:outputText value="#{participa.participa.pagamentoevento.dataRecibo}">
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:outputText>
                            </div>
                        </div>

                    </fieldset>
                    <h4>Outros dados da inscrição</h4>
                    <h:panelGrid columns="8" styleClass="table table-hover">
                        <h:outputText value="Evento"/>
                        <h:outputText value=" #{participa.participa.evento.titulo}" title="Evento" />
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value="Email"/>
                        <h:outputText value="#{participa.participa.participaPK.email}"/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                    </h:panelGrid>
                    <div class="form-actions">

                        <h:commandButton styleClass="btn btn-primary" action="#{participa.listSetupGASS}" value="Guardar" onclick="return popup()">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                        </h:commandButton>
                        &nbsp
                        <h:commandButton styleClass="btn" action="#{participa.remove}" value="Apagar participante" onclick="return popupApagar()">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                        </h:commandButton>

                        <br />
                        <br />
                        <h:commandButton styleClass="input" onmouseover="this.style.backgroundColor='mediumaquamarine'" onmouseout="this.style.backgroundColor='burlywood'" action="#{evento.listSetupGASS}" value="Show All Evento Items" immediate="true"/>
                        <br />
                        <h:commandButton styleClass="input" onmouseover="this.style.backgroundColor='mediumaquamarine'" onmouseout="this.style.backgroundColor='burlywood'" value="Voltar à Homepage" action="#{participa.listSetupGASS}" immediate="true"/>
                    </div>
                </h:form>
            </section>
        </body>
    </html>
</f:view>
