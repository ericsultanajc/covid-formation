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
			<div id="stagiaireForm" class="card mt-3">
				<div class="card-header bg-info text-white">
					<h3>Edition du stagiaire</h3>
				</div>
				<div class="card-body">
					<div class="form-group" id="civilite" >
						<div class="row">
							<div class="col">
								<p>
									M <input type="radio" id="monsieur" name="civilite"
										value="M" ${monStagiaire.civilite == "M"?"checked":""}/>
								</p>
							</div>
							<div class="col">
								<p>
									MME <input type="radio" id="madame" name="civilite"
										value="MME" ${monStagiaire.civilite == "MME"?"checked":""}/>
								</p>
							</div>
							<div class="col">
								<p>
									MLLE <input type="radio" id="mademoiselle"
										name="civilite" value="MLLE" ${monStagiaire.civilite == "MLLE"?"checked":""} />
								</p>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="nom">Nom :</label> <input
							type="text" class="form-control" id="nom"
							name="nom" value="${monStagiaire.nom}">
					</div>
					<div class="form-group">
						<label for="prenom">Prénom :</label> <input
							type="text" class="form-control" id="prenom"
							name="prenom" value="${monStagiaire.prenom}">
					</div>
					<div class="form-group">
						<label for="dtNaissance">Date de naissance :</label> <input type="date"
							class="form-control" id="dtNaissance" name="dtNaissance"
							value="${monStagiaire.dtNaissance}">
					</div>
					<div class="form-group">
						<label for="email">Email :</label> <input
							type="text" class="form-control" id="email"
							name="email" value="${monStagiaire.email}">
					</div>
					<div class="form-group">
						<label for="telephone">Numéro de telephone :</label> <input
							type="text" class="form-control" id="telephone"
							name="telephone" value="${monStagiaire.telephone}">
					</div>
					<div class="form-group">
						<label for="rue">Numéro et rue :</label> <input
							type="text" class="form-control" id="rue"
							name="rue" value="${monStagiaire.adresse.rue}">
					</div>
					<div class="form-group">
						<label for="complement">Complement :</label> <input
							type="text" class="form-control" id="complement"
							name="complement" value="${monStagiaire.adresse.complement}">
					</div>
					<div class="form-group">
						<label for="codePostal">Code postal :</label> <input
							type="text" class="form-control" id="codePostal"
							name="codePostal" value="${monStagiaire.adresse.codePostal}">
					</div>
					<div class="form-group">
						<label for="ville">Ville :</label> <input
							type="text" class="form-control" id="ville"
							name="ville" value="${monStagiaire.adresse.ville}">
					</div>
					<div class="form-group">
						<label for="niveauEtude">niveauEtude :</label> 
						<select name ="niveauEtude"id="niveauEtude" class="form-control">
							<option value="BAC" ${monStagiaire.niveauEtude == "BAC"?"selected":""}>BAC</option>
							<option value="BAC_2" ${monStagiaire.niveauEtude == "BAC_2"?"selected":""}>BAC + 2</option>
							<option value="BAC_3" ${monStagiaire.niveauEtude == "BAC_3"?"selected":""}>BAC + 3</option>
							<option value="BAC_5" ${monStagiaire.niveauEtude == "BAC_5"?"selected":""}>BAC + 5</option>
							<option value="BAC_8" ${monStagiaire.niveauEtude == "BAC_8"?"selected":""}>BAC + 8</option>
						</select>
					</div>
					<div class="form-group">
						<label for="evaluation">Evaluation :</label> 
						<select name="evaluation" class="form-control">
							<c:forEach items="${mesEvaluations}" var="eval">
							<option value="${eval.id}">${eval.id}-${eval.comportemental}-${eval.technique}-${eval.commentaires}</option>
							</c:forEach>
						</select>
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