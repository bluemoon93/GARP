<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Associacao</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Associacao</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{associacao.associacao.nif}" title="Nif" />
                <h:outputText value="Email:"/>
                <h:inputText id="email" value="#{associacao.associacao.email}" title="Email" required="true" requiredMessage="The email field is required." />
                <h:outputText value="Nome:"/>
                <h:inputText id="nome" value="#{associacao.associacao.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />
                <h:outputText value="Passwd:"/>
                <h:inputText id="passwd" value="#{associacao.associacao.passwd}" title="Passwd" required="true" requiredMessage="The passwd field is required." />
                <h:outputText value="TiposocioCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][associacao.associacao.tiposocioCollection == null ? jsfcrud_null : associacao.associacao.tiposocioCollection].jsfcrud_invoke}" title="TiposocioCollection" />
                <h:outputText value="CandidatoCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][associacao.associacao.candidatoCollection == null ? jsfcrud_null : associacao.associacao.candidatoCollection].jsfcrud_invoke}" title="CandidatoCollection" />
                <h:outputText value="EventoCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][associacao.associacao.eventoCollection == null ? jsfcrud_null : associacao.associacao.eventoCollection].jsfcrud_invoke}" title="EventoCollection" />
                <h:outputText value="EstadoCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][associacao.associacao.estadoCollection == null ? jsfcrud_null : associacao.associacao.estadoCollection].jsfcrud_invoke}" title="EstadoCollection" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{associacao.edit}" value="Save">
                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{associacao.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{associacao.listSetup}" value="Show All Associacao Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
