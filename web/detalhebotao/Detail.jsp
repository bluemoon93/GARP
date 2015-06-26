<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Detalhebotao Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Detalhebotao Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IDcaixa:"/>
                <h:outputText value="#{detalhebotao.detalhebotao.detalhebotaoPK.IDcaixa}" title="IDcaixa" />
                <h:outputText value="Conteudo:"/>
                <h:outputText value="#{detalhebotao.detalhebotao.conteudo}" title="Conteudo" />
                <h:outputText value="Nome:"/>
                <h:outputText value="#{detalhebotao.detalhebotao.nome}" title="Nome" />
                <h:outputText value="Tipo:"/>
                <h:outputText value="#{detalhebotao.detalhebotao.tipo}" title="Tipo" />
                <h:outputText value="Botao:"/>
                <h:panelGroup>
                    <h:outputText value="#{detalhebotao.detalhebotao.botao}"/>
                    <h:panelGroup rendered="#{detalhebotao.detalhebotao.botao != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{botao.detailSetup}">
                            <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao][detalhebotao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao.botao][botao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="detalhebotao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.DetalhebotaoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{botao.editSetup}">
                            <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao][detalhebotao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao.botao][botao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="detalhebotao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.DetalhebotaoController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{botao.destroy}">
                            <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao][detalhebotao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao.botao][botao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="detalhebotao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.DetalhebotaoController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{detalhebotao.remove}" value="Destroy">
                <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao][detalhebotao.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{detalhebotao.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][detalhebotao.detalhebotao][detalhebotao.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{detalhebotao.createSetup}" value="New Detalhebotao" />
            <br />
            <h:commandLink action="#{detalhebotao.listSetup}" value="Show All Detalhebotao Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
