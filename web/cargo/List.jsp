<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Cargo Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Cargo Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Cargo Items Found)<br />" rendered="#{cargo.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{cargo.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{cargo.pagingInfo.firstItem + 1}..#{cargo.pagingInfo.lastItem} of #{cargo.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{cargo.prev}" value="Previous #{cargo.pagingInfo.batchSize}" rendered="#{cargo.pagingInfo.firstItem >= cargo.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{cargo.next}" value="Next #{cargo.pagingInfo.batchSize}" rendered="#{cargo.pagingInfo.lastItem + cargo.pagingInfo.batchSize <= cargo.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{cargo.next}" value="Remaining #{cargo.pagingInfo.itemCount - cargo.pagingInfo.lastItem}"
                               rendered="#{cargo.pagingInfo.lastItem < cargo.pagingInfo.itemCount && cargo.pagingInfo.lastItem + cargo.pagingInfo.batchSize > cargo.pagingInfo.itemCount}"/>
                <h:dataTable value="#{cargo.cargoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome"/>
                        </f:facet>
                        <h:outputText value="#{item.cargoPK.nome}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Grupo"/>
                        </f:facet>
                        <h:outputText value="#{item.cargoPK.grupo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Acesso"/>
                        </f:facet>
                        <h:outputText value="#{item.acesso}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{cargo.detailSetup}">
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cargo.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{cargo.editSetup}">
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cargo.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{cargo.remove}">
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cargo.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{cargo.createSetup}" value="New Cargo"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
