package com.example.dao;

import com.example.model.Category;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDAO extends GenericDAOImpl<Category, Long> {

    public List<Category> findByNameContaining(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Category> query = session.createQuery(
                    "from Category where lower(name) like lower(:keyword)",
                    Category.class
            );
            query.setParameter("keyword", "%" + keyword + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
