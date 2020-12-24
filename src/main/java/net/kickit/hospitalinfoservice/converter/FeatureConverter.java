package net.kickit.hospitalinfoservice.converter;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
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
        return mapperFacade.map(facilityEntity, FacilityDto.class);
    }
}
