package alura_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class kteste {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		
        WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com.br/");

        WebElement campoDeTexto = driver.findElement(By.name("q"));
        campoDeTexto.sendKeys("Caelum");
        campoDeTexto.submit();
	}

}

/*	Garantir que usuários são inseridos e usuários inválidos são negados. Precisamos testar também a 
exclusão e a alteração dos dados desses usuários. No leilão, precisamos testar o cadastro de um novo 
leilão e lances que podem ser dados para o mesmo.    */
