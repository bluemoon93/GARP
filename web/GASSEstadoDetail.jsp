<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Detalhes Estado</title>
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
                function popupApagar() {
                    var x = confirm("Tem a certeza que pretende eliminar o sócio?");
                    return x;
                }
                function popupQuotas() {
                    var x = confirm("Tem a certeza que pretende eliminar os pagamentos selecionados?");
                    return x;
                }
                function popupBotaoEliminar() {
                    var x = confirm("Tem a certeza que pretende eliminar os botões selecionados?");
                    return x;
                }
                function popupMetodoEliminar() {
                    var x = confirm("Tem a certeza que pretende eliminar este método de passagem?");
                    return x;
                }

                function popMPS() {
                    alert("O temporizador permite que um determinado sócio passe automaticamente para determinado estado ao fim de algum tempo, se as suas quotas não estiverem pagas até ao ano atual ou superior");
                   
                }
                function popBotao() {
                    alert("Os botões permitem definir acções especificas para Sócios deste estado. Os botões aparecem nos detalhes do Sócio e permitem adicionar pagamentos, mudar o Sócio de estado, enviar-lhe eMails, etc.");
                    
                }
            </script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>


            <div class="page-header">
                <h1> &nbsp Detalhes do Estado <h:outputText value="#{estado.estado.nome}"/> </h1>
            </div>




            <div class="row-fluid">
                <div class="span4">
                    <div class="well">
                        <h:form >
                            <h:panelGrid columns="2" styleClass="table table-hover" >    
                                <h:outputText value="ID"/>
                                <h:outputText value="#{estado.estado.estadoPK.IDestado}" title="IDestado" />
                                <h:outputText value="Nome"/>
                                <h:outputText value="#{estado.estado.nome}" title="Nome" />

                            </h:panelGrid > 

                            <br/>

                            <h:commandButton styleClass="btn btn-primary" action="#{estado.editGassSetup}" value="Editar">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}" />
                            </h:commandButton>
                            &nbsp;
                            <h:commandButton styleClass="btn btn-primary" action="#{estado.setInicial}" value="Definir como Estado Inicial" rendered="#{estado.estado.inicial == false}" >
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}" />
                            </h:commandButton>
                            <br/>
                            <br/>
                            <h:commandButton styleClass="btn"  action="#{estado.removeGass(estado.estado)}" value="Apagar" immediate="true" >
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                            </h:commandButton>

                        </h:form>
                    </div>
                    <h:form styleClass="space">
                        <h:commandButton styleClass="btn" action="#{associacao.detailSetupGass}" value="Voltar" immediate="true">
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                        </h:commandButton>
                    </h:form>
                </div>



                <div class="span4">
                    <div class="well">
                        <h:form>
                            <h4>Botões</h4>
                            <h:outputText styleClass="lol" value="Um Estado precisa de botões para ser útil" rendered="#{empty estado.estado.botaoCollection}" />
                            &nbsp;
                            <h:commandButton image="pergunta.png" value="ola" onclick="return popBotao()" rendered="#{empty estado.estado.botaoCollection}"></h:commandButton>

                            <br/>
                            <h:panelGroup>
                                <h:dataTable value="#{estado.estado.botaoCollection}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="table table-hover" rules="all" style="border:solid 1px" 
                                             rendered="#{not empty estado.estado.botaoCollection}">
                                    <h:column>
                                        <f:facet name="header">
                                            <h:commandLink styleClass="btn" value="Apagar" action="#{botao.removeTodos}" rendered="#{not empty estado.estado.botaoCollection}" onclick="return popupBotaoEliminar()" >
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                                        </f:facet>
                                        <h:selectBooleanCheckbox id="Select2" value="#{item.checkBox}" title="Enviar Email2" />
                                    </h:column>

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Nome"/>
                                        </f:facet>
                                        <h:commandLink styleClass="link" value="#{item.nome}" action="#{botao.detailGassSetup}">
                                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                                            <f:param name="jsfcrud.relatedController" value="estado" />
                                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                                        </h:commandLink>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Funcao"/>
                                        </f:facet>
                                        <h:outputText value="#{item.funcaoParsed}"/>
                                    </h:column>

                                </h:dataTable>
                            </h:panelGroup>
                            <br/>
                            <br/>
                            <h:commandButton styleClass="btn btn-primary" action="#{botao.createGassSetup}" value="Novo Botao"/>
                        </h:form>
                    </div>
                </div>


                <div class="span4">
                    <div class="well">
                        <h:form>
                            <h4>De onde vem</h4>
                            <h:outputText styleClass="lol" value="Não existem estados a vir para o estado actual." rendered="#{empty estado.estado.metodopassagemCollection}" />
                               <br />
                            <br />
                            <h:panelGroup>
                                <h:dataTable value="#{estado.estado.metodopassagemCollection}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="table table-hover" rules="all" style="border:solid 1px" 
                                             rendered="#{not empty estado.estado.metodopassagemCollection}">
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Tempo"/>
                                        </f:facet>
                                        <h:outputText value="#{item.dias} dias, #{item.meses} meses, #{item.anos} anos"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Estado"/>
                                        </f:facet>
                                        <h:outputText value="#{item.estado1.nome}"/>
                                    </h:column>
                                </h:dataTable>
                            </h:panelGroup>
                        </h:form>

                        <h:form>
                            <h4>Para onde vai</h4>
                            <h:outputText styleClass="lol" value="Não existe nenhum temporizador que passe do estado actual para outro." rendered="#{empty estado.estado.metodopassagemCollection1}" />
                            &nbsp;
                            <h:commandButton image="pergunta.png" value="ola" onclick="return popMPS()" rendered="#{empty estado.estado.metodopassagemCollection1}"/>
                            <br/>
                            <br/>
                            
                            <h:panelGroup>
                                <h:dataTable value="#{estado.estado.metodopassagemCollection1}" var="item" 
                                             border="0" cellpadding="2" cellspacing="0" rowClasses="table table-hover" rules="all" style="border:solid 1px" 
                                             rendered="#{not empty estado.estado.metodopassagemCollection1}">

                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText escape="false" value="&nbsp;"/>
                                        </f:facet>
                                        <h:commandLink styleClass="btn" value="Apagar" action="#{metodopassagem.removeGass(item)}" onclick="return popupMetodoEliminar()" />
                                    </h:column>



                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Tempo"/>
                                        </f:facet>
                                        <h:outputText value="#{item.dias} dias, #{item.meses} meses, #{item.anos} anos"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Estado"/>
                                        </f:facet>
                                        <h:commandLink styleClass="link" value="#{item.estado.nome}" action="#{metodopassagem.detailSetupGass}">
                                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                                            <f:param name="jsfcrud.relatedController" value="estado" />
                                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                                        </h:commandLink>
                                    </h:column>
                                </h:dataTable>
                            </h:panelGroup>

                            <h:form rendered="#{empty estado.estado.metodopassagemCollection1}">

                                <h4>Novo Temporizador</h4>
                                <h:inputHidden id="validateCreateField" validator="#{metodopassagem.validateCreate}" value="value"/>
                                <h:panelGrid columns="2" styleClass="panel">

                                    <h:outputText value="Anos"/>
                                    <h:inputText styleClass="form-signin input-block-level" id="anos" value="#{metodopassagem.metodopassagem.anos}" title="Anos" />
                                    <h:outputText value="Meses"/>
                                    <h:inputText styleClass="form-signin input-block-level" id="meses" value="#{metodopassagem.metodopassagem.meses}" title="Meses" />
                                    <h:outputText value="Dias"/>
                                    <h:inputText styleClass="form-signin input-block-level" id="dias" value="#{metodopassagem.metodopassagem.dias}" title="Dias" />

                                    <h:outputText value="Próximo Estado"/>
                                    <h:selectOneMenu  id="estado1" value="#{metodopassagem.metodopassagem.estado}" title="Estado" required="true" requiredMessage="The estado1 field is required." >
                                        <f:selectItems value="#{estado.estadoItemsAvailableSelectOneGass}"/>
                                    </h:selectOneMenu>

                                </h:panelGrid>

                                <h:commandButton styleClass="btn btn-primary" action="#{metodopassagem.createGass}" value="Criar" rendered="#{empty estado.estado.metodopassagemCollection1}" />
                            </h:form>
                        </h:form>
                    </div>
                </div>



            </div>
        </body>
    </html>
</f:view>
