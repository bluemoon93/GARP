<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Detalhebotao Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Detalhebotao Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Detalhebotao Items Found)<br />" rendered="#{detalhebotao.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{detalhebotao.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{detalhebotao.pagingInfo.firstItem + 1}..#{detalhebotao.pagingInfo.lastItem} of #{detalhebotao.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{detalhebotao.prev}" value="Previous #{detalhebotao.pagingInfo.batchSize}" rendered="#{detalhebotao.pagingInfo.firstItem >= detalhebotao.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{detalhebotao.next}" value="Next #{detalhebotao.pagingInfo.batchSize}" rendered="#{detalhebotao.pagingInfo.lastItem + detalhebotao.pagingInfo.batchSize <= detalhebotao.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{detalhebotao.next}" value="Remaining #{detalhebotao.pagingInfo.itemCount - detalhebotao.pagingInfo.lastItem}"
                               rendered="#{detalhebotao.pagingInfo.lastItem < detalhebotao.pagingInfo.itemCount && detalhebotao.pagingInfo.lastItem + detalhebotao.pagingInfo.batchSize > detalhebotao.pagingInfo.itemCount}"/>
                <h:dataTable value="#{detalhebotao.detalhebotaoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                            <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][detalhebotao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{detalhebotao.editSetup}">
                            <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][detalhebotao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{detalhebotao.remove}">
                            <f:param name="jsfcrud.currentDetalhebotao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][detalhebotao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{detalhebotao.createSetup}" value="New Detalhebotao"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
