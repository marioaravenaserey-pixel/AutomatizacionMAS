package ObjectPage;

import Control.BaseController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.fail;

public class HomePage extends BaseController {
    @FindBy(xpath = "//*[text() = 'Congratulations student. You successfully logged in!']")
    public WebElement msgBienvenida;


    public void validarMsgBienvenida() {
        try {
            if (this.msgBienvenida.isDisplayed()) {
                System.out.println("EL mensaje es visible!");
            } else {
                fail("Error al visualizar el mensaje de bienvenida");
            }
        } catch (Exception e) {
            fail("Error al detectar el mensaje de bienvenida: " + e.getMessage());
        }
    }

}
