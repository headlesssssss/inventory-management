package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet("/update-product")
public class ProductUpdateServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Integer stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String sku = request.getParameter("sku");

        Optional<Product> optionalProduct = productDAO.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStockQuantity(stockQuantity);
            product.setSku(sku);

            productDAO.update(product);
        }

        response.sendRedirect("products");
    }
}