import java.sql.*;
import java.sql.Date;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.text.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

class Tuple<X, Y, Z, A> { 
  final X x; 
  final Y y; 
  final Z z; 
  final A a;
  Tuple(X x, Y y, Z z, A a) { 
    this.x = x; 
    this.y = y;
    this.z = z;
    this.a = a;
  } 
}

class Business {
	String business_id;
	String name;
	String city;
	String state;
	double stars;
	Business(String i, String n, String c, String s, double r) {
		business_id = i;
		name = n;
		city = c;
		state = s;
		stars = r;
	}
}

class Category {
	String business_id;
	String name;
	Category(String i, String n) {
		business_id = i;
		name = n;
	}
}

class SubCategory {
	String business_id;
	String name;
	SubCategory(String i, String n) {
		business_id = i;
		name = n;
	}
}

class Categories {
	String categoryName;
	String subCategoryName;
	Categories(String c, String s) {
		categoryName = c;
		subCategoryName = s;
	}
}

class Review {
	String review_id;
	String business_id;
	String user_id;
	String review_date;
	int stars;
	int votes;
	String text;
	Review(String ri, String bi, String ui, String d, int s, int v, String t) {
		review_id = ri;
		business_id = bi;
		user_id = ui;
		review_date = d;
		stars = s;
		votes = v;
		text = t;
	}
}

class Checkin {
	String business_id;
	int time_day;
	int time_hour;
	int count;
	Checkin(String i, int d, int h, int c) {
		business_id = i;
		time_day = d;
		time_hour = h;
		count = c;
	}
}

class User {
	String user_id;
	String yelping_since;
	String name;
	int review_count;
	int friend_count;
	float average_stars;
	User(String i, String s, String n, int r, int f, float a) {
		user_id = i;
		yelping_since = s;
		name = n;
		review_count = r;
		friend_count = f;
		average_stars = a;
	}
}

public class populate {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	// Database credentials
	static final String USER = "scott";
	static final String PASS = "tiger";

	// Data parsing
	public static Tuple<ArrayList<Business>, ArrayList<Category>, ArrayList<SubCategory>, ArrayList<Categories>> parseBusiness(File file) {
		ArrayList<Business> businesses = new ArrayList<Business>();
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<SubCategory> subCategories = new ArrayList<SubCategory>();
		ArrayList<Categories> mainSubCategories = new ArrayList<Categories>();
		try {
			System.out.println(file.getName() + " - parsing data...");
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				JSONObject obj = new JSONObject(line);
				String business_id = obj.getString("business_id");
				String name = obj.getString("name");
				String city = obj.getString("city");
				String state = obj.getString("state");
				double stars = obj.getDouble("stars");
				businesses.add(new Business(business_id,name,city,state,stars));
				JSONArray categoryArray = obj.getJSONArray("categories");
				ArrayList<String> mainC = new ArrayList<String>();
				ArrayList<String> subC = new ArrayList<String>();
				for(int i=0 ; i<categoryArray.length(); i++) {
					String categoryName = categoryArray.getString(i);
					if (categoryName.equals("Active Life") || categoryName.equals("Arts & Entertainment") || categoryName.equals("Automotive") || categoryName.equals("Car Rental") || categoryName.equals("Cafes") || categoryName.equals("Beauty & Spas") || categoryName.equals("Convenience Stores") || categoryName.equals("Dentists") || categoryName.equals("Doctors") || categoryName.equals("Drugstores") || categoryName.equals("Department Stores") || categoryName.equals("Education") || categoryName.equals("Event Planning & Services") || categoryName.equals("Flowers & Gifts") || categoryName.equals("Food") || categoryName.equals("Health & Medical") || categoryName.equals("Home Services") || categoryName.equals("Home & Garden") || categoryName.equals("Hospitals") || categoryName.equals("Hotels & Travel") || categoryName.equals("Hardware Stores") || categoryName.equals("Grocery") || categoryName.equals("Medical Centers") || categoryName.equals("Nurseries & Gardening") || categoryName.equals("Nightlife") || categoryName.equals("Restaurants") || categoryName.equals("Shopping") || categoryName.equals("Transportation")) {
						categories.add(new Category(business_id,categoryName));
						mainC.add(categoryName);
					} else {
						subCategories.add(new SubCategory(business_id,categoryName));
						subC.add(categoryName);
					}
				}
				for (String item : mainC) {
					for (String item2 : subC) {
						Boolean found = false;
						for (Categories s : mainSubCategories) {
							if (s.categoryName.equals(item) && s.subCategoryName.equals(item2)) {
								found = true;
								break;
							}
						}
						if (!found) {
							mainSubCategories.add(new Categories(item, item2));
						}
					}
				}
			}
			System.out.println(file.getName() + " - " + businesses.size() + " businesses parsed!");
			System.out.println(file.getName() + " - " + categories.size() + " categories parsed!");
			System.out.println(file.getName() + " - " + subCategories.size() + " subCategories parsed!");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Tuple<ArrayList<Business>, ArrayList<Category>, ArrayList<SubCategory>, ArrayList<Categories>>(businesses, categories, subCategories, mainSubCategories);
	}

