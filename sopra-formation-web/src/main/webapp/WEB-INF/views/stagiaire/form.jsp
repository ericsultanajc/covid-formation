<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition du stagiaire</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
</head>
<body>

	<div class="container">
		<c:url value="/stagiaire" var="saveUrl" />
		<form action="${saveUrl}" method="post">
			<input type="hidden" name="mode" value="save"> <input
				type="hidden" name="id" value="${monStagiaire.id}"> <input
				type="hidden" name="version" value="${monStagiaire.version}">
			<div id="filiereForm" class="card mt-3">
				<div class="card-header bg-info text-white">
					<h3>Edition du stagiaire</h3>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="civilite">Civilité:</label>
						<!--Select list (ici Civilité:): -->
						<select id="civilite" class="form-control" name="civilite" value="${monStagiaire.civilite}">
							<option value="M">M</option>
							<option value="MME">MME</option>
							<option value="MLLE">MLLE</option>
						</select>
						<!--Boutons radios en ligne: -->
						<!--<select id="civilite" class="radio-inline"> -->
						<!--<input type="radio" name="monsieur"> Monsieur -->
						<!--<input type="radio" name="madame"> Madame -->
						<!--<input type="radio" name="mademoiselle"> Mademoiselle -->
						<!--</select> -->
					</div>
					<div class="form-group">
						<label for="nom">Nom:</label> <input type="text"
							class="form-control" id="nom" name="nom" value="${monStagiaire.nom}">
					</div>
					<div class="form-group">
						<label for="prenom">Prénom:</label> <input type="text"
							class="form-control" id="prenom" name="prenom" value="${monStagiaire.prenom}">
					</div>
					<div class="form-group">
						<label for="email">Email:</label> <input type="text"
							class="form-control" id="email" name="email" value="${monStagiaire.email}">
					</div>
					<div class="form-group">
						<label for="telephone">Téléphone:</label> <input type="text"
							class="form-control" id="telephone" name="telephone" value="${monStagiaire.telephone}">
					</div>
					<div class="form-group">
						<label for="dtNaissance">Date de naissance:</label> <input
							type="date" class="form-control" id="dtNaissance" name="dtNaissance" value="${monStagiaire.dtNaissance}">
					</div>
					<div class="form-group">
						<label for="niveauEtude">Niveau d'étude:</label>
						<!--Select list (ici nieveauEtude:): -->
						<select id="niveauEtude" class="form-control" name="niveauEtude" value="${monStagiaire.niveauEtude}">
							<option value="BAC">BAC</option>
							<option value="BAC+2">BAC+2</option>
							<option value="BAC+3">BAC+3</option>
							<option value="BAC+5">BAC+5</option>
							<option value="BAC+8">BAC+8</option>
						</select>
					</div>
					<h5>Adresse:</h5>
					<div class="form-group">
						<label for="rue">Rue:</label> <input type="text"
							class="form-control" id="rue" name="rue" value="${monStagiaire.adresse.rue}"> 
					</div>
					<div class="form-group">
						<label for="complement">Complément:</label> <input type="text"
							class="form-control" id="complement" name="complement" value="${monStagiaire.adresse.complement}">
					</div>
					<div class="form-group">
						<label for="codePostal">Code postal:</label> <input type="number"
							class="form-control" id="codePostal" name="codePostal" value="${monStagiaire.adresse.codePostal}">
					</div>
					<div class="form-group">
						<label for="ville">Ville:</label> <input type="text"
							class="form-control" id="ville" name="ville" value="${monStagiaire.adresse.ville}">
					</div>

				</div>
				<div class="card-footer">
					<div class="btn-group btn-group-lg float-right">
						<button type="submit" class="btn">
							<i class="fa fa-check"></i>
						</button>
						<c:url value="/stagiaire" var="cancelUrl">
							<c:param name="mode" value="cancel" />
						</c:url>
						<a href="${cancelUrl}" class="btn"> <i class="fa fa-undo"></i>
						</a>
					</div>
				</div>

			</div>
		</form>

	</div>
</body>
</html>