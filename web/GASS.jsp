<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>GASS</title>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
               <script type="text/javascript" src="/GASSv12_new/faces/bootstrap.js"></script>
            <script type="text/javascript" src="/GASSv12_new/faces/bootstrap.min.js"></script>
 
            <!-- Le styles -->
            <link href="/GASSv12_new/faces/bootstrap.css" rel="stylesheet">
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
            <link href="/GASSv12_new/faces/bootstrap-responsive.css" rel="stylesheet">
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
            <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
            <link rel="shortcut icon" href="../assets/ico/favicon.png">

            <script>
                function popup() {
                    var x = confirm("Tem a certeza?");
                    return x;
                }
            </script>

        </head>
        <body>


            <div class="page-header">
                <h1 class ="anime "> &nbsp O Gass (Gestor de Associações)</h1>
            </div>

            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>

            
            <form class="form-horizontal well">
                <h:form styleClass="jsfcrud_list_form">
                    <h:inputHidden id="validateCreateField" validator="#{associacao.validateCreate}" value="value"/>
                    <fieldset>

                        <div class="control-group">
                            <label class="control-label" for="input01">Email</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="email" value="#{associacao.associacao.email}" title="Email" required="true" requiredMessage="The email field is required." />                     
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Password</label>
                            <div class="controls">
                                <h:inputSecret styleClass="form-signin input-block-level" id="passwd" value="#{associacao.associacao.passwd}" title="Passwd" required="true" requiredMessage="The passwd field is required." />                     
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <h:commandButton styleClass="btn btn-primary" action="#{associacao.loginAssoc}" value="Login"/>
                            </div >
                        </div>
                    </fieldset>
                </h:form>
            </form>  
            
            <form class="form-horizontal well">
                <h:form styleClass="jsfcrud_list_form">  
                    <h:inputHidden id="validateCreateField" validator="#{associacao.validateCreate}" value="value"/>
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label" for="input01">Nif</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="nif" value="#{associacao.associacao.nif}" title="Nif" required="true" requiredMessage="The nif field is required." />                     
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Email</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="email" value="#{associacao.associacao.email}" title="Email" required="true" requiredMessage="The email field is required." />                     
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Nome</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="nome" value="#{associacao.associacao.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />                     
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Password</label>
                            <div class="controls">
                                <h:inputSecret styleClass="form-signin input-block-level" id="passwd" value="#{associacao.associacao.passwd}" title="Passwd" required="true" requiredMessage="The passwd field is required." />                     
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="input01">Sigla</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="url" value="#{associacao.associacao.url}" title="Url" required="true" requiredMessage="The URL field is required." />                     
                            </div>
                        </div>
                            
                        <div class="control-group">
                            <label class="control-label" for="input01">Server de WebMail</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="Server" value="#{associacao.associacao.server}" title="Server" required="true" requiredMessage="The Server field is required." />                     
                            </div>
                        </div>
                            
                        <div class="control-group">
                            <label class="control-label" for="input01">Port do Server</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level" id="port" value="#{associacao.associacao.port}" title="port" required="true" requiredMessage="The port field is required." />                     
                            </div>
                        </div>
                            
                            
                        <div class="control-group">
                            <div class="controls">
                                <h:commandButton styleClass="btn btn-primary"  action="#{associacao.createGass}" value="Criar"/>
                            </div>
                        </div>
                    </fieldset>
                </h:form>
            </form>

        </body>
    </html>
</f:view>