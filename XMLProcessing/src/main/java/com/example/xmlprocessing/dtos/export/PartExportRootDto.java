package com.example.xmlprocessing.dtos.export;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartExportRootDto {

    @XmlElement(name = "part")
    private List<PartExportDto> parts;

    public PartExportRootDto() {
    }

    public List<PartExportDto> getParts() {
        return parts;
    }

    public void setParts(List<PartExportDto> parts) {
        this.parts = parts;
    }
}
