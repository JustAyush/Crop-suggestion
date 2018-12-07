<?php
require('db_connection.php');

$sql="CREATE TABLE product_table(
product_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
item_name VARCHAR(20),
item_categories VARCHAR(40),
quantity VARCHAR(40),
quantity_unit VARCHAR(50),
pro_price VARCHAR(10),
pro_price_unit VARCHAR(40),
pro_end_date VARCHAR(20),
pro_description VARCHAR(200),
product_timestamp VARCHAR(12),
pro_address VARCHAR(50),
pro_address_name VARCHAR(50),
pro_address_latitude VARCHAR(30),
pro_address_longitude VARCHAR(30),
pro_user_id VARCHAR(10),
pro_user_name VARCHAR(30),
pro_occupation VARCHAR(20),
pro_email VARCHAR(40),
pro_contact_no VARCHAR(15) )";

/*
$sql="CREATE TABLE request_table(
request_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
item_name VARCHAR(20),
item_categories VARCHAR(40),
quantity VARCHAR(40),
quantity_unit VARCHAR(50),
req_price VARCHAR(10),
req_price_unit VARCHAR(40),
req_end_date VARCHAR(20),
req_description VARCHAR(200),
request_timestamp VARCHAR(12),
req_address VARCHAR(50),
req_address_name VARCHAR(50),
req_address_latitude VARCHAR(30),
req_address_longitude VARCHAR(30),
req_user_id VARCHAR(10),
req_user_name VARCHAR(30),
req_occupation VARCHAR(20),
req_email VARCHAR(40),
req_contact_no VARCHAR(15) )";
*/
/*
$sql="CREATE TABLE agro_table(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
occupation VARCHAR(20),
full_name VARCHAR(40),
email VARCHAR(40),
password VARCHAR(30),
address VARCHAR(50),
address_name VARCHAR(50),
address_latitude VARCHAR(30),
address_longitude VARCHAR(30),
contact_no VARCHAR(15),
age VARCHAR(3),
gender VARCHAR(8),
acc_timestamp VARCHAR(12),
credit_point VARCHAR(6) DEFAULT 0,
verify_token VARCHAR(10) ,
request_count VARCHAR(6) DEFAULT 0,
response_count VARCHAR(6) DEFAULT 0,
login_token VARCHAR(10) )";
*/
/*$sql="CREATE TABLE MyGuests (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
email VARCHAR(50),
reg_date TIMESTAMP";
)
*/
if($conn->query($sql))
{
	echo "table created successfully";
}
else
{
	echo "could not create table";
}

/*
$servername = "localhost";
$username = "root";
$password = "";

// Create connection
$conn = new mysqli($servername, $username, $password);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

// Create database
$sql = "CREATE DATABASE agro_DB";
if ($conn->query($sql) === TRUE) {
    echo "Database created successfully";
} else {
    echo "Error creating database: " . $conn->error;
}

$conn->close();
*/
?>