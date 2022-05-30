package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersImportXmlRootDto {
    @XmlElement(name = "player")
    private List<PlayerImportXmlDto> players;

    public PlayersImportXmlRootDto() {
    }

    public List<PlayerImportXmlDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerImportXmlDto> players) {
        this.players = players;
    }
}
