<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Cargo</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Cargo</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nome:"/>
                <h:outputText value="#{cargo.cargo.cargoPK.nome}" title="Nome" />
                <h:outputText value="Grupo:"/>
                <h:outputText value="#{cargo.cargo.cargoPK.grupo}" title="Grupo" />
                <h:outputText value="Acesso:"/>
                <h:inputText id="acesso" value="#{cargo.cargo.acesso}" title="Acesso" required="true" requiredMessage="The acesso field is required." />
                <h:outputText value="EventoCollection:"/>
                <h:selectManyListbox id="eventoCollection" value="#{cargo.cargo.jsfcrud_transform[jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].eventoCollection}" title="EventoCollection" size="6" converter="#{evento.converter}" >
                    <f:selectItems value="#{evento.eventoItemsAvailableSelectMany}"/>
                </h:selectManyListbox>
                <h:outputText value="OcupaCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][cargo.cargo.ocupaCollection == null ? jsfcrud_null : cargo.cargo.ocupaCollection].jsfcrud_invoke}" title="OcupaCollection" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{cargo.edit}" value="Save">
                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{cargo.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{cargo.listSetup}" value="Show All Cargo Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
