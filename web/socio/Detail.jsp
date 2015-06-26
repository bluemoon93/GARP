<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Socio Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Socio Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Nif:"/>
                <h:outputText value="#{socio.socio.socioPK.nif}" title="Nif" />
                <h:outputText value="NrSocio:"/>
                <h:outputText value="#{socio.socio.nrSocio}" title="NrSocio" />
                <h:outputText value="DataModificacao:"/>
                <h:outputText value="#{socio.socio.dataModificacao}" title="DataModificacao" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Candidato:"/>
                <h:panelGroup>
                    <h:outputText value="#{socio.socio.candidato}"/>
                    <h:panelGroup rendered="#{socio.socio.candidato != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{candidato.detailSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.candidato][candidato.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{candidato.editSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.candidato][candidato.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{candidato.destroy}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.candidato][candidato.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Estado:"/>
                <h:panelGroup>
                    <h:outputText value="#{socio.socio.estado}"/>
                    <h:panelGroup rendered="#{socio.socio.estado != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{estado.detailSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{estado.editSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{estado.destroy}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Tiposocio:"/>
                <h:panelGroup>
                    <h:outputText value="#{socio.socio.tiposocio}"/>
                    <h:panelGroup rendered="#{socio.socio.tiposocio != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{tiposocio.detailSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{tiposocio.editSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{tiposocio.destroy}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio.tiposocio][tiposocio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="socio"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="PagamentoquotaCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty socio.socio.pagamentoquotaCollection}" value="(No Items)"/>
                    <h:dataTable value="#{socio.socio.pagamentoquotaCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty socio.socio.pagamentoquotaCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Ano"/>
                            </f:facet>
                            <h:outputText value="#{item.pagamentoquotaPK.ano}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataPag"/>
                            </f:facet>
                            <h:outputText value="#{item.dataPag}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Quantia"/>
                            </f:facet>
                            <h:outputText value="#{item.quantia}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DataRecibo"/>
                            </f:facet>
                            <h:outputText value="#{item.dataRecibo}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="NrRecibo"/>
                            </f:facet>
                            <h:outputText value="#{item.nrRecibo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Socio"/>
                            </f:facet>
                            <h:outputText value="#{item.socio}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{pagamentoquota.detailSetup}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="socio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{pagamentoquota.editSetup}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="socio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{pagamentoquota.destroy}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="socio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="OcupaCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty socio.socio.ocupaCollection}" value="(No Items)"/>
                    <h:dataTable value="#{socio.socio.ocupaCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty socio.socio.ocupaCollection}">
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
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="socio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{ocupa.editSetup}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="socio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{ocupa.destroy}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="socio" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.SocioController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{socio.remove}" value="Destroy">
                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{socio.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][socio.socio][socio.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{socio.createSetup}" value="New Socio" />
            <br />
            <h:commandLink action="#{socio.listSetup}" value="Show All Socio Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
