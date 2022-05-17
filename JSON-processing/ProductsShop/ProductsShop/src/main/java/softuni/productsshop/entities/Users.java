package softuni.productsshop.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users extends BaseEntity{
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Users> friends;
    private List<Products> sellerProducts;


    public Users() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToMany(targetEntity = Users.class)
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    public List<Users> getFriends() {
        return friends;
    }

    public void setFriends(List<Users> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "seller")
    public List<Products> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerProducts(List<Products> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }
}
