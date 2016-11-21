<?php
	require_once('inc/connexion.inc.php');
	//header('content-Type: application/json ');
	$cnn = getConnexion('androidtest');

	if (!$cnn) {
		//header('Location: error.html');
		exit();
	}
	
	//Récupère depuis le POST
	if (!filter_input(INPUT_POST, "email", FILTER_VALIDATE_EMAIL))
	{
		echo json_encode('false');
		exit;
	}
	//préparation du login et conversion en md5
	$pwd = md5($_POST['pwd']);
	$email=filter_var($_POST['email'], FILTER_SANITIZE_STRING);

/*
	$email = 'plinio.sacchetti@rpn.ch';
	$pwd = 'pass';
	$pwd = md5($pwd);
*/	
	//Cherche correspondance pour le login demandé
	$stmt = $cnn->prepare('SELECT email, type_droits FROM tbl_login WHERE email=:email and password=:password');
	$stmt->bindValue(':email', $email, PDO::PARAM_STR);
	$stmt->bindValue(':password', $pwd, PDO::PARAM_STR);
	$stmt->execute();	
	$row = $stmt->fetch(PDO::FETCH_OBJ);
    if(!$row){
		//pas de correspondance pour login/pwd
		echo json_encode('false');
		exit;
    }
	
	//Renvoi l'adresse email et le type du compte
	// Méthodes brute force
	echo json_encode($row);
	
	
	//return true;
	/*
	// http://www.lephpfacile.com/manuel-php/function.json-encode.php
		$a = array('<foo>',"'baré'",'"baz"','&blong&', "\xc3\xa9");
		echo "Normal : ",  json_encode($a), "\n";
		echo "Tags : ",	json_encode($a, JSON_HEX_TAG), "\n";
		echo "Apos : ",	json_encode($a, JSON_HEX_APOS), "\n";
		echo "Quot : ",	json_encode($a, JSON_HEX_QUOT), "\n";
		echo "Amp : ",	 json_encode($a, JSON_HEX_AMP), "\n";
		echo "Unicode : ", json_encode($a, JSON_UNESCAPED_UNICODE), "\n";
		echo "Toutes : ",	 json_encode($a, JSON_HEX_TAG | JSON_HEX_APOS | JSON_HEX_QUOT | JSON_HEX_AMP | JSON_UNESCAPED_UNICODE), "\n\n";
	*/

	
	
?>














