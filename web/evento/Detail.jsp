<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Evento Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Evento Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IDEvento:"/>
                <h:outputText value="#{evento.evento.eventoPK.IDEvento}" title="IDEvento" />
                <h:outputText value="DataEvento:"/>
                <h:outputText value="#{evento.evento.dataEvento}" title="DataEvento" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Localizacao:"/>
                <h:outputText value="#{evento.evento.localizacao}" title="Localizacao" />
                <h:outputText value="Conteudo:"/>
                <h:outputText value="#{evento.evento.conteudo}" title="Conteudo" />
                <h:outputText value="DataCriacao:"/>
                <h:outputText value="#{evento.evento.dataCriacao}" title="DataCriacao" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Titulo:"/>
                <h:outputText value="#{evento.evento.titulo}" title="Titulo" />
                <h:outputText value="Cargo:"/>
                <h:panelGroup>
                    <h:outputText value="#{evento.evento.cargo}"/>
                    <h:panelGroup rendered="#{evento.evento.cargo != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{cargo.detailSetup}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento.cargo][cargo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="evento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{cargo.editSetup}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento.cargo][cargo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="evento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{cargo.destroy}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento.cargo][cargo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="evento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Associacao:"/>
                <h:panelGroup>
                    <h:outputText value="#{evento.evento.associacao}"/>
                    <h:panelGroup rendered="#{evento.evento.associacao != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{associacao.detailSetup}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="evento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{associacao.editSetup}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="evento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{associacao.destroy}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="evento"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="ParticipaCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty evento.evento.participaCollection}" value="(No Items)"/>
                    <h:dataTable value="#{evento.evento.participaCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty evento.evento.participaCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Email"/>
                            </f:facet>
                            <h:outputText value="#{item.participaPK.email}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataInscricao"/>
                            </f:facet>
                            <h:outputText value="#{item.dataInscricao}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ValorAPagar"/>
                            </f:facet>
                            <h:outputText value="#{item.valorAPagar}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Evento"/>
                            </f:facet>
                            <h:outputText value="#{item.evento}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pagamentoevento"/>
                            </f:facet>
                            <h:outputText value="#{item.pagamentoevento}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{participa.detailSetup}">
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="evento" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{participa.editSetup}">
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="evento" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{participa.destroy}">
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="evento" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EventoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{evento.remove}" value="Destroy">
                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{evento.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][evento.evento][evento.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{evento.createSetup}" value="New Evento" />
            <br />
            <h:commandLink action="#{evento.listSetup}" value="Show All Evento Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
