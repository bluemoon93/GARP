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
                            <h:panelGrid columns="2" styleClass="table table-hover" >

                                <h:outputText value="Nome"/>
                                <h:outputText id="Nome" value="#{socio.socio.candidato.nome}" title="Nome" />
                                <h:outputText value="NIF"/>
                                <h:outputText id="nif" value="#{socio.socio.socioPK.nif}" title="nif" />
                                <h:outputText value="Telemóvel"/>
                                <h:outputText id="Telemovel" value="#{socio.socio.candidato.telemovel}" title="Telemovel" />
                                <h:outputText value="Data Nascimento"/>
                                <h:outputText id="datanasc" value="#{socio.socio.candidato.dataNasc}" title="Data Nascimento">
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:outputText>
                                <h:outputText value="eMail"/>
                                <h:outputText id="email" value="#{socio.socio.candidato.candidatoPK.email}" title="Email"  />
                                <h:outputText value="Número de Socio"/>
                                <h:outputText id="nmrSocio" value="#{socio.socio.nrSocio}" title="Numero Socio"  />
                                <h:outputText value="Estado"/>
                                <h:outputText id="estado" value="#{socio.socio.estado.nome}" title="Estado"  />
                                <h:outputText value="Tipo de sócio"/>
                                <h:outputText id="tiposocio" value="#{socio.socio.tiposocio.tiposocioPK.nome}" title="TipoSocio"  />
                                <h:outputText value="Data de Estado"/>
                                <h:outputText id="dataEst" value="#{socio.socio.dataModificacao}" title="Data Estado"  >
                                    <f:convertDateTime pattern="dd/MM/yyyy" />
                                </h:outputText>
                                <h:commandButton styleClass="btn btn-primary" action="#{socio.editarSoss}" value="Editar" >
                                    <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}" />
                                </h:commandButton> 
                                <h:commandButton styleClass="btn" action="#{socio.removeSoss}" onclick="return popup()" value="Apagar Conta" >
                                    <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}" />
                                </h:commandButton>
                                <h:commandButton styleClass="btn" action="#{passWorker.changePassPage}" value="Alterar Password">
                                    <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}" />
                                </h:commandButton>
                            </h:panelGrid>
                        </h:form>
                    </div>
                </div>


                <div class="span4">
                    <div class="well">
                        <a id="pq" name="organization" href="#Pquotas">Pagamentos de quotas</a>
                    </div>
                    <div class="well">
                        <a id="pe" name="organization" href="#Peventos">Participação em eventos</a>
                    </div>
                </div>
                <div class="span4">
                    <div class="well">
                        <h:form>
                            <h4>Próximos eventos</h4>
                            <h:panelGroup>
                                <h:dataTable id="ola" styleClass="table table-hover" value="#{evento.proximosEventos}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px"     >               
                                    <h:column>
                                        <f:facet name="header"><center>
                                                <h:outputText styleClass="modal-header" value="Nome"/>
                                            </f:facet>
                                            <h:commandLink styleClass="command" value="#{item.titulo}" action="#{evento.detailSetupGASSSocio}">
                                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                            </h:commandLink>
                                        </h:column>
                                    </h:dataTable>
                                </h:panelGroup>
                            </h:form>
                    </div>
                </div>
            </div>
            <br />
            <br />

            <h:form id="Pquotas">
                <div class="page-header"><h4>Pagamentos efectuados</h4></div>
                <h:dataTable styleClass="table table-hover" value="#{pagamentoquota.socPayList}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px"
                             rendered="#{not empty pagamentoquota.socPayList}">
                    <%--               <h:column>
                                       <f:facet name="header">
                                           <h:outputText escape="false" value="&nbsp;"/>
                                       </f:facet>
                                       <h:selectBooleanCheckbox id="Select2" value="#{item.checkBox}" title="Enviar Email2" />
                                   </h:column>
                    --%>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Ano"/>
                        </f:facet>
                        <h:commandLink styleClass="link" value="#{item.pagamentoquotaPK.ano}" action="#{pagamentoquota.detalhesQuotaSocio}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Data Pagamento"/>
                        </f:facet>
                        <h:outputText value="#{item.dataPag}">
                            <f:convertDateTime pattern="dd/MM/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Quantia"/>
                        </f:facet>
                        <h:outputText value="#{item.quantia}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Data Recibo"/>
                        </f:facet>
                        <h:outputText value="#{item.dataRecibo}">
                            <f:convertDateTime pattern="dd/MM/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nmr Recibo"/>
                        </f:facet>
                        <h:outputText value="#{item.nrRecibo}"/>
                    </h:column>
                </h:dataTable> 
            </h:form>

            <br />
            <br />
            <h:form id="Peventos">
                <div class="page-header"><h4>Eventos</h4> </div>
                <h:outputText value="Não se inscreveu em nenhum evento." rendered="#{empty socio.eventosParticipa}"/>
                <%--             &nbsp &nbsp &nbsp
                             <a href="#Peventos"> <h:commandButton styleClass="btn" action="#{socio.selectAllEventos}" rendered="#{not empty socio.eventosParticipa}" value="Selecionar Todos"  /> 
                             </a>
                             &nbsp  

                <h:commandButton styleClass="btn" action="#{socio.cleanAllEventos}" rendered="#{not empty socio.eventosParticipa}" value="Limpar Todos"  /> 
                &nbsp  

                <h:commandLink rendered="#{not empty socio.eventosParticipa}" styleClass="btn" value="Apagar" action="#{socio.BackEndDeleteParticipacao}" onclick="return popupEventos()">
                    <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                </h:commandLink>

                <br />
                <br />
                <br />
                --%>
                <h:dataTable styleClass="table table-hover" value="#{socio.eventosParticipa}" var="item" rendered="#{not empty socio.eventosParticipa}"
                             border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <%--
                                        <h:column>
                                            <f:facet name="header">
                                                <h:outputText escape="false" value="&nbsp;"/>
                                            </f:facet>
                                            <h:selectBooleanCheckbox id="Select3" value="#{item.checkBox}" title="Enviar Email3" />
                                        </h:column>
                    --%>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Título"/>
                        </f:facet>
                        <h:commandLink styleClass="command" value="#{item.titulo}" action="#{evento.detailSetupGASSSocio}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Data do Evento"/>
                        </f:facet>
                        <h:outputText value="#{item.dataEvento}">
                            <f:convertDateTime pattern="dd/MM/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Localização"/>
                        </f:facet>
                        <h:outputText value="#{item.localizacao}"/>
                    </h:column>
                </h:dataTable>
                <br/>
            </h:form>
        </body>
    </html>
</f:view>
