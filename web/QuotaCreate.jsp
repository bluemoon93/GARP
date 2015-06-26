<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Novo Pagamento Quota</title>
            <link rel="stylesheet" type="text/css" href="/GASSv7FDS/BackEnd.css" />
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
            
        <h1>Novo Pagamento Quota</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:inputHidden id="validateCreateField" validator="#{pagamentoquota.validateCreate}" value="value"/>
            <h:panelGrid columns="2" styleClass="panel">
                <h:outputText value="Nmr Transferencia:"/>
                <h:inputText id="nrTransferencia" value="#{pagamentoquota.pagamentoquota.pagamentoquotaPK.nrTransferencia}" title="Nmr Transferencia" required="true" requiredMessage="The nrTransferencia field is required." />
                <h:outputText value="Data Pagamento (MM/dd/yyyy):"/>
                <h:inputText id="dataPag" value="#{pagamentoquota.pagamentoquota.dataPag}" title="Data Pagamento" required="true" requiredMessage="The dataPag field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Quantia:"/>
                <h:inputText id="quantia" value="#{pagamentoquota.pagamentoquota.quantia}" title="Quantia" required="true" requiredMessage="The quantia field is required." />
                <h:outputText value="Data Recibo (MM/dd/yyyy):"/>
                <h:inputText id="dataRecibo" value="#{pagamentoquota.pagamentoquota.dataRecibo}" title="Data Recibo" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Nmr Recibo:"/>
                <h:inputText id="nrRecibo" value="#{pagamentoquota.pagamentoquota.nrRecibo}" title="Nmr Recibo" />
              <%--  <h:outputText value="SocioCollection:"/>
                <h:selectManyListbox id="socioCollection" value="#{pagamentoquota.pagamentoquota.jsfcrud_transform[jsfcrud_class['entitiesGARP.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['entitiesGARP.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].socioCollection}" title="SocioCollection" size="6" converter="#{socio.converter}" >
                    <f:selectItems value="#{socio.socioItemsAvailableSelectMany}"/>
                </h:selectManyListbox>--%>
                <h:outputText value="Socio:"/>
                <h:selectOneMenu styleClass="select" onmouseover="this.style.backgroundColor='burlywood'" onmouseout="this.style.backgroundColor='white'" id="socio" value="#{pagamentoquota.pagamentoquota.socio}" title="Socio" required="true" requiredMessage="The socio field is required." >
                    <f:selectItems value="#{socio.socioItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandButton styleClass="input" onmouseover="this.style.backgroundColor='mediumaquamarine'" onmouseout="this.style.backgroundColor='burlywood'" action="#{pagamentoquota.criarQuota}" value="Criar Pagamento Quota"/>
            <br />
            <br />
            <h:commandButton styleClass="input" onmouseover="this.style.backgroundColor='mediumaquamarine'" onmouseout="this.style.backgroundColor='burlywood'" action="#{pagamentoquota.listQuota}" value="Voltar a mostrar todos os pagamentos" immediate="true"/>
            <br />
            <br />

        </h:form>
        </body>
    </html>
</f:view>
