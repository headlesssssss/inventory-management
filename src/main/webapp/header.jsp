<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion d'Inventaire</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/">Gestion d'Inventaire</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">

            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">
                    <c:if test="${sessionScope.user != null && sessionScope.user.role == 'ADMIN'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                            Utilisateurs
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/users">Liste des Utilisateurs</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/user-form">Ajouter Utilisateur</a>
                        </div>
                    </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCat" role="button" data-toggle="dropdown">
                            Catégories
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/categories">Liste des Catégories</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/category-form">Ajouter Catégorie</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown">
                            Produits
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/products">Liste des Produits</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/product-form">Ajouter Produit</a>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <c:if test="${sessionScope.user != null}">
                        <li class="nav-item">
                            <span class="nav-link text-light">Bienvenue, <c:out value="${sessionScope.user.firstName}" /> (<c:out value="${sessionScope.user.role}" />)</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-danger" href="<%= request.getContextPath() %>/logout">Déconnexion</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>
<div class="container mt-4">