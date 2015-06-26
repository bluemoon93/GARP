<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Pagamentoevento Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Pagamentoevento Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Pagamentoevento Items Found)<br />" rendered="#{pagamentoevento.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{pagamentoevento.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{pagamentoevento.pagingInfo.firstItem + 1}..#{pagamentoevento.pagingInfo.lastItem} of #{pagamentoevento.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{pagamentoevento.prev}" value="Previous #{pagamentoevento.pagingInfo.batchSize}" rendered="#{pagamentoevento.pagingInfo.firstItem >= pagamentoevento.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{pagamentoevento.next}" value="Next #{pagamentoevento.pagingInfo.batchSize}" rendered="#{pagamentoevento.pagingInfo.lastItem + pagamentoevento.pagingInfo.batchSize <= pagamentoevento.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{pagamentoevento.next}" value="Remaining #{pagamentoevento.pagingInfo.itemCount - pagamentoevento.pagingInfo.lastItem}"
                               rendered="#{pagamentoevento.pagingInfo.lastItem < pagamentoevento.pagingInfo.itemCount && pagamentoevento.pagingInfo.lastItem + pagamentoevento.pagingInfo.batchSize > pagamentoevento.pagingInfo.itemCount}"/>
                <h:dataTable value="#{pagamentoevento.pagamentoeventoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataPag"/>
                        </f:facet>
                        <h:outputText value="#{item.dataPag}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Quantia"/>
                        </f:facet>
                        <h:outputText value="#{item.quantia}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataRecibo"/>
                        </f:facet>
                        <h:outputText value="#{item.dataRecibo}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="NrRecibo"/>
                        </f:facet>
                        <h:outputText value="#{item.nrRecibo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Participa"/>
                        </f:facet>
                        <h:outputText value="#{item.participa}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{pagamentoevento.detailSetup}">
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoevento.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{pagamentoevento.editSetup}">
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoevento.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{pagamentoevento.remove}">
                            <f:param name="jsfcrud.currentPagamentoevento" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoevento.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{pagamentoevento.createSetup}" value="New Pagamentoevento"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
