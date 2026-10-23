package ObjectPage;
import Control.BaseController;
import Control.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;

import java.util.List;

public class ListaEmpleadoPage extends BaseController {

    @FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")
    private WebElement username;

    @FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
    private WebElement loginbtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a")
    private WebElement pimbtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")
    private WebElement nombreempleado;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
    private WebElement buscarempleadobtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")
    private WebElement employeeId;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")
    private WebElement expectedrecords;
    //@TC-003
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i")
    private WebElement employeestatus;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")
    private WebElement employeestatustype;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[6]/div")
    private List<WebElement> listaEmploymentStatus;
// TC-004

    @FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i")
    private WebElement employeinclude;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]")
    private WebElement includeSeleccionado;

    // TC-005

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div/div[2]/i")
    private WebElement jobTitleSelector;


    //SUB UNIT
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[7]/div/div[2]/div/div/div[2]/i")
    private WebElement subUnitSelector;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[7]/div/div[2]/div/div/div[1]")
    private WebElement subUnitSeleccionada;

    //SUPERVISOR NAME
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[5]/div/div[2]/div/div/input")
    private WebElement supervisorName;
    //GRID
    @FindBy(xpath = "//div[contains(@class,'oxd-table-card')]")
    private List<WebElement> filasGrid;


    public void navegarA(String url) {
        driver.get(url);
    }

    public void escribirUsersname(String nUsuario) {
        this.username.clear();
        this.username.sendKeys(nUsuario);
    }

    public void escribirPassword(String pass) {
        this.password.clear();
        this.password.sendKeys(pass);
    }

    public void clickLogin() {
        loginbtn.click();
    }

    public void clickPIM() {
        try {
            visualizarElemento(pimbtn, 10);
            pimbtn.click();
        } catch (Exception e) {
            Assert.fail("Error al ingresar al módulo PIM: " + e.getMessage());
        }
    }

    public void escribirnEmpleado(String nEmpleado) {
        this.nombreempleado.clear();
        this.nombreempleado.sendKeys(nEmpleado);
    }
// extra para la obtstencion de busqueda

    public String obtenerNombreEmpleado() {
        return nombreempleado.getAttribute("value");
    }

    public String obtenerEmployeeId() {
        return employeeId.getAttribute("value");
    }


    public void clickBtnbuscarEmp() {
        this.buscarempleadobtn.click();
    }

    public void recordEmployeeId(String idEmpleado) {
        this.employeeId.clear();
        this.employeeId.sendKeys(idEmpleado);
    }

    public String obtenerCantidadRegistros() {

        String texto = expectedrecords.getText();

        if (texto.equals("No Records Found")) {
            return "0";
        }
        return texto.replaceAll("[^0-9]", "");
    }
    //@TC-003
    public boolean seleccionarEmploymentStatus(String status) throws InterruptedException {

        employeestatus.click();

        Thread.sleep(2000);

        List<WebElement> opciones =
                DriverContext.getDriver().findElements(
                        By.xpath(
                                "//*[normalize-space()='"
                                        + status +
                                        "']"
                        )
                );

        if (opciones.isEmpty()) {

            System.out.println(
                    "No se encontró Employment Status: "
                            + status
            );

            return false;
        }

        opciones.get(0).click();

        System.out.println(
                "Employment Status seleccionado: "
                        + status
        );

        return true;
    }
    public void clickselectStatus(String status) {

        employeestatus.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> opciones =
                driver.findElements(By.xpath("//div[contains(@class,'oxd-select-option')]"));

        System.out.println("Cantidad opciones: " + opciones.size());

        for (WebElement opcion : opciones) {
            System.out.println("OPCION -> " + opcion.getText());
        }
    }


    public boolean validarEstados(String statusEsperado) {

        for (WebElement estado : listaEmploymentStatus) {

            if (!estado.getText().equals(statusEsperado)) {
                return false;
            }
        }

        return true;
    }
// @TC-004
        public boolean seleccionarInclude(String includeOption)
        throws InterruptedException {

    employeinclude.click();

    Thread.sleep(2000);

    List<WebElement> opciones =
            DriverContext.getDriver().findElements(
                    By.xpath("//*[normalize-space()='" +
                            includeOption + "']")
            );

    if (opciones.isEmpty()) {

        System.out.println(
                "No se encontró Include: "
                        + includeOption
        );

        return false;
    }

    opciones.get(0).click();

    Thread.sleep(1000);

    String valorSeleccionado =
            includeSeleccionado.getText().trim();

    System.out.println(
            "Include seleccionado: "
                    + valorSeleccionado
    );

    return valorSeleccionado.equalsIgnoreCase(includeOption);
        }
