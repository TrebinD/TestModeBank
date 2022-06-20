
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class TestModeBankTest {
    @BeforeEach
    public void connect() {
        open("http://localhost:9999/");
    }

    @Test

    public void activUser() {
        DataUser user = UserGenerator.userGenerate("active");
        Register.setUpAll(user);
        $x("//input[@name= \"login\"]").setValue(user.getLogin());
        $x("//input[@name= \"password\"]").setValue(user.getPassword());
        $x("//button[@data-test-id=\"action-login\"]").click();
        $x("//h2").should(text("Личный кабинет"));

    }


    @Test
    public void blockUser() {
        DataUser user = UserGenerator.userGenerate("blocked");
        Register.setUpAll(user);
        $x("//input[@name= \"login\"]").setValue(user.getLogin());
        $x("//input[@name= \"password\"]").setValue(user.getPassword());
        $x("//button[@data-test-id=\"action-login\"]").click();
        $x("//div[@data-test-id = 'error-notification']").should(visible);
        $x("//div[@class = 'notification__content']").should(text("Пользователь заблокирован"));
    }

    @Test
    public void wrongLogin() {
        DataUser user = UserGenerator.userGenerate("blocked");
        Register.setUpAll(user);
        $x("//input[@name= \"login\"]").setValue("Login");
        $x("//input[@name= \"password\"]").setValue(user.getPassword());
        $x("//button[@data-test-id=\"action-login\"]").click();
        $x("//div[@class =\"notification__content\"] ").should(visible);
        $x("//div[@class =\"notification__content\"] ").should(text("Неверно указан логин или пароль"));
    }


    @Test
    public void wrongPassword() {
//        Configuration.holdBrowserOpen = true;
        DataUser user = UserGenerator.userGenerate("blocked");
        Register.setUpAll(user);
        $x("//input[@name= \"login\"]").setValue(user.getLogin());
        $x("//input[@name= \"password\"]").setValue("Pass");
        $x("//button[@data-test-id=\"action-login\"]").click();
        $x("//div[@class =\"notification__content\"] ").should(visible);
        $x("//div[@class =\"notification__content\"] ").should(text("Неверно указан логин или пароль"));
    }
}
