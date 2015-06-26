<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Ocupa Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Ocupa Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="DataFim:"/>
                <h:outputText value="#{ocupa.ocupa.dataFim}" title="DataFim" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="DataInicio:"/>
                <h:outputText value="#{ocupa.ocupa.dataInicio}" title="DataInicio" >
                    <f:convertDateTime pattern="MM/dd/yyyy" />
                </h:outputText>
                <h:outputText value="Socio:"/>
                <h:panelGroup>
                    <h:outputText value="#{ocupa.ocupa.socio}"/>
                    <h:panelGroup rendered="#{ocupa.ocupa.socio != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{socio.detailSetup}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="ocupa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.OcupaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{socio.editSetup}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="ocupa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.OcupaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{socio.destroy}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa.socio][socio.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="ocupa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.OcupaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Cargo:"/>
                <h:panelGroup>
                    <h:outputText value="#{ocupa.ocupa.cargo}"/>
                    <h:panelGroup rendered="#{ocupa.ocupa.cargo != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{cargo.detailSetup}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa.cargo][cargo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="ocupa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.OcupaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{cargo.editSetup}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa.cargo][cargo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="ocupa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.OcupaController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{cargo.destroy}">
                            <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCargo" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa.cargo][cargo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="ocupa"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.OcupaController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{ocupa.remove}" value="Destroy">
                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{ocupa.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentOcupa" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][ocupa.ocupa][ocupa.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{ocupa.createSetup}" value="New Ocupa" />
            <br />
            <h:commandLink action="#{ocupa.listSetup}" value="Show All Ocupa Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
