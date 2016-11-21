<?php

function getConnexion($dataBaseName){
    try{
	$dbh = new PDO('mysql:host=localhost;dbname='. $dataBaseName, 'root', '');
	$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$dbh->exec("SET CHARACTER SET utf8");
        return $dbh;
	} catch (PDOException $e) {
		return false;
	}
}

?>