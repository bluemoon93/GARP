<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Associacao Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Associacao Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{associacao.associacao.nif}" title="Nif" />
                <h:outputText value="Email:"/>
                <h:outputText value="#{associacao.associacao.email}" title="Email" />
                <h:outputText value="Nome:"/>
                <h:outputText value="#{associacao.associacao.nome}" title="Nome" />
                <h:outputText value="Passwd:"/>
                <h:outputText value="#{associacao.associacao.passwd}" title="Passwd" />

                <h:outputText value="TiposocioCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty associacao.associacao.tiposocioCollection}" value="(No Items)"/>
                    <h:dataTable value="#{associacao.associacao.tiposocioCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty associacao.associacao.tiposocioCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nome"/>
                            </f:facet>
                            <h:outputText value="#{item.tiposocioPK.nome}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Quantia"/>
                            </f:facet>
                            <h:outputText value="#{item.quantia}"/>
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
                            <h:commandLink value="Show" action="#{tiposocio.detailSetup}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tiposocio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tiposocio.editSetup}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tiposocio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tiposocio.destroy}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tiposocio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="CandidatoCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty associacao.associacao.candidatoCollection}" value="(No Items)"/>
                    <h:dataTable value="#{associacao.associacao.candidatoCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty associacao.associacao.candidatoCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Email"/>
                            </f:facet>
                            <h:outputText value="#{item.candidatoPK.email}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PalavraPasse"/>
                            </f:facet>
                            <h:outputText value="#{item.palavraPasse}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nome"/>
                            </f:facet>
                            <h:outputText value="#{item.nome}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataNasc"/>
                            </f:facet>
                            <h:outputText value="#{item.dataNasc}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Telemovel"/>
                            </f:facet>
                            <h:outputText value="#{item.telemovel}"/>
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
                            <h:commandLink value="Show" action="#{candidato.detailSetup}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][candidato.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{candidato.editSetup}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][candidato.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{candidato.destroy}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][candidato.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="EventoCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty associacao.associacao.eventoCollection}" value="(No Items)"/>
                    <h:dataTable value="#{associacao.associacao.eventoCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty associacao.associacao.eventoCollection}">
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
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{evento.editSetup}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{evento.destroy}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="EstadoCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty associacao.associacao.estadoCollection}" value="(No Items)"/>
                    <h:dataTable value="#{associacao.associacao.estadoCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty associacao.associacao.estadoCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDestado"/>
                            </f:facet>
                            <h:outputText value="#{item.estadoPK.IDestado}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nome"/>
                            </f:facet>
                            <h:outputText value="#{item.nome}"/>
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
                            <h:commandLink value="Show" action="#{estado.detailSetup}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{estado.editSetup}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{estado.destroy}">
                                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="associacao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{associacao.remove}" value="Destroy">
                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{associacao.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{associacao.createSetup}" value="New Associacao" />
            <br />
            <h:commandLink action="#{associacao.listSetup}" value="Show All Associacao Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
