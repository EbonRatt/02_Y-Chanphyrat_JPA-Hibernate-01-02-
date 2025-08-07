package com.hrd.jpahibernate.repository;

import com.hrd.jpahibernate.model.entity.Product;
import com.hrd.jpahibernate.model.reponse.Pagination;
import com.hrd.jpahibernate.model.request.ProductRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class CRUDRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertItem(Product product) {
        entityManager.persist(product);

    }

    public Product findItem(Long id) {
        return entityManager.find(Product.class,id);
    }

    public Product deleteItem(Long id) {
        Product product = findItem(id);
        entityManager.remove(product);
        return product;
    }

    public Product updateItem(ProductRequest product, Long id){
        Product newProduct = findItem(id);

        if(newProduct != null){
            newProduct.setId(id);
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());
            newProduct.setQuantity(product.getQuantity());
        }
        entityManager.merge(newProduct);
        return newProduct;
    }


    public List<Product> getAllItems(int pageNumber, int pageSize) {
        List<Product> productList = entityManager.createQuery("SELECT p FROM Product AS p ORDER BY p.id", Product.class)
                .setFirstResult((pageNumber - 1 ) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return productList;
    }

    public Pagination getPagination(int pageNumber, int pageSize){
        Query queryTotal = entityManager.createQuery
                ("Select count(p.id) from Product AS p");

        Long countResult = (Long) queryTotal.getSingleResult();
        Long totalPage = ((countResult / pageSize) + 1);

        Pagination response = new Pagination();
        response.setCurrentPage(pageNumber);
        response.setTotalElements(countResult);
        response.setPageSize(pageSize);
        response.setTotalPages(totalPage);

        return response;
    }

    public List<Product> getItemByName(String name){
        List<Product> productList = entityManager.createQuery("SELECT p FROM Product AS p WHERE p.name ILIKE :name", Product.class)
                .setParameter("name", "%"+ name + "%")
                .getResultList();
        return productList;
    }

    public List<Product> getItemLowStock(Integer qty){
        List<Product> productList = entityManager.createQuery("SELECT p FROM Product AS p WHERE p.quantity < :qty", Product.class)
                .setParameter("qty", qty)
                .getResultList();
        return productList;
    }

}
