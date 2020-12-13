package net.kickit.hospitalinfoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Services {
    public Boolean vip;
    @JsonProperty("all_hours")
    public Boolean allHours;
    public Boolean pi;
    public Boolean entities;
    public Boolean premium;
    @JsonProperty("english_speaking")
    public Boolean englishSpeaking;
}
