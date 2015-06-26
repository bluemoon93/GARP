<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Criar Novo Botão</title>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.js"></script>
            <script type="text/javascript" src="/GASSv12/faces/bootstrap.min.js"></script>

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
                  .space{
                    margin-left: 20px;
                }


            </style>
            <link href="/GASSv12/faces/bootstrap-responsive.css" rel="stylesheet"><!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
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
                function popup() {
                    var x = confirm("Tem a certeza?");
                    return x;
                }


            </script>
            <script>
                function popupBotao() {
                    alert("Pode usar tags references aos detalhes do sócio! Por exemplo, <nome>, <tipo>, <estado>, <bDay> e <nmrSocio> podem usar usadas.\n\nAlem disso, também pode criar tags referentes aos detalhes do seu botão! Se tiver uma caixa de texto chamada teste, use a tag <teste> para escrever esse texto no seu email automaticamente");
                }


            </script>

        </head>

        <body>

            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>

            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container">          
                        <a class="brand" href="#">Get started</a>
                        <div class="nav-collapse collapse">
                            <ul class="nav">
                                <li class="active"><a href="#">GASS</a></li>
                                <li><a href="#about">Ajuda</a></li>
                                <li><a href="#contact">Contactos</a></li>
                            </ul>
                        </div><!--/.nav-collapse -->
                    </div>
                </div>
            </div>


            <div class="page-header">
                <h1> &nbsp Criar Novo Botão</h1>
            </div>
            <section id="forms">
                <h:form styleClass="form-horizontal well">
                    <label class="control-label" for="input01">Utilize tags no seu email! </label>
                    <a href="javascript:popupBotao()"><img src="pergunta.png" border="0"></a>
               <%-- <h:commandButton image="pergunta.png" value="ola" onclick="popupBotao()"></h:commandButton>--%>
                </h:form>
                <h:form styleClass="form-horizontal well">
                    <h:inputHidden id="validateCreateField" validator="#{botao.validateCreate}" value="value"/>

                    <fieldset>

                        <div class="control-group">
                            <label class="control-label" for="input01">Nome</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level"  id="nome" value="#{botao.botao.nome}" title="Nome" required="true" requiredMessage="The nome field is required." />                  
                            </div>
                        </div> 

                        <div class="control-group">
                            <label class="control-label" for="input01">Enviar eMail</label>
                            <div class="controls">
                                <h:selectBooleanCheckbox id="test" value="#{buttonWorker.funcao}" title="Enviar Email" />
                            </div>
                        </div>  
                            
                            <div class="control-group">
                            <label class="control-label" for="input01">Assunto do eMail</label>
                            <div class="controls">
                                <h:inputText styleClass="form-signin input-block-level"  id="texto2" value="#{buttonWorker.assunto}" title="assunto" />  
                            </div>
                        </div> 

                        <div class="control-group">
                            <label class="control-label" for="input01">Texto do eMail</label>
                            <div class="controls">
                                <h:inputTextarea  styleClass="input-xxlarge" rows="6" cols="100" id="texto" value="#{botao.botao.texto}" title="Texto" />
                            </div>
                        </div> 

                        <div class="control-group">
                            <label class="control-label" for="input01">Mudar de estado</label>
                            <div class="controls">
                                <h:selectBooleanCheckbox  id="test2" value="#{buttonWorker.funcao2}" title="Enviar Email" />
                            </div>
                        </div> 

                        <div class="control-group">
                            <label class="control-label" for="input01">(apenas se as quotas do Sócio estiverem em dia)</label>
                            <div class="controls">
                                <h:selectBooleanCheckbox  id="test20" value="#{buttonWorker.changeIf}" title="changeIf" />
                            </div>
                        </div>     
                            
                        <div class="control-group">
                            <label class="control-label" for="input01">Estado destino</label>
                            <div class="controls">
                                <h:selectOneMenu id="test3" value="#{buttonWorker.estado}" title="Estado">
                                    <f:selectItems value="#{estado.estadoItemsAvailableSelectOneGass}"/>
                                </h:selectOneMenu>
                            </div>
                        </div> 
                        <div class="control-group">
                            <label class="control-label" for="input01">Adiciona Pagamento</label>
                            <div class="controls">
                                <h:selectBooleanCheckbox id="test4" value="#{buttonWorker.addPay}" title="Adicionar Pagamento" />
                            </div>
                        </div> 
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <h:commandButton styleClass="btn btn-primary" action="#{botao.createGass}" value="Criar"/>
&nbsp;&nbsp;&nbsp;
                        

                    </fieldset>
                </h:form>
                    <h:form  styleClass="space">
                    <h:commandButton styleClass="btn"  action="#{estado.detailSetupGass}" value="Voltar">
                            <f:param name="jsfcrud.currentEstado" value="#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][estado.estado][estado.converter].jsfcrud_invoke}"/>
                        </h:commandButton>
                    </h:form>
            </section>
        </body>
    </html>
</f:view>
