package exam.model.dtos;

import com.google.gson.annotations.Expose;
import exam.model.WarrantyType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class LaptopImportDto {
    @Expose
    private String macAddress;
    @Expose
    private double cpuSpeed;
    @Expose
    private int ram;
    @Expose
    private int storage;
    @Expose
    private String description;
    @Expose
    private BigDecimal price;
    @Expose
    private String warrantyType;
    @Expose

    private ShopLaptopImportDto shop;

    public LaptopImportDto() {
    }

    @Length(min = 9)
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    @Min(value = 0)

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    @Min(value = 8)
    @Max(value = 128)
    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Min(value = 128)
    @Max(value = 1024)
    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Length(min = 10)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Min(value = 0)

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }
}
