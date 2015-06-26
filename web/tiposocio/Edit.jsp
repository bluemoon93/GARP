<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Tiposocio</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Tiposocio</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nome:"/>
                <h:outputText value="#{tiposocio.tiposocio.tiposocioPK.nome}" title="Nome" />
                <h:outputText value="Quantia:"/>
                <h:inputText id="quantia" value="#{tiposocio.tiposocio.quantia}" title="Quantia" required="true" requiredMessage="The quantia field is required." />
                <h:outputText value="Associacao:"/>
                <h:outputText value=" #{tiposocio.tiposocio.associacao}" title="Associacao" />
                <h:outputText value="SocioCollection:"/>
                <h:selectManyListbox id="socioCollection" value="#{tiposocio.tiposocio.jsfcrud_transform[jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].socioCollection}" title="SocioCollection" size="6" converter="#{socio.converter}" >
                    <f:selectItems value="#{socio.socioItemsAvailableSelectMany}"/>
                </h:selectManyListbox>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{tiposocio.edit}" value="Save">
                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{tiposocio.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{tiposocio.listSetup}" value="Show All Tiposocio Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
