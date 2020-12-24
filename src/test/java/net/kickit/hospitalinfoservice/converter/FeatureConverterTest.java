package net.kickit.hospitalinfoservice.converter;


import net.kickit.hospitalinfoservice.configuration.TestConfiguration;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.entity.FacilityEntity;
import net.kickit.hospitalinfoservice.entity.ServicesEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FeatureConverter.class})
@Import({TestConfiguration.class})
public class FeatureConverterTest {
    @Autowired
    private FeatureConverter featureConverter;

    @Test
    void intWorksOricaTest(){
        ServicesEntity servicesEntity = ServicesEntity.builder()
                .vip(true)
                .pi(false)
                .premium(true)
                .entities(false)
                .englishSpeaking(true)
                .allHours(false)
                .id(1).build();

        FacilityEntity entity = FacilityEntity.builder()
                .companyId(30)
                .recordId(218)
                .isOnline(true)
                .isVisible(false)
                .workHours("some working hours")
                .nameEn("en")
                .nameRu("ru")
                .nameUa("ua")
                .services(servicesEntity)
                .build();

        FacilityDto facilityDto = featureConverter.mapFacilityEntityToDto(entity);

        System.out.println(facilityDto);

    }


}
