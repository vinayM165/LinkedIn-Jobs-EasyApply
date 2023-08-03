
public class Config {

	public Config() {
		// TODO Auto-generated constructor stub
	}
	
	// Optional! run browser in headless mode, no browser screen will be shown it will work in background.
	boolean headless = false;
	// Optional! for Firefox enter profile dir to run the bot without logging in your account each time
	String firefoxProfileRootDir = "";
	// If you left above field empty enter your Linkedin password and username below
	// Linkedin credits
	String email = "mandgevinay16@gmail.com";
	String password = "Vkshimpi16599";

	// These settings are for running Linkedin job apply bot
	String LinkedinBotProPasswrod = "";
	// location you want to search the jobs - ex : ["Poland", "Singapore", "New York City Metropolitan Area", "Monroe County"]
	// continent locations:["Europe", "Asia", "Australia", "NorthAmerica", "SouthAmerica", "Africa", "Australia"]
	String[] location = {"India"};
	// keywords related with your job search
	String[] keywords = {"Java Developer Spring boot", "Java developer microservices", "Java Developer Core Java","Backend development",};
	// keywords = ["programming"]
	//job experience Level - ex:  ["Internship", "Entry level" , "Associate" , "Mid-Senior level" , "Director" , "Executive"]
	String[] experienceLevels = {"Internship", "Entry level" , "Associate" };
	//job posted date - ex: ["Any Time", "Past Month" , "Past Week" , "Past 24 hours"] - select only one
	String[] datePosted = {"Any Time", "Past Month" , "Past Week" , "Past 24 hours"};
	String[] jobType = {"Full-time", "Part-time" , "Contract"};
	String[] remote = {"On-site" , "Remote" , "Hybrid"};
	String[] salary = { "$80,000+"};
	String[] sort = {"Recent"};
	String[] blacklist = {"EPAM Anywhere"};
	String[] blackListTitles = {""};
	String[] onlyApply = {""};
	String[] onlyApplyTitles = {""};
	boolean followCompanies = false;
	String country_code = "<PhoneNumber>";
	String phone_number = "";
	String AngelCoBotPassword = "";
	String AngelCoEmail = "";
	String AngelCoPassword = "";
	String[] angelCoJobTitle = {"Frontend Engineer"};
	String[] angelCoLocation = {"Poland"};
	String GlobalLogicBotPassword = "";
	String GlobalLogicEmail = "";
	String GlobalLogicPassword = "";
	String[] GlobalLogicFunctions = {"Engineering"};
	String[] GlobalLogicExperience = {"0-1 years", "<PhoneNumber>"};
	String[] GlobalLogicLocation = {"poland"};
	String[] GlobalLogicFreelance = {"no"};
	String[] GlobalLogicRemoteWork = {"yes"};
	String[] GlobalLogicKeyword = {"react"};
	String FirstName = "<EUGPSCoordinates>";
	String LastName = "<EUGPSCoordinates>";
	String Email = "<Email>";
	String LinkedInProfileURL = "www.google.com";
	String Phone = "";
	String Location = "";
	String HowDidYouHeard = "";
	boolean ConsiderMeForFutureOffers = true;



}
