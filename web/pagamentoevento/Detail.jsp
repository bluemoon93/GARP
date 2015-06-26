<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Pagamentoevento Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Pagamentoevento Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="DataPag:"/>
                <h:outputText value="#{pagamentoevento.pagamentoevento.dataPag}" title="DataPag" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Quantia:"/>
                <h:outputText value="#{pagamentoevento.pagamentoevento.quantia}" title="Quantia" />
                <h:outputText value="DataRecibo:"/>
                <h:outputText value="#{pagamentoevento.pagamentoevento.dataRecibo}" title="DataRecibo" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="NrRecibo:"/>
                <h:outputText value="#{pagamentoevento.pagamentoevento.nrRecibo}" title="NrRecibo" />
                <h:outputText value="Participa:"/>
                <h:panelGroup>
                    <h:outputText value="#{pagamentoevento.pagamentoevento.participa}"/>
                    <h:panelGroup rendered="#{pagamentoevento.pagamentoevento.participa != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{participa.detailSetup}">
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="pagamentoevento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.PagamentoeventoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{participa.editSetup}">
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="pagamentoevento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.PagamentoeventoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{participa.destroy}">
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="pagamentoevento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.PagamentoeventoController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{pagamentoevento.remove}" value="Destroy">
                <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{pagamentoevento.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][pagamentoevento.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{pagamentoevento.createSetup}" value="New Pagamentoevento" />
            <br />
            <h:commandLink action="#{pagamentoevento.listSetup}" value="Show All Pagamentoevento Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
