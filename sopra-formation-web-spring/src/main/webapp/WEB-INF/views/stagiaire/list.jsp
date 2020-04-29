<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des stagiaires</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/all.css">
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container-fluid">
		<div class="card mt-3">
			<div class="card-header bg-info text-white">
				<h2>Liste des stagiaires</h2>
			</div>
			<div class="card-body">
				<table id="stagiaireTable" class="table table-striped">
					<thead>
						<tr>
							<th>Identifiant</th>
							<th>Version</th>
							<th>Civilité</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Date de naissance</th>
							<th>Email</th>
							<th>Telephone</th>
							<th>Rue</th>
							<th>Complément</th>
							<th>Code Postal</th>
							<th>Ville</th>
							<th>Niveau d'études </th>
							<th>Evaluation</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mesStagiaires}" var="stag">
						<c:url value="/stagiaire/edit" var="editUrl">
							<c:param name="id" value="${stag.id}"/>
						</c:url>
						<c:url value="/stagiaire/delete" var="deleteUrl">
							<c:param name="id" value="${stag.id}"/>
						</c:url>
						<tr>
							<td>${stag.id}</td>
							<td>${stag.version}</td>
							<td>${stag.civilite}</td>
							<td>${stag.nom}</td>
							<td>${stag.prenom}</td>
							<td>${stag.dtNaissance}</td>
							<td>${stag.email}</td>
							<td>${stag.telephone}</td>
							<td>${stag.adresse.rue}</td>
							<td>${stag.adresse.complement}</td>
							<td>${stag.adresse.codePostal}</td>
							<td>${stag.adresse.ville}</td>
							<td>${stag.niveauEtude}</td>
							<td>${stag.evaluation.id}</td>
							<td><div class="btn-group btn-group-sm"><a class="btn btn-primary" href="${editUrl}"><i class="fa fa-edit"></i></a><a class="btn btn-danger" href="${deleteUrl}"><i class="fa fa-trash"></i></a></div></td>
						</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
			<div class="card-footer">
				<c:url value="/stagiaire/add" var="addUrl"/>
				<a href="${addUrl}" class="btn btn-success btn-lg">
					<i class="fa fa-plus"></i>
				</a>
			</div>
		</div>
	</div>
</body>
</html>