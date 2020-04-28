<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page import="java.util.List"%> --%>
<%-- <%@ page import="sopra.formation.model.Evaluation"%> --%>
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

	<div class="container-fluid">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<h2>Liste des stagiaires</h2>
			</div>
			<div class="card-body">
				<table id="stagiaireTable" class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Version</th>
							<th>Civilité</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Email</th>
							<th>Téléphone</th>
							<th>Date de naissance</th>
							<th>Niveau d'étude</th>
							<th>Rue</th>
							<th>Complément</th>
							<th>Code Postal</th>
							<th>Ville</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mesStagiaires}" var="stagiaire">
							<c:url value="/stagiaire" var="editUrl">
								<c:param name="mode" value="edit" />
								<c:param name="id" value="${stagiaire.id}" />
							</c:url>
							<c:url value="/stagiaire" var="deleteUrl">
								<c:param name="mode" value="delete" />
								<c:param name="id" value="${stagiaire.id}" />
							</c:url>
							<tr>
								<td>${stagiaire.id}</td>
								<td>${stagiaire.version}</td>
								<td>${stagiaire.civilite}</td>
								<td>${stagiaire.nom}</td>
								<td>${stagiaire.prenom}</td>
								<td>${stagiaire.email}</td>
								<td>${stagiaire.telephone}</td>
								<td>${stagiaire.dtNaissance}</td>
								<td>${stagiaire.niveauEtude}</td>
								<td>${stagiaire.adresse.rue}</td>
								<td>${stagiaire.adresse.complement}</td>
								<td>${stagiaire.adresse.codePostal}</td>
								<td>${stagiaire.adresse.ville}</td>
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
				<a href="${addUrl}" class="btn btn-dark"> <i
					class="far fa-plus-square fa-3x"></i>
				</a>
			</div>
		</div>
	</div>

</body>
</html>