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
					<h3>Edition du Stagiaire</h3>
				</div>
				<div class="card-body">
					<div id="civilite">
						<input required type="radio" id="monsieur" name="civilite"  value="M" ${monStagiaire.civilite=="M"?"checked":""} /> 
							<label for="monsieur"> Monsieur</label> 
						<input type="radio" id="madame"
							name="civilite" value="MME" ${monStagiaire.civilite=="MME"?"checked":""} /> 
							<label for="madame"> Madame</label> 
						<input type="radio"
							id="mademoiselle" name="civilite" value="MLLE" ${monStagiaire.civilite=="MLLE"?"checked":""} /> 
							<label for="mademoiselle"> Mademoiselle</label>
					</div>
					<div class="form-group">
						<label for="technique">Nom :</label> <input type="text"
							class="form-control" id="nom" name="nom"
							value="${monStagiaire.nom}">
					</div>
					<div class="form-group">
						<label for="commentaires">Prénom :</label> <input type="text"
							class="form-control" id="prenom" name="prenom"
							value="${monStagiaire.prenom}">
					</div>
					<div class="form-group">
						<label for="commentaires">Email :</label> <input type="text"
							class="form-control" id="email" name="email"
							value="${monStagiaire.email}">
					</div>
					<div class="form-group">
						<label for="commentaires">Téléphone :</label> <input type="text"
							class="form-control" id="telephone" name="telephone"
							value="${monStagiaire.telephone}">
					</div>
					<div class="form-group">
						<label for="commentaires">Rue :</label> <input type="text"
							class="form-control" id="rue" name="rue"
							value="${monStagiaire.adresse.rue}">
					</div>
					<div class="form-group">
						<label for="commentaires">Complément :</label> <input type="text"
							class="form-control" id="complement" name="complement"
							value="${monStagiaire.adresse.complement}">
					</div>
					<div class="form-group">
						<label for="commentaires">Code postal :</label> <input type="text"
							class="form-control" id="codePostal" name=codePostal
							value="${monStagiaire.adresse.codePostal}">
					</div>
					<div class="form-group">
						<label for="commentaires">Ville :</label> <input type="text"
							class="form-control" id="ville" name="ville"
							value="${monStagiaire.adresse.ville}">
					</div>
					<div class="form-group">
						<label for="commentaires">Date de Naissance :</label> <input type="text"
							class="form-control" id="dtNaissance" name="dtNaissance"
							value="${monStagiaire.dtNaissance}">
					</div>
					<div id="niveauEtude">
						<input required type="radio" id="bac" name="niveauEtude" value="BAC" ${monStagiaire.niveauEtude=="BAC"?"checked":""} /> 
							<label for="monsieur"> Bac </label> 
						<input type="radio" id="bac" name="niveauEtude" value="BAC_2" ${monStagiaire.niveauEtude=="BAC_2"?"checked":""} /> 
							<label for="monsieur"> Bac + 2 </label> 
						<input type="radio" id="bac" name="niveauEtude" value="BAC_3" ${monStagiaire.niveauEtude=="BAC_3"?"checked":""} /> 
							<label for="monsieur"> Bac + 3 </label> 								
						<input type="radio" id="bac" name="niveauEtude" value="BAC_5" ${monStagiaire.niveauEtude=="BAC_5"?"checked":""} /> 
							<label for="monsieur"> Bac + 5 </label> 
						<input type="radio" id="bac" name="niveauEtude" value="BAC_5" ${monStagiaire.niveauEtude=="BAC_8"?"checked":""} /> 
							<label for="monsieur"> Bac + 8 </label> 
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