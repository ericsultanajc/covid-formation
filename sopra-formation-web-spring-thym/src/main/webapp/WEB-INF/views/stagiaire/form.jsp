<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition du stagiaire</title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/css/all.css"/>">
<script src="<c:url value="/js/jquery-3.5.0.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.bundle.min.js"/>" /></script>
</head>
<body>

	<div class="container">
		<c:url value="/stagiaire/save" var="saveUrl" />
		<form action="${saveUrl}" method="post">
			<input type="hidden" name="id" value="${monStagaire.id}"> 
			<input type="hidden" name="version" value="${monStagaire.version}">
			<div id="stagiaireForm" class="card mt-3">
				<div class="card-header bg-dark text-white">
					<h2>Formulaire</h2>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="civilite">Civilité</label> 
						<select	class="form-control" id="civilite" name="civilite">
							<option value ="M" ${monStagiaire.civilite == 'M' ? 'selected' : ''}>M</option>
							<option value ="MME" ${monStagiaire.civilite == 'MME' ? 'selected' : ''}>MME</option>
							<option value ="MLLE" ${monStagiaire.civilite == 'MLLE' ? 'selected' : ''}>MLLE</option>
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
							class="form-control" id="niveauEtude" name="niveauEtude">
							<option value = "BAC" ${monStagiaire.niveauEtude == 'BAC' ? 'selected' : ''}>BAC</option>
							<option value = "BAC_2" ${monStagiaire.niveauEtude == 'BAC_2' ? 'selected' : ''}>BAC_2</option>
							<option value = "BAC_3" ${monStagiaire.niveauEtude == 'BAC_3' ? 'selected' : ''}>BAC_3</option>
							<option value = "BAC_5" ${monStagiaire.niveauEtude == 'BAC_5' ? 'selected' : ''}>BAC_5</option>
							<option value = "BAC_8" ${monStagiaire.niveauEtude == 'BAC_8' ? 'selected' : ''}>BAC_8</option>
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
						<c:url value="/stagiaire/cancel" var="cancelUrl"/>
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