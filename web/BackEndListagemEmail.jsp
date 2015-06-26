<%-- 
    Document   : BackEndListagemEmail
    Created on : 26/Abr/2013, 19:40:59
    Author     : Tiago
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listar Emails</title>
            <link rel="stylesheet" type="text/css" href="/GASSv7FDS/BackEnd.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
           
        <div class="navbar navbar-inverse navbar-fixed-top">
                <div  class="navbar-inner">
                    <div class="container-fluid">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                        </a>
                    
                        <div class="btn-group pull-right">
                            <h:form>            
                                <h:commandButton styleClass="btn"  value="Logout" action="#{socio.logout()}"></h:commandButton>
                            </h:form>
                        </div>

                        <p class="navbar-text pull-right" style="margin-right: 40px">
                            Logged in as <h:outputText value="#{candidato.pessoaLogin.nome}"/>
                        </p>
                        <h:form>
                            <div style="background: #000" class="nav-collapse">
                                <ul class="nav">

                                    <li><h:commandLink  value="#{associacao.associacao.nome}" action="#{socio.homeSecretariado()}"/></li>
                                    <li><h:commandLink  value="GASS" action="#{associacao.gassHome}"/></li>
                                    <li><a href="Contactos.jsp">Contactos</a></li> 
                                    <li><a href="BackEndHome1.jsp">Home</a></li>

                                    </li>
                                </ul>
                            </div>
                        </h:form>
                        <div style="background: #000" class="nav-collapse pull-right">
                        </div>

                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>    
            
        <h1>Listar Emails</h1>
           <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Socio Items Found)<br />" rendered="#{socio.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{socio.pagingInfo.itemCount > 0}">
                <%--<h:outputText value="Item #{socio.pagingInfo.firstItem + 1}..#{socio.pagingInfo.lastItem} of #{socio.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{socio.prev}" value="Previous #{socio.pagingInfo.batchSize}" rendered="#{socio.pagingInfo.firstItem >= socio.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{socio.next}" value="Next #{socio.pagingInfo.batchSize}" rendered="#{socio.pagingInfo.lastItem + socio.pagingInfo.batchSize <= socio.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{socio.next}" value="Remaining #{socio.pagingInfo.itemCount - socio.pagingInfo.lastItem}"
                               rendered="#{socio.pagingInfo.lastItem < socio.pagingInfo.itemCount && socio.pagingInfo.lastItem + socio.pagingInfo.batchSize > socio.pagingInfo.itemCount}"/>--%>
                <h:dataTable value="#{socio.socioItems}"  var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="NrSocio"/>
                        </f:facet>
                        <h:outputText value="#{item.nrSocio}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome"/>
                        </f:facet>
                        <h:outputText value="#{item.candidato.nome}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Email"/>
                        </f:facet>
                        <h:outputText value="#{item.candidato.candidatoPK.email}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Associacao"/>
                        </f:facet>
                        <h:outputText value="#{item.candidato.associacao.nome}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Estado"/>
                        </f:facet>
                        <h:outputText value="#{item.estado.nome}"/>
                    </h:column>
                   </h:dataTable>
            </h:panelGroup>
            <br />
            <%--<h:commandLink action="#{socio.createSoss()}" value="New Socio"/>
            <br />
            <br />
            --%>
            <%--<h:commandLink value="Index" action="welcome" immediate="true" />--%>
            <h:commandLink styleClass="command" action="#{socio.homeSecretariado()}"  value="Home Secretariado"/>

        </h:form>
        </body>
    </html>
</f:view>
