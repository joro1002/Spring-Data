package softuni.jsonprocessing.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CustomerWithPurchaseDto {
    @Expose
    private String name;
    @Expose
    private Integer boughtCars;
    @Expose
    private BigDecimal spentMoney;

    public CustomerWithPurchaseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
