<?php
$authenticate_user=false;
$id_user=$_POST['user_id'];
$login_token=$_POST['login_token'];
$sql = "SELECT id, login_token FROM agro_table WHERE id='$id_user' AND login_token='$login_token'";
$result = $conn->query($sql);
if ($result->num_rows > 0) 
{
	$authenticate_user=true;
}
?>