<?php
echo "here we go";
foreach($required as $value)
{
	$talue=$value;
	${$value}=$_POST[$value];
//	echo $value." * ".$_POST[$talue];
}
echo "after we go";
?>
