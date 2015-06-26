<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Estado</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Estado</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{estado.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="IDestado:"/>
                <h:inputText id="IDestado" value="#{estado.estado.estadoPK.IDestado}" title="IDestado" required="true" requiredMessage="The IDestado field is required." />
                <h:outputText value="Nome:"/>
                <h:inputText id="nome" value="#{estado.estado.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />
                <h:outputText value="Associacao:"/>
                <h:selectOneMenu id="associacao" value="#{estado.estado.associacao}" title="Associacao" required="true" requiredMessage="The associacao field is required." >
                    <f:selectItems value="#{associacao.associacaoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="SocioCollection:"/>
                <h:selectManyListbox id="socioCollection" value="#{estado.estado.jsfcrud_transform[jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].socioCollection}" title="SocioCollection" size="6" converter="#{socio.converter}" >
                    <f:selectItems value="#{socio.socioItemsAvailableSelectMany}"/>
                </h:selectManyListbox>
                <h:outputText value="MetodopassagemCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][estado.estado.metodopassagemCollection == null ? jsfcrud_null : estado.estado.metodopassagemCollection].jsfcrud_invoke}" title="MetodopassagemCollection" />
                <h:outputText value="MetodopassagemCollection1:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][estado.estado.metodopassagemCollection1 == null ? jsfcrud_null : estado.estado.metodopassagemCollection1].jsfcrud_invoke}" title="MetodopassagemCollection1" />
                <h:outputText value="BotaoCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][estado.estado.botaoCollection == null ? jsfcrud_null : estado.estado.botaoCollection].jsfcrud_invoke}" title="BotaoCollection" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{estado.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{estado.listSetup}" value="Show All Estado Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
