<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Metodopassagem</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Metodopassagem</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IDmetodo:"/>
                <h:outputText value="#{metodopassagem.metodopassagem.metodopassagemPK.IDmetodo}" title="IDmetodo" />
                <h:outputText value="Anos:"/>
                <h:inputText id="anos" value="#{metodopassagem.metodopassagem.anos}" title="Anos" />
                <h:outputText value="Meses:"/>
                <h:inputText id="meses" value="#{metodopassagem.metodopassagem.meses}" title="Meses" />
                <h:outputText value="Dias:"/>
                <h:inputText id="dias" value="#{metodopassagem.metodopassagem.dias}" title="Dias" />
                <h:outputText value="Estado:"/>
                <h:outputText value=" #{metodopassagem.metodopassagem.estado}" title="Estado" />
                <h:outputText value="Estado1:"/>
                <h:outputText value=" #{metodopassagem.metodopassagem.estado1}" title="Estado1" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{metodopassagem.edit}" value="Save">
                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{metodopassagem.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{metodopassagem.listSetup}" value="Show All Metodopassagem Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
