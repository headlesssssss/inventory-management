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

@WebServlet("/update-product")
public class ProductUpdateServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
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

        Optional<Product> optionalProduct = productDAO.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStockQuantity(stockQuantity);
            product.setSku(sku);

            String categoryIdStr = request.getParameter("categoryId");
            if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                Long categoryId = Long.parseLong(categoryIdStr);
                Optional<Category> category = categoryDAO.findById(categoryId);
                category.ifPresent(product::setCategory);
            } else {
                product.setCategory(null);
            }

            Map<String, String> errors = ValidationUtil.validate(product);
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("product", product);
                request.setAttribute("categories", categoryDAO.findAll());
                request.getRequestDispatcher("product-form.jsp").forward(request, response);
                return;
            }

            productDAO.update(product);
        }

        response.sendRedirect("products");
    }
}