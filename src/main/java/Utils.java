import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utils {
	Config config = new Config();
	Constants constants = new Constants();	
	public Utils() {
		// TODO Auto-generated constructor stub
	}
	public  ChromeOptions browserOptions() {
	    ChromeOptions options = new ChromeOptions();
	   // String firefoxProfileRootDir = config.firefoxProfileRootDir;
	    options.addArguments("--start-maximized");
	    options.addArguments("--ignore-certificate-errors");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-extensions");
	    options.addArguments("--disable-gpu");
	    if (config.headless) {
	        options.addArguments("--headless");
	    }
	    options.addArguments("--disable-blink-features");
	    options.addArguments("--disable-blink-features=AutomationControlled");
	    options.addArguments("--incognito");
	    options.addArguments("-profile");
	    //options.addArguments(firefoxProfileRootDir);

	    return options;
	}

	public  void prRed(String prt) {
	    System.out.println("\033[91m" + prt + "\033[00m");
	}

	public  void prGreen(String prt) {
	    System.out.println("\033[92m" + prt + "\033[00m");
	}

	public  void prYellow(String prt) {
	    System.out.println("\033[93m" + prt + "\033[00m");
	}

	public  String[] getUrlDataFile() {
	    String[] urlData = new String[0];
	    try {
	        urlData = Files.readAllLines(Paths.get("data/urlData.txt")).toArray(new String[0]);
	    } catch (FileNotFoundException e) {
	        String text = "FileNotFound:urlData.txt file is not found. Please run ./data folder exists and check config.py values of yours. Then run the bot again";
	        prRed(text);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return urlData;
	}

	public int jobsToPages(String numOfJobs) {
	    int number_of_pages = 1;

	    if (numOfJobs.contains(" ")) {
	        int spaceIndex = numOfJobs.indexOf(" ");
	        String totalJobs = numOfJobs.substring(0, spaceIndex);
	        int totalJobs_int = Integer.parseInt(totalJobs.replace(",", ""));
	        number_of_pages = (int) Math.ceil(totalJobs_int / (double) constants.jobsPerPage);
	        if (number_of_pages > 40)
	            number_of_pages = 40;

	    } else {
	        number_of_pages = Integer.parseInt(numOfJobs);
	    }

	    return number_of_pages;
	}

	public String[] urlToKeywords(String url) {
	    String keywordUrl = url.substring(url.indexOf("keywords=") + 9);
	    String keyword = keywordUrl.substring(0, keywordUrl.indexOf("&"));
	    String locationUrl = url.substring(url.indexOf("location=") + 9);
	    String location = locationUrl.substring(0, locationUrl.indexOf("&"));
	    return new String[]{keyword, location};
	}
	public void writeResults(String text) {
	    String timeStr = new SimpleDateFormat("yyyyMMdd").format(new Date(0));
	    String fileName = "Applied Jobs DATA - " + timeStr + ".txt";
	    try (BufferedReader file = Files.newBufferedReader(Paths.get("data/" + fileName), StandardCharsets.UTF_8)) {
	        List<String> lines = new ArrayList<>();
	        String line;
	        while ((line = file.readLine()) != null) {
	            if (!line.contains("----")) {
	                lines.add(line);
	            }
	        }
	        try (BufferedWriter f = Files.newBufferedWriter(Paths.get("data/" + fileName), StandardCharsets.UTF_8)) {
	            f.write("---- Applied Jobs Data ---- created at: " + timeStr + "\n");
	            f.write("---- Number | Job Title | Company | Location | Work Place | Posted Date | Applications | Result " + "\n");
	            for (String l : lines) {
	                f.write(l + "\n");
	            }
	            f.write(text + "\n");
	        }
	    } catch (Exception e) {
	        try (BufferedWriter f = Files.newBufferedWriter(Paths.get("data/" + fileName), StandardCharsets.UTF_8)) {
	            f.write("---- Applied Jobs Data ---- created at: " + timeStr + "\n");
	            f.write("---- Number | Job Title | Company | Location | Work Place | Posted Date | Applications | Result " + "\n");

	            f.write(text + "\n");
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
	public  void printInfoMes(String bot) {
	    prYellow("ℹ️ " + bot + " is starting soon... ");
	}

	public void donate() {
	    prYellow("If you like the project, please support me so that i can make more such projects, thanks!");
	    try {
	       // driver.get("https://commerce.coinbase.com/checkout/576ee011-ba40-47d5-9672-ef7ad29b1e6c");
	    } catch (Exception e) {
	        prRed("Error in donate: " + e.getMessage());
	    }
	}

}
