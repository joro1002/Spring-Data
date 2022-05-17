package softuni.productsshop.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products extends BaseEntity{
    private String name;
    private BigDecimal price;
    private Users seller;
    private Users buyer;
    private List<Categories> categories;

    public Products() {
        this.categories = new ArrayList<>();
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public Users getSeller() {
        return seller;
    }

    public void setSeller(Users seller) {
        this.seller = seller;
    }

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public Users getBuyer() {
        return buyer;
    }

    public void setBuyer(Users buyer) {
        this.buyer = buyer;
    }

    @ManyToMany(targetEntity = Categories.class)
    @JoinTable(
            name = "products_categories")
    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
