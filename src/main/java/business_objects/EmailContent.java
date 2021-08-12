package business_objects;

public class EmailContent {
    private final String addressee;
    private final String subject;
    private final String body;

    private EmailContent(EmailContentBuilder builder) {
        this.addressee = builder.addressee;
        this.subject = builder.subject;
        this.body = builder.body;
    }

    public String getAddressee() {
        return addressee;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public static class EmailContentBuilder {
        private final String addressee;
        private final String subject;
        private String body;

        public EmailContentBuilder(String addressee, String subject) {
            this.addressee = addressee;
            this.subject = subject;
        }

        public EmailContentBuilder body(String body) {
            this.body = body;
            return this;
        }

        public EmailContent build() {
            return new EmailContent(this);
        }
    }


}