//@TC-005


    public boolean seleccionarJobTitle(String jobTitle) throws InterruptedException {

        jobTitleSelector.click();
        Thread.sleep(2000);

        List<WebElement> opciones =
                DriverContext.getDriver().findElements(
                        By.xpath("//*[normalize-space()='" + jobTitle + "']")
                );

        if (opciones.isEmpty()) {

            System.out.println(
                    "No se encontró el Job Title: " + jobTitle
            );

            return false;
        }

        System.out.println(
                "Job Title encontrado: " + jobTitle
        );

        opciones.get(0).click();

        Thread.sleep(1000);

        WebElement valorSeleccionado =
                DriverContext.getDriver().findElement(
                        By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div/div[1]")
                );

        String textoSeleccionado =
                valorSeleccionado.getText().trim();

        System.out.println(
                "Job Title seleccionado: " + textoSeleccionado
        );

        if (!textoSeleccionado.equalsIgnoreCase(jobTitle)) {

            System.out.println(
                    "ERROR: se esperaba [" + jobTitle +
                            "] pero quedó seleccionado [" +
                            textoSeleccionado + "]"
            );

            return false;
        }

        return true;
    }

    public boolean seleccionarSubUnit(String subUnit)
            throws InterruptedException {

        subUnitSelector.click();

        Thread.sleep(2000);

        List<WebElement> opciones =
                DriverContext.getDriver().findElements(
                        By.xpath("//*[normalize-space()='" +
                                subUnit + "']")
                );

        if (opciones.isEmpty()) {

            System.out.println(
                    "No se encontró la Sub Unit: "
                            + subUnit
            );

            return false;
        }

        opciones.get(0).click();

        Thread.sleep(1000);

        String valorSeleccionado =
                subUnitSeleccionada.getText().trim();

        System.out.println(
                "Sub Unit seleccionada: "
                        + valorSeleccionado
        );

        return valorSeleccionado.equalsIgnoreCase(subUnit);
    }
    public boolean ingresarSupervisor(String supervisor)
            throws InterruptedException {

        supervisorName.clear();
        supervisorName.sendKeys(supervisor);

        Thread.sleep(3000);

        List<WebElement> opciones =
                DriverContext.getDriver().findElements(
                        By.xpath("//div[@role='listbox']//span")
                );

        System.out.println(
                "Opciones encontradas: " +
                        opciones.size()
        );

        for (WebElement opcion : opciones) {

            System.out.println(
                    "Supervisor disponible: " +
                            opcion.getText()
            );

            if (opcion.getText()
                    .trim()
                    .equalsIgnoreCase(supervisor)) {

                opcion.click();

                System.out.println(
                        "Supervisor seleccionado: "
                                + supervisor
                );

                return true;
            }
        }

        System.out.println(
                "Supervisor no encontrado: "
                        + supervisor
        );

        return false;
    }

    //validación de grid

    public boolean validarResultadosTC005(String jobTitle,
                                          String subUnit,
                                          String supervisor) {

        System.out.println("Filas encontradas: " + filasGrid.size());
        System.out.println(
                "Texto registros: " +
                        expectedrecords.getText()
        );
        for (WebElement fila : filasGrid) {

            List<WebElement> celdas =
                    fila.findElements(By.className("oxd-table-cell"));

            if (celdas.size() < 8) {
                continue;
            }

            String jobGrid = celdas.get(4).getText().trim();
            String statusGrid = celdas.get(5).getText().trim();
            String subUnitGrid = celdas.get(6).getText().trim();
            String supervisorGrid = celdas.get(7).getText().trim();

            if (!jobGrid.isEmpty()) {

                System.out.println(
                        "Job: " + jobGrid +
                                " Status: " + statusGrid +
                                " SubUnit: " + subUnitGrid +
                                " Supervisor: " + supervisorGrid
                );

                return jobGrid.equalsIgnoreCase(jobTitle)
                        && subUnitGrid.equalsIgnoreCase(subUnit)
                        && supervisorGrid.equalsIgnoreCase(supervisor);
            }
        }

        return false;

    }
    public int obtenerCantidadRecords() {

        String texto = expectedrecords.getText();

        System.out.println("Texto registros: " + texto);

        return Integer.parseInt(
                texto.replaceAll("[^0-9]", "")
        );
    }
}



