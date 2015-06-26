<%@page import="entitiesGASS.jsf.AssociacaoController"%>
<%@page import="entitiesGASS.jsf.beanWorker"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Detalhes GASS</title>
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
                    var x = confirm("Tem a certeza que pretende eliminar a associação?");
                    return x;
                }
            </script>
            <script>
                function popupQuotas() {
                    var x = confirm("Tem a certeza que pretende eliminar os pagamentos selecionados?");
                    return x;
                }

                function popupEstado() {
                    var x = confirm("Tem a certeza que pretende eliminar os estados selecionados?");
                    return x;
                }

                function popupOcupa() {
                    var x = confirm("Tem a certeza que pretende eliminar os estados ocupados selecionados?");
                    return x;
                }

                function popupTipoSocio() {
                    var x = confirm("Tem a certeza que pretende eliminar os tipos de sócio selecionados?");
                    return x;
                }
                function popupEstado2() {
                    alert("Um sócio pertence a um estado. Pode ser um estado Inicial, Regular, Expulso, mas tem de pertencer a um estado. Cada estado tem as suas funções e nos detalhes de um Sócio aparecem os botões e funções do seu estado respetivo.");

                }

                function popSocios() {
                    alert("Permite dar diferentes tipos de acessos aos vários sócios no BackEnd da associação. É preciso haver alguem num cargo para gerir os membros da Associação");

                }


                function popTipoSocios() {
                    alert("Um Sócio tem um tipo e a quota associada ao Sócio pode definir o valor dos seus pagamentos. Ex: Estudante, 20€; Reformado, 35€");

                }
            </script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>

            <div class="page-header">
                <h1> &nbsp Detalhes da Associação</h1>
            </div>

            <div class="row-fluid">
                <div class="span4">
                    <div class="well">
                        <h:form >
                            <h4>Características</h4>
                            <h:panelGrid columns="2" styleClass="table table-hover" >
                                <h:outputText value="NIF"/>
                                <h:outputText value="#{associacao.associacao.nif}" title="Nif" />
                                <h:outputText value="Email"/>
                                <h:outputText value="#{associacao.associacao.email}" title="Email" />
                                <h:outputText value="Nome"/>
                                <h:outputText value="#{associacao.associacao.nome}" title="Nome" />
                                <h:commandButton styleClass="btn btn-primary" action="#{associacao.editSetupGass}" value="Editar">
                                    <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}" />
                                </h:commandButton>
                                <h:commandButton styleClass="btn" action="#{passWorker.assPassPage2}" value="Alterar Password"/>

                                <h:commandButton styleClass="btn" action="#{associacao.removeGass}" value="Apagar" onclick="return popupApagar()" >
                                    <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}" />
                                </h:commandButton>
                                <h:outputText value=""/>
                            </h:panelGrid>
                        </h:form>

                    </div>
                </div>

                <div class="span4" style="width: auto">
                    <div class="well">
                        <h:form>
                            <h4>Páginas web da associação</h4>
                            <h:panelGrid columns="2" styleClass="table table-hover" >
                                <h:outputText value="Para o login de candidatos/sócios: "/>
                                <div class="clearfix _5142">
                                    <div>
                                        <ul class="uiList _1dsl _509- _4ki _6-h _6-j _6-i">
                                            <li class="_4j">
                                                <a <% AssociacaoController assCtrl = (AssociacaoController) beanWorker.getTheBean("associacao");
                                                    String link = "http://192.168.160.26:8080/GASSv12/faces/Login";
                                                    link += assCtrl.sigLink() + ".jsp";%> data-onclick="[[&quot;ComposerXAttachmentBootstrap&quot;,&quot;bootstrap&quot;]]" class="_9lb" aria-pressed="false" href="<%=link%>" >
                                                    <%=link%>
                                                    <%--http://localhost:8080/GASSv10/faces/Login.jsp--%>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <%--<h:outputText value="http://localhost:8080/GASSv10/faces/LoginSPR.jsp"/>--%>
                                <h:outputText value="Para a criação de candidatos: "/>
                                <div class="clearfix _5142">
                                    <div>
                                        <ul class="uiList _1dsl _509- _4ki _6-h _6-j _6-i">
                                            <li class="_4j">
                                                <a <% String link1 = "http://192.168.160.26:8080/GASSv12/faces/CandyCriar";
                                                    link1 += assCtrl.sigLink() + ".jsp";%> data-onclick="[[&quot;ComposerXAttachmentBootstrap&quot;,&quot;bootstrap&quot;]]" class="_9lb" aria-pressed="false" href="<%=link1%>" >
                                                    <%=link1%>
                                                    <%--http://localhost:8080/GASSv10/faces/Login.jsp--%>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <%--<h:outputText value="http://localhost:8080/GASSv10/faces/CandyCriarSPR.jsp"/>--%>
                                <h:outputText value="Para a criação de sócios: "/>
                                <div class="clearfix _5142">
                                    <div>
                                        <ul class="uiList _1dsl _509- _4ki _6-h _6-j _6-i">
                                            <li class="_4j">
                                                    <a <% String link2 = "http://192.168.160.26:8080/GASSv12/faces/SocioCriar";
                                                        link2 += assCtrl.sigLink() + ".jsp";%> data-onclick="[[&quot;ComposerXAttachmentBootstrap&quot;,&quot;bootstrap&quot;]]" class="_9lb" aria-pressed="false" href="<%=link2%>" >
                                                    <%=link2%>
                                                    <%--http://localhost:8080/GASSv10/faces/Login.jsp--%>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <%--<h:outputText value="http://localhost:8080/GASSv10/faces/SocioCriarSPR.jsp"/>--%>
                                <h:outputText value="Para a criação de candidatos e sócios: "/>
                                <div class="clearfix _5142">
                                    <div>
                                        <ul class="uiList _1dsl _509- _4ki _6-h _6-j _6-i">
                                            <li class="_4j">
                                                <a <% String link3 = "http://192.168.160.26:8080/GASSv12/faces/CriarCanSoc";
                                                    link3 += assCtrl.sigLink() + ".jsp";%> data-onclick="[[&quot;ComposerXAttachmentBootstrap&quot;,&quot;bootstrap&quot;]]" class="_9lb" aria-pressed="false" href="<%=link3%>" >
                                                    <%=link3%>
                                                    <%--http://localhost:8080/GASSv10/faces/Login.jsp--%>
                                                </a>

                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <h:outputText value="Para o login dos Gestores: "/>
                                <div class="clearfix _5142">
                                    <div>
                                        <ul class="uiList _1dsl _509- _4ki _6-h _6-j _6-i">
                                            <li class="_4j">

                                                <a <% String link4 = "http://192.168.160.26:8080/GASSv12/faces/BackEndLogin";
                                                    link4 += assCtrl.sigLink() + ".jsp";%> data-onclick="[[&quot;ComposerXAttachmentBootstrap&quot;,&quot;bootstrap&quot;]]" class="_9lb" aria-pressed="false" href="<%=link4%>" >
                                                    <%=link4%>
                                                    <%--http://localhost:8080/GASSv10/faces/Login.jsp--%>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <%--<h:outputText value="http://localhost:8080/GASSv10/faces/BackEndLoginSPR.jsp"/>--%>
                            </h:panelGrid>
                        </h:form>

                    </div>
                </div>
            </div>


            <div class="span4" id="A" >
                <div class="well">
                    <h:form>
                        <h:commandLink rendered="#{'A' != associacao.where}" styleClass="bold" action="#{associacao.gotoEstados}" value="Estados" />
                        <br /> <br/> <br/>
                        <h:outputText styleClass="lol" value="Uma associação precisa de Estados para os seus Sócios" rendered="#{empty associacao.associacao.estadoCollection }" style="margin-right:15px" />
                        &nbsp;
                        <h:commandButton image="pergunta.png" value="ola" onclick="return popupEstado2()" rendered="#{empty associacao.associacao.estadoCollection}"></h:commandButton>

                    </h:form>
                    <h:form rendered="#{'A' == associacao.where}">
                        <h4><h:outputText styleClass="lol" value="Lista de Estados" rendered="#{not empty associacao.associacao.estadoCollection}"/></h4>

                        
                        <br/>
                        <br/>

                        <h:panelGroup>
                            <h:dataTable value="#{associacao.associacao.estadoCollection}" var="item"
                                         border="0" cellpadding="2" cellspacing="0" rowClasses="table table-hover" rules="all" style="border:solid 1px"
                                         rendered="#{not empty associacao.associacao.estadoCollection}">

                                <h:column>
                                    <f:facet name="header">
                                        <h:commandLink rendered="#{not empty associacao.associacao.estadoCollection}" styleClass="btn btn-small" value="Apagar" action="#{estado.removeTodos}" onclick="return popupEstado()" >
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="associacao" />
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                        </h:commandLink>
                                    </f:facet>
                                    <h:selectBooleanCheckbox id="Select2" value="#{item.checkBox}" title="Enviar Email2" />
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="ID"/>
                                    </f:facet>
                                    <h:outputText value="#{item.estadoPK.IDestado}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Nome"/>
                                    </f:facet>

                                    <h:commandLink styleClass="link" value="#{item.nome}" action="#{estado.detailSetupGass}">
                                        <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                        <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                                        <f:param name="jsfcrud.relatedController" value="associacao" />
                                        <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                                    </h:commandLink>
                                </h:column>
                            </h:dataTable>
                        </h:panelGroup>
                        <br/>
                        <h:form >
                            <h4>Novo estado</h4>
                            <h:inputHidden id="validateCreateField" validator="#{estado.validateCreate}" value="value"/>
                            <h:panelGrid columns="2" styleClass="table table-hover" >
                                <h:outputText value="Nome:"/>
                                <h:inputText styleClass="form-signin input-block-level" id="nome" value="#{estado.estado.nome}" title="Nome" />
                            </h:panelGrid>
                            <h:commandButton styleClass="btn btn-primary" action="#{estado.createGass}" value="Adicionar Estado">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}" />
                            </h:commandButton>
                            <br />
                        </h:form>
                    </h:form>
                </div>
            </div>

            <div class="span4" id="C">
                <div class="well">
                    <h:form>
                        <h:commandLink rendered="#{'C' != associacao.where}" styleClass="bold" action="#{associacao.gotoMetodos()}" value="Tipos de Socio" />
                        <br /> <br/> <br/>
                        <h:outputText styleClass="lol" value="Uma associação precisa de Tipos para os seus Sócios" rendered="#{empty tiposocio.tiposocioItems }" style="margin-right:15px" />
                        &nbsp;    
                        <h:commandButton image="pergunta.png" value="ola" onclick="popTipoSocios()" rendered="#{empty tiposocio.tiposocioItems}"></h:commandButton>

                    </h:form>
                    <h:form rendered="#{'C' == associacao.where}" >
                        
                        <h4><h:outputText styleClass="lol" value="Lista de Tipos de Sócio" rendered="#{not empty tiposocio.tiposocioItems}"/></h4>
                        <br>
                        <br>
                        <h:panelGroup>
                            <h:dataTable value="#{tiposocio.tiposocioItems}" var="item" border="0" cellpadding="2" cellspacing="0"
                                         rowClasses="table table-hover" rules="all" style="border:solid 1px"
                                         rendered="#{not empty tiposocio.tiposocioItems}">

                                <h:column>
                                    <f:facet name="header">
                                        <h:commandLink rendered="#{not empty tiposocio.tiposocioItems}" styleClass="btn" value="Apagar" action="#{tiposocio.removeTodos}" onclick="return popupTipoSocio()" />
                        
                                    </f:facet>
                                    <h:selectBooleanCheckbox id="Select2" value="#{item.checkBox}" title="Enviar Email2" />
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Nome"/>
                                    </f:facet>

                                    <h:commandLink styleClass="link" value="#{item.tiposocioPK.nome}" action="#{tiposocio.editSetupGass}">
                                        <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tiposocio.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Quantia"/>
                                    </f:facet>
                                    <h:outputText value="#{item.quantia}"/>
                                </h:column>

                            </h:dataTable>
                        </h:panelGroup>

                        <br/>
                        <h:form>
                            <h:inputHidden id="validateCreateField2" validator="#{tiposocio.validateCreate}" value="value"/>

                            <h:panelGrid columns="2" styleClass="table table-hover" >
                                <h:outputText value="Nome:"/>
                                <h:inputText styleClass="form-signin input-block-level" id="nome" value="#{tiposocio.tiposocio.tiposocioPK.nome}" title="Nome" />
                                <h:outputText value="Quantia:"/>
                                <h:inputText styleClass="form-signin input-block-level" id="quantia" value="#{tiposocio.tiposocio.quantia}" title="Quantia" />
                            </h:panelGrid>

                            <h:commandButton styleClass="btn btn-primary" action="#{tiposocio.createGass}" value="Novo Tipo Socio" />

                            <br />
                            <br />
                        </h:form>
                    </h:form>
                </div>
            </div>

            <div class="span4" id="B">
                <div class="well">
                    <h:form>
                        <h:commandLink rendered="#{'B' != associacao.where}" styleClass="bold" action="#{associacao.gotoBotoes}" value="Sócios e Cargos" />
                        <br /> 
                        <h:outputText styleClass="lol" value="São precisos cargos para gerir os membros da Associacao" rendered="#{empty ocupa.ocupaItems }" style="margin-right:15px" />
                        &nbsp;    
                        <h:commandButton image="pergunta.png" value="ola" onclick="return popSocios()" rendered="#{empty ocupa.ocupaItems}"></h:commandButton>
                    </h:form>
                    <h:form rendered="#{'B' == associacao.where}" >
                        <h4>Lista de Sócios com Cargos</h4>
                        
                        <br>
                        <br>
                        <h:panelGroup>
                            <h:dataTable rendered="#{not empty ocupa.ocupaItems}" value="#{ocupa.ocupaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="table table-hover" rules="all" style="border:solid 1px">
                                <%--<h:column>
                                <f:facet name="header">
                                <h:outputText value="De:"/>
                                </f:facet>
                                <h:outputText value="#{item.dataInicio}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                                </h:column>
                                <h:column>
                                <f:facet name="header">
                                <h:outputText value="AtÃ©:"/>
                                </f:facet>
                                <h:outputText value="#{item.dataFim}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                                </h:column>--%>
                                <h:column>
                                    <f:facet name="header">
                                        <h:commandLink rendered="#{not empty ocupa.ocupaItems}" styleClass="btn btn-small" value="Apagar" action="#{ocupa.removeTodos}" onclick="return popupOcupa()" >
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                                    </f:facet>
                                    <h:selectBooleanCheckbox id="Select2" value="#{item.checkBox}" title="Enviar Email2" />
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Socio"/>
                                    </f:facet>
                                    <h:commandLink styleClass="link" value="#{item.socio.candidato.nome}" action="#{ocupa.editSetupGass}">
                                        <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                                    </h:commandLink>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Cargo"/>
                                    </f:facet>
                                    <h:outputText value="#{item.cargo.cargoPK.nome} de #{item.cargo.cargoPK.grupo}"/>
                                </h:column>
                            </h:dataTable>
                        </h:panelGroup>
                        <br/>

                        <h:commandButton styleClass="btn btn-primary" action="#{ocupa.criarCandySoss}" value="Criar Sócios e Cargos "/>
                        <br />
                        <br />
                        <h:commandButton styleClass="btn btn-primary" action="#{ocupa.createSetupGass}" value="Definir Cargos para Sócios existentes "/>

                    </h:form>
                </div>
            </div>
        </div>
    </body>
</html>
</f:view>