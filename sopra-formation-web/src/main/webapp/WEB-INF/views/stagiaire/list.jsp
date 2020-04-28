<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ETAPE 5 : Traitement de de la JSP et retour de la réponse au navigateur  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des stagiaires</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
</head>
<body>

	<div class="container" style="max-width:1800px">
		<div class="card m-5">
			<div class="card-header bg-info text-white">
				<h1>Liste des élèves</h1>
			</div>
			<div class="card-body mx-auto">
				<table class="table table-striped" id="stagiaireTable">
					<thead>
						<tr>
							<th rowspan="2">Id</th>
							<th rowspan="2">Civilité</th>
							<th rowspan="2">Nom</th>
							<th rowspan="2">Prénom</th>
							<th rowspan="2">Email</th>
							<th rowspan="2">Téléphone</th>
							<th rowspan="2">Date de naissance</th>
							<th rowspan="2">Niveau d'étude</th>
							<th colspan="4">Adresse</th>
						</tr>
						<tr>
							<th>Rue</th>
							<th>Complément</th>
							<th>Code Postal</th>
							<th>Ville</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${mesStagiaires}" var="stag">
							<c:url value="/stagiaire" var="editUrl">
								<c:param name="mode" value="edit" />
								<c:param name="id" value="${stag.id}" />
							</c:url>
							<c:url value="/stagiaire" var="deleteUrl">
								<c:param name="mode" value="delete" />
								<c:param name="id" value="${stag.id}" />
							</c:url>
							<tr>
								<td>${stag.id}</td>
								<td>${stag.civilite.label}</td>
								<td>${stag.nom}</td>
								<td>${stag.prenom}</td>
								<td>${stag.email}</td>
								<td>${stag.telephone}</td>
								<td>${stag.dtNaissance}</td>
								<td>${stag.niveauEtude}</td>
								<td>${stag.adresse.rue}</td>
								<td>${stag.adresse.complement}</td>
								<td>${stag.adresse.codePostal}</td>
								<td>${stag.adresse.ville}</td>
								<td><div class="btn-group btn-group-sm">
										<a class="btn btn-primary" href="${editUrl}"><i
											class="fa fa-edit"></i></a><a class="btn btn-danger"
											href="${deleteUrl}"><i class="fa fa-trash"></i></a>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="card-footer">
				<c:url value="/stagiaire" var="addUrl">
					<c:param name="mode" value="add" />
				</c:url>
				<a href="${addUrl}" class="btn"> <i class="fa fa-plus"></i>
				</a>
			</div>
		</div>
	</div>
</body>
</html>