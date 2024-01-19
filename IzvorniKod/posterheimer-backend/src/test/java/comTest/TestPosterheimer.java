package comTest;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.test.web.servlet.MockMvc;
import org.openqa.selenium.JavascriptExecutor;


public class TestPosterheimer {
	@Test
	public void genericLogin() {
		  
		System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    driver.get("https://posterheimer.onrender.com/");
	    driver.manage().window().setSize(new Dimension(1552, 840));
	   	    
	    driver.findElement(By.xpath("(//button[contains(text(),'Pristupi')])[2]")).click();
	    // broj postaviti na mjesto konferencije u listi
	   
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("visitor.test@mail.hr");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("Lozinka1");
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	    
	    driver.findElement(By.linkText("Konferencija")).click();

	    String redirURL = driver.getCurrentUrl();
	    boolean comperRes = redirURL.contains("/conference");
	    if(comperRes == true) {
	    	System.out.println("Pristupljeno konferenciji");
	    } else {
	    	System.out.println("Pogreška");
	    }
	    assertEquals(comperRes, true);
	    driver.quit();
	  }
	
	  @Test
	  public void correctSpecificLogin() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	    driver.get("https://posterheimer.onrender.com/");
	    driver.manage().window().setSize(new Dimension(1552, 840));
	    driver.findElement(By.cssSelector(".list-group-item:nth-child(2) > .float-end")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("register.email@mail.hr");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("Lozinka1");
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	    
	    driver.findElement(By.linkText("Konferencija")).click();

	    String redirURL = driver.getCurrentUrl();
	    boolean comperRes = redirURL.contains("/conference");
	    if(comperRes == true) {
	    	System.out.println("Pristupljeno konferenciji");
	    } else {
	    	System.out.println("Pogreška pri prijavi");
	    }
	    
	    driver.findElement(By.id("user-dropdown")).click();
	    driver.findElement(By.cssSelector(".fa-right-from-bracket")).click();
	    
	    redirURL = driver.getCurrentUrl();
	    comperRes = redirURL.equals("https://posterheimer.onrender.com/");
	    if(comperRes == true) {
	    	System.out.println("Uspješna odjava");
	    } else {
	    	System.out.println("Pogreška pri odjavi");
	    }
	    driver.quit();
	  }	
	  
	  @Test
	  public void incorrectLogin() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    driver.get("https://posterheimer.onrender.com/");
	    driver.manage().window().setSize(new Dimension(1552, 840));
	    driver.findElement(By.xpath("(//button[@type=\'button\'])[2]")).click();

	    driver.findElement(By.cssSelector(".mb-3:nth-child(1)")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("test");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("test");
	    driver.findElement(By.cssSelector(".btn-primary")).click();

	    driver.findElement(By.cssSelector(".alert")).click();
	    WebElement alert = driver.findElement(By.cssSelector(".alert"));
	    String errorMessage = alert.getText();
	    if(errorMessage.contains("email ili lozinka!")) {
	    	System.out.println("Prikaz prikladne obavijesti");
	    }

	    driver.findElement(By.cssSelector(".btn-close")).click();
	    
	    String redirURL = driver.getCurrentUrl();
	    boolean comperRes = redirURL.equals("https://posterheimer.onrender.com/");
	    if(comperRes) {
	    	System.out.println("Ostvaren predviđen rezultat");
	    }
	    driver.quit();
	  }
	  
	  @Test
	  public void adminLogin() {
			System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// Vrijeme povecano zbog ucitavanja postera, koje je znatno sporije za administratora
			
		    driver.get("https://posterheimer.onrender.com/");
		    driver.manage().window().setSize(new Dimension(1552, 840));
		    driver.findElement(By.cssSelector(".list-group-item:nth-child(2) > .float-end")).click();
		    driver.findElement(By.id("username")).click();
		    driver.findElement(By.id("username")).sendKeys("admin.test@mail.hr");
		    driver.findElement(By.id("password")).click();
		    driver.findElement(By.id("password")).sendKeys("Lozinka1");
		    driver.findElement(By.cssSelector(".btn-primary")).click();
		    driver.findElement(By.cssSelector(".conference-content")).click();
		    driver.findElement(By.linkText("Posteri")).click();
		    driver.findElement(By.cssSelector(".fa-solid")).click();
		    driver.findElement(By.cssSelector(".btn-close")).click();
		    driver.findElement(By.linkText("Fotografije")).click();
		    driver.findElement(By.cssSelector(".image-container > img")).click();
		    driver.findElement(By.cssSelector(".modal-title")).click();
		    driver.findElement(By.cssSelector(".btn-close")).click();
		    driver.findElement(By.linkText("Pokrovitelji")).click();
		    driver.findElement(By.cssSelector(".fa-solid")).click();
		    driver.findElement(By.cssSelector(".modal-title")).click();
		    driver.findElement(By.cssSelector(".btn-close")).click();
		    
		    String redirURL = driver.getCurrentUrl();
		    boolean comperRes = redirURL.contains("sponsors");
		    if(comperRes) {
		    	System.out.println("Ostvaren predviđen rezultat");
		    }
		    driver.quit();
		  }
	 
	  @Test
	  public void navBar() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		
	    driver.get("https://posterheimer.onrender.com/");
	    driver.manage().window().setSize(new Dimension(1552, 840));

	   	    
	    driver.findElement(By.xpath("(//button[contains(text(),'Pristupi')])[2]")).click();
	    // broj postaviti na mjesto konferencije u listi
	   
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("visitor.test@mail.hr");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("Lozinka1");
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	    
	    driver.findElement(By.linkText("Konferencija")).click();
	    String redirURL = driver.getCurrentUrl();
	    boolean comperRes = redirURL.contains("/conference");
	    if(comperRes == true) {
	    	System.out.println("Pristupljeno konferenciji");
	    } else {
	    	System.out.println("Pogreška");
	    }
	    
