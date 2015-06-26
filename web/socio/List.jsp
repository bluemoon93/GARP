<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Socio Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv12/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Socio Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Socio Items Found)<br />" rendered="#{socio.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{socio.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{socio.pagingInfo.firstItem + 1}..#{socio.pagingInfo.lastItem} of #{socio.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{socio.prev}" value="Previous #{socio.pagingInfo.batchSize}" rendered="#{socio.pagingInfo.firstItem >= socio.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{socio.next}" value="Next #{socio.pagingInfo.batchSize}" rendered="#{socio.pagingInfo.lastItem + socio.pagingInfo.batchSize <= socio.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{socio.next}" value="Remaining #{socio.pagingInfo.itemCount - socio.pagingInfo.lastItem}"
                               rendered="#{socio.pagingInfo.lastItem < socio.pagingInfo.itemCount && socio.pagingInfo.lastItem + socio.pagingInfo.batchSize > socio.pagingInfo.itemCount}"/>
                <h:dataTable value="#{socio.socioItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nif"/>
                        </f:facet>
                        <h:outputText value="#{item.socioPK.nif}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="NrSocio"/>
                        </f:facet>
                        <h:outputText value="#{item.nrSocio}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataModificacao"/>
                        </f:facet>
                        <h:outputText value="#{item.dataModificacao}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Candidato"/>
                        </f:facet>
                        <h:outputText value="#{item.candidato}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Estado"/>
                        </f:facet>
                        <h:outputText value="#{item.estado}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Tiposocio"/>
                        </f:facet>
                        <h:outputText value="#{item.tiposocio}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{socio.detailSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{socio.editSetup}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{socio.remove}">
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{socio.createSetup}" value="New Socio"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
