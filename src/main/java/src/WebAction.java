package src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebAction {

	
	private String No;
	private String Description;
	private String Action;
	private String Object;
	private String Value;
	
	public WebAction(String[] line)
	{
		this.No = line[0];
		this.Description = line[1];
		this.Action = line[2];
		this.Object = line[3];
		this.Value = line[4];
	}
	
	public void execute(WebDriver driver)
	{
		switch(this.Action)
		{
			case "INPUT" 		:
				driver.findElement(By.xpath(this.Object)).sendKeys(this.Value);
				break;
			case "NAVIGATE"		:
				driver.navigate().to(this.Value);
				break;
			case "CLICK"		:
				driver.findElement(By.partialLinkText(this.Object)).click();
				break;
		}
	}
}
