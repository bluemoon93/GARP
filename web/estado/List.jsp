<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Estado Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Estado Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Estado Items Found)<br />" rendered="#{estado.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{estado.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{estado.pagingInfo.firstItem + 1}..#{estado.pagingInfo.lastItem} of #{estado.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{estado.prev}" value="Previous #{estado.pagingInfo.batchSize}" rendered="#{estado.pagingInfo.firstItem >= estado.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{estado.next}" value="Next #{estado.pagingInfo.batchSize}" rendered="#{estado.pagingInfo.lastItem + estado.pagingInfo.batchSize <= estado.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{estado.next}" value="Remaining #{estado.pagingInfo.itemCount - estado.pagingInfo.lastItem}"
                               rendered="#{estado.pagingInfo.lastItem < estado.pagingInfo.itemCount && estado.pagingInfo.lastItem + estado.pagingInfo.batchSize > estado.pagingInfo.itemCount}"/>
                <h:dataTable value="#{estado.estadoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{estado.editSetup}">
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{estado.remove}">
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{estado.createSetup}" value="New Estado"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
