<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Tiposocio Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Tiposocio Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Tiposocio Items Found)<br />" rendered="#{tiposocio.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{tiposocio.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{tiposocio.pagingInfo.firstItem + 1}..#{tiposocio.pagingInfo.lastItem} of #{tiposocio.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{tiposocio.prev}" value="Previous #{tiposocio.pagingInfo.batchSize}" rendered="#{tiposocio.pagingInfo.firstItem >= tiposocio.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{tiposocio.next}" value="Next #{tiposocio.pagingInfo.batchSize}" rendered="#{tiposocio.pagingInfo.lastItem + tiposocio.pagingInfo.batchSize <= tiposocio.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{tiposocio.next}" value="Remaining #{tiposocio.pagingInfo.itemCount - tiposocio.pagingInfo.lastItem}"
                               rendered="#{tiposocio.pagingInfo.lastItem < tiposocio.pagingInfo.itemCount && tiposocio.pagingInfo.lastItem + tiposocio.pagingInfo.batchSize > tiposocio.pagingInfo.itemCount}"/>
                <h:dataTable value="#{tiposocio.tiposocioItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome"/>
                        </f:facet>
                        <h:outputText value="#{item.tiposocioPK.nome}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Quantia"/>
                        </f:facet>
                        <h:outputText value="#{item.quantia}"/>
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
                        <h:commandLink value="Show" action="#{tiposocio.detailSetup}">
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tiposocio.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{tiposocio.editSetup}">
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tiposocio.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{tiposocio.remove}">
                            <f:param name="jsfcrud.currentTiposocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tiposocio.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{tiposocio.createSetup}" value="New Tiposocio"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
