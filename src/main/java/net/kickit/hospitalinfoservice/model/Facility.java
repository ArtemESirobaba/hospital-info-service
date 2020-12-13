package net.kickit.hospitalinfoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Facility {

    @JsonProperty("id")
    private Integer recordId;
    private String type;
    @JsonProperty("company_id")
    private Integer companyId;
    @JsonProperty("name_ru")
    private String nameRu;
    @JsonProperty("name_ua")
    private String nameUa;
    @JsonProperty("name_en")
    private String nameEn;
    @JsonProperty("work_hours")
    private Object workHours;
    @JsonProperty("is_online")
    private Boolean isOnline;
    @JsonProperty("is_visible")
    private Boolean isVisible;
    private Services services;
}
