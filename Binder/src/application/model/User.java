package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
/**
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * This object represents a user for binder application. Contains course list, assignment list, completed list, 
 * a userHashMap, and user name, display name as well as a password for each user. 
 */
public class User {
	
	private ArrayList<Course> courseList;
	private String userName;
	private String displayName;
	private String password; 
	private ArrayList<Assignment> assignmentList;
	private ArrayList<Assignment> completedList;
	private ObservableList<Assignment> assignmentTableList;
	private ObservableList<Assignment> completedTableList;
	private HashMap<String, String> userHashMap;
/**
 * Constructor for User object 
 * @param userName String username for User
 * @param password String password for User
 */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.displayName = userName;
		courseList = new ArrayList<Course>();
		assignmentList = new ArrayList<Assignment>();
		completedList = new ArrayList<Assignment>();
		userHashMap = new HashMap<String, String>();
	}
/**
 * Method to check if User exists
 * @param username String to search username with
 * @param password String to search password with
 * @return Main.user
 */
public static User authenticate(String username, String password) {
		
		//User profile = null;
		String accountFile = "users.txt";
		/**
		 * Everything below is for scanning in the two files and creating the proper 
		 * objects, then adding them to the correct ArrayLists.
		 */
		try {
			Scanner scan = new Scanner( new File(accountFile) );
			while( scan.hasNextLine() ) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if(tokens[0].equals(username)) {
					if(tokens[1].equals(password)) {
						Main.user = new User(username, password);
						return Main.user;
					}
					else { //if password isn't correct
						Main.user = null;
					}
				}
				else { //if no username is found
					Main.user = null;
				}
			}
			scan.close();
	
		}catch( FileNotFoundException e) {
			System.out.println("Error: Could not find file.");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return Main.user;
	}

	/**
	 * Method to read in data and populate already existing user 
	 */
	public void readData() {
		String fileName = "UserData/" + this.getUserName();
		String data = "";
		int count = 0;
		try {
			Scanner scan = new Scanner(new File(fileName));
			
			while(scan.hasNextLine()) {
			
				data = scan.nextLine();
				System.out.println(data);
				String[] tokens = data.split(",");
				for(int i = 0; i < tokens.length; i++) {
					//System.out.println(i);
					//System.out.println(tokens[i]);
				}
				
				if(tokens[0].equals("DISPLAYNAME"))
				{
					System.out.println("in display");
					this.setDisplayName(tokens[1]);
					System.out.println("displayname set");
				}
				
				if(tokens[0].equals("COURSE"))
				{
					System.out.println("In COURSE");
					Course course = new Course(tokens[1]);
					System.out.println("course made");
					if(!courseList.isEmpty())
						count++;
					System.out.println("count updated: " + count);

					this.addCourse(course);
					System.out.println("course added");

				}
				
				if(tokens[0].equals("ASSIGNMENT")) {
					System.out.println("IN ASSIGN");
					Assignment assign = new Assignment(tokens[1],tokens[2],tokens[3]);
					//System.out.println("ASSIGN CREATED");
					System.out.println(count);
					
					this.courseList.get(count).addAssignment(assign);
					
					//System.out.println("OUT ASSIGN");
					
				}
				if(tokens[0].equals("COMPLETED")) {
					System.out.println("IN COMPLETE");
					Assignment assign = new Assignment(tokens[1],tokens[2],tokens[3]);
					this.courseList.get(count).addCompleted(assign);
				}
				
			}
			this.setListData();
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("No user data");
			//e.printStackTrace();
		}
	}
	
	/**
	 * Method to write and save a user profile
	 */
	public void writeData() {
		String path = String.format("./UserData/%s", this.getUserName());
		try {
			Formatter output = new Formatter(path);
			output.format("DISPLAYNAME,%s\n", this.displayName);
			for(int i = 0; i < courseList.size(); i++) {
				output.format("COURSE,%S\n", courseList.get(i).getName());
				System.out.printf("COURSE,%S\n", courseList.get(i).getName());
				
				for(int j = 0; j < courseList.get(i).getAssignmentList().size(); j++) {
					output.format("ASSIGNMENT,%s,%s,%s\n",  courseList.get(i).getAssignmentList().get(j).getName(),
							courseList.get(i).getAssignmentList().get(j).getDate(),courseList.get(i).getAssignmentList().get(j).getCourse());
					
					System.out.printf("ASSIGNMENT,%s,%s,%s\n",
							courseList.get(i).getAssignmentList().get(j).getName(),courseList.get(i).getAssignmentList().get(j).getDate(),
							courseList.get(i).getAssignmentList().get(j).getCourse());
				}
			}
			for(int j = 0; j < this.getCompletedList().size(); j++){
				output.format("COMPLETED,%s,%s,%s\n",completedList.get(j).getName(),completedList.get(j).getDate(),completedList.get(j).getCourse());
			}
			
			if(output != null)
				output.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * This method checks to see if a Username exists
	 * @param username to search (String)
	 * @return boolean true or false
	 */
	public boolean checkUsername(String username) {
		try {
			Scanner scan = new Scanner(new File("users.txt"));
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				
				String[] token = line.split(",");
				
				if(token[0].equals(username)) {
					System.out.println("username already exists");
					return false;
				}
			
			}
			scan.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	/** 
	 * Method to get user's weekly assignments 
	 * @return ObservableList user's weekly assignments 
	 */
	public ObservableList<Assignment> weeklyAssignments(){
		ObservableList<Assignment> weekList = FXCollections.observableArrayList();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime()); //2016/11/16 12:08:43
		
		
		for(int i = 0; i < 3; i++) {
			for(Assignment name : assignmentList) {
				if(name.getDate().equals(dateFormat.format(cal.getTime()).toString()))
				{
					weekList.add(name);
					System.out.println(name.getName() + " " + name.getDate());
				}
			}
			cal.add(Calendar.DATE,1);
		}
		
		return weekList;
	}
	
	/**
	 * Method to get list of assignments 
	 * @return ObservableList list of Assignments 
	 */
	public ObservableList<Assignment> getAssignmentTableList(){
		 assignmentTableList = FXCollections.observableArrayList();
	
		for(int i = 0; i < assignmentList.size(); i++) {
			assignmentTableList.add(assignmentList.get(i));
		}

		return assignmentTableList;
	}	
	
	/**
	 * Method to get completed assignment list
	 * @return ObsivableList list of completed assignments 
	 */
	public ObservableList<Assignment> getCompletedTableList(){
		completedTableList = FXCollections.observableArrayList();
	
		for(int i = 0; i < completedList.size(); i++) {
			completedTableList.add(completedList.get(i));
		}

		return completedTableList;
	}	
	
	/**
	 * Method to get data to populate pie chart
	 * contains completed list, uncompleted assignments, and the total of the two 
	 * @return ObservableList data 
	 */
	public ObservableList<PieChart.Data> pieData() {
		double completed = this.completedList.size();
		double assignments = this.assignmentList.size();
		double total = completed + assignments;
		double completedPerc = ((completed/total)*100);
		double assignPerc = ((assignments/total)*100);
		
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList(new PieChart.Data("Completed", completedPerc), 
				new PieChart.Data("In Progress", assignPerc));
		
		return data;
	}
	
	/**
	 * Method to set Assignment list and completed assignment list
	 */
	public void setListData() {
		
		for(int i = 0; i < courseList.size(); i++) {
			for(int j = 0; j < courseList.get(i).getAssignmentList().size(); j++) {
				this.assignmentList.add(courseList.get(i).getAssignmentList().get(j));
				//System.out.println(courseList.get(i).getAssignmentList().get(i));
			}
			for(int j = 0; j < courseList.get(i).getCompletedAssignment().size(); j++){
				this.completedList.add(courseList.get(i).getCompletedAssignment().get(j));
			}
		}
	}
	
	/**
	 * Returns an ObservableList of courses. Used for the course choice box
	 * on Add An Assignment
	 * @return ObservableList courses
	 */
	public ObservableList<String> getObservableCourseList() {
		ObservableList<String> courses = FXCollections.observableArrayList();
		for (Course name: courseList) {
			courses.add(name.getName());
		}
		return courses;
		
	}
	
	/**
	 * Method returns list of courses 
	 * @return ObservableList list of courses from User
	 */
	public ObservableList<Course> getCourseTable() {
		ObservableList<Course> courses = FXCollections.observableArrayList();
		for (Course name: courseList) {
			courses.add(name);
		}
		return courses;
		
	}
	
	/**
	 * Method to remove specified assignment from user's assignment list
	 * @param assign Assignment to remove from User
	 */
	public void removeAssignment(Assignment assign) {
		for(Course course : this.getCourseList()) {
			if(course.getName().equals(assign.getCourse())) {
				course.removeAssignment(assign);
				System.out.println(course.getAssignmentList());
			}
		}
		this.assignmentList.remove(assign);
	}
	
	/**
	 * Method to remove course from user's course list
	 * @param course Course to remove
	 */
	public void removeCourse(Course course) {
		for(Assignment assign : this.getCompletedList()) {
			if(assign.getCourse().equals(course.getName()))
				this.completedList.remove(assign);
		}
		this.courseList.remove(course);
		this.assignmentList.clear();
		this.setListData();
	}
	
	/**
	 * Method to remove Assignment from user's completed assignment list
	 * @param assignment Assignment to remove 
	 */
	public void removeCompletedAssignment(Assignment assignment) {
		this.completedList.remove(assignment);
	}
	
	
	/**
	 * Method to add a course to the courseList
	 * @param course to add (Course)
	 */
	public void addCourse(Course course) {
		courseList.add(course);
		//this.writeData();
	}
	
	/**
	 * Method to get user's courseLists
	 * @return ArrayList courseList to return
	 */
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	
	/**
	 * Method to set courseList
	 * @param courseList ArrayList course list to set
	 */
	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Method to get user name
	 * @return String user name 
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Method to set user's username 
	 * @param userName String to set username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Method to get String password
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Method to set password
	 * @param password String to set password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Method to get user's assignment list 
	 * @return ArrayList user's assignment list 
	 */
	public ArrayList<Assignment> getAssignmentList() {
		return assignmentList;
	}
	
	/**
	 * Method to set the user assignmentList
	 * @param assignmentList to set (ArrayList)
	 */
	public void setAssignmentList(ArrayList<Assignment> assignmentList) {
		this.assignmentList = assignmentList;
	}
	
	/**
	 * Method to add assignment to user's assignment list
	 * @param assign Assignment to add 
	 */
	public void addAssignment(Assignment assign) {
		assignmentList.add(assign);
		
	}
	
	/**
	 * Method to add Assignment object to usere's completed list 
	 * @param assign Assignment to add
	 */
	public void addCompleted(Assignment assign) {
		completedList.add(assign);
		
	}
	
	/**
	 * @return completedList
	 */
	public ArrayList<Assignment> getCompletedList() {
		return completedList;
	}
	
	/**
	 * @param completedList ArrayList to set completed list
	 */
	public void setCompletedList(ArrayList<Assignment> completedList) {
		this.completedList = completedList;
	}
	
	/**
	 * @param assignmentTableList ObservableList to set assignment table 
	 */
	public void setAssignmentTableList(ObservableList<Assignment> assignmentTableList) {
		this.assignmentTableList = assignmentTableList;
	}
	
	/**
	 * @param completedTableList ObservableList to set completed table 
	 */
	public void setCompletedTableList(ObservableList<Assignment> completedTableList) {
		this.completedTableList = completedTableList;
	}
	
	/**
	 * @return String display name
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * @param displayName String to set display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Method to initialize HashMap to save user information
	 */
	public void initializeHashMap()
	{
		try {
			Scanner scan = new Scanner(new File("users.txt"));
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				userHashMap.put(tokens[0], tokens[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * method to save user file
	 */
	public void writeMaptoFile()
	{
		Formatter output;
		try {
			output = new Formatter("users.txt");  //clears file every time
			for (Map.Entry<String, String> entry : userHashMap.entrySet())
			{
				String username = entry.getKey();
				String password = entry.getValue();
				String line = username + "," + password +"\n";
				System.out.println(line);
				output.format(line);
				System.out.println(line);
			}
			if(output != null)
				output.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return HashMap user's information
	 */
	public HashMap<String, String> getUserHashMap()
	{
		return this.userHashMap;
	}
	
	
}
