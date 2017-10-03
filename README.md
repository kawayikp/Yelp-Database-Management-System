# Summary
1. Provide users a Java application to query from Yelp Dataset
2. Analyzed the dataset, designed the ER model, created the database schema and parsed Json into Oracle Database 11
3. Built GUI to provide restaurant and user retrieval by generating dynamic queries



# Operation
cmd moving to working folder

$-> sqlplus scott/tiger
$-sql> @dropdb.sql 
$-sql> @createdb.sql
$-sql> exit

$-> javac -cp .\json.jar .\populate.java
$-> java populate yelp_business.json yelp_review.json yelp_checkin.json yelp_user.json

# Snapshot
https://github.com/kawayikp/Yelp-Database-Management-System/blob/master/snapshot.pdf