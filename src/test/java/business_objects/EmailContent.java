package business_objects;

public class EmailContent {
    private static final String ADDRESSEE = "jan3doetest@yandex.com";
    private static final String SUBJECT = "test";
    private static final String BODY = "testtest";

    public String getAddressee() {
        return ADDRESSEE;
    }

    public String getSubject() {
        return SUBJECT;
    }

    public String getBody() {
        return BODY;
    }
}
