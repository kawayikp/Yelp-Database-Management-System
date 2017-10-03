##################
##### YUE LIU ####
##### COEN280 ####
## ASSIGNMENT 3 ##
##################

##################
### Containing ###
##################



#######################
### Execution Order ###
#######################

cmd moving to working folder

$-> sqlplus scott/tiger
$-sql> @dropdb.sql 
$-sql> @createdb.sql
$-sql> exit

$-> javac -cp .\json.jar .\populate.java
$-> java populate yelp_business.json yelp_review.json yelp_checkin.json yelp_user.json