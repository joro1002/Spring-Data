package softuni.productsshop.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Categories extends BaseEntity{
    private String name;
    private List<Products> products;

    public Categories() {
        this.products = new ArrayList<>();
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(targetEntity = Products.class, mappedBy = "categories", cascade = CascadeType.ALL)
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
