<%@ include file="header.jsp" %>

<c:if test="${product != null}">
    <h2>Modifier le Produit</h2>
    <form action="<%= request.getContextPath() %>/update-product" method="post">
    <input type="hidden" name="id" value="<c:out value='${product.id}' />" />
</c:if>
<c:if test="${product == null}">
    <h2>Ajouter un Produit</h2>
    <form action="<%= request.getContextPath() %>/create-product" method="post">
</c:if>

<div class="form-group">
    <label for="name">Nom du produit</label>
    <input type="text" class="form-control <c:if test='${errors != null && errors.name != null}'>is-invalid</c:if>" id="name" name="name" value="<c:out value='${product.name}' />" required>
    <c:if test="${errors != null && errors.name != null}">
        <div class="invalid-feedback"><c:out value="${errors.name}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="description">Description</label>
    <textarea class="form-control <c:if test='${errors != null && errors.description != null}'>is-invalid</c:if>" id="description" name="description" rows="3"><c:out value='${product.description}' /></textarea>
    <c:if test="${errors != null && errors.description != null}">
        <div class="invalid-feedback"><c:out value="${errors.description}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="price">Prix</label>
    <input type="number" step="0.01" class="form-control <c:if test='${errors != null && errors.price != null}'>is-invalid</c:if>" id="price" name="price" value="<c:out value='${product.price}' />" required>
    <c:if test="${errors != null && errors.price != null}">
        <div class="invalid-feedback"><c:out value="${errors.price}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="stockQuantity">Quantité en stock</label>
    <input type="number" class="form-control <c:if test='${errors != null && errors.stockQuantity != null}'>is-invalid</c:if>" id="stockQuantity" name="stockQuantity" value="<c:out value='${product.stockQuantity}' />" required>
    <c:if test="${errors != null && errors.stockQuantity != null}">
        <div class="invalid-feedback"><c:out value="${errors.stockQuantity}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="sku">SKU (Code produit unique)</label>
    <input type="text" class="form-control <c:if test='${errors != null && errors.sku != null}'>is-invalid</c:if>" id="sku" name="sku" value="<c:out value='${product.sku}' />" required>
    <c:if test="${errors != null && errors.sku != null}">
        <div class="invalid-feedback"><c:out value="${errors.sku}" /></div>
    </c:if>
</div>

<div class="form-group">
    <label for="categoryId">Catégorie</label>
    <select class="form-control" id="categoryId" name="categoryId">
        <option value="">Sélectionnez une catégorie</option>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}" <c:if test="${product != null && product.category != null && product.category.id == category.id}">selected</c:if>>
                <c:out value="${category.name}" />
            </option>
        </c:forEach>
    </select>
</div>

<button type="submit" class="btn btn-primary">Enregistrer</button>
<a href="<%= request.getContextPath() %>/products" class="btn btn-secondary">Annuler</a>
</form>

<%@ include file="footer.jsp" %>