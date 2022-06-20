import com.github.javafaker.Faker;


public class UserGenerator {
    public static DataUser userGenerate(String status) {
        return new DataUser(generateLogin(), generatePassword(), status);
    }

    public static String generatePassword() {
        Faker faker = new Faker();
        String login = faker.internet().password();
        return login;
    }

    public static String generateLogin() {
        Faker faker = new Faker();
        return faker.name().username();
    }
}
