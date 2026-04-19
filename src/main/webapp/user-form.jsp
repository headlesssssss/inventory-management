<%@ include file="header.jsp" %>

<c:if test="${user != null}">
    <h2>Modifier l'Utilisateur</h2>
    <form action="<%= request.getContextPath() %>/update-user" method="post">
    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
</c:if>
<c:if test="${user == null}">
    <h2>Ajouter un Utilisateur</h2>
    <form action="<%= request.getContextPath() %>/create-user" method="post">
</c:if>

<div class="form-group">
    <label for="firstName">Prénom</label>
    <input type="text" class="form-control <c:if test='${errors != null && errors.firstName != null}'>is-invalid</c:if>" id="firstName" name="firstName" value="<c:out value='${user.firstName}' />" required>
    <c:if test="${errors != null && errors.firstName != null}">
        <div class="invalid-feedback"><c:out value="${errors.firstName}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="lastName">Nom</label>
    <input type="text" class="form-control <c:if test='${errors != null && errors.lastName != null}'>is-invalid</c:if>" id="lastName" name="lastName" value="<c:out value='${user.lastName}' />" required>
    <c:if test="${errors != null && errors.lastName != null}">
        <div class="invalid-feedback"><c:out value="${errors.lastName}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="email">Email</label>
    <input type="email" class="form-control <c:if test='${errors != null && errors.email != null}'>is-invalid</c:if>" id="email" name="email" value="<c:out value='${user.email}' />" required>
    <c:if test="${errors != null && errors.email != null}">
        <div class="invalid-feedback"><c:out value="${errors.email}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="password">Mot de passe</label>
    <input type="password" class="form-control <c:if test='${errors != null && errors.password != null}'>is-invalid</c:if>" id="password" name="password" <c:if test="${user == null}">required</c:if>>
    <c:if test="${errors != null && errors.password != null}">
        <div class="invalid-feedback"><c:out value="${errors.password}" /></div>
    </c:if>
    <c:if test="${user != null}">
        <small class="form-text text-muted">Laissez vide pour conserver le mot de passe actuel.</small>
    </c:if>
</div>

<button type="submit" class="btn btn-primary">Enregistrer</button>
<a href="<%= request.getContextPath() %>/users" class="btn btn-secondary">Annuler</a>
</form>

<%@ include file="footer.jsp" %>