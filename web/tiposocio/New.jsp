<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Tiposocio</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Tiposocio</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{tiposocio.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="Nome:"/>
                <h:inputText id="nome" value="#{tiposocio.tiposocio.tiposocioPK.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />
                <h:outputText value="Quantia:"/>
                <h:inputText id="quantia" value="#{tiposocio.tiposocio.quantia}" title="Quantia" required="true" requiredMessage="The quantia field is required." />
                <h:outputText value="Associacao:"/>
                <h:selectOneMenu id="associacao" value="#{tiposocio.tiposocio.associacao}" title="Associacao" required="true" requiredMessage="The associacao field is required." >
                    <f:selectItems value="#{associacao.associacaoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="SocioCollection:"/>
                <h:selectManyListbox id="socioCollection" value="#{tiposocio.tiposocio.jsfcrud_transform[jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].socioCollection}" title="SocioCollection" size="6" converter="#{socio.converter}" >
                    <f:selectItems value="#{socio.socioItemsAvailableSelectMany}"/>
                </h:selectManyListbox>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{tiposocio.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{tiposocio.listSetup}" value="Show All Tiposocio Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
