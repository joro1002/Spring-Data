package exam.model.dtos;

import com.google.gson.annotations.Expose;

public class TownImportDto {
    @Expose
    private String name;

    public TownImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
