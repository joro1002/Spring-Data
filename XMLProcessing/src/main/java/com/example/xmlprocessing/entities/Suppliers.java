package com.example.xmlprocessing.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Suppliers extends BaseEntity{
  private String name;
  private boolean isImporter;

    public Suppliers() {
    }

    public Suppliers(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_importer")
    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }


}
