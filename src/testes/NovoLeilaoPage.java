package testes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NovoLeilaoPage {
	
	private final WebDriver driver;
	
	public NovoLeilaoPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void preenche(String nome, double valor, String usuario, boolean usado){
		WebElement fieldNome = driver.findElement(By.name("leilao.nome"));
		WebElement fieldValor = driver.findElement(By.name("leilao.valorInicial"));
		
		fieldNome.sendKeys(nome);
		fieldValor.sendKeys(String.valueOf(valor));
		
		Select cbUsuario = new Select(driver.findElement(By.name("leilao.usuario.id")));
		cbUsuario.selectByVisibleText(usuario);
		
		if (usado){
			WebElement ckUsado = driver.findElement(By.name("leilao.usado"));
			ckUsado.click();
		}
		
		fieldNome.submit();
	}
	public boolean validaNomeObrigatorio() {
        return driver.getPageSource().contains("Nome obrigatorio!");
    }
	public boolean validaValorObrigatorio() {
        return driver.getPageSource().contains("Valor inicial deve ser maior que zero!");
    }
}
