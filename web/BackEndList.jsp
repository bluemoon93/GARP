<%@page import="entitiesGASS.jsf.SocioController"%>
<%@page import="entitiesGASS.jsf.beanWorker"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
    <html>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Lista Secretariado</title>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.min.js"></script>
            <!-- dropdown-->
            <script src="js/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <!-- Le styles -->
            <link href="/GASSv12/faces/bootstrap.css" rel="stylesheet">
            <style type="text/css">
                body {
                    padding-top: 60px;
                    padding-bottom: 40px;
                }

                .form-signin {
                    max-width: 300px;
                    padding: 19px 29px 29px;
                    margin: 0 auto 20px;
                    background-color: #fff;
                    border: 1px solid #e5e5e5;
                    -webkit-border-radius: 5px;
                    -moz-border-radius: 5px;
                    border-radius: 5px;
                    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                    box-shadow: 0 1px 2px rgba(0,0,0,.05);
                }
                .form-signin .form-signin-heading,
                .form-signin .checkbox {
                    margin-bottom: 10px;
                }
                .form-signin input[type="text"],
                .form-signin input[type="password"] {
                    font-size: 16px;
                    height: auto;
                    margin-bottom: 15px;
                    padding: 7px 9px;
                }

            </style>
            <link href="/GASSv12/faces/bootstrap-responsive.css" rel="stylesheet">
            <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
            <!--[if lt IE 9]>
              <script src="../assets/js/html5shiv.js"></script>
            <![endif]-->

            <!-- Fav and touch icons -->
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
            <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
            <link rel="shortcut icon" href="../assets/ico/favicon.png">


            <script>
                $('.dropdown-toggle').dropdown();

                function popup() {
                    var x = confirm("Tem a certeza que pretende eliminar todos os sócios selecionados?");
                    return x;
                }
            </script>
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
                    </div>
                </div>
            </div>

            <h:form>
                <div class="masthead">
                    <h3  <h:outputText styleClass="muted" value="#{associacao.associacao.nome}"/></h3>

                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container">

                                <div class="nav-collapse collapse">

                                    <ul class="nav">
                                        <li><h:commandLink action="#{pagamentoquota.listQuota}" value="Quotas" rendered="#{ocupa.read == true}" /></li>
                                        &nbsp 
                                        <li><h:commandLink styleClass="link" action="#{pagamentoquota.getAllNullReceipt}" value="Pagamentos por processar" rendered="#{ocupa.read == true}" /></li>
                                        &nbsp 
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Eventos <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a><h:commandLink styleClass="link" action="#{evento.createSetupGASS}" value="Criar Evento"/></a></li>
                                                &nbsp 
                                                <li><a><h:commandLink styleClass="link" action="#{evento.listSetupGASS}" value="Ver Eventos"/></a></li>
                                            </ul>
                                        </li> 
                                        
                                        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.navbar -->
                </div>
            </h:form>
            <br />
            <h:form>
                &nbsp 
                <h:commandButton styleClass="btn btn-warning" action="#{socio.shutAllText}" value="Mostrar Todos" rendered="#{socio.current == -1}"/> 
                <h:commandButton styleClass="btn btn-success" action="#{socio.shutAllText}" value="Mostrar Todos" rendered="#{socio.current != -1}"/> 
                &nbsp 

                <h:dataTable styleClass="tabelaEsc" value="#{associacao.meusEstados}" var="item"
                             rendered="#{not empty associacao.meusEstados}">  
                    <h:column> 
                    <tr>
                        <h:commandButton styleClass="btn btn-warning" value="#{item.nome}" action="#{socio.stateList()}" rendered="#{socio.current == item.estadoPK.IDestado}" >                  
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="associacao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController"/>
                            <f:param name="jsfcrud.relatedController" value="estado"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController"/>      
                            &nbsp 
                        </h:commandButton> 
                        <h:commandButton styleClass="btn btn-success" value="#{item.nome}" action="#{socio.stateList()}" rendered="#{socio.current != item.estadoPK.IDestado}" >                  
                            <f:param name="jsfcrud.currentAssociacao" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][associacao.associacao][associacao.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][estado.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="associacao"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.AssociacaoController"/>
                            <f:param name="jsfcrud.relatedController" value="estado"/>
                            <f:param name="jsfcrud.relatedControllerType" value="entitiesGASS.jsf.EstadoController"/>      
                            &nbsp 
                        </h:commandButton>
                    </h:column>
                </h:dataTable>
            </h:form>

            <h:form >
                
                
                &nbsp 
                <h:commandButton styleClass="btn btn-info" action="#{socio.searchSoc}" value="Procurar"/>  

                &nbsp 
                <h:commandButton styleClass="btn btn-info" action="#{mailWorker.mailPage}" value="Notificar Sócios" rendered="#{not empty socio.pendentList}"/>
                
                &nbsp  

                <h:commandLink styleClass="btn" value="Apagar" action="#{socio.BackEndDeleteAll}" onclick="return popup()" rendered="#{not empty socio.pendentList}">
                    <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                
                &nbsp 

                <h:commandButton styleClass="btn" action="#{socio.selectAll}" rendered="#{not empty socio.pendentList}" value="Selecionar Todos"  /> 
                &nbsp  

                <h:commandButton styleClass="btn" action="#{socio.cleanAll}" rendered="#{not empty socio.pendentList}" value="Limpar Todos"  /> 
                &nbsp

            <br />
            <br />
            <br />
        <h:panelGroup>
                    <h:dataTable id="ola" styleClass="table table-hover" value="#{socio.pendentList}" var="item" 
                                 border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px"     >               
                        <<h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:selectBooleanCheckbox id="Select" value="#{item.checkBox}" title="Enviar Email" />
                        </h:column>

                        <h:column>
                            <f:facet name="header"><center>
                                <h:panelGroup>
                                    <h:commandLink styleClass="link" action="#{socio.setOrder(1)}" value="Nome" />        
                                    <h:inputText id="search"  styleClass="form-signin input-block-level" value="#{socio.search_Nome}"  />
                                </h:panelGroup>
                            </f:facet> 
                            <h:commandLink styleClass="link" value="#{item.candidato.nome}" style="color: #000" action="#{socio.socPayList}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                            </h:commandLink></center>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><center>
                                <h:panelGroup>
                                    <h:commandLink styleClass="link" action="#{socio.setOrder(2)}" value="NIF" />        
                                    <h:inputText id="nif" styleClass="form-signin input-block-level" value="#{socio.search_Nif}"  />
                                </h:panelGroup>
                            </f:facet>

                            <h:commandLink styleClass="link" value="#{item.socioPK.nif}" style="color: #000" action="#{socio.socPayList}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                            </h:commandLink></center>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><center>
                                <h:panelGroup>
                                    <h:commandLink styleClass="link"  action="#{socio.setOrder(3)}" value="Nr Sócio" />     
                                    <h:inputText id="searchNumSoc" styleClass="form-signin input-block-level" value="#{socio.search_NumSoc}"  />
                                </h:panelGroup>
                            </f:facet>


                            <h:commandLink styleClass="link" value="#{item.nrSocio}" style="color: #000" action="#{socio.socPayList}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                            </h:commandLink></center>

                    </h:column>
                    <h:column>
                        <f:facet name="header"><center>
                                <h:panelGroup>
                                    <h:commandLink styleClass="link" action="#{socio.setOrder(4)}" value="Email" /> <br />   
                                    <h:inputText id="searchMail" styleClass="form-signin input-block-level" value="#{socio.search_Mail}"  />
                                </h:panelGroup>
                            </f:facet>

                            <h:commandLink styleClass="link" value="#{item.candidato.candidatoPK.email}" style="color: #000" action="#{socio.socPayList}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                            </h:commandLink></center>

                    </h:column>
                    <h:column>
                        <f:facet name="header"><center>
                                <h:panelGroup>
                                    <h:commandLink styleClass="link"  action="#{socio.setOrder(5)}" value="Estado" />            
                                    <h:inputText id="searchEstado" styleClass="form-signin input-block-level" value="#{socio.search_Estado}"  />
                                </h:panelGroup>
                            </f:facet>
                            <h:commandLink styleClass="link" value="#{item.estado.nome}" style="color: #000" action="#{socio.socPayList}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                            </h:commandLink></center>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><center>
                                <h:panelGroup>
                                    <h:commandLink styleClass="link" action="#{socio.setOrder(6)}" value="Tipo/Escalão" />             
                                    <h:inputText id="searchTipo" styleClass="form-signin input-block-level" value="#{socio.search_Tipo}"  />
                                </h:panelGroup>
                            </f:facet>
                            <h:commandLink styleClass="link" value="#{item.tiposocio.tiposocioPK.nome}" style="color: #000" action="#{socio.socPayList}">
                                <f:param name="jsfcrud.currentSocio" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][socio.converter].jsfcrud_invoke}"/>
                            </h:commandLink></center>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><center>
                                <h:panelGroup>
                                    <h:commandLink styleClass="link" action="#{socio.setOrder(7)}" value="Ultimo Ano Pago" />             
                                    <h:inputText id="searchAno" styleClass="form-signin input-block-level" value="#{socio.search_Ano}"  />
                                </h:panelGroup>
                            </f:facet>
                            <h:outputText value="#{item.xxx(item)}"/></center>
                        </h:column>
                </h:dataTable>
            </h:panelGroup>
            <h:form id="tabela"></h:form>
        </h:form> 
        <br/>
        <hr>

        <div>
            <footer id="footer">
                <p class="pull-right"><a href="#top">Topo da página</a></p>
                &nbsp; &nbsp;
                <p>Criado por: Ana Domingos, David Simões, Rui Brás, Tiago Lourenço, Tiago Soares</p>
                <p>Projecto em Engenharia Informática 2012/2013 | <a href="http://www.ua.pt/deti">DETI</a> | <a href="http://www.ua.pt">Universidade de Aveiro</a></p>
            </footer>
        </div>
        
            <!-- Placed at the end of the document so the pages load faster -->
    <script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/bootstrap-transition.js"></script>
    <script src="../assets/js/bootstrap-alert.js"></script>
    <script src="../assets/js/bootstrap-modal.js"></script>
    <script src="../assets/js/bootstrap-dropdown.js"></script>
    <script src="../assets/js/bootstrap-scrollspy.js"></script>
    <script src="../assets/js/bootstrap-tab.js"></script>
    <script src="../assets/js/bootstrap-tooltip.js"></script>
    <script src="../assets/js/bootstrap-popover.js"></script>
    <script src="../assets/js/bootstrap-button.js"></script>
    <script src="../assets/js/bootstrap-collapse.js"></script>
    <script src="../assets/js/bootstrap-carousel.js"></script>
    <script src="../assets/js/bootstrap-typeahead.js"></script>

    </body>
</html>
</f:view>