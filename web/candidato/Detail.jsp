<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Candidato Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Candidato Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Email:"/>
                <h:outputText value="#{candidato.candidato.candidatoPK.email}" title="Email" />
                <h:outputText value="PalavraPasse:"/>
                <h:outputText value="#{candidato.candidato.palavraPasse}" title="PalavraPasse" />
                <h:outputText value="Nome:"/>
                <h:outputText value="#{candidato.candidato.nome}" title="Nome" />
                <h:outputText value="DataNasc:"/>
                <h:outputText value="#{candidato.candidato.dataNasc}" title="DataNasc" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Telemovel:"/>
                <h:outputText value="#{candidato.candidato.telemovel}" title="Telemovel" />
                <h:outputText value="Associacao:"/>
                <h:panelGroup>
                    <h:outputText value="#{candidato.candidato.associacao}"/>
                    <h:panelGroup rendered="#{candidato.candidato.associacao != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{associacao.detailSetup}">
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="candidato"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CandidatoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{associacao.editSetup}">
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="candidato"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CandidatoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{associacao.destroy}">
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="candidato"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CandidatoController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="SocioCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty candidato.candidato.socioCollection}" value="(No Items)"/>
                    <h:dataTable value="#{candidato.candidato.socioCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty candidato.candidato.socioCollection}">
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
                                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="candidato" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CandidatoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{socio.editSetup}">
                                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="candidato" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CandidatoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{socio.destroy}">
                                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="candidato" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.CandidatoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{candidato.remove}" value="Destroy">
                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{candidato.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{candidato.createSetup}" value="New Candidato" />
            <br />
            <h:commandLink action="#{candidato.listSetup}" value="Show All Candidato Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
