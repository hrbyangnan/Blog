<?php

$host = "localhost"; /* Host name */
$username = "root"; /* User */
$password = ""; /* Password */
$dbname = "user"; /* Database name */

$con = mysqli_connect($host, $username, $password,$dbname);
// Check connection
if (!$con) {
 die("Connection failed: " . mysqli_connect_error());
}