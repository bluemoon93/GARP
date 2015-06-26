<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Evento</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Evento</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IDEvento:"/>
                <h:outputText value="#{evento.evento.eventoPK.IDEvento}" title="IDEvento" />
                <h:outputText value="DataEvento (MM/dd/yyyy):"/>
                <h:inputText id="dataEvento" value="#{evento.evento.dataEvento}" title="DataEvento" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Localizacao:"/>
                <h:inputText id="localizacao" value="#{evento.evento.localizacao}" title="Localizacao" />
                <h:outputText value="Conteudo:"/>
                <h:inputText id="conteudo" value="#{evento.evento.conteudo}" title="Conteudo" />
                <h:outputText value="DataCriacao (MM/dd/yyyy):"/>
                <h:inputText id="dataCriacao" value="#{evento.evento.dataCriacao}" title="DataCriacao" required="true" requiredMessage="The dataCriacao field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Titulo:"/>
                <h:inputText id="titulo" value="#{evento.evento.titulo}" title="Titulo" required="true" requiredMessage="The titulo field is required." />
                <h:outputText value="ParticipaCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][evento.evento.participaCollection == null ? jsfcrud_null : evento.evento.participaCollection].jsfcrud_invoke}" title="ParticipaCollection" />
                <h:outputText value="Cargo:"/>
                <h:selectOneMenu id="cargo" value="#{evento.evento.cargo}" title="Cargo" required="true" requiredMessage="The cargo field is required." >
                    <f:selectItems value="#{cargo.cargoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="Associacao:"/>
                <h:outputText value=" #{evento.evento.associacao}" title="Associacao" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{evento.edit}" value="Save">
                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{evento.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{evento.listSetup}" value="Show All Evento Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
