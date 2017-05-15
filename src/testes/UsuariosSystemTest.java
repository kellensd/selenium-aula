package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsuariosSystemTest {
	private WebDriver driver;
	private UsuariosPage usuarios;
	private NovoUsuarioPage newuserpage;
	
	@Before
    public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.usuarios = new UsuariosPage(driver);
		this.newuserpage = new NovoUsuarioPage(driver);
		usuarios.visita();
    }	
	@Test
	public void deveAdicionarUsuario() {
		if (usuarios.existeNaListagem("teste fulano", "fulano@gmail.com") == false){
			usuarios.novo().cadastro("teste fulano", "fulano@gmail.com");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
		}
			assertTrue(usuarios.existeNaListagem("teste fulano", "fulano@gmail.com"));
	}
	@Test
    public void naoDeveAdicionarUmUsuarioSemNome() {
		NovoUsuarioPage form = usuarios.novo();
		form.cadastro("", "ronaldo2009@terra.com.br");
		assertTrue(form.validacaoDeNomeObrigatorio());        
    }
	@Test
    public void deveExcluirUmUsuario() {
		if (usuarios.existeNaListagem("bernardo", "bernardo@gmail.com") == false){
			usuarios.novo().cadastro("bernardo", "bernardo@gmail.com");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
			assertTrue(usuarios.existeNaListagem("bernardo", "bernardo@gmail.com"));
		}
		usuarios.excluirUsuarioPosicao(3);     
		assertFalse(usuarios.existeNaListagem("bernardo", "bernardo@gmail.com"));
    }
	@Test
    public void deveAlterarUmUsuario() {
		if (usuarios.existeNaListagem("aaaamelia", "aaaamelia@gmail.com") == false){
			usuarios.novo().cadastro("aaaamelia", "aaaamelia@gmail.com");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
			assertTrue(usuarios.existeNaListagem("aaaamelia", "aaaamelia@gmail.com"));
		}
			usuarios.alteraUsuarioPage();
			newuserpage.altera("testeAmelia", "testeAmelia@gmail.com");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
			assertTrue(usuarios.existeNaListagem("testeAmelia", "testeAmelia@gmail.com"));
			assertFalse(usuarios.existeNaListagem("aaaamelia", "aaaamelia@gmail.com"));
	}
	@After
    public void finaliza() {
		driver.close();
    }
}
