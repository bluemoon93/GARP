<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>LogIn</title>
        <link href="/GASSv12/faces/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
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
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="../assets/ico/favicon.png">
    </head>
    <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <div class="container">
            <h:form styleClass="form-signin">
                <h2 class="form-signin-heading">Login</h2>
                <h:inputHidden id="validateCreateField" validator="#{candidato.validateCreate}" value="value"/> 
                <h:outputText styleClass="input-block-level" value="Email "/>
                <h:inputText styleClass="form-signin" id="email" value="#{candidato.candidato.candidatoPK.email}" title="Email" required="true" requiredMessage="The email field is required." />
                <h:outputText styleClass="input-block-level" value="Password "/>
                <h:inputSecret styleClass="form-signin" id="palavraPasse" value="#{candidato.candidato.palavraPasse}" title="PalavraPasse" required="true" requiredMessage="The palavraPasse field is required." />
                <br />
                <h:commandButton styleClass="btn btn-large btn-primary" type="submit" action="#{candidato.loginCandy(1111)}" value="Log-In" />
                <br />
                <br />

            </h:form>
            <h:form styleClass="form-signin">
                <h:inputHidden id="validateCreateField" validator="#{candidato.validateCreate}" value="value"/> 
                <h:outputText styleClass="input-block-level" value="Email "/>
                <h:inputText styleClass="form-signin" id="email" value="#{candidato.candidato.candidatoPK.email}" title="Email" required="true" requiredMessage="The email field is required." />

                <h:commandButton styleClass="btn" type="submit" action="#{candidato.recuperarPass(1111)}" value="Recuperar Pass" />
                <br />
                <br />
            </h:form>
    </body>
</html>
</f:view>
