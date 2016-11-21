<?php
	require_once('inc/connexion.inc.php');
	//header('content-Type: application/json ');
	$cnn = getConnexion('androidtest');

	if (!$cnn) {
		//header('Location: error.html');
		exit();
	}
	
	//Cherche correspondance pour le login demandé
	$stmt = $cnn->prepare('SELECT numero, email, type_droits FROM tbl_login');
	$stmt->execute();	
	$row = $stmt->fetch(PDO::FETCH_OBJ);
    if(!$row){
		//pas de correspondance pour login/pwd
		echo json_encode('false');
		exit;
    }
	
	//Renvoi l'adresse email et le type du compte
	// Méthodes brute force
	echo '{"users":['.json_encode($row);
	
	while($row = $stmt->fetch(PDO::FETCH_OBJ))
	{
		echo ','.json_encode($row);		
	}
	echo ']}';	
?>














