import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

    WebDriver driver;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test (priority = 0)
    public void openWebsite(){
        driver.get("https://www.saucedemo.com/" );
    }

    @Test (priority = 1)
    public void validCredentials  () throws InterruptedException {
        //Valid Username and Password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        WebElement swagLabs = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
       /* Another way to check our test case
       if(swagLabs.getText().equalsIgnoreCase("Swag Labs")){
          //  System.out.println("succesfull login");
        }
        */
        Assert.assertTrue(swagLabs.isDisplayed());
    }

    @Test (priority = 2)
    public void invalidCredentials () throws InterruptedException {

        //Invalid Username and Password
        driver.get("https://www.saucedemo.com/" );
        driver.findElement(By.id("user-name")).sendKeys("standard_user ");
        driver.findElement(By.id("password")).sendKeys("secret_sauc");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        WebElement error =driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
       /* Another way to check our test case
       if(error.getText().equalsIgnoreCase("Epic sadface: Username and password do not match any user in this service")){
            System.out.println("failed login");
        }
        */
        Assert.assertTrue(error.isDisplayed());
    }

    @Test (priority = 3)
    public void emptyUsername () throws InterruptedException {
        //empty username
        driver.get("https://www.saucedemo.com/" );
        driver.findElement(By.id("password")).sendKeys("ahj");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        WebElement error =driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test (priority = 4)
    public void emptyPassword () throws InterruptedException {
        //empty username
        driver.get("https://www.saucedemo.com/" );
        driver.findElement(By.id("user-name")).sendKeys("Maya");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        WebElement error =driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
        Assert.assertTrue(error.isDisplayed());
    }

    @AfterTest
    public void close(){
        driver.quit();

    }

}

