package site.metacoding.junitproject.util;

// 추후에 Mail 클래스가 완성되면 코드를 완성하면 됨.
public class MailSenderAdapter implements MailSender {

    private Mail mail;

    public MailSenderAdapter() {
        this.mail = new Mail();
    }

    @Override
    public boolean send() {
        return mail.sendMail();
    }

}
