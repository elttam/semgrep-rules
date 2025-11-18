package demo;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Folder;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.NewsAddress;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.InternetAddressEditor;

import jakarta.validation.constraints.Email;

import java.io.InputStream;

public class JakartaMailEmailPrimitivesDemo {

    //ruleid: hibernate-email-annotation-field
    @Email
    private String userEmail;

    public void demoInternetAddressConstructors() throws Exception {
        String raw = "(blah)";
        String addr = "user@example.com";
        String personal = "User Name";

        //ruleid: jakarta-mail-internetaddress-overloaded-ctor
        InternetAddress overloaded1 = new InternetAddress(raw, personal);
        //ruleid: jakarta-mail-internetaddress-overloaded-ctor
        InternetAddress overloaded2 = new InternetAddress(raw, personal, "UTF-8");
        //ruleid: jakarta-mail-internetaddress-single-arg-validator
        InternetAddress validated = new InternetAddress(addr);
    }

    public void demoMimeMessageUsage(Session session, Folder folder, InputStream is) throws Exception {
        //ruleid: jakarta-mail-mimemessage-raw-envelope-ctor
        MimeMessage sourceMessage = new MimeMessage(session);
        //ruleid: jakarta-mail-mimemessage-raw-envelope-ctor
        MimeMessage m1 = new MimeMessage(session, is);
        //ruleid: jakarta-mail-mimemessage-raw-envelope-ctor
        MimeMessage m2 = new MimeMessage(folder, is, 1);
        //ruleid: jakarta-mail-mimemessage-raw-envelope-ctor
        MimeMessage m3 = new MimeMessage(sourceMessage);

        //ruleid: jakarta-mail-mimemessage-newsgroups-recipients
        m1.getRecipients(MimeMessage.RecipientType.NEWSGROUPS);
    }

    public void demoSpringMailUsage() {
        //ruleid: spring-internetaddresseditor-usage
        InternetAddressEditor editor = new InternetAddressEditor();
        editor.setAsText("=?UTF-8?Q?Administrator_=3Cadmin@example.com=3E?= <attacker@evil.com>");

        SimpleMailMessage msg = new SimpleMailMessage();
        String from = "=?UTF-8?Q?Admin_=3Cadmin@example.com=3E?= <attacker@evil.com>";
        //ruleid: spring-simplemailmessage-setfrom-raw
        msg.setFrom(from);
    }

    public void demoNaiveDomainParsing() {
        String email = "\"foo@bar.com\"@example.com";

        //ruleid: email-domain-split-index
        String domain1 = email.split("@")[1];

        //ruleid: email-domain-lastindexof
        String domain2 = email.substring(email.lastIndexOf("@") + 1);

        String EMAIL_DOMAIN = "@trusted.com";
        //ruleid: email-domain-endswith
        if (email.endsWith(EMAIL_DOMAIN)) {
            grantStaffAccess();
        }
    }
}
