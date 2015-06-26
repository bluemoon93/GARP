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
            <title>ListaSecretariado</title>
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
                
                .size{
                    height: 200px;
                    width: 200px;
                     padding: 200px;
                      margin: 200px;
                }
                
                .center{
                    margin-left: 200px;
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
                                    <li> <h:commandLink  value="Home" action="#{socio.homeSecretariado()}"/>

                                    </li>
                                </ul>
                            </div>
                        </h:form>
                        <div style="background: #000" class="nav-collapse pull-right">
                            <%--<h:form>                                
                                <% beanWorker.setPage(request.getHeader("Referer")); %>
                                <a href="<%=request.getHeader("Referer")%>" >Voltar</a>
                            </h:form>--%>
                        </div>

                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <h1>Contactos</h1>
            <section id="forms" >


                    <div class="row-fluid" >
                        <div class="span4" >
                            <div class="well" >
                        
                                <%--<div class="control-group" >--%>
                                    <h5>A ideia GASS surgiu no projecto final de Licenciatura em Engenharia de Computadores e Telemática da Universidade de Aveiro, tendo sido realizado pelos seguintes elementos:</h5>
                                    <br />
                                  <%-- <div class="controls">--%>
                                  <h:panelGrid columns="2" >
                                           
                                                <h:outputText styleClass="form-signin input-block-level" id="titulo" value="Ana Domingos: ana.domingos@ua.pt" title="Titulo" /> 
                                                 <h:commandButton image="ana.jpg"  value="ola"> </h:commandButton>                                   
                                                <h:outputText styleClass="form-signin input-block-level" id="titulo1" value="David Simões: david.simoes@ua.pt" title="Titulo" />   
                                                <h:commandButton image="david_simoes.png" value="ola"> </h:commandButton>
                                                <h:outputText styleClass="form-signin input-block-level" id="titulo2" value="Rui Brás: nuno.carrulo@ua.pt" title="Titulo" />            
                                                 <h:commandButton image="rui.png" value="ola"> </h:commandButton>
                                                <h:outputText styleClass="form-signin input-block-level" id="titulo3" value="Tiago Lourenço: tiago.vf.lourenco@ua.pt" title="Titulo" />     
                                                 <h:commandButton image="tiagolourenco.JPG" value="ola"> </h:commandButton>
                                                <h:outputText styleClass="form-signin input-block-level" id="titulo4" value="Tiago Soares: tiagomsoares@ua.pt" title="Titulo" />
                                                <h:commandButton image="tiago_soares.jpg"  value="ola"> </h:commandButton>
                                              </h:panelGrid>
                                                
                                               
                        
                                               
                                   
                                <%--    </div> --%>
                             <%--   </div> --%>
                                <%-- <h:panelGroup>
                                     <h:dataTable styleClass="table table-hover" 
                                                  border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                                  >        
                                         <h:column>
                                             <f:facet name="header"><center>
                                                     <h:commandButton image="tiago.jpg" value="lol"> </h:commandButton>
                                                 </f:facet>
                                             </h:column>
                                             <h:column>
                                                 <div class="control-group">
                                                     <div class="controls" >
                                                         <f:facet name="header"><center>
                                                                 <h:outputText styleClass="form-signin input-block-level" id="titulo" value="Ana Domingos: ana.domingos@ua.pt" title="Titulo" />                  
                                                                 <h:outputText styleClass="form-signin input-block-level" id="titulo1" value="David Simões: david.simoes@ua.pt" title="Titulo" />                  
                                                                 <h:outputText styleClass="form-signin input-block-level" id="titulo2" value="Rui Brás: nuno.carrulo@ua.pt" title="Titulo" />                  
                                                                 <h:outputText styleClass="form-signin input-block-level" id="titulo3" value="Tiago Lourenço: tiago.vf.lourenco@ua.pt" title="Titulo" />                  
                                                                 <h:outputText styleClass="form-signin input-block-level" id="titulo4" value="Tiago Soares: tiagomsoares@ua.pt" title="Titulo" />
                                                             </f:facet>
                                                     </div>
                                                 </div>
                                             </h:column>
                                         </h:dataTable>
                                     </h:panelGroup> --%>

                            </div>
                        </div>

                        <div class="span4">
                            <div class="well">   
                                <div class="control-group">
                                    <h5> Cliente (SPR) </h5>
                                    <div class="controls" > 
                                        <h:outputText styleClass="form-signin input-block-level" id="titulo5" value="Pedro Fonseca: pf@ua.pt " title="Titulo" /> 
                                    
                                        <h:commandButton image="pf.1.jpg"  styleClass="center" value="ola"> </h:commandButton> 
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="span4">
                            <div class="well">   
                                <div class="control-group">
                                    <h5> Docentes PEI </h5>
                                    <div class="controls" >           
                                        <h:outputText styleClass="form-signin input-block-level" id="titulo7" value="José Maria Fernandes: jmf@ua.pt" title="Titulo" /> 
                                        <h:commandButton image="JOSE.jpg" styleClass="center"  value="ola"> </h:commandButton>
                                        <br/>
                                        <br/>
                                        <h:outputText styleClass="form-signin input-block-level" id="titulo8" value="Ilídio Oliveira: ico@ua.pt" title="Titulo" /> 
                                        <h:commandButton image="foto.jpg" styleClass="center" value="ola"> </h:commandButton>
                                    </div>
                                </div>
                            </div>
                        </div>



            </section>
        </body>
    </html>
</f:view>