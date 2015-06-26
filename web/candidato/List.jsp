<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Candidato Items</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Candidato Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Candidato Items Found)<br />" rendered="#{candidato.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{candidato.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{candidato.pagingInfo.firstItem + 1}..#{candidato.pagingInfo.lastItem} of #{candidato.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{candidato.prev}" value="Previous #{candidato.pagingInfo.batchSize}" rendered="#{candidato.pagingInfo.firstItem >= candidato.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{candidato.next}" value="Next #{candidato.pagingInfo.batchSize}" rendered="#{candidato.pagingInfo.lastItem + candidato.pagingInfo.batchSize <= candidato.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{candidato.next}" value="Remaining #{candidato.pagingInfo.itemCount - candidato.pagingInfo.lastItem}"
                               rendered="#{candidato.pagingInfo.lastItem < candidato.pagingInfo.itemCount && candidato.pagingInfo.lastItem + candidato.pagingInfo.batchSize > candidato.pagingInfo.itemCount}"/>
                <h:dataTable value="#{candidato.candidatoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Email"/>
                        </f:facet>
                        <h:outputText value="#{item.candidatoPK.email}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="PalavraPasse"/>
                        </f:facet>
                        <h:outputText value="#{item.palavraPasse}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome"/>
                        </f:facet>
                        <h:outputText value="#{item.nome}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DataNasc"/>
                        </f:facet>
                        <h:outputText value="#{item.dataNasc}">
                            <f:convertDateTime pattern="MM/dd/yyyy" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Telemovel"/>
                        </f:facet>
                        <h:outputText value="#{item.telemovel}"/>
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
                        <h:commandLink value="Show" action="#{candidato.detailSetup}">
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][candidato.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{candidato.editSetup}">
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][candidato.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{candidato.remove}">
                            <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][candidato.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{candidato.createSetup}" value="New Candidato"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
