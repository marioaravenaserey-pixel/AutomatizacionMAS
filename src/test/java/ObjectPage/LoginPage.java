package ObjectPage;

import Control.BaseController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.fail;

public class LoginPage extends BaseController {
    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement nombreUsuario;

    @FindBy(id = "password")
    private WebElement claveUsuario;

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElement btnSubmit;

    @FindBy(xpath="//*[@id='loop-container']/div/article/div[1]/h1")
    private WebElement mensajeLogin;



    public void escribirUsersname(String nUsuario) {
      this.nombreUsuario.clear();
      this.nombreUsuario.sendKeys(nUsuario);
    }

    public void escribirPassword(String pass) {
        this.claveUsuario.clear();
        this.claveUsuario.sendKeys(pass);
    }

    public void clickBtnSubmit() {
            this.btnSubmit.click();
           }

    public void validarLogin()     {
       this.mensajeLogin.getText().equals("Logged In Successfully");}
    }