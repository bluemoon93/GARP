<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Botao Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Botao Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Botao Items Found)<br />" rendered="#{botao.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{botao.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{botao.pagingInfo.firstItem + 1}..#{botao.pagingInfo.lastItem} of #{botao.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{botao.prev}" value="Previous #{botao.pagingInfo.batchSize}" rendered="#{botao.pagingInfo.firstItem >= botao.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{botao.next}" value="Next #{botao.pagingInfo.batchSize}" rendered="#{botao.pagingInfo.lastItem + botao.pagingInfo.batchSize <= botao.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{botao.next}" value="Remaining #{botao.pagingInfo.itemCount - botao.pagingInfo.lastItem}"
                               rendered="#{botao.pagingInfo.lastItem < botao.pagingInfo.itemCount && botao.pagingInfo.lastItem + botao.pagingInfo.batchSize > botao.pagingInfo.itemCount}"/>
                <h:dataTable value="#{botao.botaoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{botao.editSetup}">
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{botao.remove}">
                            <f:param name="jsfcrud.currentBotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][botao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{botao.createSetup}" value="New Botao"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
