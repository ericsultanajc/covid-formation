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
				type="hidden" name="id" value="${monStagaire.id}"> <input
				type="hidden" name="version" value="${monStagaire.version}">
			<div id="stagiaireForm" class="card mt-3">
				<div class="card-header bg-dark text-white">
					<h2>Formulaire</h2>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="civilite">Civilité</label> <select
							class="form-control" id="civilite" name="civilite">
							<option value = ${monStagiaire.civilite == 'M' ? 'selected' : ''}>M</option>
							<option value = ${monStagiaire.civilite == 'MME' ? 'selected' : ''}>MME</option>
							<option value = ${monStagiaire.civilite == 'MLLE' ? 'selected' : ''}>MLLE</option>
						</select>
					</div>
					<div class="form-group">
						<label for="nom">Nom</label> <input type="text"
							class="form-control" id="nom" name="nom" placeholder="DUPONT"
							value="${monStagiaire.nom}">
					</div>
					<div class="form-group">
						<label for="prenom">Prénom</label> <input type="text"
							class="form-control" id="prenom" name="prenom" placeholder="Jean"
							value="${monStagiaire.prenom}">
					</div>
					<div class="form-group">
						<label for="email">Email</label> <input type="text"
							class="form-control" id="email" name="email" placeholder="jean.dupont@gmail.com"
							value="${monStagiaire.email}">
					</div>
					<div class="form-group">
						<label for="telephone">Téléphone</label> <input type="number"
							class="form-control" id="telephone" name="telephone" placeholder="0606060606"
							value="${monStagiaire.telephone}">
					</div>
					<div class="form-group">
						<label for="dtNaissance">Date de Naissance</label> <input type="date"
							class="form-control" id="dtNaissance" name="dtNaissance"
							value="${monStagiaire.dtNaissance}">
					</div>
					<div class="form-group">
						<label for="niveauEtude">Niveau d'étude</label> <select
							class="form-control" id="niveauEtude" name="niveauEtude" value = "${monStagiaire.niveauEtude}">
							<option value = "${monStagiaire.civilite == 'BAC' ? 'selected' : ">BAC</option>
							<option value = "BAC_2">BAC_2</option>
							<option value = "BAC_3">BAC_3</option>
							<option value = "BAC_5">BAC_5</option>
							<option value = "BAC_8">BAC_8</option>
						</select>
					</div>
					<div class="form-group">
						<label for="rue">Rue</label> <input type="text"
							class="form-control" id="rue" name="rue"
							value="${monStagiaire.adresse.rue}">
					</div>
					<div class="form-group">
						<label for="complement">Complément</label> <input type="text"
							class="form-control" id="complement" name="complement"
							value="${monStagiaire.adresse.complement}">
					</div>
					<div class="form-group">
						<label for="codePostal">Code Postal</label> <input type="number"
							class="form-control" id="codePostal" name="codePostal"
							value="${monStagiaire.adresse.codePostal}">
					</div>
					<div class="form-group">
						<label for="ville">Ville</label> <input type="text"
							class="form-control" id="ville" name="ville"
							value="${monStagiaire.adresse.ville}">
					</div>

				</div>
				<div class="card-footer">
					<div class="btn-group btn-group-lg float-right">
						<button type="submit" class="btn btn-success">
							<i class="fa fa-check"></i>
						</button>
						<c:url value="/stagiaire" var="cancelUrl">
							<c:param name="mode" value="cancel" />
						</c:url>
						<a href="${cancelUrl}" class="btn btn-warning"> <i
							class="fa fa-undo"></i>
						</a>
					</div>
				</div>

			</div>
		</form>

	</div>
</body>
</html>