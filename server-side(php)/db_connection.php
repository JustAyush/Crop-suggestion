<?php
//$conn=new mysqli("localhost","root","","agro_db")

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "agro_db";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 



?>