package business_objects;

public class TestEmailContent {

    public EmailContent buildEmailContent() {
        return new EmailContent.EmailContentBuilder("jan3doetest@yandex.com", "test")
                .body("testtest")
                .build();
    }
}
