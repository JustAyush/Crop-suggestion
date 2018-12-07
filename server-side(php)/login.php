<?php
require('db_connection.php');
$credit=10;
$time=time();
$required=array('user','password');
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
	$authentication=false;
	$user=$_POST['user'];
	$password=$_POST['password'];
	$sql = "SELECT contact_no,email,password FROM agro_table WHERE contact_no='$user' OR email='$user'";
	$result = $conn->query($sql);
	$idmatch=0;
	if ($result->num_rows > 0) {
	while($row = $result->fetch_assoc()) 
	{
		if($row["password"]==$password)
		{
		$authentication=true;
		$email=$row['email'];
		$contact_no=$row['contact_no'];
		break;
	}
}
	}
}
else
{
	die('invalid input');
}

if($authentication==true)
{
	$comment="";
	require('return_login_details.php');
}
else 
{
	echo "already_registered_error";
}

$conn->close();
?>
