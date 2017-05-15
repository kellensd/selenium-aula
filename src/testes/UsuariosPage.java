package testes;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuariosPage {
	
	private WebDriver driver;
	
	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}
	public void visita(){
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver.get("http://localhost:8080/usuarios");
		driver.manage().window().maximize();
	}
	public NovoUsuarioPage novo(){
		driver.findElement(By.linkText("Novo Usuário")).click();
		return new NovoUsuarioPage(driver);
	}
	public boolean existeNaListagem(String nome, String email){
		return	driver.getPageSource().contains(nome) && driver.getPageSource().contains(email);
	}
	public void excluirUsuarioPosicao(int posicao){
		driver.findElements(By.tagName("button")).get(posicao-1).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public void alteraUsuarioPage(){
		int posicao = 1;
		driver.findElements(By.linkText("editar")).get(posicao-1).click();
	}
	
}