	    driver.findElement(By.linkText("Posteri")).click();
	    redirURL = driver.getCurrentUrl();
	    comperRes = redirURL.contains("/posters");
	    if(comperRes == true) {
	    	System.out.println("Pristupljeno posterima");
	    } else {
	    	System.out.println("Pogreška");
	    }
	  
	  driver.findElement(By.linkText("Fotografije")).click();
	    redirURL = driver.getCurrentUrl();
	    comperRes = redirURL.contains("/photos");
	    if(comperRes == true) {
	    	System.out.println("Pristupljeno fotografijama");
	    } else {
	    	System.out.println("Pogreška");
	    }
		  driver.findElement(By.linkText("Pokrovitelji")).click();
		    redirURL = driver.getCurrentUrl();
		    comperRes = redirURL.contains("/sponsors");
		    if(comperRes == true) {
		    	System.out.println("Pristupljeno pokroviteljima");
		    } else {
		    	System.out.println("Pogreška");
		    }

		driver.findElement(By.linkText("Rezultati")).click();
		redirURL = driver.getCurrentUrl();
		comperRes = redirURL.contains("/results");
			if(comperRes == true) {
			   System.out.println("Pristupljeno rezultatima");
			} else {
			   System.out.println("Pogreška");
			}

		driver.quit();
	  }
	  
	  @Test
	  public void incorrectRegister() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
	    driver.get("https://posterheimer.onrender.com/");
	    driver.manage().window().setSize(new Dimension(1552, 840));
	    driver.findElement(By.cssSelector(".list-group-item:nth-child(2) > .float-end")).click();
	    
	    driver.findElement(By.cssSelector("form")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("visitor.test@mail.hr");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("Lozinka1");
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	    driver.findElement(By.linkText("Registracija")).click();
	    js.executeScript("window.scrollTo(0,0)");
	    driver.findElement(By.id("formIme")).click();
	    driver.findElement(By.id("formIme")).sendKeys("TestIme");
	    driver.findElement(By.id("formPrezime")).click();
	    driver.findElement(By.id("formPrezime")).sendKeys("TestPrezime");
	    driver.findElement(By.id("formBasicEmail")).click();
	    driver.findElement(By.id("formBasicEmail")).sendKeys("email");
	    driver.findElement(By.id("formBasicPassword")).click();
	    driver.findElement(By.id("formBasicPassword")).sendKeys("Lozinka1");
	    driver.findElement(By.cssSelector(".mb-3:nth-child(2) > #formBasicPassword")).click();
	    driver.findElement(By.cssSelector(".mb-3:nth-child(2) > #formBasicPassword")).sendKeys("Lozinka1");
	    driver.switchTo().frame(0);
	    driver.findElement(By.cssSelector(".recaptcha-checkbox-border")).click();
	    driver.switchTo().defaultContent();
	    driver.findElement(By.cssSelector(".ml-2")).click();
	    driver.findElement(By.cssSelector(".mx-2 > .mb-3:nth-child(1)")).click();
	    driver.findElement(By.cssSelector(".mx-2 > .mb-3:nth-child(1)")).click();
	    
	    String redirURL = driver.getCurrentUrl();
	    boolean comperRes = redirURL.contains("/register");
	    if(comperRes == true) {
	    	System.out.println("Neuspješna registracija");
	    } else {
	    	System.out.println("Pogreška");
	    }
	    
	    driver.quit();
	  }	  

	  @Test
	  public void incorrectPosterFormat() {
		  System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver\\chromedriver.exe");
		  WebDriver driver = new ChromeDriver();
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  File filepath = new File("src\\test\\java\\files\\example.pptx");


		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			  
		  driver.get("https://posterheimer.onrender.com/");
		  driver.manage().window().setSize(new Dimension(1552, 840));
		  driver.findElement(By.cssSelector(".list-group-item:nth-child(2) > .float-end")).click();	  

		   driver.findElement(By.id("username")).click();
		   driver.findElement(By.id("username")).sendKeys("admin.test@mail.hr");
		   driver.findElement(By.id("password")).click();
		   driver.findElement(By.id("password")).sendKeys("Lozinka1");
		   driver.findElement(By.cssSelector(".btn-primary")).click();
		   driver.findElement(By.linkText("Posteri")).click();
		   driver.findElement(By.cssSelector(".fa-solid")).click();
		   
		   WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
		   fileInput.sendKeys(filepath.getAbsolutePath());

		   driver.findElement(By.name("imePoster")).click();
		   driver.findElement(By.name("imePoster")).sendKeys("Netočan format");
		   driver.findElement(By.name("imeAutor")).click();
		   driver.findElement(By.name("imeAutor")).sendKeys("Ime");
		   driver.findElement(By.name("prezimeAutor")).click();
		   driver.findElement(By.name("prezimeAutor")).sendKeys("Prezime");
		   driver.findElement(By.cssSelector("form")).click();
		   driver.findElement(By.name("posterEmail")).click();
		   driver.findElement(By.name("posterEmail")).sendKeys("test.poster@posterheimer.hr");
		   driver.findElement(By.cssSelector(".btn")).click();
		   
		   WebElement alert = driver.findElement(By.cssSelector(".alert"));
		   String errorMessage = alert.getText();
		   if(errorMessage.contains("Izabrana datoteka nije PDF!")) {
		    	System.out.println("Prikaz prikladne obavijesti");
		   } else {
		    	System.out.println("Error");
		   }		
		   
		   driver.findElement(By.cssSelector(".alert")).click();
		   driver.findElement(By.cssSelector(".btn-close")).click();

		  
		  driver.quit();
	  }
}