	public static ArrayList<Review> parseReview(File file) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			System.out.println(file.getName() + " - parsing data...");
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				JSONObject obj = new JSONObject(line);
				String review_id = obj.getString("review_id");
				String business_id = obj.getString("business_id");
				String user_id = obj.getString("user_id");
				String review_date = obj.getString("date");
				int stars = obj.getInt("stars");
				JSONObject review_votes = obj.getJSONObject("votes");
				int votes = review_votes.getInt("useful") + review_votes.getInt("funny") + review_votes.getInt("cool");
				String text = obj.getString("text");
				reviews.add(new Review(review_id, business_id, user_id, review_date, stars, votes, text));
			}
			System.out.println(file.getName() + " - " + reviews.size() + " reviews parsed!");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return reviews;
	}

	public static ArrayList<Checkin> parseCheckin(File file) {
		ArrayList<Checkin> checkins = new ArrayList<Checkin>();
		try {
			System.out.println(file.getName() + " - parsing data...");
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				JSONObject obj = new JSONObject(line);
				String business_id = obj.getString("business_id");
				JSONObject time_slots = obj.getJSONObject("checkin_info");
				for(int i=0; i<time_slots.names().length(); i++) {
					String[] timeAndDay = time_slots.names().getString(i).split("-");
					int time_day = Integer.parseInt(timeAndDay[0]);
					int time_hour = Integer.parseInt(timeAndDay[1]);
					int count = time_slots.getInt(time_slots.names().getString(i));
					checkins.add(new Checkin(business_id, time_day, time_hour, count));
				}
			}
			System.out.println(file.getName() + " - " + checkins.size() + " checkins parsed!");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return checkins;
	}

	public static ArrayList<User> parseUser(File file) {
		ArrayList<User> users = new ArrayList<User>();
		try {
			System.out.println(file.getName() + " - parsing data...");
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				JSONObject obj = new JSONObject(line);
				String user_id = obj.getString("user_id");
				String yelping_since = obj.getString("yelping_since");
				String name = obj.getString("name");
				int review_count = obj.getInt("review_count");
				JSONArray friends = obj.getJSONArray("friends");
				int friend_count = friends.length();
				float average_stars = (float) obj.getDouble("average_stars");
				users.add(new User(user_id,yelping_since,name,review_count,friend_count,average_stars));
			}
			System.out.println(file.getName() + " - " + users.size() + " users parsed!");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return users;
	}

	public static void main(String[] argv) {

		// Data sets
		ArrayList<Business> businesses = new ArrayList<Business>();
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<SubCategory> subCategories = new ArrayList<SubCategory>();
		ArrayList<Categories> mainSubCategories = new ArrayList<Categories>();
		ArrayList<Review> reviews = new ArrayList<Review>();
		ArrayList<Checkin> checkins = new ArrayList<Checkin>();
		ArrayList<User> users = new ArrayList<User>();

		// PREPARE DATA
		System.out.println("Preparing data...");
		// file input order
		// yelp_business.json yelp_review.json yelp_checkin.json yelp_user.json
		if (argv.length >= 4) {
			File business = new File(argv[0]);
			Tuple<ArrayList<Business>,ArrayList<Category>,ArrayList<SubCategory>,ArrayList<Categories>> tuple = parseBusiness(business);
			businesses = tuple.x;
			categories = tuple.y;
			subCategories = tuple.z;
			mainSubCategories = tuple.a;
			File review = new File(argv[1]);
			reviews = parseReview(review);
			File checkin = new File(argv[2]);
			checkins = parseCheckin(checkin);
			File user = new File(argv[3]);
			users = parseUser(user);
		} else {
			System.out.println("Check your data input, terminated!");
			return;
		}

		// TESTING DRIVER
		System.out.println("JDBC Connection Testing...");
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Missing Oracle JDBC Driver!");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle JDBC Driver Registered!");

		// TESTING CONNECTION
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Connection successfully established!");
			} else {
				conn.close();
				System.out.println("Cannot establish a connection!");
				return;
			}
		} catch (SQLException e) {
			System.out.println("Cannot establish a connection!");
			e.printStackTrace();
			return;
		}

		// CLEAR TABLES
		Statement stmt = null;

		try {
			System.out.println("Checkin - clearing table!");
			stmt = conn.createStatement();
			String sql = "DELETE FROM Checkin";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Checkin - failed clearing!");
			e.printStackTrace();
		}
		System.out.println("Checkin - cleared!");

		try {
			System.out.println("Review - clearing table!");
			stmt = conn.createStatement();
			String sql = "DELETE FROM Review";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Review - failed clearing!");
			e.printStackTrace();
		}
		System.out.println("Review - cleared!");

		try {
			System.out.println("Categories Relation - clearing table!");
			stmt = conn.createStatement();
			String sql = "DELETE FROM Categories";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Categories Relation - failed clearing!");
			e.printStackTrace();
		}
		System.out.println("Categories Relation - cleared!");

		try {
			System.out.println("SubCategory - clearing table!");
			stmt = conn.createStatement();
			String sql = "DELETE FROM SubCategory";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SubCategory - failed clearing!");
			e.printStackTrace();
		}
		System.out.println("SubCategory - cleared!");

		try {
			System.out.println("Category - clearing table!");
			stmt = conn.createStatement();
			String sql = "DELETE FROM Category";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Category - failed clearing!");
			e.printStackTrace();
		}
		System.out.println("Category - cleared!");

		try {
			System.out.println("Business - clearing table!");
			stmt = conn.createStatement();
			String sql = "DELETE FROM Business";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Business - failed clearing!");
			e.printStackTrace();
		}
		System.out.println("Business - cleared!");

		try {
			System.out.println("YelpUser - clearing table!");
			stmt = conn.createStatement();
			String sql = "DELETE FROM YelpUser";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("YelpUser - failed clearing!");
			e.printStackTrace();
		}
		System.out.println("YelpUser - cleared!");
		
		// INSERT INTO TABLES
		PreparedStatement pstmt = null;

		try {
			System.out.println("Business - inserting entries!");
			String sql =	"INSERT INTO Business\n" +
							"(business_id, name, city, state, stars)\n" +
							"VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (Business b : businesses) {
				pstmt.setString(1, b.business_id);
				pstmt.setString(2, b.name);
				pstmt.setString(3, b.city);
				pstmt.setString(4, b.state);
				pstmt.setDouble(5, b.stars);
				pstmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println("Business - failed inserting!");
			e.printStackTrace();
		}
		System.out.println("Business - inserted!");

		try {
			System.out.println("Category - inserting entries!");
			String sql =	"INSERT INTO Category\n" +
							"(business_id, name)\n" +
							"VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (Category c : categories) {
				pstmt.setString(1, c.business_id);
				pstmt.setString(2, c.name);
				pstmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println("Category - failed inserting!");
			e.printStackTrace();
		}
		System.out.println("Category - inserted!");

		try {
			System.out.println("SubCategory - inserting entries!");
			String sql =	"INSERT INTO SubCategory\n" +
							"(business_id, name)\n" +
							"VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (SubCategory s : subCategories) {
				pstmt.setString(1, s.business_id);
				pstmt.setString(2, s.name);
				pstmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println("SubCategory - failed inserting!");
			e.printStackTrace();
		}
		System.out.println("SubCategory - inserted!");

		try {
			System.out.println("Categories Relation - inserting entries!");
			String sql =	"INSERT INTO Categories\n" +
							"(categoryName, subCategoryName)\n" +
							"VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			Set<Categories> rs = new HashSet<Categories>(mainSubCategories);
			for (Categories r : rs) {
				pstmt.setString(1, r.categoryName);
				pstmt.setString(2, r.subCategoryName);
				pstmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println("Categories Relation - failed inserting!");
			e.printStackTrace();
		}
		System.out.println("Categories Relation - inserted!");

		try {
			System.out.println("Review - inserting entries!");
			String sql =	"INSERT INTO Review\n" +
							"(review_id, business_id, user_id, review_date, stars, votes, text)\n" +
							"VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (Review r : reviews) {
				pstmt.setString(1, r.review_id);
				pstmt.setString(2, r.business_id);
				pstmt.setString(3, r.user_id);
				pstmt.setDate(4, (new Date(0)).valueOf(r.review_date));
				pstmt.setInt(5, r.stars);
				pstmt.setInt(6, r.votes);
				pstmt.setString(7, r.text);
				pstmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println("Review - failed inserting!");
			e.printStackTrace();
		}
		System.out.println("Review - inserted!");

		try {
			System.out.println("Checkin - inserting entries!");
			String sql =	"INSERT INTO Checkin\n" +
							"(business_id, time_day, time_hour, count)\n" +
							"VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (Checkin c : checkins) {
				pstmt.setString(1, c.business_id);
				pstmt.setInt(2, c.time_day);
				pstmt.setInt(3, c.time_hour);
				pstmt.setInt(4, c.count);
				pstmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println("Checkin - failed inserting!");
			e.printStackTrace();
		}
		System.out.println("Checkin - inserted!");

		try {
			System.out.println("YelpUser - inserting entries!");
			String sql =	"INSERT INTO YelpUser\n" +
							"(user_id, yelping_since, name, review_count, friend_count, average_stars)\n" +
							"VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (User u : users) {
				pstmt.setString(1, u.user_id);
				pstmt.setDate(2, (new Date(0)).valueOf(u.yelping_since+"-1"));
				pstmt.setString(3, u.name);
				pstmt.setInt(4, u.review_count);
				pstmt.setInt(5, u.friend_count);
				pstmt.setFloat(6, u.average_stars);
				pstmt.executeQuery();
			}
		} catch (SQLException e) {
			System.out.println("YelpUser - failed inserting!");
			e.printStackTrace();
		}
		System.out.println("YelpUser - inserted!");

		// CLOSE CONNECTION
		try {
			conn.close();
			System.out.println("All jobs done, terminated!");
			return;
		} catch(SQLException e) {
			System.out.println("Cannot close the connection!");
			e.printStackTrace();
			return;
		}
	}
}