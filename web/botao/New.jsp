<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Botao</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Botao</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{botao.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="IDbotao:"/>
                <h:inputText id="IDbotao" value="#{botao.botao.botaoPK.IDbotao}" title="IDbotao" required="true" requiredMessage="The IDbotao field is required." />
                <h:outputText value="Funcao:"/>
                <h:inputText id="funcao" value="#{botao.botao.funcao}" title="Funcao" required="true" requiredMessage="The funcao field is required." />
                <h:outputText value="Nome:"/>
                <h:inputText id="nome" value="#{botao.botao.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />
                <h:outputText value="Texto:"/>
                <h:inputText id="texto" value="#{botao.botao.texto}" title="Texto" />
                <h:outputText value="DetalhebotaoCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][botao.botao.detalhebotaoCollection == null ? jsfcrud_null : botao.botao.detalhebotaoCollection].jsfcrud_invoke}" title="DetalhebotaoCollection" />
                <h:outputText value="Estado:"/>
                <h:selectOneMenu id="estado" value="#{botao.botao.estado}" title="Estado" required="true" requiredMessage="The estado field is required." >
                    <f:selectItems value="#{estado.estadoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{botao.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{botao.listSetup}" value="Show All Botao Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
