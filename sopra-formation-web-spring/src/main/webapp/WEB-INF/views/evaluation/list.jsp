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
<title>Liste des évaluations</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
</head>

<%-- <%List<Evaluation> mesEvaluations = (List<Evaluation>)request.getAttribute("mesEvaluations"); %> --%>
<body>

	<div class="container-fluid">
		<div class="card mt-3">
			<div class="card-header bg-info text-white">
				<h2>Liste des évaluations</h2>
			</div>
			<div class="card-body">
				<table id="evaluationTable" class="table table-striped">
					<thead>
						<tr>
							<th>Identifiant</th>
							<th>Version</th>
							<th>Comportementale</th>
							<th>Technique</th>
							<th>Commentaires</th>
							<th></th>
						</tr>
					</thead>
<!-- 					<tbody> -->
<%-- 						<% for(Evaluation eval : mesEvaluations) {%> --%>
<!-- 						<tr> -->
<%-- 							<td><%=eval.getId() %></td> --%>
<%-- 							<td><%=eval.getVersion() %></td> --%>
<%-- 							<td><%=eval.getComportemental() %></td> --%>
<%-- 							<td><%=eval.getTechnique() %></td> --%>
<%-- 							<td><%=eval.getCommentaires() %></td> --%>
<!-- 							<td></td> -->
<!-- 						</tr> -->
<%-- 						<% } %> --%>
<!-- 					</tbody> -->
					
					
					<tbody>
						<c:forEach items="${mesEvaluations}" var="evaluation">
						<c:url value="/evaluation/edit" var="editUrl">
							<c:param name="id" value="${evaluation.id}"/>
						</c:url>
						<c:url value="/evaluation/delete" var="deleteUrl">
							<c:param name="id" value="${evaluation.id}"/>
						</c:url>
						<tr>
							<td>${evaluation.id}</td>
							<td>${evaluation.version}</td>
							<td>${evaluation.comportemental}</td>
							<td>${evaluation.technique}</td>
							<td>${evaluation.commentaires}</td>
							<td><div class="btn-group btn-group-sm"><a class="btn btn-primary" href="${editUrl}"><i class="fa fa-edit"></i></a><a class="btn btn-danger" href="${deleteUrl}"><i class="fa fa-trash"></i></a></div></td>
						</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
			<div class="card-footer">
				<c:url value="/evaluation/add" var="addUrl"/>
				<a href="${addUrl}" class="btn">
					<i class="fa fa-plus"></i>
				</a>
			</div>
		</div>
	</div>
</body>
</html>