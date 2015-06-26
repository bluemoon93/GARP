<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Pagamentoevento</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Pagamentoevento</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{pagamentoevento.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="DataPag (MM/dd/yyyy):"/>
                <h:inputText id="dataPag" value="#{pagamentoevento.pagamentoevento.dataPag}" title="DataPag" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Quantia:"/>
                <h:inputText id="quantia" value="#{pagamentoevento.pagamentoevento.quantia}" title="Quantia" required="true" requiredMessage="The quantia field is required." />
                <h:outputText value="DataRecibo (MM/dd/yyyy):"/>
                <h:inputText id="dataRecibo" value="#{pagamentoevento.pagamentoevento.dataRecibo}" title="DataRecibo" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="NrRecibo:"/>
                <h:inputText id="nrRecibo" value="#{pagamentoevento.pagamentoevento.nrRecibo}" title="NrRecibo" />
                <h:outputText value="Participa:"/>
                <h:selectOneMenu id="participa" value="#{pagamentoevento.pagamentoevento.participa}" title="Participa" required="true" requiredMessage="The participa field is required." >
                    <f:selectItems value="#{participa.participaItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{pagamentoevento.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{pagamentoevento.listSetup}" value="Show All Pagamentoevento Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
