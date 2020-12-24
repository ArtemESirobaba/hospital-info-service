package net.kickit.hospitalinfoservice.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
public class ServicesEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Boolean vip;
    @Column(name = "all_hours")
    private Boolean allHours;
    private Boolean pi;
    private Boolean entities;
    private Boolean premium;
    @Column(name = "english_speaking")
    private Boolean englishSpeaking;
}