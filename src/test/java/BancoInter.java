import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class BancoInter {

	// -------------------------------------------------------------------------
	WebDriver driver;
	// -------------------------------------------------------------------------

	@Before
	public void setUp() throws Exception {

		// Cod para indicar o navegador
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");

		// cod para chamar o driver dentro do before
		driver = new ChromeDriver();

		// Comando para entrar indicar o link
		driver.get("https://www.4devs.com.br/gerador_de_pessoas");

		// Cod para maximixar a tela
		driver.manage().window().maximize();

		// cod para interagir com elemento (Gerar pessoa no 4devs)
		driver.findElement(By.cssSelector("#bt_gerar_pessoa")).click();

		// pausa
		Thread.sleep(1000);
		// Nome 4devs
		String nome = driver.findElement(By.id("nome")).getText();
		// celular 4devs
		String tel = driver.findElement(By.id("celular")).getText();
		// E-mail 4devs
		String email = driver.findElement(By.id("email")).getText();
		// CPF 4devs
		String cpf = driver.findElement(By.id("cpf")).getText();
		// Data de nascimento 4devs
		String datanasc = driver.findElement(By.id("data_nasc")).getText();

		// Abrir uma nova aba
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.bancointer.com.br/");

		// pausa
		Thread.sleep(2000);

		// cod para interagir com elemento (ok no site do inter)
		driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();

		// cod para interagir com elemento (abra sua conta)
		driver.findElement(By.cssSelector("#gatsby-focus-wrapper > div > header > section"
				+ " > div > div > div > div > div.styles__LogoNIcons-sc-1gbjc3e-6.gjJzHM"
				+ " > div.styles__SearchNFlags-sc-1gbjc3e-8.yVPnY"
				+ " > div.styles__ButtonsWrapper-sc-1gbjc3e-9.PKrxs > button:nth-child(1)")).click();

		// pausa
		Thread.sleep(1000);

		// Preencher dados do banco
		// Nome
		driver.findElement(By.id("nome")).sendKeys(nome);
		// Cel
		driver.findElement(By.id("celular")).sendKeys(tel);
		// Email
		driver.findElement(By.id("email")).sendKeys(email);
		// CPF
		driver.findElement(By.id("cpf")).sendKeys(cpf);
		// Data de Nascimento
		driver.findElement(By.id("dataNascimento")).sendKeys(datanasc);
		// checkbox
		driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK"
				+ " > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center"
				+ " > div > div:nth-child(2) > form > div > div:nth-child(6) > div > label")).click();
		// continue
		driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK"
				+ " > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center"
				+ " > div > div:nth-child(2) > form > div > div.col-12.text-center > button")).click();

		// Tirar um print
		File print = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Salvar o print
		Files.copy(print, new File("/Users/apple/Desktop/printscreen.png"));
		
	}

	@After
	public void tearDown() throws Exception {

		// Pausa
		Thread.sleep(1000);
		
		// Fechar abas
		driver.quit();

	}

	@Test
	public void test() throws InterruptedException {

		Thread.sleep(1000);

		String texto = driver
				.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK >"
						+ " div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center.sent > div > p"))
				.getText();

		System.out.println(texto);

		// Validação
		assertEquals("Prontinho! Recebemos os seus dados.", texto);

	}

}
