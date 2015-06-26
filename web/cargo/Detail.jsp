<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Cargo Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Cargo Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nome:"/>
                <h:outputText value="#{cargo.cargo.cargoPK.nome}" title="Nome" />
                <h:outputText value="Grupo:"/>
                <h:outputText value="#{cargo.cargo.cargoPK.grupo}" title="Grupo" />
                <h:outputText value="Acesso:"/>
                <h:outputText value="#{cargo.cargo.acesso}" title="Acesso" />

                <h:outputText value="EventoCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty cargo.cargo.eventoCollection}" value="(No Items)"/>
                    <h:dataTable value="#{cargo.cargo.eventoCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty cargo.cargo.eventoCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDEvento"/>
                            </f:facet>
                            <h:outputText value="#{item.eventoPK.IDEvento}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataEvento"/>
                            </f:facet>
                            <h:outputText value="#{item.dataEvento}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Localizacao"/>
                            </f:facet>
                            <h:outputText value="#{item.localizacao}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Conteudo"/>
                            </f:facet>
                            <h:outputText value="#{item.conteudo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataCriacao"/>
                            </f:facet>
                            <h:outputText value="#{item.dataCriacao}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Titulo"/>
                            </f:facet>
                            <h:outputText value="#{item.titulo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Cargo"/>
                            </f:facet>
                            <h:outputText value="#{item.cargo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Associacao"/>
                            </f:facet>
                            <h:outputText value="#{item.associacao}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{evento.detailSetup}">
                                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cargo" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CargoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{evento.editSetup}">
                                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cargo" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CargoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{evento.destroy}">
                                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cargo" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CargoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="OcupaCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty cargo.cargo.ocupaCollection}" value="(No Items)"/>
                    <h:dataTable value="#{cargo.cargo.ocupaCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty cargo.cargo.ocupaCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataFim"/>
                            </f:facet>
                            <h:outputText value="#{item.dataFim}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataInicio"/>
                            </f:facet>
                            <h:outputText value="#{item.dataInicio}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Socio"/>
                            </f:facet>
                            <h:outputText value="#{item.socio}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Cargo"/>
                            </f:facet>
                            <h:outputText value="#{item.cargo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{ocupa.detailSetup}">
                                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cargo" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CargoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{ocupa.editSetup}">
                                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cargo" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CargoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{ocupa.destroy}">
                                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cargo" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CargoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{cargo.remove}" value="Destroy">
                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{cargo.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cargo.cargo][cargo.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{cargo.createSetup}" value="New Cargo" />
            <br />
            <h:commandLink action="#{cargo.listSetup}" value="Show All Cargo Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
