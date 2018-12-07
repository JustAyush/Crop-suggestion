<?php
$servername = "localhost";
$usrname = "root";
$password = "114912dell";
$dbname = "pclient";
$dbname1="pclient";
// Create connection
$conn = new mysqli($servername, $usrname, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$conn1 = new mysqli($servername, $usrname, $password, $dbname1);
// Check connection
if ($conn1->connect_error) {
    die("Connection failed: " . $conn1->connect_error);
} 


$time=time()+86400000;
$sql = "SELECT message_box_name,login_token FROM dot_table where contact_no='$receiver'";




$result=$conn->query($sql);


if($result->num_rows>0)
{
	while($row=$result->fetch_assoc())
	{
	$login_token=$row['login_token'];
	if($login_token==$token)
	{
		$message_box_name=$row['message_box_name'];
		break;
	}
	else
	{
		die("sorry user couldnot be authenticated");
	}
	
	}
}

$sql2="INSERT INTO '$message_box_name' ('user_no','user_name','message','time') VALUES ('$user_no','$user_no','$message','$time')";
if($conn->query($sql2))
{
	echo "message input success";
}
else
{
	die("sorry message couldnot be sent". $conn->error);
}


$conn->close();
?>