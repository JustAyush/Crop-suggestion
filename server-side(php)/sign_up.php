<?php
require('db_connection.php');
$comment="";
    foreach ($_POST as $key => $value) {
    $comment.= $key."        ";
    $comment.= $value."        ";
	}
$credit=10;
$time=time();
$required=array('name','email','age','password','gender','phone_no','occupation','latitude','longitude','address_name','address');
require('required_list.php');
function getToken($length){
     $token = "";
     $codeAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     $codeAlphabet.= "abcdefghijklmnopqrstuvwxyz";
     $codeAlphabet.= "0123456789";
     $max = strlen($codeAlphabet); 

    for ($i=0; $i < $length; $i++) {
        $token .= $codeAlphabet[rand(0, $max-1)];
    }

    return $token;
}
if($error==false)
{
	$contact_no=$_POST["phone_no"];
	$email=$_POST['email'];
	$sql = "SELECT contact_no,email FROM agro_table";
	$result = $conn->query($sql);
	$idmatch=0;
	$comment.= "hello";	
	if ($result->num_rows > 0) {
	while($row = $result->fetch_assoc()) 
	{
		if($row["contact_no"]==$contact_no)
		{
		$error_1=1;
		$idmatch=1;
		break;
		}
		if($row["email"]==$email)
		{
		$error_1=1;
		$idmatch=1;
		break;
		}
		
	}
}

if($idmatch==0)
{
	$name=$_POST["name"];
	$password=$_POST["password"];
	$address=$_POST["address"];
	$email=$_POST["email"];
	$address_name=$_POST["address_name"];
	$contact_no=$_POST["phone_no"];
    $address_latitude=$_POST["latitude"];
    $address_longitude=$_POST["longitude"];
	$occupation=$_POST["occupation"];
	$age=$_POST["age"];
	$gender=$_POST["gender"];
	$occupation=$_POST["occupation"];
	$time=time();
	$login_token=getToken(8);
	if ($_POST['email']!=null)
	{
		$email=$_POST['email'];
		$comment.= $email;
		if (!filter_var($email, FILTER_VALIDATE_EMAIL)) 
	{
        $email=NULL;
        $comment.= "invalid email so is set empty";
        	$verify_email_token=null;
	}
	else
{
		$verify_email_token=getToken(10);
	}
	}
	else
	{
		$verify_email_token=NULL;
		$comment.= "base camp 3";
	}	
	
	$verify_token=getToken(8);
	
	$sql = "INSERT INTO agro_table(occupation,full_name,email,password,address,address_name,address_latitude,address_longitude,contact_no,age,gender,acc_timestamp,credit_point,verify_token,login_token)
        VALUES ('$occupation','$name','$email','$password','$address','$address_name','$address_latitude','$address_longitude','$contact_no','$age','$gender','$time','$credit','$verify_token','$login_token')";


if ($conn->query($sql) === TRUE) 
{
    $comment.= "successful_entry";
	require('return_login_details.php');
	
} 
else 
{
	$comment.= "database_error". $conn->error;
}

}
else 
{
	$comment.= "already_registered_error";
}
}
else
{
	$comment.= "the available info is incomplete";
}
$conn->close();
?>
