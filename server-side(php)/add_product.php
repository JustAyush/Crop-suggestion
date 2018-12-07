<?php
require('db_connection.php');
$required=array("item_categories" ,"quantity_unit","pro_price","pro_price_unit","quantity" ,"item_name" ,"pro_end_date","pro_address" ,"pro_address_name" 
,"pro_address_latitude","pro_address_longitude","pro_description","user_id", "pro_user_name",  "pro_occupation"  ,"pro_email","pro_contact_no"); 
require('required_list.php');
if($error==false)
{
	require('authenticate_user.php');
	if($authenticate_user==true)
	{
	require('post_required_list.php');
	$time=time();
	$sql = "INSERT INTO product_table(item_categories,quantity_unit ,pro_price,pro_price_unit,quantity,item_name,pro_end_date,pro_address,pro_address_name 
	,pro_address_latitude,pro_address_longitude,pro_description,pro_user_id,pro_user_name,pro_occupation  ,pro_email,pro_contact_no,product_timestamp)
    VALUES ('$item_categories','$quantity_unit','$pro_price','$pro_price_unit','$quantity','$item_name','$pro_end_date','$pro_address','$pro_address_name 
	','$pro_address_latitude','$pro_address_longitude','$pro_description','$user_id','$pro_user_name','$pro_occupation','$pro_email','$pro_contact_no','$time')";
	echo $sql;
	if($conn->query($sql))
	{
		echo "added successfully to the server";
 	}
	else
	{
		echo $sql;
		die ("failed to add data to the server".$conn->error);
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