<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Candidato</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Candidato</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Email:"/>
                <h:outputText value="#{candidato.candidato.candidatoPK.email}" title="Email" />
                <h:outputText value="PalavraPasse:"/>
                <h:inputText id="palavraPasse" value="#{candidato.candidato.palavraPasse}" title="PalavraPasse" required="true" requiredMessage="The palavraPasse field is required." />
                <h:outputText value="Nome:"/>
                <h:inputText id="nome" value="#{candidato.candidato.nome}" title="Nome" />
                <h:outputText value="DataNasc (MM/dd/yyyy):"/>
                <h:inputText id="dataNasc" value="#{candidato.candidato.dataNasc}" title="DataNasc" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:inputText>
                <h:outputText value="Telemovel:"/>
                <h:inputText id="telemovel" value="#{candidato.candidato.telemovel}" title="Telemovel" />
                <h:outputText value="Associacao:"/>
                <h:outputText value=" #{candidato.candidato.associacao}" title="Associacao" />
                <h:outputText value="SocioCollection:"/>
                <h:selectManyListbox id="socioCollection" value="#{candidato.candidato.jsfcrud_transform[jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].socioCollection}" title="SocioCollection" size="6" converter="#{socio.converter}" >
                    <f:selectItems value="#{socio.socioItemsAvailableSelectMany}"/>
                </h:selectManyListbox>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{candidato.edit}" value="Save">
                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{candidato.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentCandidato" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{candidato.listSetup}" value="Show All Candidato Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
