package testes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoUsuarioPage {
	
	private WebDriver driver;
	
	public NovoUsuarioPage(WebDriver driver){
		this.driver = driver;
	}
	public void cadastro(String nome, String email){
        WebElement txtnome = driver.findElement(By.name("usuario.nome"));
        WebElement txtemail = driver.findElement(By.name("usuario.email"));
        WebElement botaoSalvar =  driver.findElement(By.id("btnSalvar"));
        txtnome.sendKeys(nome);
        txtemail.sendKeys(email);
        botaoSalvar.submit();
	}
	public boolean validacaoDeNomeObrigatorio() {
        return driver.getPageSource().contains("Nome obrigatorio!");
    }
}
