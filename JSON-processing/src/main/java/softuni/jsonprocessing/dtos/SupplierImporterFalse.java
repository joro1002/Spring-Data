package softuni.jsonprocessing.dtos;

import com.google.gson.annotations.Expose;

public class SupplierImporterFalse {
    @Expose
    private Long id;
    @Expose
    private String name;

    public SupplierImporterFalse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
