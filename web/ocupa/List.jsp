<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Ocupa Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Ocupa Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Ocupa Items Found)<br />" rendered="#{ocupa.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{ocupa.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{ocupa.pagingInfo.firstItem + 1}..#{ocupa.pagingInfo.lastItem} of #{ocupa.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{ocupa.prev}" value="Previous #{ocupa.pagingInfo.batchSize}" rendered="#{ocupa.pagingInfo.firstItem >= ocupa.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{ocupa.next}" value="Next #{ocupa.pagingInfo.batchSize}" rendered="#{ocupa.pagingInfo.lastItem + ocupa.pagingInfo.batchSize <= ocupa.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{ocupa.next}" value="Remaining #{ocupa.pagingInfo.itemCount - ocupa.pagingInfo.lastItem}"
                               rendered="#{ocupa.pagingInfo.lastItem < ocupa.pagingInfo.itemCount && ocupa.pagingInfo.lastItem + ocupa.pagingInfo.batchSize > ocupa.pagingInfo.itemCount}"/>
                <h:dataTable value="#{ocupa.ocupaItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataFim"/>
                        </f:facet>
                        <h:outputText value="#{item.dataFim}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataInicio"/>
                        </f:facet>
                        <h:outputText value="#{item.dataInicio}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Socio"/>
                        </f:facet>
                        <h:outputText value="#{item.socio}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cargo"/>
                        </f:facet>
                        <h:outputText value="#{item.cargo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{ocupa.detailSetup}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{ocupa.editSetup}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{ocupa.remove}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][ocupa.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{ocupa.createSetup}" value="New Ocupa"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
