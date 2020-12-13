package net.kickit.hospitalinfoservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicesDto {

    private Boolean vip;
    private Boolean allHours;
    private Boolean pi;
    private Boolean entities;
    private Boolean premium;
    private Boolean englishSpeaking;
}
