/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Carlos Manuel
 */
public class beanWorker {

    public static Object getTheBean(String exp) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, exp);
    }

        //titas man
    public static boolean validarNif(int nif) {

        //convert nif in string
        String NIF;
        NIF = String.valueOf(nif);

        char c;
        int checkDigit;
        //Verifica se é nulo, se é numérico e se tem 9 dígitos
        if (NIF != null && IsNumeric(NIF) && NIF.length() == 9) {
            //Obtem o primeiro número do NIF
            c = NIF.charAt(0);
            //Verifica se o primeiro número é (1, 2, 5, 6, 8 ou 9)
            if (c == '1' || c == '2' || c == '5' || c == '6' || c == '8' || c == '9') {
                //Calculo do Digito de Controlo
                //c e um char, nao um int, dai fazermos o parse
                checkDigit = Integer.parseInt(String.valueOf(c)) * 9;

                //percorrer todos os digitos ate ao fim (i=8) desde i=2
                for (int i = 2; i <= 8; i++) {
                    checkDigit += Integer.parseInt(String.valueOf(NIF.charAt(i - 1))) * (10 - i);
                }
                checkDigit = 11 - (checkDigit % 11);

                //Se o digito de controlo é maior ou igual a dez, coloca-o a zero
                if (checkDigit >= 10) {
                    checkDigit = 0;
                }

                //Compara o digito de controlo com o último 
                //Se igual, o NIF é válido. (nao esquecer que indice comeca em 0, dai o ultimo ser = 8)
                if (checkDigit == Integer.parseInt(String.valueOf(NIF.charAt(8)))) {
                    return true;
                }
            }
        }
        return false;
    }

    //funcao que verifica se um determinado nmr e numerico ou nao
    public static boolean IsNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    
    //<Rui
    public static String encodePass(String pass) {
        byte[] hashed;
        StringBuilder encripted = new StringBuilder();

        try {
            encripted.delete(0, encripted.length());
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());
            hashed = md.digest();

            //HEXA
            for (int i = 0; i < hashed.length; i++) {
                int parteAlta = ((hashed[i] >> 4) & 0xf) << 4;
                int parteBaixa = hashed[i] & 0xf;
                if (parteAlta == 0) {
                    encripted.append('0');
                }
                encripted.append(Integer.toHexString(parteAlta | parteBaixa));
            }

            return encripted.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    

    public static void createPages(int nif, String url) {
        //File fout = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\GASSv12\\build\\web\\CandyCriar" + url + ".jsp");
        File fout = new File("/opt/glassfish3/glassfish/domains/domain1/applications/GASSv12/CandyCriar"+url+".jsp");         

      //  File fout = new File("/home/ana/GASSv12/web/CandyCriar" + url + ".jsp");


        try {
            PrintWriter pw = new PrintWriter(fout);
            pw.println("<%@page contentType=\"text/html\"%>\n"
                    + "<%@page pageEncoding=\"UTF-8\"%>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/core\" prefix=\"f\" %>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/html\" prefix=\"h\" %>\n"
                    + "<f:view>\n"
                    + "    <html>\n"
                    + "        <head>\n"
                    + "            <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "            <title>Página de Inscricao</title>\n"
                    + "            <script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                    + "            <script type=\"text/javascript\" src=\"/GASSv12/faces/bootstrap.js\"></script>\n"
                    + "            <script type=\"text/javascript\" src=\"/GASSv12/faces/bootstrap.min.js\"></script>\n"
                    + "            <%--\n"
                    + "            <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap.js\" rel=\"stylesheet\">\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap.min.js\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "            --%>\n"
                    + "            <!-- Le styles -->\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap.css\" rel=\"stylesheet\">\n"
                    + "            <style type=\"text/css\">\n"
                    + "                body {\n"
                    + "                    padding-top: 60px;\n"
                    + "                    padding-bottom: 40px;\n"
                    + "                }\n"
                    + "                .bold{\n"
                    + "                    font-size: 20px;\n"
                    + "                    font-weight: bold;\n"
                    + "                }\n"
                    + "                .form-signin {\n"
                    + "                    max-width: 300px;\n"
                    + "                    padding: 19px 29px 29px;\n"
                    + "                    margin: 0 auto 20px;\n"
                    + "                    background-color: #fff;\n"
                    + "                    border: 1px solid #e5e5e5;\n"
                    + "                    -webkit-border-radius: 5px;\n"
                    + "                    -moz-border-radius: 5px;\n"
                    + "                    border-radius: 5px;\n"
                    + "                    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                    box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                }\n"
                    + "                .form-signin .form-signin-heading,\n"
                    + "                .form-signin .checkbox {\n"
                    + "                    margin-bottom: 10px;\n"
                    + "                }\n"
                    + "                .form-signin input[type=\"text\"],\n"
                    + "                .form-signin input[type=\"password\"] {\n"
                    + "                    font-size: 16px;\n"
                    + "                    height: auto;\n"
                    + "                    margin-bottom: 15px;\n"
                    + "                    padding: 7px 9px;\n"
                    + "                }\n"
                    + "            </style>\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap-responsive.css\" rel=\"stylesheet\">\n"
                    + "            <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n"
                    + "            <!--[if lt IE 9]>\n"
                    + "            <script src=\"../assets/js/html5shiv.js\"></script>\n"
                    + "            <![endif]-->\n"
                    + "            <!-- Fav and touch icons -->\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"../assets/ico/apple-touch-icon-144-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"../assets/ico/apple-touch-icon-114-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"../assets/ico/apple-touch-icon-72-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" href=\"../assets/ico/apple-touch-icon-57-precomposed.png\">\n"
                    + "            <link rel=\"shortcut icon\" href=\"../assets/ico/favicon.png\">\n"
                    + "            <script>\n"
                    + "                $('.dropdown-toggle').dropdown();\n"
                    + "                function popup() {\n"
                    + "                    var x = confirm(\"Tem a certeza?\");\n"
                    + "                    return x;\n"
                    + "                }\n"
                    + "            </script>\n"
                    + "            <script>\n"
                    + "                function popupQuotas() {\n"
                    + "                    var x = confirm(\"Tem a certeza que pretende eliminar os pagamentos selecionados?\");\n"
                    + "                    return x;\n"
                    + "                }\n"
                    + "            </script>\n"
                    + "        </head>\n"
                    + "        <body>\n"
                    + "            <h:panelGroup id=\"messagePanel\" layout=\"block\">\n"
                    + "                <h:messages errorStyle=\"color: red\" infoStyle=\"color: green\" layout=\"table\"/>\n"
                    + "            </h:panelGroup>\n"
                    + "            <div class=\"page-header\">\n"
                    + "                <h1> &nbsp Inscricao</h1>\n"
                    + "            </div>\n"
                    + "            <div class=\"row-fluid\">\n"
                    + "                <div class=\"span4\">\n"
                    + "                    <div class=\"well\">\n"
                    + "                        <h:form styleClass=\"jsfcrud_list_form\"> \n"
                    + "                            <h:inputHidden id=\"validateCreateField\" validator=\"#{candidato.validateCreate}\" value=\"value\"/>\n"
                    + "                            <h:panelGrid columns=\"2\" styleClass=\"table table-hover\" > \n"
                    + "                                <h:outputText value=\"eMail *:\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"email\" value=\"#{candidato.candidato.candidatoPK.email}\" title=\"Email\" required=\"true\" requiredMessage=\"The email field is required.\" />\n"
                    + "                                <h:outputText value=\"Palavra Passe *:\"/>\n"
                    + "                                <h:inputSecret styleClass=\"form-signin input-block-level\" id=\"palavraPasse\" value=\"#{candidato.candidato.palavraPasse}\" title=\"PalavraPasse\" required=\"true\" requiredMessage=\"The palavraPasse field is required.\" />\n"
                    + "                                <h:outputText value=\"Confirme a Palavra Passe *:\"/>\n"
                    + "                                <h:inputSecret styleClass=\"form-signin input-block-level\" id=\"palavraPasse1\" value=\"#{stringWorker.pw}\" title=\"PalavraPasse1\" required=\"true\" requiredMessage=\"The palavraPasse field is required.\" />\n"
                    + "                                <h:outputText value=\"Nome *:\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"nome\" value=\"#{candidato.candidato.nome}\" title=\"Nome\" />\n"
                    + "                                <h:outputText value=\"Data de Nascimento (dd/mm/yyyy)*\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"dataNasc\" value=\"#{candidato.candidato.dataNasc}\" title=\"DataNasc\" >\n"
                    + "                                    <f:convertDateTime pattern=\"dd/MM/yyyy\" />\n"
                    + "                                </h:inputText>\n"
                    + "                                <h:outputText value=\"Telemovel:\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"telemovel\" value=\"#{candidato.candidato.telemovel}\" title=\"Telemovel\" />\n"
                    + "                            </h:panelGrid>\n"
                    + "                            <br />\n"
                    + "                            <h:outputText value=\"Campos marcados com * sao obrigatorios.\"/>\n"
                    + "                            <br />\n"
                    + "                            <br/>\n"
                    + "                            <h:commandButton styleClass=\"btn btn-primary\" action=\"#{candidato.createSoss(" + nif + ")}\" value=\"Registar conta\"/>\n"
                    + "                        </h:form>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </body>\n"
                    + "    </html>\n"
                    + "</f:view>");
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

       fout = new File("/opt/glassfish3/glassfish/domains/domain1/applications/GASSv12/BackEndLogin"+url+".jsp");         
      //  fout = new File("/home/ana/GASSv12/web/BackEndLogin" + url + ".jsp");
      //   fout = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\GASSv12\\build\\web\\BackEndLogin" + url + ".jsp");
        try {
            PrintWriter pw = new PrintWriter(fout);
            pw.println("<%@page contentType=\"text/html\"%>\n"
                    + "<%@page pageEncoding=\"UTF-8\"%>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/core\" prefix=\"f\" %>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/html\" prefix=\"h\" %>\n"
                    + "<f:view>\n"
                    + "\n"
                    + "    <html>\n"
                    + "        <head>\n"
                    + "            <meta charset=\"utf-8\">\n"
                    + "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "            <title>BackEndLogin</title>\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap.css\" rel=\"stylesheet\">\n"
                    + "            <style type=\"text/css\">\n"
                    + "                body {\n"
                    + "                    padding-top: 40px;\n"
                    + "                    padding-bottom: 40px;\n"
                    + "                    background-color: #f5f5f5;\n"
                    + "                }\n"
                    + "\n"
                    + "                .form-signin {\n"
                    + "                    max-width: 300px;\n"
                    + "                    padding: 19px 29px 29px;\n"
                    + "                    margin: 0 auto 20px;\n"
                    + "                    background-color: #fff;\n"
                    + "                    border: 1px solid #e5e5e5;\n"
                    + "                    -webkit-border-radius: 5px;\n"
                    + "                    -moz-border-radius: 5px;\n"
                    + "                    border-radius: 5px;\n"
                    + "                    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                    box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                }\n"
                    + "                .form-signin .form-signin-heading,\n"
                    + "                .form-signin .checkbox {\n"
                    + "                    margin-bottom: 10px;\n"
                    + "                }\n"
                    + "                .form-signin input[type=\"text\"],\n"
                    + "                .form-signin input[type=\"password\"] {\n"
                    + "                    font-size: 16px;\n"
                    + "                    height: auto;\n"
                    + "                    margin-bottom: 15px;\n"
                    + "                    padding: 7px 9px;\n"
                    + "                }\n"
                    + "\n"
                    + "            </style>\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap-responsive.css\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "            <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n"
                    + "            <!--[if lt IE 9]>\n"
                    + "              <script src=\"../assets/js/html5shiv.js\"></script>\n"
                    + "            <![endif]-->\n"
                    + "\n"
                    + "            <!-- Fav and touch icons -->\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"../assets/ico/apple-touch-icon-144-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"../assets/ico/apple-touch-icon-114-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"../assets/ico/apple-touch-icon-72-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" href=\"../assets/ico/apple-touch-icon-57-precomposed.png\">\n"
                    + "            <link rel=\"shortcut icon\" href=\"../assets/ico/favicon.png\">\n"
                    + "        </head>\n"
                    + "\n"
                    + "\n"
                    + "        <body>\n"
                    + "            <h:panelGroup id=\"messagePanel\" layout=\"block\">\n"
                    + "                <h:messages errorStyle=\"color: red\" infoStyle=\"color: green\" layout=\"table\"/>\n"
                    + "            </h:panelGroup>\n"
                    + "\n"
                    + "\n"
                    + "            <div class=\"container\">\n"
                    + "                <h:form styleClass=\"form-signin\">\n"
                    + "                    <h2 class=\"form-signin-heading\">LogIn Secretariado</h2>\n"
                    + "                    <h:inputHidden id=\"validateCreateField\" validator=\"#{candidato.validateCreate}\" value=\"value\"/> \n"
                    + "\n"
                    + "                    <h:outputText styleClass=\"input-block-level\" value=\"Email\"/>\n"
                    + "                    <h:inputText  styleClass=\"form-signin\" id=\"email\" value=\"#{candidato.candidato.candidatoPK.email}\" title=\"Email\" required=\"true\" requiredMessage=\"The email field is required.\" />\n"
                    + "                    <h:outputText styleClass=\"input-block-level\" value=\"Password\"/>\n"
                    + "                    <h:inputSecret styleClass=\"form-signin\" id=\"palavraPasse\" value=\"#{candidato.candidato.palavraPasse}\" title=\"PalavraPasse\" required=\"true\" requiredMessage=\"The palavraPasse field is required.\" />\n"
                    + "                    <br />\n"
                    + "\n"
                    + "                    <br />\n"
                    + "                    <h:commandButton styleClass=\"btn btn-large btn-primary\" action=\"#{candidato.loginSecretariado(" + nif + ")}\" type=\"submit\" value=\"Log-In\">\n"
                    + "                        <%--<f:param name=\"jsfcrud.currentCandidato\" value=\"#{jsfcrud_class['entitiesGASS.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][candidato.candidato][candidato.converter].jsfcrud_invoke}\" />\n"
                    + "                        --%>\n"
                    + "                    </h:commandButton>\n"
                    + "                    <br />\n"
                    + "                    <br />\n"
                    + "\n"
                    + "                </h:form>\n"
                    + "            </div>\n"
                    + "\n"
                    + "\n"
                    + "            <script src=\"../assets/js/jquery.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-transition.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-alert.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-modal.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-dropdown.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-scrollspy.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-tab.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-tooltip.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-popover.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-button.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-collapse.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-carousel.js\"></script>\n"
                    + "            <script src=\"../assets/js/bootstrap-typeahead.js\"></script>\n"
                    + "        </body>\n"
                    + "\n"
                    + "    </html>\n"
                    + "</f:view>");
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

       fout = new File("/opt/glassfish3/glassfish/domains/domain1/applications/GASSv12/Login"+url+".jsp");         
        //fout = new File("/home/ana/GASSv12/web/Login" + url + ".jsp");
      //   fout = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\GASSv12\\build\\web\\Login" + url + ".jsp");
        try {
            PrintWriter pw = new PrintWriter(fout);
            pw.println("<%@page contentType=\"text/html\"%>\n"
                    + "<%@page pageEncoding=\"UTF-8\"%>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/core\" prefix=\"f\" %>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/html\" prefix=\"h\" %>\n"
                    + "<f:view>\n"
                    + "    <html>\n"
                    + "        <meta charset=\"utf-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <title>LogIn</title>\n"
                    + "        <link href=\"/GASSv12/faces/bootstrap.css\" rel=\"stylesheet\">\n"
                    + "        <style type=\"text/css\">\n"
                    + "            body {\n"
                    + "                padding-top: 40px;\n"
                    + "                padding-bottom: 40px;\n"
                    + "                background-color: #f5f5f5;\n"
                    + "            }\n"
                    + "            .form-signin {\n"
                    + "                max-width: 300px;\n"
                    + "                padding: 19px 29px 29px;\n"
                    + "                margin: 0 auto 20px;\n"
                    + "                background-color: #fff;\n"
                    + "                border: 1px solid #e5e5e5;\n"
                    + "                -webkit-border-radius: 5px;\n"
                    + "                -moz-border-radius: 5px;\n"
                    + "                border-radius: 5px;\n"
                    + "                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "            }\n"
                    + "            .form-signin .form-signin-heading,\n"
                    + "            .form-signin .checkbox {\n"
                    + "                margin-bottom: 10px;\n"
                    + "            }\n"
                    + "            .form-signin input[type=\"text\"],\n"
                    + "            .form-signin input[type=\"password\"] {\n"
                    + "                font-size: 16px;\n"
                    + "                height: auto;\n"
                    + "                margin-bottom: 15px;\n"
                    + "                padding: 7px 9px;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "        <link href=\"/GASSv12/faces/bootstrap-responsive.css\" rel=\"stylesheet\">\n"
                    + "        <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"../assets/ico/apple-touch-icon-144-precomposed.png\">\n"
                    + "        <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"../assets/ico/apple-touch-icon-114-precomposed.png\">\n"
                    + "        <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"../assets/ico/apple-touch-icon-72-precomposed.png\">\n"
                    + "        <link rel=\"apple-touch-icon-precomposed\" href=\"../assets/ico/apple-touch-icon-57-precomposed.png\">\n"
                    + "        <link rel=\"shortcut icon\" href=\"../assets/ico/favicon.png\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <h:panelGroup id=\"messagePanel\" layout=\"block\">\n"
                    + "            <h:messages errorStyle=\"color: red\" infoStyle=\"color: green\" layout=\"table\"/>\n"
                    + "        </h:panelGroup>\n"
                    + "        <div class=\"container\">\n"
                    + "            <h:form styleClass=\"form-signin\">\n"
                    + "                <h2 class=\"form-signin-heading\">Login</h2>\n"
                    + "                <h:inputHidden id=\"validateCreateField\" validator=\"#{candidato.validateCreate}\" value=\"value\"/> \n"
                    + "                <h:outputText styleClass=\"input-block-level\" value=\"Email \"/>\n"
                    + "                <h:inputText styleClass=\"form-signin\" id=\"email\" value=\"#{candidato.candidato.candidatoPK.email}\" title=\"Email\" required=\"true\" requiredMessage=\"The email field is required.\" />\n"
                    + "                <h:outputText styleClass=\"input-block-level\" value=\"Password \"/>\n"
                    + "                <h:inputSecret styleClass=\"form-signin\" id=\"palavraPasse\" value=\"#{candidato.candidato.palavraPasse}\" title=\"PalavraPasse\" required=\"true\" requiredMessage=\"The palavraPasse field is required.\" />\n"
                    + "                <br />\n"
                    + "                <h:commandButton styleClass=\"btn btn-large btn-primary\" type=\"submit\" action=\"#{candidato.loginCandy(" + nif + ")}\" value=\"Log-In\" />\n"
                    + "                <br />\n"
                    + "                <br />\n"
                    + "\n"
                    + "            </h:form>\n"
                    + "            <h:form styleClass=\"form-signin\">\n"
                    + "                <h:inputHidden id=\"validateCreateField\" validator=\"#{candidato.validateCreate}\" value=\"value\"/> \n"
                    + "                <h:outputText styleClass=\"input-block-level\" value=\"Email \"/>\n"
                    + "                <h:inputText styleClass=\"form-signin\" id=\"email\" value=\"#{candidato.candidato.candidatoPK.email}\" title=\"Email\" required=\"true\" requiredMessage=\"The email field is required.\" />\n"
                    + "\n"
                    + "                <h:commandButton styleClass=\"btn\" type=\"submit\" action=\"#{candidato.recuperarPass(" + nif + ")}\" value=\"Recuperar Pass\" />\n"
                    + "                <br />\n"
                    + "                <br />\n"
                    + "            </h:form>\n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "</f:view>\n"
                    + "");
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

       fout = new File("/opt/glassfish3/glassfish/domains/domain1/applications/GASSv12/SocioCriar"+url+".jsp");         
       // fout = new File("/home/ana/GASSv12/web/SocioCriar" + url + ".jsp");
       //  fout = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\GASSv12\\build\\web\\SocioCriar" + url + ".jsp");
        try {
            PrintWriter pw = new PrintWriter(fout);
            pw.println("<%@page contentType=\"text/html\"%>\n"
                    + "<%@page pageEncoding=\"UTF-8\"%>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/core\" prefix=\"f\" %>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/html\" prefix=\"h\" %>\n"
                    + "<f:view>\n"
                    + "    <html>\n"
                    + "        <html>\n"
                    + "            <head>\n"
                    + "                <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "                <title>Página de Inscricao</title>\n"
                    + "                <script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                    + "                <script type=\"text/javascript\" src=\"/GASSv12/faces/bootstrap.js\"></script>\n"
                    + "                <script type=\"text/javascript\" src=\"/GASSv12/faces/bootstrap.min.js\"></script>\n"
                    + "                <%--\n"
                    + "                <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                    + "                <link href=\"/GASSv12/faces/bootstrap.js\" rel=\"stylesheet\">\n"
                    + "                <link href=\"/GASSv12/faces/bootstrap.min.js\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "                --%>\n"
                    + "                <!-- Le styles -->\n"
                    + "                <link href=\"/GASSv12/faces/bootstrap.css\" rel=\"stylesheet\">\n"
                    + "                <style type=\"text/css\">\n"
                    + "                    body {\n"
                    + "                        padding-top: 60px;\n"
                    + "                        padding-bottom: 40px;\n"
                    + "                    }\n"
                    + "                    .bold{\n"
                    + "                        font-size: 20px;\n"
                    + "                        font-weight: bold;\n"
                    + "                    }\n"
                    + "                    .form-signin {\n"
                    + "                        max-width: 300px;\n"
                    + "                        padding: 19px 29px 29px;\n"
                    + "                        margin: 0 auto 20px;\n"
                    + "                        background-color: #fff;\n"
                    + "                        border: 1px solid #e5e5e5;\n"
                    + "                        -webkit-border-radius: 5px;\n"
                    + "                        -moz-border-radius: 5px;\n"
                    + "                        border-radius: 5px;\n"
                    + "                        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                        -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                        box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                    }\n"
                    + "                    .form-signin .form-signin-heading,\n"
                    + "                    .form-signin .checkbox {\n"
                    + "                        margin-bottom: 10px;\n"
                    + "                    }\n"
                    + "                    .form-signin input[type=\"text\"],\n"
                    + "                    .form-signin input[type=\"password\"] {\n"
                    + "                        font-size: 16px;\n"
                    + "                        height: auto;\n"
                    + "                        margin-bottom: 15px;\n"
                    + "                        padding: 7px 9px;\n"
                    + "                    }\n"
                    + "                </style>\n"
                    + "                <link href=\"/GASSv12/faces/bootstrap-responsive.css\" rel=\"stylesheet\">\n"
                    + "                <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n"
                    + "                <!--[if lt IE 9]>\n"
                    + "                <script src=\"../assets/js/html5shiv.js\"></script>\n"
                    + "                <![endif]-->\n"
                    + "                <!-- Fav and touch icons -->\n"
                    + "                <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"../assets/ico/apple-touch-icon-144-precomposed.png\">\n"
                    + "                <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"../assets/ico/apple-touch-icon-114-precomposed.png\">\n"
                    + "                <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"../assets/ico/apple-touch-icon-72-precomposed.png\">\n"
                    + "                <link rel=\"apple-touch-icon-precomposed\" href=\"../assets/ico/apple-touch-icon-57-precomposed.png\">\n"
                    + "                <link rel=\"shortcut icon\" href=\"../assets/ico/favicon.png\">\n"
                    + "                <script>\n"
                    + "                    $('.dropdown-toggle').dropdown();\n"
                    + "                    function popup() {\n"
                    + "                        var x = confirm(\"Tem a certeza?\");\n"
                    + "                        return x;\n"
                    + "                    }\n"
                    + "                </script>\n"
                    + "                <script>\n"
                    + "                    function popupQuotas() {\n"
                    + "                        var x = confirm(\"Tem a certeza que pretende eliminar os pagamentos selecionados?\");\n"
                    + "                        return x;\n"
                    + "                    }\n"
                    + "                </script>\n"
                    + "            </head>\n"
                    + "            <body>\n"
                    + "                <h:panelGroup id=\"messagePanel\" layout=\"block\">\n"
                    + "                    <h:messages errorStyle=\"color: red\" infoStyle=\"color: green\" layout=\"table\"/>\n"
                    + "                </h:panelGroup>\n"
                    + "                <div class=\"page-header\">\n"
                    + "                    <h1> &nbsp Registar Socio</h1>\n"
                    + "                </div>\n"
                    + "                <div class=\"row-fluid\">\n"
                    + "                    <div class=\"span4\">\n"
                    + "                        <div class=\"well\">\n"
                    + "                            <h:form styleClass=\"jsfcrud_list_form\"> \n"
                    + "                                <h:inputHidden id=\"validateCreateField\" validator=\"#{socio.validateCreate}\" value=\"value\"/>\n"
                    + "                                <h:panelGrid columns=\"2\" styleClass=\"table table-hover\" > \n"
                    + "                                    <h:outputText value=\"Nif: *\"/>\n"
                    + "                                    <h:inputText styleClass=\"form-signin input-block-level\" id=\"nif\" value=\"#{socioWorker.nif}\" title=\"Nif\" required=\"true\" requiredMessage=\"The nif field is required.\" />\n"
                    + "                                    <h:outputText value=\"eMail: *\"/>\n"
                    + "                                    <h:inputText styleClass=\"form-signin input-block-level\" id=\"emailz\" value=\"#{socioWorker.email}\" title=\"Tipo\" required=\"true\" requiredMessage=\"The tipo field is required.\" />\n"
                    + "                                    <h:outputText value=\"Password: *\"/>\n"
                    + "                                    <h:inputSecret styleClass=\"form-signin input-block-level\" id=\"pass\" value=\"#{socioWorker.password}\" title=\"Pw\" required=\"true\" requiredMessage=\"The tipo field is required.\" />\n"
                    + "                                    <%-- \n"
                    + "                                    <h:outputText value=\"Tipo: *\"/>\n"
                    + "                                    <h:inputText id=\"tipo\" value=\"#{socioWorker.password}\" title=\"Pw\" required=\"true\" requiredMessage=\"The tipo field is required.\" />\n"
                    + "                                    --%>\n"
                    + "                                    <h:outputText value=\"Tipo: *\"/>\n"
                    + "                                    <h:selectOneMenu id=\"tipo2\" value=\"#{socioWorker.tipoSocio}\" title=\"Associacao\" required=\"true\" requiredMessage=\"The tiposocio field is required.\" >\n"
                    + "                                        <f:selectItems value=\"#{socioWorker.tipoItemsAvailableSelectOne(" + nif + ")}\"/>\n"
                    + "                                    </h:selectOneMenu>\n"
                    + "                                    <%--\n"
                    + "                                    <h:outputText value=\"Associacao *:\"/>\n"
                    + "                                    <h:selectOneMenu styleClass=\"select\" onmouseover=\"this.style.backgroundColor='burlywood'\" onmouseout=\"this.style.backgroundColor='white'\" id=\"associacao\" value=\"#{socioWorker.assoc}\" title=\"Associacao\" required=\"true\" requiredMessage=\"The associacao field is required.\" >\n"
                    + "                                    <f:selectItems value=\"#{associacao.associacaoItemsAvailableSelectOne}\"/>\n"
                    + "                                    </h:selectOneMenu>\n"
                    + "                                    --%>\n"
                    + "                                    <%--\n"
                    + "                                    <h:outputText value=\"ID Assoc:\"/>\n"
                    + "                                    <h:inputText id=\"idassoc\" value=\"#{socioWorker.idAssoc}\" title=\"ID Assoc\" required=\"true\" requiredMessage=\"The ID Assoc field is required.\" />\n"
                    + "                                    --%>\n"
                    + "                                </h:panelGrid>\n"
                    + "                                <br/>\n"
                    + "                                <h:outputText value=\"Campos marcados com * sao obrigatorios.\"/>\n"
                    + "                                <br />\n"
                    + "                                <br/>\n"
                    + "                                <h:commandButton styleClass=\"btn btn-primary\" action=\"#{socio.createSoss(" + nif + ")}\" value=\"Registar\"/>\n"
                    + "                                <br />\n"
                    + "                                <br />\n"
                    + "                            </h:form>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </body>\n"
                    + "        </html>\n"
                    + "    </f:view>");
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

      fout = new File("/opt/glassfish3/glassfish/domains/domain1/applications/GASSv12/CriarCanSoc"+url+".jsp");         
      //  fout = new File("/home/ana/GASSv12/web/CriarCanSoc" + url + ".jsp");
      //    fout = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\GASSv12\\build\\web\\CriarCanSoc" + url + ".jsp");
        try {
            PrintWriter pw = new PrintWriter(fout);
            pw.println("<%@page contentType=\"text/html\"%>\n"
                    + "<%@page pageEncoding=\"UTF-8\"%>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/core\" prefix=\"f\" %>\n"
                    + "<%@taglib uri=\"http://java.sun.com/jsf/html\" prefix=\"h\" %>\n"
                    + "<f:view>\n"
                    + "    <html>\n"
                    + "        <head>\n"
                    + "            <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "            <title>Inscriçao</title>\n"
                    + "            <script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                    + "            <script type=\"text/javascript\" src=\"/GASSv12/faces/bootstrap.js\"></script>\n"
                    + "            <script type=\"text/javascript\" src=\"/GASSv12/faces/bootstrap.min.js\"></script>\n"
                    + "            <%--\n"
                    + "            <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                    + "             <link href=\"/GASSv12/faces/bootstrap.js\" rel=\"stylesheet\">\n"
                    + "     <link href=\"/GASSv12/faces/bootstrap.min.js\" rel=\"stylesheet\">\n"
                    + "            \n"
                    + "            --%>\n"
                    + "            <!-- Le styles -->\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap.css\" rel=\"stylesheet\">\n"
                    + "            <style type=\"text/css\">\n"
                    + "                body {\n"
                    + "                    padding-top: 60px;\n"
                    + "                    padding-bottom: 40px;\n"
                    + "                }\n"
                    + "\n"
                    + "                .bold{\n"
                    + "                    font-size: 20px;\n"
                    + "                    font-weight: bold;\n"
                    + "\n"
                    + "\n"
                    + "                }\n"
                    + "\n"
                    + "                .form-signin {\n"
                    + "                    max-width: 300px;\n"
                    + "                    padding: 19px 29px 29px;\n"
                    + "                    margin: 0 auto 20px;\n"
                    + "                    background-color: #fff;\n"
                    + "                    border: 1px solid #e5e5e5;\n"
                    + "                    -webkit-border-radius: 5px;\n"
                    + "                    -moz-border-radius: 5px;\n"
                    + "                    border-radius: 5px;\n"
                    + "                    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                    box-shadow: 0 1px 2px rgba(0,0,0,.05);\n"
                    + "                }\n"
                    + "                .form-signin .form-signin-heading,\n"
                    + "                .form-signin .checkbox {\n"
                    + "                    margin-bottom: 10px;\n"
                    + "                }\n"
                    + "                .form-signin input[type=\"text\"],\n"
                    + "                .form-signin input[type=\"password\"] {\n"
                    + "                    font-size: 16px;\n"
                    + "                    height: auto;\n"
                    + "                    margin-bottom: 15px;\n"
                    + "                    padding: 7px 9px;\n"
                    + "                }\n"
                    + "\n"
                    + "            </style>\n"
                    + "            <link href=\"/GASSv12/faces/bootstrap-responsive.css\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "            <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n"
                    + "            <!--[if lt IE 9]>\n"
                    + "              <script src=\"../assets/js/html5shiv.js\"></script>\n"
                    + "            <![endif]-->\n"
                    + "\n"
                    + "            <!-- Fav and touch icons -->\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"../assets/ico/apple-touch-icon-144-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"../assets/ico/apple-touch-icon-114-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"../assets/ico/apple-touch-icon-72-precomposed.png\">\n"
                    + "            <link rel=\"apple-touch-icon-precomposed\" href=\"../assets/ico/apple-touch-icon-57-precomposed.png\">\n"
                    + "            <link rel=\"shortcut icon\" href=\"../assets/ico/favicon.png\">\n"
                    + "\n"
                    + "\n"
                    + "            <script>\n"
                    + "                $('.dropdown-toggle').dropdown();\n"
                    + "\n"
                    + "                function popup() {\n"
                    + "                    var x = confirm(\"Tem a certeza?\");\n"
                    + "                    return x;\n"
                    + "                }\n"
                    + "            </script>\n"
                    + "\n"
                    + "            <script>\n"
                    + "                function popupQuotas() {\n"
                    + "                    var x = confirm(\"Tem a certeza que pretende eliminar os pagamentos selecionados?\");\n"
                    + "                    return x;\n"
                    + "                }\n"
                    + "            </script>\n"
                    + "            <script>\n"
                    + "                function popupBotao() {\n"
                    + "                    var x = confirm(\"Tem a certeza que pretende eliminar os botões selecionados?\");\n"
                    + "                    return x;\n"
                    + "                }\n"
                    + "            </script>\n"
                    + "            <script>\n"
                    + "                function popupMetodo() {\n"
                    + "                    var x = confirm(\"Tem a certeza que pretende eliminar os métodos de passagem selecionados?\");\n"
                    + "                    return x;\n"
                    + "                }\n"
                    + "            </script>\n"
                    + "        </head>\n"
                    + "\n"
                    + "        <body>\n"
                    + "\n"
                    + "            <h:panelGroup id=\"messagePanel\" layout=\"block\">\n"
                    + "                <h:messages errorStyle=\"color: red\" infoStyle=\"color: green\" layout=\"table\"/>\n"
                    + "            </h:panelGroup>\n"
                    + "\n"
                    + "\n"
                    + "            <div class=\"page-header\">\n"
                    + "                <h1> &nbsp Registar Candidato e Socio</h1>\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <div class=\"row-fluid\">\n"
                    + "                <div class=\"span4\">\n"
                    + "                    <div class=\"well\">\n"
                    + "                        <h:form >\n"
                    + "\n"
                    + "                            <h:panelGrid columns=\"2\" styleClass=\"panel\">\n"
                    + "                                <h:outputText value=\"Email\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"email\" value=\"#{ocupaWorker.email}\" title=\"Email\" required=\"true\" requiredMessage=\"The email field is required.\" />\n"
                    + "                                <h:outputText value=\"Palavra Passe \"/>\n"
                    + "                                <h:inputSecret styleClass=\"form-signin input-block-level\" id=\"palavraPasse\" value=\"#{ocupaWorker.password}\" title=\"PalavraPasse\" required=\"true\" requiredMessage=\"The palavraPasse field is required.\" />\n"
                    + "                                <h:outputText value=\"Confirme a Palavra Passe\"/>\n"
                    + "                                <h:inputSecret styleClass=\"form-signin input-block-level\" id=\"palavraPasse1\" value=\"#{stringWorker.pw}\" title=\"PalavraPasse1\" required=\"true\" requiredMessage=\"The palavraPasse field is required.\" />\n"
                    + "                                <h:outputText value=\"Nome\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"nome\" value=\"#{ocupaWorker.nome}\" title=\"Nome\" />\n"
                    + "                                <h:outputText value=\"Data de Nascimento(dd/mm/yyyy)\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"dataNasc\" value=\"#{ocupaWorker.dataNasc}\" title=\"DataNasc\" >\n"
                    + "                                    <f:convertDateTime pattern=\"dd/MM/yyyy\" />\n"
                    + "                                </h:inputText>\n"
                    + "                                <h:outputText value=\"Telemovel\"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"telemovel\" value=\"#{ocupaWorker.telefone}\" title=\"Telemovel\" />\n"
                    + "\n"
                    + "                                <h:outputText value=\"Nif \"/>\n"
                    + "                                <h:inputText styleClass=\"form-signin input-block-level\" id=\"nif\" value=\"#{ocupaWorker.nif}\" title=\"Nif\" required=\"true\" requiredMessage=\"The nif field is required.\" />\n"
                    + "                                \n"
                    + "                                <h:outputText value=\"Tipo: \"/>\n"
                    + "                                <h:selectOneMenu  id=\"tipo2\" value=\"#{ocupaWorker.tipo}\" title=\"Associacao\" required=\"true\" requiredMessage=\"The tiposocio field is required.\" >\n"
                    + "                                    <f:selectItems value=\"#{ocupaWorker.tipoItemsAvailableSelectOne2("+nif+")}\"/>\n"
                    + "                                </h:selectOneMenu>\n"
                    + "\n"
                    + "                            </h:panelGrid>\n"
                    + "                            <br/>\n"
                    + "                            <h:commandButton styleClass=\"btn btn-primary\" action=\"#{socio.createGass2("+nif+")}\" value=\"Registar\"/>\n"
                    + "                        </h:form>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </body>\n"
                    + "    </html>\n"
                    + "</f:view>\n"
                    + "");

            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void doQuery(String tablename) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM " + tablename;

        Connection dbConnection;
        String strUrl = "jdbc:mysql://localhost:3330/gassv5?zeroDateTimeBehavior=convertToNull";
        Statement stmt;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(strUrl, "dave", "1234lol");
        stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int haha = rs.findColumn(tablename);
        Array tmp = rs.getArray(haha);

    }

    //private static String sendPort = "587";
    public static void send(String host, final String username,
            final String password, String recipient,
            String subject, String body, List<File> files, String sendPort)
            throws NoSuchProviderException, AddressException, MessagingException {

        //Define server properties.
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", sendPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.submitter", username);

        //start authentication.
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        Transport transport = session.getTransport();

        Message msg = new MimeMessage(session);
        //define addresses.
        InternetAddress addressFrom = new InternetAddress(username);
        msg.setFrom(addressFrom);

        InternetAddress addressTo = new InternetAddress(recipient);
        msg.setRecipient(Message.RecipientType.TO, addressTo);
        //set subject.
        msg.setSubject(subject);
        //create body with attachment support.
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        //add files.
        if (files != null && files.size() > 0) {
            for (File file : files) {
                MimeBodyPart attach = new MimeBodyPart();
                try {
                    attach.attachFile(file);
                } catch (IOException ex) {
                    // Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
                }
                multipart.addBodyPart(attach);
            }
        }

        msg.setContent(multipart);

        transport.connect();

        transport.sendMessage(msg,
                msg.getRecipients(Message.RecipientType.TO));

        transport.close();
    }
}
