<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Pagamentoquota</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Pagamentoquota</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Ano:"/>
                <h:outputText value="#{pagamentoquota.pagamentoquota.pagamentoquotaPK.ano}" title="Ano" />
                <h:outputText value="DataPag (MM/dd/yyyy):"/>
                <h:inputText id="dataPag" value="#{pagamentoquota.pagamentoquota.dataPag}" title="DataPag" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Quantia:"/>
                <h:inputText id="quantia" value="#{pagamentoquota.pagamentoquota.quantia}" title="Quantia" required="true" requiredMessage="The quantia field is required." />
                <h:outputText value="DataRecibo (MM/dd/yyyy):"/>
                <h:inputText id="dataRecibo" value="#{pagamentoquota.pagamentoquota.dataRecibo}" title="DataRecibo" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="NrRecibo:"/>
                <h:inputText id="nrRecibo" value="#{pagamentoquota.pagamentoquota.nrRecibo}" title="NrRecibo" />
                <h:outputText value="Socio:"/>
                <h:outputText value=" #{pagamentoquota.pagamentoquota.socio}" title="Socio" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{pagamentoquota.edit}" value="Save">
                <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{pagamentoquota.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{pagamentoquota.listSetup}" value="Show All Pagamentoquota Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
