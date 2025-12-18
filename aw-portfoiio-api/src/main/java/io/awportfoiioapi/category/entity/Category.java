package io.awportfoiioapi.category.entity;

import io.awportfoiioapi.category.dto.request.CategoryPutRequest;
import io.awportfoiioapi.mapperd.DateSuperClass;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Table(name = "CATEGORY")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Category extends DateSuperClass {
    
    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Column(name = "CATEGORY_ORDERS")
    private Integer categoryOrders;
    @Column(name = "CATEGORY_SLUG")
    private String categorySlug;
    
    public void modify(CategoryPutRequest request) {
        this.categoryName = request.getName();
        this.categoryOrders = request.getOrder();
        this.categorySlug = request.getSlug();
    }
}
