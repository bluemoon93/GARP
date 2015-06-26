<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Tiposocio Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Tiposocio Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nome:"/>
                <h:outputText value="#{tiposocio.tiposocio.tiposocioPK.nome}" title="Nome" />
                <h:outputText value="Quantia:"/>
                <h:outputText value="#{tiposocio.tiposocio.quantia}" title="Quantia" />
                <h:outputText value="Associacao:"/>
                <h:panelGroup>
                    <h:outputText value="#{tiposocio.tiposocio.associacao}"/>
                    <h:panelGroup rendered="#{tiposocio.tiposocio.associacao != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{associacao.detailSetup}">
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="tiposocio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.TiposocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{associacao.editSetup}">
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="tiposocio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.TiposocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{associacao.destroy}">
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="tiposocio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.TiposocioController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="SocioCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty tiposocio.tiposocio.socioCollection}" value="(No Items)"/>
                    <h:dataTable value="#{tiposocio.tiposocio.socioCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty tiposocio.tiposocio.socioCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nif"/>
                            </f:facet>
                            <h:outputText value="#{item.socioPK.nif}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="NrSocio"/>
                            </f:facet>
                            <h:outputText value="#{item.nrSocio}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataModificacao"/>
                            </f:facet>
                            <h:outputText value="#{item.dataModificacao}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Candidato"/>
                            </f:facet>
                            <h:outputText value="#{item.candidato}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Estado"/>
                            </f:facet>
                            <h:outputText value="#{item.estado}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Tiposocio"/>
                            </f:facet>
                            <h:outputText value="#{item.tiposocio}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{socio.detailSetup}">
                                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tiposocio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.TiposocioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{socio.editSetup}">
                                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tiposocio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.TiposocioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{socio.destroy}">
                                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tiposocio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.TiposocioController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{tiposocio.remove}" value="Destroy">
                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{tiposocio.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tiposocio.tiposocio][tiposocio.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{tiposocio.createSetup}" value="New Tiposocio" />
            <br />
            <h:commandLink action="#{tiposocio.listSetup}" value="Show All Tiposocio Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
