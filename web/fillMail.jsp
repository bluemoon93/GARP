<%-- 
    Document   : fillMail
    Created on : 25/Mai/2013, 23:21:41
    Author     : Nuno
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>


<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Novo E-Mail</title>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap-datetimepicker.min.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.min.js"></script>
            <link href="/GASSv12/faces/bootstrap-datetimepicker.min.css" rel="stylesheet">
            <%--
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
             <link href="/GASSv7FDS/faces/bootstrap.js" rel="stylesheet">
     <link href="/GASSv7FDS/faces/bootstrap.min.js" rel="stylesheet">
            
            --%>
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

            <script type="text/javascript">
                /*
                 * jQuery UI Datepicker: Disable Specific Dates
                 * http://salman-w.blogspot.com/2013/01/jquery-ui-datepicker-examples.html
                 */
                $(function() {
                    var date1 = new Date;
                    $(".dateP").datepicker({
                        beforeShowDay: function(date) {
                            return [date > date1, ""];
                        }
                    });

                    $(".dateP").datepicker('option', 'dateFormat', 'dd/mm/yy');
                });
            </script>
            <script>
                function popup() {
                    var x = confirm("Tem a certeza que pretende enviar o mail ?");
                    return x;
                }
            </script>
            <script>
                function popupCancel() {
                    var x = confirm("Tem a certeza ?");
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
                            <%--<h:form>                                
                                <% beanWorker.setPage(request.getHeader("Referer")); %>
                                <a href="<%=request.getHeader("Referer")%>" >Voltar</a>
                            </h:form>--%>
                        </div>

                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
                       
            <h1>Novo e-mail</h1>
            <section id="forms">
                <h:form styleClass="form-horizontal well">
                    <%--<h:inputHidden id="validateCreateField" validator="#{evento.validateCreate}" value="value"/>
                        --%>
                    <fieldset>
                        <legend>Preenchimento dos Campos do e-mail</legend>                      
                           
                        <div class="control-group">
                            <label class="control-label" for="input01">Para:</label>
                            <div class="controls">
                                <h:form>
                                    <h:outputText id="dest"  value="#{mailWorker.allMails}" title="Para:"/>
                                </h:form>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="input01">Assunto</label>
                            <div class="controls">
                                <h:inputTextarea id="assunto" value="#{mailWorker.assunto}" title="Assunto" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="input01">Corpo da Mensagem</label>
                            <div class="controls">
                                <h:inputTextarea  styleClass="input-xxlarge" rows="6" cols="100" id="conteudo" value="#{mailWorker.body}" title="Corpo da Mensagem" />
                            </div>
                        </div>

                        <div class="form-actions">

                            <h:commandButton styleClass="btn btn-primary" action="#{mailWorker.fillMail}" value="Enviar"  onclick="return popup()"/>
                            &nbsp 
                            <h:commandButton styleClass="btn" action="#{mailWorker.cancelar}" value="Cancelar" onclick="return popupCancel()"/>      

                        </div>

                    </fieldset>
                </h:form>
            </section>
        </body>
    </html>
</f:view>

