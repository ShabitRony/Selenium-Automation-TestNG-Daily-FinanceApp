package utils;


import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {
    public static boolean checkEmail(String host, String storeType, String username, String password, String keywordInSubject) {
        try {
            // Set properties
            Properties properties = new Properties();
            properties.put("mail.store.protocol", storeType);
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.ssl.enable", "true");

            // Connect to email
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore(storeType);
            store.connect(host, username, password);

            // Open inbox folder
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // Get unseen (unread) messages
            Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (int i = messages.length - 1; i >= 0; i--) {
                String subject = messages[i].getSubject();
                if (subject != null && subject.contains(keywordInSubject)) {
                    System.out.println("Found email with subject: " + subject);
                    return true;
                }
            }

            emailFolder.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String fetchResetPasswordLink(String host, String storeType, String username, String password, String keywordInSubject) {
        long timeout = System.currentTimeMillis() + 60000;

        while (System.currentTimeMillis() < timeout) {
            Store store = null;
            Folder emailFolder = null;

            try {
                Properties properties = new Properties();
                properties.put("mail.store.protocol", storeType);
                properties.put("mail.imap.host", host);
                properties.put("mail.imap.port", "993");
                properties.put("mail.imap.ssl.enable", "true");

                Session emailSession = Session.getDefaultInstance(properties);
                emailSession.setDebug(true);

                store = emailSession.getStore(storeType);
                store.connect(host, username, password);

                emailFolder = store.getFolder("INBOX");
                emailFolder.open(Folder.READ_WRITE);

                Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

                for (int i = messages.length - 1; i >= 0; i--) {
                    Message message = messages[i];
                    String subject = message.getSubject();

                    if (subject != null && subject.contains(keywordInSubject)) {
                        String content = getTextFromMessage(message);

                        // Extract first URL from content
                        Pattern linkPattern = Pattern.compile("https?://\\S+");
                        Matcher matcher = linkPattern.matcher(content);
                        if (matcher.find()) {
                            return matcher.group();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (emailFolder != null && emailFolder.isOpen()) emailFolder.close(false);
                    if (store != null) store.close();
                } catch (MessagingException ignore) {}
            }

            try {
                Thread.sleep(5000); // wait 5 seconds before retry
            } catch (InterruptedException ignored) {}
        }

        return null; // No link found
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < mimeMultipart.getCount(); i++) {
                BodyPart part = mimeMultipart.getBodyPart(i);
                if (part.isMimeType("text/plain")) {
                    result.append(part.getContent());
                }
            }
            return result.toString();
        }
        return "";
    }
}
