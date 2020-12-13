package net.kickit.hospitalinfoservice.config;

import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import net.kickit.hospitalinfoservice.entity.FacilityEntity;
import net.kickit.hospitalinfoservice.model.Facility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
    public MapperFacade mapperFacade(final MapperFactory factory) {
        return factory.getMapperFacade();
    }

    private <A, B> Mapper<A, B> toRegisteredMapper(final ClassMapBuilder<A, B> builder) {
        builder.register();
        return builder.toClassMap().getCustomizedMapper();
    }
}
