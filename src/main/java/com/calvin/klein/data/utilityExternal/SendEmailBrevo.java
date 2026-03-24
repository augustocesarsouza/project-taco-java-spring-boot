package com.calvin.klein.data.utilityExternal;

import com.calvin.klein.data.utilityExternal.Interface.ISendEmailBrevo;
import com.calvin.klein.domain.InfoErrors.InfoErrors;
import com.calvin.klein.domain.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class SendEmailBrevo implements ISendEmailBrevo {
    @Value("${BREVO-SECRET-KEY}")
    private String brevoSecretKey;

    public InfoErrors<String> sendEmail(User user, String url) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(brevoSecretKey);

        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail("augustocesarsantana53@gmail.com");
            sender.setName("augusto");
            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");
            Properties params = new Properties();
            params.setProperty("parameter", "My param value");
            params.setProperty("subject", "New Subject");
            to.setEmail(user.getEmail());
            to.setName(user.getName());
            toList.add(to);
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent(String.format("<html><body><h1>Clique no token disponivel: %s</h1></body></html>", url));
            sendSmtpEmail.setSubject("My {{params.subject}}");
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);

            return InfoErrors.Ok("Everything ok with sending the email");
        } catch (Exception e) {
            return InfoErrors.Fail("Erro no envio do email, ERROR: " + e.getMessage());
        }
    }
    @Override
    public InfoErrors<String> sendCode(User user, int codeRandom) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(brevoSecretKey);

        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail("augustocesarsantana53@gmail.com");
            sender.setName("augusto");
            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");
            Properties params = new Properties();
            params.setProperty("parameter", "My param value");
            params.setProperty("subject", "SEU NUMERO ALEATORIO DE CONFIRMAÇÃO");
            to.setEmail(user.getEmail());
            to.setName(user.getName());
            toList.add(to);
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();

            String TextContent = "CÓDIGO DE ACESSO: " + codeRandom;

            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent(String.format("<html><body><h1>CÓDIGO DE ACESSO:  %s</h1></body></html>", TextContent));
            sendSmtpEmail.setSubject("My {{params.subject}}");
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);

            return InfoErrors.Ok("Everything ok with sending the email");
        } catch (Exception e) {
            return InfoErrors.Fail("Erro no envio do email, ERROR: " + e.getMessage());
        }
    }

    @Override
    public InfoErrors<String> sendCodeString(int codeI, String email) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(brevoSecretKey);

        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            String codeS = String.valueOf(codeI);

            sender.setEmail("augustocesarsantana53@gmail.com");
            sender.setName("augusto");
            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");
            Properties params = new Properties();
            params.setProperty("parameter", "My param value");
            params.setProperty("subject", String.format("Sua chave de acesso é %s", codeS));
            to.setEmail(email);
//            to.setName(user.getName());
            toList.add(to);
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();

            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);

            int indexGet = email.indexOf('@');
            String nameString = email.substring(0, indexGet);

            String htmlContent = String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                <meta charset="UTF-8">
                <title>Código de Acesso</title>
                </head>
                <body style="margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;">
                
                <table align="center" width="100%%" cellpadding="0" cellspacing="0" style="max-width:600px;background-color:#ffffff;margin-top:40px;border-radius:8px;">
                
                    <tr>
                        <td align="center" style="font-size:40px;font-weight:bold;letter-spacing:4px;padding-bottom:30px;">
                            TACO
                        </td>
                    </tr>
                
                    <tr>
                        <td align="center" style="font-size:22px;padding-bottom:20px;">
                            CÓDIGO DE <strong>ACESSO</strong>
                        </td>
                    </tr>
                
                    <tr>
                        <td align="center" style="font-size:16px;color:#333;padding-bottom:10px;">
                            Olá, %s! Tudo bem?
                        </td>
                    </tr>
                
                    <tr>
                        <td align="center" style="font-size:16px;color:#333;padding-bottom:10px;">
                            Você solicitou uma chave de acesso na TACO.
                        </td>
                    </tr>
                
                    <tr>
                        <td align="center" style="font-size:16px;color:#333;padding-bottom:30px;">
                            Retorne ao site e digite o código:
                        </td>
                    </tr>
                
                    <tr>
                        <td align="center">
                            <div style="background-color:#000;color:#fff;
                                        display:inline-block;
                                        padding:15px 40px;
                                        font-size:28px;
                                        font-weight:bold;
                                        border-radius:6px;
                                        letter-spacing:3px;">
                                %s
                            </div>
                        </td>
                    </tr>
                
                    <tr>
                        <td align="center" style="padding-top:40px;font-size:12px;color:#777;">
                            Esse e-mail é enviado automaticamente e não recebe respostas.
                        </td>
                    </tr>
                
                </table>
                
                </body>
                </html>
                """, nameString, codeI);

            sendSmtpEmail.setHtmlContent(htmlContent);
            sendSmtpEmail.setSubject("{{params.subject}}");
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);

            return InfoErrors.Ok("Everything ok with sending the email");
        } catch (Exception e) {
            return InfoErrors.Fail("Erro no envio do email, ERROR: " + e.getMessage());
        }
    }
}
