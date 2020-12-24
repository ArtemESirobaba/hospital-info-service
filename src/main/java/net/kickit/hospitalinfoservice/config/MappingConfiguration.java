package net.kickit.hospitalinfoservice.config;

import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.entity.FacilityEntity;
import net.kickit.hospitalinfoservice.model.Facility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;


@Configuration
public class MappingConfiguration {

    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder()
                .mapNulls(Boolean.FALSE)
                .build();
    }

    @Bean
    public Mapper<Facility, FacilityEntity> servicesFacilityToEntity(MapperFactory factory){
        return toRegisteredMapper(factory.classMap(Facility.class, FacilityEntity.class)
                .byDefault()
                .exclude("servicesEntity"));
    }

    @Bean
    public Mapper<FacilityEntity, FacilityDto> servicesFacilityEntityToDto(MapperFactory factory){
        return toRegisteredMapper(factory.classMap(FacilityEntity.class, FacilityDto.class)
                .byDefault()
        .customize(new CustomMapper<FacilityEntity, FacilityDto>() {
            @Override
            public void mapAtoB(FacilityEntity facilityEntity, FacilityDto facilityDto, MappingContext ctx) {
                Optional.ofNullable(facilityEntity)
                        .map(FacilityEntity::getRecordId)
                        .ifPresent(facilityDto::setId);
            }
        }));
    }


    @Bean
    public MapperFacade mapperFacade(final MapperFactory factory) {
        return factory.getMapperFacade();
    }

    private <A, B> Mapper<A, B> toRegisteredMapper(final ClassMapBuilder<A, B> builder) {
        builder.register();
        return builder.toClassMap().getCustomizedMapper();
    }
}
