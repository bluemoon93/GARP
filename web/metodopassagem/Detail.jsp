<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Metodopassagem Detail</title>
            <link rel="stylesheet" type="text/css" href="/GASSv10/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Metodopassagem Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="IDmetodo:"/>
                <h:outputText value="#{metodopassagem.metodopassagem.metodopassagemPK.IDmetodo}" title="IDmetodo" />
                <h:outputText value="Anos:"/>
                <h:outputText value="#{metodopassagem.metodopassagem.anos}" title="Anos" />
                <h:outputText value="Meses:"/>
                <h:outputText value="#{metodopassagem.metodopassagem.meses}" title="Meses" />
                <h:outputText value="Dias:"/>
                <h:outputText value="#{metodopassagem.metodopassagem.dias}" title="Dias" />
                <h:outputText value="Estado:"/>
                <h:panelGroup>
                    <h:outputText value="#{metodopassagem.metodopassagem.estado}"/>
                    <h:panelGroup rendered="#{metodopassagem.metodopassagem.estado != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{estado.detailSetup}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="metodopassagem"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.MetodopassagemController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{estado.editSetup}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="metodopassagem"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.MetodopassagemController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{estado.destroy}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem.estado][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="metodopassagem"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.MetodopassagemController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="Estado1:"/>
                <h:panelGroup>
                    <h:outputText value="#{metodopassagem.metodopassagem.estado1}"/>
                    <h:panelGroup rendered="#{metodopassagem.metodopassagem.estado1 != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{estado.detailSetup}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem.estado1][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="metodopassagem"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.MetodopassagemController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{estado.editSetup}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem.estado1][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="metodopassagem"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.MetodopassagemController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{estado.destroy}">
                            <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem.estado1][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="metodopassagem"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.MetodopassagemController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{metodopassagem.remove}" value="Destroy">
                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{metodopassagem.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentMetodopassagem" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][metodopassagem.metodopassagem][metodopassagem.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{metodopassagem.createSetup}" value="New Metodopassagem" />
            <br />
            <h:commandLink action="#{metodopassagem.listSetup}" value="Show All Metodopassagem Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
