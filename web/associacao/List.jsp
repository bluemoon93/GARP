<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Associacao Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Associacao Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Associacao Items Found)<br />" rendered="#{associacao.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{associacao.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{associacao.pagingInfo.firstItem + 1}..#{associacao.pagingInfo.lastItem} of #{associacao.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{associacao.prev}" value="Previous #{associacao.pagingInfo.batchSize}" rendered="#{associacao.pagingInfo.firstItem >= associacao.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{associacao.next}" value="Next #{associacao.pagingInfo.batchSize}" rendered="#{associacao.pagingInfo.lastItem + associacao.pagingInfo.batchSize <= associacao.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{associacao.next}" value="Remaining #{associacao.pagingInfo.itemCount - associacao.pagingInfo.lastItem}"
                               rendered="#{associacao.pagingInfo.lastItem < associacao.pagingInfo.itemCount && associacao.pagingInfo.lastItem + associacao.pagingInfo.batchSize > associacao.pagingInfo.itemCount}"/>
                <h:dataTable value="#{associacao.associacaoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nif"/>
                        </f:facet>
                        <h:outputText value="#{item.nif}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Email"/>
                        </f:facet>
                        <h:outputText value="#{item.email}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome"/>
                        </f:facet>
                        <h:outputText value="#{item.nome}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Passwd"/>
                        </f:facet>
                        <h:outputText value="#{item.passwd}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{associacao.detailSetup}">
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][associacao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{associacao.editSetup}">
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][associacao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{associacao.remove}">
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][associacao.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{associacao.createSetup}" value="New Associacao"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
