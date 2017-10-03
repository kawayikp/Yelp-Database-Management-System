-- table creations
CREATE TABLE YelpUser (
	user_id			VARCHAR(50),
	yelping_since	VARCHAR(20),
	name 			VARCHAR(100) NOT NULL,
	review_count	INTEGER,
	friend_count	INTEGER,
	average_stars	NUMBER,
	PRIMARY KEY (user_id)
);

CREATE TABLE Business (
	business_id 	VARCHAR(50),
	name			VARCHAR(100) NOT NULL,
	city			VARCHAR(50),
	state			VARCHAR(30),
	stars 			NUMBER,
	PRIMARY KEY (business_id)
);

CREATE TABLE Category (
	business_id		VARCHAR(50),
	name 			VARCHAR(100),
	PRIMARY KEY (business_id, name),
	FOREIGN KEY (business_id) REFERENCES Business 
);

CREATE TABLE SubCategory (
	business_id		VARCHAR(50),
	name 			VARCHAR(100),
	PRIMARY KEY (business_id, name),
	FOREIGN KEY (business_id) REFERENCES Business 
);

CREATE TABLE Review(
	review_id		VARCHAR(50),
	user_id			VARCHAR(50),
	business_id		VARCHAR(50) NOT NULL,
	review_date		VARCHAR(20),
	stars 			INTEGER,
	votes			INTEGER,
	content			LONG,
	PRIMARY KEY (review_id),
	FOREIGN KEY (business_id) REFERENCES Business(business_id),
 	FOREIGN KEY (user_id) REFERENCES YelpUser(user_id)
);

CREATE TABLE Checkin (
	business_id 	VARCHAR(50),
	time_slot		VARCHAR(30),
	count 			INTEGER,
	PRIMARY KEY (business_id, time_slot),
	FOREIGN KEY (business_id) REFERENCES Business 
);