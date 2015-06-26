<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Metodopassagem Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Metodopassagem Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Metodopassagem Items Found)<br />" rendered="#{metodopassagem.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{metodopassagem.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{metodopassagem.pagingInfo.firstItem + 1}..#{metodopassagem.pagingInfo.lastItem} of #{metodopassagem.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{metodopassagem.prev}" value="Previous #{metodopassagem.pagingInfo.batchSize}" rendered="#{metodopassagem.pagingInfo.firstItem >= metodopassagem.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{metodopassagem.next}" value="Next #{metodopassagem.pagingInfo.batchSize}" rendered="#{metodopassagem.pagingInfo.lastItem + metodopassagem.pagingInfo.batchSize <= metodopassagem.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{metodopassagem.next}" value="Remaining #{metodopassagem.pagingInfo.itemCount - metodopassagem.pagingInfo.lastItem}"
                               rendered="#{metodopassagem.pagingInfo.lastItem < metodopassagem.pagingInfo.itemCount && metodopassagem.pagingInfo.lastItem + metodopassagem.pagingInfo.batchSize > metodopassagem.pagingInfo.itemCount}"/>
                <h:dataTable value="#{metodopassagem.metodopassagemItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IDmetodo"/>
                        </f:facet>
                        <h:outputText value="#{item.metodopassagemPK.IDmetodo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Anos"/>
                        </f:facet>
                        <h:outputText value="#{item.anos}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Meses"/>
                        </f:facet>
                        <h:outputText value="#{item.meses}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Dias"/>
                        </f:facet>
                        <h:outputText value="#{item.dias}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Estado"/>
                        </f:facet>
                        <h:outputText value="#{item.estado}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Estado1"/>
                        </f:facet>
                        <h:outputText value="#{item.estado1}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{metodopassagem.detailSetup}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{metodopassagem.editSetup}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{metodopassagem.remove}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][metodopassagem.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{metodopassagem.createSetup}" value="New Metodopassagem"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
