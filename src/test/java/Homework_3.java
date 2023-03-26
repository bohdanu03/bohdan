import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Homework_3 {

    @Test(invocationCount = 20)
    public void task_1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/Students/Index");

        try {
            WebElement createNewButton = driver.findElement(By.cssSelector(".btn-info"));
            createNewButton.click();

            Thread.sleep(1000);

            driver.switchTo().frame("aswift_2").switchTo().frame("ad_iframe");
            Thread.sleep(800);
            WebElement dismissButton = driver.findElement(By.xpath("//div[@id = 'dismiss-button']"));
            dismissButton.click();
        } catch (Exception e) {
            try {
                driver.switchTo().parentFrame();
                Thread.sleep(800);
                WebElement dismissButton = driver.findElement(By.xpath("//div[@id = 'dismiss-button']"));
                dismissButton.click();
            } catch (Exception e1) {
            }
        } finally {
            //driver.navigate().refresh();
            driver.switchTo().defaultContent();
        }

        Thread.sleep(1000);
        WebElement firstName = driver.findElement(By.xpath("//input[@id = 'FirstName']"));
        firstName.sendKeys("tt");

        WebElement lastName = driver.findElement(By.cssSelector("#LastName"));
        lastName.sendKeys("tr");

        WebElement enrolmentDate = driver.findElement(By.cssSelector("#EnrollmentDate"));
        enrolmentDate.sendKeys("03/02/2023");

        WebElement createButton = driver.findElement(By.xpath("//input[@value = 'Create']"));
        createButton.click();

        /*Thread.sleep(3000);
        WebElement search = driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Bohdan");

        WebElement searchButton = driver.findElement(By.xpath("//input[@value = 'Find']"));
        searchButton.click();

        Thread.sleep(1000);
        List<WebElement> listOfAllNames = driver.findElements(By.tagName("tr"));
        List<String> actualListOfName = new ArrayList<>();

        for (WebElement e : listOfAllNames) {
            actualListOfName.add(BrowserUtils.getText(e).replaceAll("(\\s[0-9]+:.*)$", ""));
        }
        Assert.assertTrue(actualListOfName.contains("Bohdan Shymkiv 3/2/2023"));*/

        driver.quit();
    }

    @Test
    public void task_2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/Students/Index");

        WebElement search = driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Shymkiv", Keys.ENTER);

        Thread.sleep(3000);
        WebElement editButton = driver.findElement(By.xpath("//button[contains(text(),'EDIT')]"));
        editButton.click();

        WebElement editFirstName = driver.findElement(By.cssSelector("#FirstName"));
        editFirstName.clear();
        editFirstName.sendKeys("SBS");

        WebElement saveButton = driver.findElement(By.xpath("//input[@value = 'Save']"));
        saveButton.click();

        search = driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("SBS", Keys.ENTER);

        Thread.sleep(1000);
        List<WebElement> listOfAllNames = driver.findElements(By.tagName("tr"));
        List<String> actualListOfName = new ArrayList<>();

        for (WebElement e : listOfAllNames) {
            actualListOfName.add(BrowserUtils.getText(e).replaceAll("(\\s[0-9]+:.*)$", ""));
        }
        Assert.assertTrue(actualListOfName.contains("SBS Shymkiv 3/2/2023"));

        driver.quit();
    }

    @Test
    public void task_3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/Students/Index");

        WebElement search = driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Shymkiv", Keys.ENTER);

        Thread.sleep(2000);
        WebElement deleteButton = driver.findElement(By.xpath("//button[contains(text(),'DELETE')]"));
        deleteButton.click();

        Thread.sleep(1000);
        WebElement confirmDelete = driver.findElement(By.xpath("//input[@value ='Delete']"));
        confirmDelete.click();

        Thread.sleep(2000);
        search = driver.findElement(By.cssSelector("#Search_Data"));
        search.sendKeys("Shymkiv", Keys.ENTER);

        WebElement message = driver.findElement(By.xpath("//div[@class = 'pagination-container']/.."));
        Assert.assertTrue(BrowserUtils.getText(message).contains("There are zero students with this search text Page 0 of 0"));
        driver.quit();
    }

    @Test
    public void task_4() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/");

        WebElement dragMeBox = driver.findElement(By.cssSelector("#draggable"));
        WebElement dropHereBox = driver.findElement(By.cssSelector("#droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragMeBox, dropHereBox).perform();

        Thread.sleep(2000);
        Assert.assertEquals(BrowserUtils.getText(dropHereBox), "Dropped!");

        driver.quit();
    }


    @Test
    public void task_5() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[@name = 'dblClick']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).perform();

        //Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText().trim(), "Double Clicked !!");
        alert.accept();

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void task_6() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/");

        driver.switchTo().frame("iframe_a");

        WebElement enterName = driver.findElement(By.cssSelector("#name"));
        enterName.sendKeys("Bohdan");

        driver.switchTo().parentFrame();

        WebElement link = driver.findElement(By.linkText("uitestpractice.com"));
        link.click();

        driver.switchTo().frame("iframe_a");

        Thread.sleep(2000);
        WebElement message = driver.findElement(By.xpath("//div[@id = 'sub-frame-error-details']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(message).perform();

        Assert.assertEquals(BrowserUtils.getText(message), "www.uitestpractice.com refused to connect.");
        driver.quit();
    }

    @Test
    public void task_7() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://uitestpractice.com/");

        WebElement openLinkNewWindow = driver.findElement(By.linkText("Click here to watch videos on C#"));
        openLinkNewWindow.click();

        BrowserUtils.switchById(driver);
        Assert.assertTrue(driver.getTitle().contains("C# Beginner to advanced"));
        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
        driver.quit();
    }
}
