<%@ include file="header.jsp" %>

<c:if test="${category != null}">
    <h2>Modifier la Catégorie</h2>
    <form action="<%= request.getContextPath() %>/update-category" method="post">
    <input type="hidden" name="id" value="<c:out value='${category.id}' />" />
</c:if>
<c:if test="${category == null}">
    <h2>Ajouter une Catégorie</h2>
    <form action="<%= request.getContextPath() %>/create-category" method="post">
</c:if>

<div class="form-group">
    <label for="name">Nom de la catégorie</label>
    <input type="text" class="form-control <c:if test='${errors != null && errors.name != null}'>is-invalid</c:if>" id="name" name="name" value="<c:out value='${category.name}' />" required>
    <c:if test="${errors != null && errors.name != null}">
        <div class="invalid-feedback"><c:out value="${errors.name}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="description">Description</label>
    <textarea class="form-control <c:if test='${errors != null && errors.description != null}'>is-invalid</c:if>" id="description" name="description" rows="3"><c:out value='${category.description}' /></textarea>
    <c:if test="${errors != null && errors.description != null}">
        <div class="invalid-feedback"><c:out value="${errors.description}" /></div>
    </c:if>
</div>

<button type="submit" class="btn btn-primary">Enregistrer</button>
<a href="<%= request.getContextPath() %>/categories" class="btn btn-secondary">Annuler</a>
</form>

<%@ include file="footer.jsp" %>
