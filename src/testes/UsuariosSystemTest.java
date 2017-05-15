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
		usuarios.novo().cadastro("teste fulano", "fulano@gmail.com");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
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
		usuarios.novo().cadastro("teste dois", "dois@gmail.com");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
		assertTrue(usuarios.existeNaListagem("teste dois", "dois@gmail.com"));
		usuarios.excluirUsuarioPosicao(4);     
		assertFalse(usuarios.existeNaListagem("teste dois", "dois@gmail.com"));
    }
	@Test
    public void deveAlterarUmUsuario() {
		usuarios.novo().cadastro("aaaamelia", "amelia@gmail.com");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
		assertTrue(usuarios.existeNaListagem("aaaamelia", "amelia@gmail.com"));
		usuarios.alteraUsuarioPage();
		newuserpage.cadastro("testeAmelia", "testeAmelia@gmail.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Novo Usuário")));
		assertTrue(usuarios.existeNaListagem("testeAmelia", "testeAmelia@gmail.com"));
	}
	@After
    public void finaliza() {
		driver.close();
    }
}
