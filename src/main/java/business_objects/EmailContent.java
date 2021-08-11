package business_objects;

public class EmailContent {
    private static final String ADDRESSEE = "jan3doetest@yandex.com";
    private static final String SUBJECT = "test";
    private static final String BODY = "testtest";

    public static String getADDRESSEE() {
        return ADDRESSEE;
    }

    public static String getSUBJECT() {
        return SUBJECT;
    }

    public static String getBODY() {
        return BODY;
    }
}
