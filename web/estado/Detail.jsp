<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Estado Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Estado Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IDestado:"/>
                <h:outputText value="#{estado.estado.estadoPK.IDestado}" title="IDestado" />
                <h:outputText value="Nome:"/>
                <h:outputText value="#{estado.estado.nome}" title="Nome" />
                <h:outputText value="Associacao:"/>
                <h:panelGroup>
                    <h:outputText value="#{estado.estado.associacao}"/>
                    <h:panelGroup rendered="#{estado.estado.associacao != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{associacao.detailSetup}">
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="estado"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{associacao.editSetup}">
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="estado"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{associacao.destroy}">
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="estado"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="SocioCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty estado.estado.socioCollection}" value="(No Items)"/>
                    <h:dataTable value="#{estado.estado.socioCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty estado.estado.socioCollection}">
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
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{socio.editSetup}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{socio.destroy}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="MetodopassagemCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty estado.estado.metodopassagemCollection}" value="(No Items)"/>
                    <h:dataTable value="#{estado.estado.metodopassagemCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty estado.estado.metodopassagemCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDmetodo"/>
                            </f:facet>
                            <h:outputText value="#{item.metodopassagemPK.IDmetodo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Anos"/>
                            </f:facet>
                            <h:outputText value="#{item.anos}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Meses"/>
                            </f:facet>
                            <h:outputText value="#{item.meses}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Dias"/>
                            </f:facet>
                            <h:outputText value="#{item.dias}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Estado"/>
                            </f:facet>
                            <h:outputText value="#{item.estado}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Estado1"/>
                            </f:facet>
                            <h:outputText value="#{item.estado1}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{metodopassagem.detailSetup}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{metodopassagem.editSetup}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{metodopassagem.destroy}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="MetodopassagemCollection1:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty estado.estado.metodopassagemCollection1}" value="(No Items)"/>
                    <h:dataTable value="#{estado.estado.metodopassagemCollection1}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty estado.estado.metodopassagemCollection1}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDmetodo"/>
                            </f:facet>
                            <h:outputText value="#{item.metodopassagemPK.IDmetodo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Anos"/>
                            </f:facet>
                            <h:outputText value="#{item.anos}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Meses"/>
                            </f:facet>
                            <h:outputText value="#{item.meses}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Dias"/>
                            </f:facet>
                            <h:outputText value="#{item.dias}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Estado"/>
                            </f:facet>
                            <h:outputText value="#{item.estado}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Estado1"/>
                            </f:facet>
                            <h:outputText value="#{item.estado1}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{metodopassagem.detailSetup}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{metodopassagem.editSetup}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{metodopassagem.destroy}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <h:outputText value="BotaoCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty estado.estado.botaoCollection}" value="(No Items)"/>
                    <h:dataTable value="#{estado.estado.botaoCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty estado.estado.botaoCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDbotao"/>
                            </f:facet>
                            <h:outputText value="#{item.botaoPK.IDbotao}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Funcao"/>
                            </f:facet>
                            <h:outputText value="#{item.funcao}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nome"/>
                            </f:facet>
                            <h:outputText value="#{item.nome}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Texto"/>
                            </f:facet>
                            <h:outputText value="#{item.texto}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Estado"/>
                            </f:facet>
                            <h:outputText value="#{item.estado}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{botao.detailSetup}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{botao.editSetup}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{botao.destroy}">
                                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="estado" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{estado.remove}" value="Destroy">
                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{estado.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{estado.createSetup}" value="New Estado" />
            <br />
            <h:commandLink action="#{estado.listSetup}" value="Show All Estado Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
