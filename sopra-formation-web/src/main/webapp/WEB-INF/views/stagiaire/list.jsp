<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<<<<<<< HEAD
<%-- <%@ page import="java.util.List"%> --%>
<%-- <%@ page import="sopra.formation.model.Evaluation"%> --%>
<!-- ETAPE 5 : Traitement de de la JSP et retour de la réponse au navigateur  -->
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
>>>>>>> master
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
<<<<<<< HEAD

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
=======
<body>

	<div class="container-fluid">
		<div class="card mt-3">
			<div class="card-header bg-info text-white">
				<h2>Liste des stagiaires</h2>
			</div>
			<div class="card-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Civilité</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Courriel</th>
>>>>>>> master
							<th>Téléphone</th>
							<th>Date de naissance</th>
							<th>Niveau d'étude</th>
							<th>Rue</th>
							<th>Complément</th>
<<<<<<< HEAD
							<th>Code Postal</th>
=======
							<th>Code postal</th>
>>>>>>> master
							<th>Ville</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
<<<<<<< HEAD
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
=======
						<c:forEach items="${stagiaires}" var="stagiaire">
							<c:url value="/stagiaire" var="editUrl">
								<c:param name="mode" value="edit"/>
								<c:param name="id" value="${stagiaire.id}"/>
							</c:url>
							<c:url value="/stagiaire" var="deleteUrl">
								<c:param name="mode" value="delete"/>
								<c:param name="id" value="${stagiaire.id}"/>
							</c:url>
							<tr>
								<td>${stagiaire.civilite.label}</td>
>>>>>>> master
								<td>${stagiaire.nom}</td>
								<td>${stagiaire.prenom}</td>
								<td>${stagiaire.email}</td>
								<td>${stagiaire.telephone}</td>
<<<<<<< HEAD
								<td>${stagiaire.dtNaissance}</td>
=======
								<td><fmt:formatDate value="${stagiaire.dtNaissance}" pattern="dd/MM/yyyy"/> </td>
>>>>>>> master
								<td>${stagiaire.niveauEtude}</td>
								<td>${stagiaire.adresse.rue}</td>
								<td>${stagiaire.adresse.complement}</td>
								<td>${stagiaire.adresse.codePostal}</td>
								<td>${stagiaire.adresse.ville}</td>
<<<<<<< HEAD
								<td><div class="btn-group btn-group-sm">
										<a class="btn btn-primary" href="${editUrl}"><i
											class="fa fa-edit"></i></a><a class="btn btn-danger"
											href="${deleteUrl}"><i class="fa fa-trash"></i></a>
									</div></td>
=======
								<td><div class="btn-group btn-group-sm"><a class="btn btn-primary" href="${editUrl}"><i class="fa fa-edit"></i></a><a class="btn btn-danger" href="${deleteUrl}"><i class="fa fa-trash"></i></a></div></td>
>>>>>>> master
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="card-footer">
				<c:url value="/stagiaire" var="addUrl">
<<<<<<< HEAD
					<c:param name="mode" value="add" />
				</c:url>
				<a href="${addUrl}" class="btn btn-dark"> <i
					class="far fa-plus-square fa-3x"></i>
=======
					<c:param name="mode" value="add"/>
				</c:url>
				<a href="${addUrl}" class="btn btn-success btn-lg">
					<i class="fa fa-plus"></i>
>>>>>>> master
				</a>
			</div>
		</div>
	</div>
<<<<<<< HEAD

=======
>>>>>>> master
</body>
</html>