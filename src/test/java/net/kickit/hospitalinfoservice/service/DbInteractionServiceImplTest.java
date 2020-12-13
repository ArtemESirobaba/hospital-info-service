package net.kickit.hospitalinfoservice.service;

import net.kickit.hospitalinfoservice.configuration.TestConfiguration;
import net.kickit.hospitalinfoservice.converter.FeatureConverter;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.entity.FacilityEntity;
import net.kickit.hospitalinfoservice.repository.FacilityRepository;
import net.kickit.hospitalinfoservice.service.impl.DataExtractorImpl;
import net.kickit.hospitalinfoservice.service.impl.DbInteractionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DbInteractionServiceImpl.class, FacilityRepository.class, DataExtractorImpl.class, FeatureConverter.class})
@Import({TestConfiguration.class})
public class DbInteractionServiceImplTest {
    public static final String ADD_DATA_TO_REPO = "classpath:db/add_data.sql";
    public static final String DROP_DATA_FROM_REPO = "classpath:db/drop_data.sql";

    @MockBean
    private DataExtractorImpl dataExtractor;

    @Autowired
    private FeatureConverter featureConverter;

    @Autowired
    private DbInteractionServiceImpl dbInteractionService;

    @Autowired
    private FacilityRepository facilityRepository;

    @Test
    @Sql(scripts = {DROP_DATA_FROM_REPO, ADD_DATA_TO_REPO})
    void getsAllFacilities(){
        List<FacilityEntity> all = facilityRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
        assertThat(all.get(0)).isNotNull();
    }

    @Test
    @Sql(scripts = {DROP_DATA_FROM_REPO, ADD_DATA_TO_REPO})
    void getsAllFacilities2(){
        List<FacilityEntity> all = facilityRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
        assertThat(all.get(0)).isNotNull();
    }

    @Test
    @Sql(scripts = {DROP_DATA_FROM_REPO, ADD_DATA_TO_REPO})
    void getsAllFacilitiesFromService(){
        List<FacilityDto> all = dbInteractionService.getAllFacilities();
        assertThat(all.size()).isEqualTo(3);
        assertThat(all.get(0)).isNotNull();
    }

    @Test
    @Sql(scripts = {DROP_DATA_FROM_REPO, ADD_DATA_TO_REPO})
    void findsFacilitiesFromServiceById(){
        final Integer id = 218;
        FacilityDto result = dbInteractionService.findFacilityInfoById(id);
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    @Sql(scripts = {DROP_DATA_FROM_REPO, ADD_DATA_TO_REPO})
    void deletesFacilitiesFromServiceById(){
        final Integer id = 218;
        dbInteractionService.deleteFacilityInfoById(id);

        List<FacilityEntity> all = facilityRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getId()).isNotEqualTo(id);
        assertThat(all.get(1).getId()).isNotEqualTo(id);
    }





}
