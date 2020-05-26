package src;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

public class WebTest {
    private WebDriver driver;
    private String CSVFile;

    @BeforeTest
    public void prepare() {
    	System.setProperty(
                "webdriver.chrome.driver",
                "webdriver/chromedriver");
        driver = new ChromeDriver();

        //maximize window
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		CSVFile = "test-suite/login.csv";
    }

    @Test
    public void testTitle() throws IOException {
    	CSVReader reader = null;
    	
    	reader = new CSVReaderBuilder(new FileReader(CSVFile))
                // Skip the header
                .withSkipLines(0)
                .build();
    	
    	WebAction webAction;
    	List<String[]> lines = reader.readAll();
    	for(int a=1; a<=lines.size(); a++)
    	{
    		webAction = new WebAction(lines.get(a));
    		webAction.execute(driver);
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
//		driver.navigate().to("https://www.linkgoeshere.co.id");
//    	driver.findElement(By.partialLinkText("LOGIN")).click();
//    	driver.findElement(By.xpath("//*[@ng-model='login.email']")).sendKeys("asd");
//    	driver.findElement(By.xpath("//*[@ng-model='login.password']")).sendKeys("pwd");
    }

    @AfterTest
    public void teardown() throws IOException {
        driver.quit();
}
}
