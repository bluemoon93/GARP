<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Detalhebotao</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Detalhebotao</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{detalhebotao.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="IDcaixa:"/>
                <h:inputText id="IDcaixa" value="#{detalhebotao.detalhebotao.detalhebotaoPK.IDcaixa}" title="IDcaixa" required="true" requiredMessage="The IDcaixa field is required." />
                <h:outputText value="Conteudo:"/>
                <h:inputText id="conteudo" value="#{detalhebotao.detalhebotao.conteudo}" title="Conteudo" />
                <h:outputText value="Nome:"/>
                <h:inputText id="nome" value="#{detalhebotao.detalhebotao.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />
                <h:outputText value="Tipo:"/>
                <h:inputText id="tipo" value="#{detalhebotao.detalhebotao.tipo}" title="Tipo" required="true" requiredMessage="The tipo field is required." />
                <h:outputText value="Botao:"/>
                <h:selectOneMenu id="botao" value="#{detalhebotao.detalhebotao.botao}" title="Botao" required="true" requiredMessage="The botao field is required." >
                    <f:selectItems value="#{botao.botaoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{detalhebotao.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{detalhebotao.listSetup}" value="Show All Detalhebotao Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
