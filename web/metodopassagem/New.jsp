<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Metodopassagem</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Metodopassagem</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{metodopassagem.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="IDmetodo:"/>
                <h:inputText id="IDmetodo" value="#{metodopassagem.metodopassagem.metodopassagemPK.IDmetodo}" title="IDmetodo" required="true" requiredMessage="The IDmetodo field is required." />
                <h:outputText value="Anos:"/>
                <h:inputText id="anos" value="#{metodopassagem.metodopassagem.anos}" title="Anos" />
                <h:outputText value="Meses:"/>
                <h:inputText id="meses" value="#{metodopassagem.metodopassagem.meses}" title="Meses" />
                <h:outputText value="Dias:"/>
                <h:inputText id="dias" value="#{metodopassagem.metodopassagem.dias}" title="Dias" />
                <h:outputText value="Estado:"/>
                <h:selectOneMenu id="estado" value="#{metodopassagem.metodopassagem.estado}" title="Estado" required="true" requiredMessage="The estado field is required." >
                    <f:selectItems value="#{estado.estadoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="Estado1:"/>
                <h:selectOneMenu id="estado1" value="#{metodopassagem.metodopassagem.estado1}" title="Estado1" required="true" requiredMessage="The estado1 field is required." >
                    <f:selectItems value="#{estado.estadoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{metodopassagem.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{metodopassagem.listSetup}" value="Show All Metodopassagem Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
