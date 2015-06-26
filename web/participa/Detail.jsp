<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Participa Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Participa Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Email:"/>
                <h:outputText value="#{participa.participa.participaPK.email}" title="Email" />
                <h:outputText value="DataInscricao:"/>
                <h:outputText value="#{participa.participa.dataInscricao}" title="DataInscricao" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="ValorAPagar:"/>
                <h:outputText value="#{participa.participa.valorAPagar}" title="ValorAPagar" />
                <h:outputText value="Evento:"/>
                <h:panelGroup>
                    <h:outputText value="#{participa.participa.evento}"/>
                    <h:panelGroup rendered="#{participa.participa.evento != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{evento.detailSetup}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="participa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.ParticipaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{evento.editSetup}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="participa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.ParticipaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{evento.destroy}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="participa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.ParticipaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Pagamentoevento:"/>
                <h:panelGroup>
                    <h:outputText value="#{participa.participa.pagamentoevento}"/>
                    <h:panelGroup rendered="#{participa.participa.pagamentoevento != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{pagamentoevento.detailSetup}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="participa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.ParticipaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{pagamentoevento.editSetup}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="participa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.ParticipaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{pagamentoevento.destroy}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa.pagamentoevento][pagamentoevento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="participa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.ParticipaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{participa.remove}" value="Destroy">
                <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{participa.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][participa.participa][participa.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{participa.createSetup}" value="New Participa" />
            <br />
            <h:commandLink action="#{participa.listSetup}" value="Show All Participa Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
