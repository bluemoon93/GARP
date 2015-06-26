<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Pagamentoquota Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Pagamentoquota Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Ano:"/>
                <h:outputText value="#{pagamentoquota.pagamentoquota.pagamentoquotaPK.ano}" title="Ano" />
                <h:outputText value="DataPag:"/>
                <h:outputText value="#{pagamentoquota.pagamentoquota.dataPag}" title="DataPag" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Quantia:"/>
                <h:outputText value="#{pagamentoquota.pagamentoquota.quantia}" title="Quantia" />
                <h:outputText value="DataRecibo:"/>
                <h:outputText value="#{pagamentoquota.pagamentoquota.dataRecibo}" title="DataRecibo" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="NrRecibo:"/>
                <h:outputText value="#{pagamentoquota.pagamentoquota.nrRecibo}" title="NrRecibo" />
                <h:outputText value="Socio:"/>
                <h:panelGroup>
                    <h:outputText value="#{pagamentoquota.pagamentoquota.socio}"/>
                    <h:panelGroup rendered="#{pagamentoquota.pagamentoquota.socio != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{socio.detailSetup}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="pagamentoquota"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.PagamentoquotaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{socio.editSetup}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="pagamentoquota"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.PagamentoquotaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{socio.destroy}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="pagamentoquota"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.PagamentoquotaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{pagamentoquota.remove}" value="Destroy">
                <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{pagamentoquota.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoquota.pagamentoquota][pagamentoquota.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{pagamentoquota.createSetup}" value="New Pagamentoquota" />
            <br />
            <h:commandLink action="#{pagamentoquota.listSetup}" value="Show All Pagamentoquota Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
