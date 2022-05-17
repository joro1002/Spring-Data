package softuni.jsonprocessing.dtos;

import com.google.gson.annotations.Expose;

public class SupplierSeedDto {
    @Expose
    private String name;
    @Expose
    private boolean isImporter;

    public SupplierSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
