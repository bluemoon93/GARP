<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Participa Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Participa Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Participa Items Found)<br />" rendered="#{participa.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{participa.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{participa.pagingInfo.firstItem + 1}..#{participa.pagingInfo.lastItem} of #{participa.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{participa.prev}" value="Previous #{participa.pagingInfo.batchSize}" rendered="#{participa.pagingInfo.firstItem >= participa.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{participa.next}" value="Next #{participa.pagingInfo.batchSize}" rendered="#{participa.pagingInfo.lastItem + participa.pagingInfo.batchSize <= participa.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{participa.next}" value="Remaining #{participa.pagingInfo.itemCount - participa.pagingInfo.lastItem}"
                               rendered="#{participa.pagingInfo.lastItem < participa.pagingInfo.itemCount && participa.pagingInfo.lastItem + participa.pagingInfo.batchSize > participa.pagingInfo.itemCount}"/>
                <h:dataTable value="#{participa.participaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Email"/>
                        </f:facet>
                        <h:outputText value="#{item.participaPK.email}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataInscricao"/>
                        </f:facet>
                        <h:outputText value="#{item.dataInscricao}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="ValorAPagar"/>
                        </f:facet>
                        <h:outputText value="#{item.valorAPagar}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Evento"/>
                        </f:facet>
                        <h:outputText value="#{item.evento}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Pagamentoevento"/>
                        </f:facet>
                        <h:outputText value="#{item.pagamentoevento}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{participa.detailSetup}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{participa.editSetup}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{participa.remove}">
                            <f:param name="jsfcrud.currentParticipa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][participa.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{participa.createSetup}" value="New Participa"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
