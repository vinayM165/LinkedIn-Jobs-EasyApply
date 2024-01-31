
public class Config {

	public Config() {
		// TODO Auto-generated constructor stub
	}
	// Optional! run browser in headless mode, no browser screen will be shown it will work in background.
	public static boolean headless = false;
	// If you left above field empty enter your Linkedin password and username below
	// Linkedin credits
	public static String email = ""; //Enter your email
	public static String password = ""; //Enter password
	// These settings are for running Linkedin job apply bot
	public static String LinkedinBotProPasswrod = "";
	// location you want to search the jobs - ex : ["Poland", "Singapore", "New York City Metropolitan Area", "Monroe County"]
	// continent locations:["Europe", "Asia", "Australia", "NorthAmerica", "SouthAmerica", "Africa", "Australia"]
	public static String[] location = {"India"};
	// keywords related with your job search
	public static String[] keywords = {"Java Developer Spring boot", "Java developer microservices", "Java Developer Core Java","Backend development",};
	// keywords = ["programming"]
	//job experience Level - ex:  ["Internship", "Entry level" , "Associate" , "Mid-Senior level" , "Director" , "Executive"]
	public static String[] experienceLevels = {"Internship", "Entry level" , "Associate" };
	//job posted date - ex: ["Any Time", "Past Month" , "Past Week" , "Past 24 hours"] - select only one
	public static String[] datePosted = {"Any Time", "Past Month" , "Past Week" , "Past 24 hours"};
	public static String[] jobType = {"Full-time", "Part-time" , "Contract"};
	public static String[] remote = {"On-site" , "Remote" , "Hybrid"};
	public static String[] salary = {};
	public static String[] sort = {"Recent"};
	public static boolean followCompanies = false;
	public static String country_code = "<PhoneNumber>";
	public static String phone_number = "";
	String LinkedInProfileURL = "www.google.com";
	String Phone = "";
	String Location = "";
	String HowDidYouHeard = "";
	boolean ConsiderMeForFutureOffers = true;



}
