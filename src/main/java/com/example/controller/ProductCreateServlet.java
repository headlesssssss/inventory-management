package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.dao.CategoryDAO;
import com.example.model.Product;
import com.example.model.Category;
import com.example.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@WebServlet("/create-product")
public class ProductCreateServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = null;
        try {
            price = new BigDecimal(request.getParameter("price"));
        } catch(Exception ignored) {}
        Integer stockQuantity = null;
        try {
            stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        } catch(Exception ignored) {}
        String sku = request.getParameter("sku");

        Product newProduct = new Product(name, description, price, stockQuantity, sku);

        String categoryIdStr = request.getParameter("categoryId");
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            Long categoryId = Long.parseLong(categoryIdStr);
            Optional<Category> category = categoryDAO.findById(categoryId);
            category.ifPresent(newProduct::setCategory);
        }

        Map<String, String> errors = ValidationUtil.validate(newProduct);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("product", newProduct);
            request.setAttribute("categories", categoryDAO.findAll());
            request.getRequestDispatcher("product-form.jsp").forward(request, response);
            return;
        }

        productDAO.save(newProduct);

        response.sendRedirect("products");
    }
}