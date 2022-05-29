package exam.model.dtos;

import com.google.gson.annotations.Expose;

public class ShopLaptopImportDto {
    @Expose
    private String name;

    public ShopLaptopImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
