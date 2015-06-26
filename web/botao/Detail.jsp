<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Botao Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Botao Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IDbotao:"/>
                <h:outputText value="#{botao.botao.botaoPK.IDbotao}" title="IDbotao" />
                <h:outputText value="Funcao:"/>
                <h:outputText value="#{botao.botao.funcao}" title="Funcao" />
                <h:outputText value="Nome:"/>
                <h:outputText value="#{botao.botao.nome}" title="Nome" />
                <h:outputText value="Texto:"/>
                <h:outputText value="#{botao.botao.texto}" title="Texto" />
                <h:outputText value="Estado:"/>
                <h:panelGroup>
                    <h:outputText value="#{botao.botao.estado}"/>
                    <h:panelGroup rendered="#{botao.botao.estado != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{estado.detailSetup}">
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="botao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.BotaoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{estado.editSetup}">
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="botao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.BotaoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{estado.destroy}">
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="botao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.BotaoController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>

                <h:outputText value="DetalhebotaoCollection:" />
                <h:panelGroup>
                    <h:outputText rendered="#{empty botao.botao.detalhebotaoCollection}" value="(No Items)"/>
                    <h:dataTable value="#{botao.botao.detalhebotaoCollection}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                 rendered="#{not empty botao.botao.detalhebotaoCollection}">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IDcaixa"/>
                            </f:facet>
                            <h:outputText value="#{item.detalhebotaoPK.IDcaixa}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Conteudo"/>
                            </f:facet>
                            <h:outputText value="#{item.conteudo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nome"/>
                            </f:facet>
                            <h:outputText value="#{item.nome}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Tipo"/>
                            </f:facet>
                            <h:outputText value="#{item.tipo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Botao"/>
                            </f:facet>
                            <h:outputText value="#{item.botao}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{detalhebotao.detailSetup}">
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][detalhebotao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="botao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.BotaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{detalhebotao.editSetup}">
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][detalhebotao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="botao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.BotaoController" />
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{detalhebotao.destroy}">
                                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][detalhebotao.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="botao" />
                                <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.BotaoController" />
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{botao.remove}" value="Destroy">
                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{botao.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][botao.botao][botao.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{botao.createSetup}" value="New Botao" />
            <br />
            <h:commandLink action="#{botao.listSetup}" value="Show All Botao Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
