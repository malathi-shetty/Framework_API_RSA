package oAuth_Courses;

public class Pojo_GetCourse {
	
	//variables
	private String url;
	private String services;
	private String expertise;
	private String instructor;
	private String linkedIn;
	private Pojo_Courses courses; // inject Pojo_Courses class into courses class
	
	//getters and setters = Alt + Shift + S for above all variables & select generate setters and getters & select all variables
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public Pojo_Courses getCourses() {
		return courses;
	}
	public void setCourses(Pojo_Courses courses) {
		this.courses = courses;
	}
	
	
			
	
	

}
