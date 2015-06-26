<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Socio</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Socio</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{socio.socio.socioPK.nif}" title="Nif" />
                <h:outputText value="NrSocio:"/>
                <h:inputText id="nrSocio" value="#{socio.socio.nrSocio}" title="NrSocio" />
                <h:outputText value="DataModificacao (MM/dd/yyyy):"/>
                <h:inputText id="dataModificacao" value="#{socio.socio.dataModificacao}" title="DataModificacao" required="true" requiredMessage="The dataModificacao field is required." >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="PagamentoquotaCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][socio.socio.pagamentoquotaCollection == null ? jsfcrud_null : socio.socio.pagamentoquotaCollection].jsfcrud_invoke}" title="PagamentoquotaCollection" />
                <h:outputText value="Candidato:"/>
                <h:selectOneMenu id="candidato" value="#{socio.socio.candidato}" title="Candidato" required="true" requiredMessage="The candidato field is required." >
                    <f:selectItems value="#{candidato.candidatoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="Estado:"/>
                <h:selectOneMenu id="estado" value="#{socio.socio.estado}" title="Estado" required="true" requiredMessage="The estado field is required." >
                    <f:selectItems value="#{estado.estadoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="Tiposocio:"/>
                <h:selectOneMenu id="tiposocio" value="#{socio.socio.tiposocio}" title="Tiposocio" required="true" requiredMessage="The tiposocio field is required." >
                    <f:selectItems value="#{tiposocio.tiposocioItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="OcupaCollection:"/>
                <h:outputText escape="false" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][socio.socio.ocupaCollection == null ? jsfcrud_null : socio.socio.ocupaCollection].jsfcrud_invoke}" title="OcupaCollection" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{socio.edit}" value="Save">
                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{socio.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{socio.listSetup}" value="Show All Socio Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
