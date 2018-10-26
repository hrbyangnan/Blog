<?php

include "config.php";

/* Get username */
$username = $_POST["uname"];

/* Query */
$query = "select count(*) as cntUser from user where username='".$username."'";

$result = mysqli_query($con,$query);

$row = mysqli_fetch_array($result);

$count = $row["cntUser"];

echo $count;