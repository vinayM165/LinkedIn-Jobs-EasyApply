import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.*;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedIn {
	Config config = new Config();
	Utils utils = new Utils();
	Constants constants = new Constants();
	WebDriver driver;
	WebDriverWait wait;
	public LinkedIn() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\VMANDGE\\Downloads\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		 wait = new WebDriverWait(driver, 10);
		 driver.get("https://www.linkedin.com/login");
		  WebElement emailInput = driver.findElement(By.id("username"));
		  emailInput.sendKeys("mandgevinay16@gmail.com");
		  WebElement passwordInput = driver.findElement(By.id("password"));
		  passwordInput.sendKeys("vkshimpi16599");
		  WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
		  signInButton.click();
	}
	public void generateUrls() {
	    if (!Files.exists(Paths.get("data"))) {
	        new File("data").mkdirs();
	    }
	    try (FileWriter writer = new FileWriter(Paths.get("data/urlData.txt").toFile());
	    		BufferedWriter file =new BufferedWriter(writer)) {
	        List<String> linkedinJobLinks = new LinkednUrlGenerate().generateUrlLinks();
	        for (String url : linkedinJobLinks) {
	            file.write(url + "\n");
	        }
	        utils.prGreen("Urls are created successfully, now the bot will visit those urls.");
	    } catch (Exception e) {
	        utils.prRed("Couldnt generate url, make sure you have /data folder and modified  for your preferances.");
	    }
	}
	
	public void linkJobApply() throws InterruptedException {
	    generateUrls();
	    int countApplied = 0;
	    int countJobs = 0;
	    String[] urlData = utils.getUrlDataFile();
	    for (String url : urlData) {
	        driver.get(url);
	        String totalJobs = driver.findElement(By.xpath("//small")).getText();
	        int totalPages = utils.jobsToPages(totalJobs);
	        String[] urlWords = utils.urlToKeywords(url);
	        String lineToWrite = "\n Category: " + urlWords[0] + ", Location: " + urlWords[1] + ", Applying " + totalJobs + " jobs.";
	        displayWriteResults(lineToWrite);
	        for (int page = 0; page < totalPages; page++) {
	            int currentPageJobs = constants.jobsPerPage * page;
	            url = url + "&start=" + currentPageJobs;
	            driver.get(url);
	            Thread.sleep(constants.botSpeed * 1000);
	            List<WebElement> offersPerPage = driver.findElements(By.xpath("//li[@data-occludable-job-id]"));
	            List<Long> offerIds = new ArrayList<>();
	            for (WebElement offer : offersPerPage) {
	                String offerId = offer.getAttribute("data-occludable-job-id");
	                System.out.println("OfferID : "+ offerId);
	                String[] parts = offerId.split(":");
	                offerIds.add(Long.parseLong(parts[parts.length - 1]));
	            }
	            for (long jobID : offerIds) {
	                String offerPage = "https://www.linkedin.com/jobs/view/" + jobID;
	                driver.get(offerPage);
	               Thread.sleep((long) constants.botSpeed * 1000);
	                countJobs++;
	                String jobProperties = getJobProperties(countJobs);
	                WebElement button = easyApplyButton();
	                if (button != null) {
	                    button.click();
	                    Thread.sleep(constants.botSpeed * 1000);
	                    countApplied++;
	                    try {
	                        driver.findElement(By.cssSelector("button[aria-label='Submit application']")).click();
	                        Thread.sleep((long) (constants.botSpeed * 1000));

	                        String lineToWrite1 = jobProperties + " | " + "* 🥳 Just Applied to this job: " + offerPage;
	                        displayWriteResults(lineToWrite1);
	                    } catch (Exception e) {
	                        try {
	                            driver.findElement(By.cssSelector("button[aria-label='Continue to next step']")).click();
	                            Thread.sleep((long) (constants.botSpeed * 1000));
	                            String comPercentage = driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div/div/span")).getText();
	                            int percenNumber = Integer.parseInt(comPercentage.substring(0, comPercentage.indexOf("%")));
	                            String result = applyProcess(percenNumber, offerPage)	;
	                            String lineToWrite1 = jobProperties + " | " + result;
	                            displayWriteResults(lineToWrite1);
	                        } catch (Exception e1) {
	                            try {
	                                driver.findElement(By.cssSelector("option[value='urn:li:country:" + config.country_code + "']")).click();
	                                Thread.sleep((long) (constants.botSpeed * 1000));
	                                driver.findElement(By.cssSelector("input")).sendKeys(config.phone_number);
	                                Thread.sleep((long) (constants.botSpeed * 1000));
	                                driver.findElement(By.cssSelector("button[aria-label='Continue to next step']")).click();
	                                Thread.sleep((long) (constants.botSpeed * 1000));
	                                String comPercentage = driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div/div/span")).getText();
	                                int percenNumber = Integer.parseInt(comPercentage.substring(0, comPercentage.indexOf("%")));
	                                String result = applyProcess(percenNumber, offerPage);
	                                String lineToWrite1 = jobProperties + " | " + result;
	                                displayWriteResults(lineToWrite1);
	                            } catch (Exception e2) {
	                                String lineToWrite1 = jobProperties + " | " + "* 🥵 Cannot apply to this Job! " + offerPage;
	                                displayWriteResults(lineToWrite1);
	                            }
	                        }
	                    } 
	                }else {
                        String lineToWrite1 = jobProperties + " | " + "* 🥳 Already applied! Job: " + offerPage;
                        displayWriteResults(lineToWrite1);
                    }
                    utils.prYellow("Category: " + urlWords[0] + "," + urlWords[1] + " applied: " + countApplied +
                            " jobs out of " + countJobs + ".");
                    utils.donate();
	            }
	        }
	      }
	    }
	public String getJobProperties(int count) {
	    String textToWrite = "";
	    String jobTitle = "";
	    String jobCompany = "";
	    String jobLocation = "";
	    String jobWOrkPlace = "";
	    String jobPostedDate = "";
	    String jobApplications = "";

	    try {
	        jobTitle = driver.findElement(By.xpath("//h1[contains(@class, 'job-title')]"))
	                .getAttribute("innerHTML").strip();
	    } catch (Exception e) {
	        utils.prYellow("Warning in getting jobTitle: " + e.getMessage().substring(0, 50));
	        jobTitle = "";
	    }
	    try {
	        jobCompany = driver.findElement(By.xpath("//a[contains(@class, 'ember-view t-black t-normal')]"))
	                .getAttribute("innerHTML").strip();
	    } catch (Exception e) {
	    	utils.prYellow("Warning in getting jobCompany: " + e.getMessage().substring(0, 50));
	        jobCompany = "";
	    }
	    try {
	        jobLocation = driver.findElement(By.xpath("//span[contains(@class, 'bullet')]"))
	                .getAttribute("innerHTML").strip();
	    } catch (Exception e) {
	    	utils.prYellow("Warning in getting jobLocation: " + e.getMessage().substring(0, 50));
	        jobLocation = "";
	    }
	    try {
	        jobWOrkPlace = driver.findElement(By.xpath("//span[contains(@class, 'workplace-type')]"))
	                .getAttribute("innerHTML").strip();
	    } catch (Exception e) {
	    	utils.prYellow("Warning in getting jobWorkPlace: " + e.getMessage().substring(0, 50));
	        jobWOrkPlace = "";
	    }
	    try {
	        jobPostedDate = driver.findElement(By.xpath("//span[contains(@class, 'posted-date')]"))
	                .getAttribute("innerHTML").strip();
	    } catch (Exception e) {
	    	utils.prYellow("Warning in getting jobPostedDate: " + e.getMessage().substring(0, 50));
	        jobPostedDate = "";
	    }
	    try {
	        jobApplications = driver.findElement(By.xpath("//span[contains(@class, 'applicant-count')]"))
	                .getAttribute("innerHTML").strip();
	    } catch (Exception e) {
	    	utils.prYellow("Warning in getting jobApplications: " + e.getMessage().substring(0, 50));
	        jobApplications = "";
	    }

	    textToWrite = count + " | " + jobTitle + " | " + jobCompany + " | " + jobLocation + " | " + jobWOrkPlace + " | "
	            + jobPostedDate + " | " + jobApplications;
	    return textToWrite;
	}

	public WebElement easyApplyButton() {
	    WebElement EasyApplyButton;
	    try {
	        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, \"jobs-apply-button\")]")));
	        EasyApplyButton = button;
	    } catch (Exception e) {
	        EasyApplyButton = null;
	    }

	    return EasyApplyButton;
	}
	public String applyProcess(int percentage, String offerPage) {
	    int applyPages = (int) Math.floor(100.0 / percentage);
	    String result = "";
	    try {
	        for (int pages = 0; pages < applyPages - 2; pages++) {
	            driver.findElement(By.cssSelector("button[aria-label='Continue to next step']")).click();
	            System.out.println("Clicked on next button");
	            Thread.sleep((long) constants.botSpeed * 1000);
	        }

	        driver.findElement(By.cssSelector("button[aria-label='Review your application']")).click();
	        System.out.println("Clicked on review button");
	        Thread.sleep((long) (constants.botSpeed * 2000));

//	        if (!config.followCompanies) {
//	            driver.findElement(By.cssSelector("label[for='follow-company-checkbox']")).click();
//	            Thread.sleep((long) (constants.botSpeed * 1000));
//	        }

	        driver.findElement(By.cssSelector("button[aria-label='Submit application']")).click();
	        System.out.println("Clicked on submit button");
	        Thread.sleep((long) (constants.botSpeed * 1000));

	        result = "* 🥳 Just Applied to this job: " + offerPage;
	    } catch (Exception e) {
	        result = "* 🥵 " + applyPages + " Pages, couldn't apply to this job! Extra info needed. Link: " + offerPage;
	    }
	    return result;
	}

	public void displayWriteResults(String lineToWrite) {
	    try {
	        System.out.println(lineToWrite);
	        utils.writeResults(lineToWrite);
	    } catch (Exception e) {
	        utils.prRed("Error in DisplayWriteResults: " + e.getMessage());
	    }
	}


}
