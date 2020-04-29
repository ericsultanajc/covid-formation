<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition de l'évaluation</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
</head>
<body>

	<div class="container">
		<c:url value="/evaluation/save" var="saveUrl"/>
		<form action="${saveUrl}" method="post">
			<input type="hidden" name="id" value="${monEvaluation.id}"> 
			<input type="hidden" name="version" value="${monEvaluation.version}">
			<div id="evaluationForm" class="card mt-3">
				<div class="card-header bg-info text-white">
					<h3>Edition de l'évaluation</h3>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="comportemental">Note comportementale:</label> <input
							type="number" class="form-control" id="comportemental"
							name="comportemental" value="${monEvaluation.comportemental}">
					</div>
					<div class="form-group">
						<label for="technique">Note technique:</label> <input
							type="number" class="form-control" id="technique"
							name="technique" value="${monEvaluation.technique}">
					</div>
					<div class="form-group">
						<label for="commentaires">Commentaires:</label> <input type="text"
							class="form-control" id="commentaires" name="commentaires"
							value="${monEvaluation.commentaires}">
					</div>

				</div>
				<div class="card-footer">
					<div class="btn-group btn-group-lg float-right">
						<button type="submit" class="btn btn-success">
							<i class="fa fa-check"></i>
						</button>
						<c:url value="/evaluation/cancel" var="cancelUrl"/>
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