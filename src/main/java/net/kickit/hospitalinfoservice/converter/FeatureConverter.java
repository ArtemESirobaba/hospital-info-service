package net.kickit.hospitalinfoservice.converter;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.dto.ServicesDto;
import net.kickit.hospitalinfoservice.entity.FacilityEntity;
import net.kickit.hospitalinfoservice.entity.ServicesEntity;
import net.kickit.hospitalinfoservice.model.Facility;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FeatureConverter {
    private final MapperFacade mapperFacade;

    public FacilityEntity featureToEntity(Facility facility){
        FacilityEntity facilityEntity = mapperFacade.map(facility, FacilityEntity.class);
        ServicesEntity servicesEntity = mapperFacade.map(facility.getServices(), ServicesEntity.class);
        facilityEntity.setServices(servicesEntity);
        return facilityEntity;
    }

    public FacilityDto mapFacilityEntityToDto(FacilityEntity facilityEntity){
        ServicesDto servicesDto =ServicesDto.builder()
                .allHours(facilityEntity.getServices().getAllHours())
                .englishSpeaking(facilityEntity.getServices().getEnglishSpeaking())
                .pi(facilityEntity.getServices().getPi())
                .entities(facilityEntity.getServices().getEntities())
                .premium(facilityEntity.getServices().getPremium())
                .vip(facilityEntity.getServices().getVip())
                .build();

        return FacilityDto.builder()
                .type(facilityEntity.getType())
                .nameUa(facilityEntity.getNameUa())
                .nameRu(facilityEntity.getNameRu())
                .nameEn(facilityEntity.getNameEn())
                .isVisible(facilityEntity.getIsVisible())
                .isOnline(facilityEntity.getIsOnline())
                .companyId(facilityEntity.getCompanyId())
                .id(facilityEntity.getRecordId())
                .services(servicesDto)
                .workHours(facilityEntity.getWorkHours())
                .build();
    }
}
