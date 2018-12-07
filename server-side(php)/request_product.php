<?php
require('db_connection.php');
$credit=10;
//echo $_POST["quantity_unit"]."hellos";
$time=time();
$required=array("item_categories","quantity_unit","req_price","req_price_unit","quantity" ,"item_name" ,"req_end_date","req_address" ,"req_address_name" 
,"req_address_latitude","req_address_longitude","req_description","user_id", "req_user_name",  "req_occupation"  ,"req_email","req_contact_no") ;
require('required_list.php');

if($error==false)
{
	require('authenticate_user.php');
	if($authenticate_user==true)
	{
	require('post_required_list.php');
	$time=time();
	$sql = "INSERT INTO request_table(item_categories,quantity_unit ,req_price,req_price_unit,quantity,item_name,req_end_date,req_address,req_address_name 
	,req_address_latitude,req_address_longitude,req_description,req_user_id,req_user_name,req_occupation  ,req_email,req_contact_no,request_timestamp)
    VALUES ('$item_categories','$quantity_unit ','$req_price','$req_price_unit','$quantity','$item_name','$req_end_date','$req_address','$req_address_name 
	','$req_address_latitude','$req_address_longitude','$req_description','$user_id','$req_user_name','$req_occupation  ','$req_email','$req_contact_no','$time')";
	if($conn->query($sql))
	{
		echo "added successfully to the server";
 	}
	else
	{
		die ("failed to add data to the server");
	}
	}
	else
	{
		die('authentication failed');
	}
}
else
{
	die("the available info is incomplete");
}
$conn->close();
?>