package StepDefinition;

import ObjectPage.HomePage;
import ObjectPage.ListaEmpleadoPage;
import ObjectPage.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import io.cucumber.java.en_scouse.An;

public class OrangeDefinition {
    private final ListaEmpleadoPage listaEmpleadoPage = new ListaEmpleadoPage();


    @And("que el usuario inicia sesión en OrangeHRM con el usuario {string} y la contraseña {string}")
    public void ingresarUsuario(String nombreUsuario, String password) {
        listaEmpleadoPage.escribirUsersname(nombreUsuario);
        listaEmpleadoPage.escribirPassword(password);
        listaEmpleadoPage.clickLogin();
    }

    @And("que el usuario ingresa al módulo PIM")
    public void moduloPIM() {
        listaEmpleadoPage.clickPIM();
    }

    @Given("el usuario ingresa {string} en el campo Nombre del empleado de PIM")
    public void ingresarEmpleado(String nEmpleado) {
        listaEmpleadoPage.escribirnEmpleado(nEmpleado);
    }

    @And("hace clic en Buscar")
    public void buscarEmpleado() throws InterruptedException {
        listaEmpleadoPage.clickBtnbuscarEmp();
        Thread.sleep(8000);

    }

    @Then("se deben mostrar los resultados de la búsqueda")
    public void validarResultados() {

    }

    @And("cada resultado debe contener {string}")
    public void validarNombreEmpleado(String expectedName) {
    }

    @When("the user enters employee id {string}")
    public void ingresarElIdDelEmpleado(String employeeId) {
        listaEmpleadoPage.recordEmployeeId(employeeId);
    }

    @And("clicks Search")
    public void clicksSearch() throws InterruptedException {
        listaEmpleadoPage.clickBtnbuscarEmp();
        Thread.sleep(10000);
        System.out.println(
                "Nombre: " +
                        listaEmpleadoPage.obtenerNombreEmpleado()
        );

        System.out.println(
                "ID: " +
                        listaEmpleadoPage.obtenerEmployeeId()
        );
    }

    @Then("the system should return {string} records")
    public void validarResultados(String expectedRecords) {
        String actual = listaEmpleadoPage.obtenerCantidadRegistros();
        Assert.assertEquals(expectedRecords, actual);
    }

    // @TC-003
    @When("the user selects employment status {string}")
    public void seleccionarEmploymentStatus(String status) throws InterruptedException {

        boolean seleccionado =
                listaEmpleadoPage.seleccionarEmploymentStatus(status);

        Assert.assertTrue(
                "No se pudo seleccionar Employment Status: " + status,
                seleccionado
        );
    }

    @And("clicks Search2")
    public void clicksSearch2() throws InterruptedException {
        listaEmpleadoPage.clickBtnbuscarEmp();
        Thread.sleep(10000);
    }

    @Then("every result should have employment status {string}")
    public void resultadoStatus(String status) {

        Assert.assertTrue(
                listaEmpleadoPage.validarEstados(status)
        );

    }

    // @TC-004
    @When("the user selects include option {string}")
    public void seleccionarInclude(String includeOption)
            throws InterruptedException {

        Assert.assertTrue(
                listaEmpleadoPage
                        .seleccionarInclude(includeOption)
        );
    }

    @Then("results should belong to {string}")
    public void validarInclude(String includeOption) {

        String resultado = listaEmpleadoPage.obtenerCantidadRegistros();

        System.out.println("Resultado encontrado: " + resultado);

        if (includeOption.equals("Past Employees Only")) {

            Assert.assertTrue(
                    resultado.contains("No Records Found")
            );

        } else {

            Assert.assertFalse(resultado.isEmpty());

        }
    }
    // @TC-005

    private String jobTitleSeleccionado;
    private String subUnitSeleccionada;
    private String supervisorIngresado;

    @When("the user enters employee name {string}")
    public void ingEmpleado(String nEmpleado) {
        listaEmpleadoPage.escribirnEmpleado(nEmpleado);
    }

    @And("the user selects job title {string}")
    public void seleccionarJobTitle(String jobTitle) throws InterruptedException {

        jobTitleSeleccionado = jobTitle;

        boolean encontrado =
                listaEmpleadoPage.seleccionarJobTitle(jobTitle);

        Assert.assertTrue(
                "No fue posible seleccionar correctamente el Job Title: "
                        + jobTitle,
                encontrado
        );
    }

    @And("the user selects sub unit {string}")
    public void seleccionarSubUnit(String subUnit)
            throws InterruptedException {

        subUnitSeleccionada = subUnit;

        Assert.assertTrue(
                listaEmpleadoPage.seleccionarSubUnit(subUnit)
        );
    }


    @And("the user selects supervisor {string}")
    public void seleccionarSupervisor(String supervisor) throws InterruptedException {

        supervisorIngresado = supervisor;

        listaEmpleadoPage.ingresarSupervisor(supervisor);
    }

    @Then("every result should match all selected criteria")
    public void every_result_should_match_all_selected_criteria() {

        Assert.assertTrue(
                listaEmpleadoPage.validarResultadosTC005(
                        jobTitleSeleccionado,
                        subUnitSeleccionada,
                        supervisorIngresado
                )
        );
        Assert.assertEquals(
                1,
                listaEmpleadoPage.obtenerCantidadRecords()
        );
    }
}



