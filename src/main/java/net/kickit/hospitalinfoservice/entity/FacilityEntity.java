package net.kickit.hospitalinfoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facility")
public class FacilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "id")
    private Integer recordId;
    private String type;
    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "company_ru")
    private String nameRu;
    @Column(name = "company_ua")
    private String nameUa;
    @Column(name = "company_en")
    private String nameEn;
    @Column(name = "is_online")
    private Boolean isOnline;
    @Column(name = "is_visible")
    private Boolean isVisible;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private ServicesEntity services;
    @Column(name = "working_hours")
    private String workHours;
}
