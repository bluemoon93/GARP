<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Ocupa</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Ocupa</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="DataFim (MM/dd/yyyy):"/>
                <h:inputText id="dataFim" value="#{ocupa.ocupa.dataFim}" title="DataFim" required="true" requiredMessage="The dataFim field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="DataInicio (MM/dd/yyyy):"/>
                <h:inputText id="dataInicio" value="#{ocupa.ocupa.dataInicio}" title="DataInicio" required="true" requiredMessage="The dataInicio field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Socio:"/>
                <h:outputText value=" #{ocupa.ocupa.socio}" title="Socio" />
                <h:outputText value="Cargo:"/>
                <h:outputText value=" #{ocupa.ocupa.cargo}" title="Cargo" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{ocupa.edit}" value="Save">
                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{ocupa.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{ocupa.listSetup}" value="Show All Ocupa Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
