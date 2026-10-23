package StepDefinition;

import ObjectPage.HomePage;
import ObjectPage.LoginPage;
import io.cucumber.java.de.Dann;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;


public class LoginDefinition {
    private final LoginPage loginPage = new LoginPage();


    @And("the user is logged into OrangeHRM with user {string} and password {string}")
    public void ingresarELUsuario(String nombreUsuario, String password) {

    }



    @And("ingresar la pass {string}")
    public void ingresarLaPass(String password) {
        loginPage.escribirPassword(password);
    }

    @When("presiono el boton Submit")
    public void presionoElBotonSubmit() {
        loginPage.clickBtnSubmit();
    }

    @Then("validacion de mensaje Logged In Successfully")
    public void ValidoMensaje(){
    loginPage.validarLogin();
    }



}