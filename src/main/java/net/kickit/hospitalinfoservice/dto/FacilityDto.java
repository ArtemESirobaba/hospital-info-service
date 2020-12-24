package net.kickit.hospitalinfoservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDto {
    private Integer id;
    private String type;
    private Integer companyId;
    private String nameRu;
    private String nameUa;
    private String nameEn;
    private Boolean isOnline;
    private Boolean isVisible;
    private ServicesDto services;
    private String workHours;
}
