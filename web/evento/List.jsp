<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Evento Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Evento Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Evento Items Found)<br />" rendered="#{evento.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{evento.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{evento.pagingInfo.firstItem + 1}..#{evento.pagingInfo.lastItem} of #{evento.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{evento.prev}" value="Previous #{evento.pagingInfo.batchSize}" rendered="#{evento.pagingInfo.firstItem >= evento.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{evento.next}" value="Next #{evento.pagingInfo.batchSize}" rendered="#{evento.pagingInfo.lastItem + evento.pagingInfo.batchSize <= evento.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{evento.next}" value="Remaining #{evento.pagingInfo.itemCount - evento.pagingInfo.lastItem}"
                               rendered="#{evento.pagingInfo.lastItem < evento.pagingInfo.itemCount && evento.pagingInfo.lastItem + evento.pagingInfo.batchSize > evento.pagingInfo.itemCount}"/>
                <h:dataTable value="#{evento.eventoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IDEvento"/>
                        </f:facet>
                        <h:outputText value="#{item.eventoPK.IDEvento}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataEvento"/>
                        </f:facet>
                        <h:outputText value="#{item.dataEvento}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Localizacao"/>
                        </f:facet>
                        <h:outputText value="#{item.localizacao}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Conteudo"/>
                        </f:facet>
                        <h:outputText value="#{item.conteudo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataCriacao"/>
                        </f:facet>
                        <h:outputText value="#{item.dataCriacao}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Titulo"/>
                        </f:facet>
                        <h:outputText value="#{item.titulo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cargo"/>
                        </f:facet>
                        <h:outputText value="#{item.cargo}"/>
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
                        <h:commandLink value="Show" action="#{evento.detailSetup}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{evento.editSetup}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{evento.remove}">
                            <f:param name="jsfcrud.currentEvento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][evento.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{evento.createSetup}" value="New Evento"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
