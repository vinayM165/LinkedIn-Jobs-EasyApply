import java.util.ArrayList;
import java.util.List;

public class LinkednUrlGenerate {
	Config config = new Config();
	Constants constants = new Constants();
	public List<String> generateUrlLinks() {
        List<String> path = new ArrayList<>();
        for (String location : config.location) {
            for (String keyword : config.keywords) {
                String url = constants.linkJobUrl + "?f_AL=true&keywords=" + keyword + jobType() + remote() + checkJobLocation(location) + jobExp() + datePosted() + sortBy();
                path.add(url);
            }
        }
        return path;
    }
	public String checkJobLocation(String job) {
	    String jobLoc = "&location=" + job + "&geoId=102713980";
	    return jobLoc;
	}
	
	public String jobExp() {
	    String[] jobtExpArray = config.experienceLevels;
	    String firstJobExp = jobtExpArray[0];
	    String jobExp = "";
	    switch (firstJobExp) {
	        case "Internship":
	            jobExp = "&f_E=1";
	            break;
	        case "Entry level":
	            jobExp = "&f_E=2";
	            break;
	        case "Associate":
	            jobExp = "&f_E=3";
	            break;
	        case "Mid-Senior level":
	            jobExp = "&f_E=4";
	            break;
	        case "Director":
	            jobExp = "&f_E=5";
	            break;
	        case "Executive":
	            jobExp = "&f_E=6";
	            break;
	    }
	    for (int index = 1; index < jobtExpArray.length; index++) {
	        switch (jobtExpArray[index]) {
	            case "Internship":
	                jobExp += "%2C1";
	                break;
	            case "Entry level":
	                jobExp += "%2C2";
	                break;
	            case "Associate":
	                jobExp += "%2C3";
	                break;
	            case "Mid-Senior level":
	                jobExp += "%2C4";
	                break;
	            case "Director":
	                jobExp += "%2C5";
	                break;
	            case "Executive":
	                jobExp += "%2C6";
	                break;
	        }
	    }
	    return jobExp;
	}
	public String datePosted() {
	    String datePosted = "";
	    switch (config.datePosted[0]) {
	        case "Any Time":
	            datePosted = "";
	            break;
	        case "Past Month":
	            datePosted = "&f_TPR=r2592000&";
	            break;
	        case "Past Week":
	            datePosted = "&f_TPR=r604800&";
	            break;
	        case "Past 24 hours":
	            datePosted = "&f_TPR=r86400&";
	            break;
	    }
	    return datePosted;
	}
	public String jobType() {
	    String[] jobTypeArray = config.jobType;
	    String firstjobType = jobTypeArray[0];
	    String jobType = "";
	    switch (firstjobType) {
	        case "Full-time":
	            jobType = "&f_JT=F";
	            break;
	        case "Part-time":
	            jobType = "&f_JT=P";
	            break;
	        case "Contract":
	            jobType = "&f_JT=C";
	            break;
	        case "Temporary":
	            jobType = "&f_JT=T";
	            break;
	        case "Volunteer":
	            jobType = "&f_JT=V";
	            break;
	        case "Intership":
	            jobType = "&f_JT=I";
	            break;
	        case "Other":
	            jobType = "&f_JT=O";
	            break;
	    }
	    for (int index = 1; index < jobTypeArray.length; index++) {
	        switch (jobTypeArray[index]) {
	            case "Full-time":
	                jobType += "%2CF";
	                break;
	            case "Part-time":
	                jobType += "%2CP";
	                break;
	            case "Contract":
	                jobType += "%2CC";
	                break;
	            case "Temporary":
	                jobType += "%2CT";
	                break;
	            case "Volunteer":
	                jobType += "%2CV";
	                break;
	            case "Intership":
	                jobType += "%2CI";
	                break;
	            case "Other":
	                jobType += "%2CO";
	                break;
	        }
	    }
	    jobType += "&";
	    return jobType;
	}

	public String remote() {
	    String[] remoteArray = config.remote;
	    String firstJobRemote = remoteArray[0];
	    String jobRemote = "";
	    switch (firstJobRemote) {
	        case "On-site":
	            jobRemote = "f_WT=1";
	            break;
	        case "Remote":
	            jobRemote = "f_WT=2";
	            break;
	        case "Hybrid":
	            jobRemote = "f_WT=3";
	            break;
	    }
	    for (int index = 1; index < remoteArray.length; index++) {
	        switch (remoteArray[index]) {
	            case "On-site":
	                jobRemote += "%2C1";
	                break;
	            case "Remote":
	                jobRemote += "%2C2";
	                break;
	            case "Hybrid":
	                jobRemote += "%2C3";
	                break;
	        }
	    }
	    return jobRemote;
	}
	public String salary() {
	    String salary = "";
	    switch (config.salary[0]) {
	        case "$40,000+":
	            salary = "f_SB2=1&";
	            break;
	        case "$60,000+":
	            salary = "f_SB2=2&";
	            break;
	        case "$80,000+":
	            salary = "f_SB2=3&";
	            break;
	        case "$100,000+":
	            salary = "f_SB2=4&";
	            break;
	        case "$120,000+":
	            salary = "f_SB2=5&";
	            break;
	        case "$140,000+":
	            salary = "f_SB2=6&";
	            break;
	        case "$160,000+":
	            salary = "f_SB2=7&";
	            break;
	        case "$180,000+":
	            salary = "f_SB2=8&";
	            break;
	        case "$200,000+":
	            salary = "f_SB2=9&";
	            break;
	    }
	    return salary;
	}
	public String sortBy() {
	    String sortBy = "";
	    switch (config.sort[0]) {
	        case "Recent":
	            sortBy = "sortBy=DD";
	            break;
	        case "Relevent":
	            sortBy = "sortBy=R";
	            break;
	    }
	    return sortBy;
	}

}
