<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Pagamentoquota Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Pagamentoquota Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Pagamentoquota Items Found)<br />" rendered="#{pagamentoquota.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{pagamentoquota.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{pagamentoquota.pagingInfo.firstItem + 1}..#{pagamentoquota.pagingInfo.lastItem} of #{pagamentoquota.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{pagamentoquota.prev}" value="Previous #{pagamentoquota.pagingInfo.batchSize}" rendered="#{pagamentoquota.pagingInfo.firstItem >= pagamentoquota.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{pagamentoquota.next}" value="Next #{pagamentoquota.pagingInfo.batchSize}" rendered="#{pagamentoquota.pagingInfo.lastItem + pagamentoquota.pagingInfo.batchSize <= pagamentoquota.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{pagamentoquota.next}" value="Remaining #{pagamentoquota.pagingInfo.itemCount - pagamentoquota.pagingInfo.lastItem}"
                               rendered="#{pagamentoquota.pagingInfo.lastItem < pagamentoquota.pagingInfo.itemCount && pagamentoquota.pagingInfo.lastItem + pagamentoquota.pagingInfo.batchSize > pagamentoquota.pagingInfo.itemCount}"/>
                <h:dataTable value="#{pagamentoquota.pagamentoquotaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Ano"/>
                        </f:facet>
                        <h:outputText value="#{item.pagamentoquotaPK.ano}"/>
                    </h:column>
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
                            <h:outputText value="Socio"/>
                        </f:facet>
                        <h:outputText value="#{item.socio}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{pagamentoquota.detailSetup}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{pagamentoquota.editSetup}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{pagamentoquota.remove}">
                            <f:param name="jsfcrud.currentPagamentoquota" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][pagamentoquota.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{pagamentoquota.createSetup}" value="New Pagamentoquota"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
