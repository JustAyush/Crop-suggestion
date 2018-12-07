<?php
$error=false;
foreach($required as $value)
{
    if(empty($_POST[$value]))
    {
        $error=true;
		echo $value." and ";
    }
}
?>

