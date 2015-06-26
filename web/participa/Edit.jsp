<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Participa</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Participa</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Email:"/>
                <h:outputText value="#{participa.participa.participaPK.email}" title="Email" />
                <h:outputText value="DataInscricao (MM/dd/yyyy):"/>
                <h:inputText id="dataInscricao" value="#{participa.participa.dataInscricao}" title="DataInscricao" required="true" requiredMessage="The dataInscricao field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="ValorAPagar:"/>
                <h:inputText id="valorAPagar" value="#{participa.participa.valorAPagar}" title="ValorAPagar" required="true" requiredMessage="The valorAPagar field is required." />
                <h:outputText value="Evento:"/>
                <h:outputText value=" #{participa.participa.evento}" title="Evento" />
                <h:outputText value="Pagamentoevento:"/>
                <h:outputText value=" #{participa.participa.pagamentoevento}" title="Pagamentoevento" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{participa.edit}" value="Save">
                <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{participa.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{participa.listSetup}" value="Show All Participa Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
