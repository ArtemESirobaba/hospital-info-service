package net.kickit.hospitalinfoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kickit.hospitalinfoservice.converter.FeatureConverter;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.entity.FacilityEntity;
import net.kickit.hospitalinfoservice.exception.FacilityNotFoundException;
import net.kickit.hospitalinfoservice.repository.FacilityRepository;
import net.kickit.hospitalinfoservice.service.DbInteractionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DbInteractionServiceImpl implements DbInteractionService {
    @Value(value = "${data-file.path}")
    private String dataFilePath;
    private final FacilityRepository repository;
    private final DataExtractorImpl dataExtractor;
    private final FeatureConverter featureConverter;


    @PostConstruct
    public void initDbData() throws IOException {
        dataExtractor.getFeatureListFromFile(dataFilePath)
                .stream().map(featureConverter::featureToEntity).collect(Collectors.toList())
                .stream().peek(x -> log.info("Saving company entity: {}", x.toString()))
                .forEach(repository::save);

    }

    @Override
    public FacilityDto findFacilityInfoById(Integer id) {

        FacilityEntity facility = repository.findByRecordId(id).stream().findFirst()
                .orElseThrow( () -> new FacilityNotFoundException(" Cannot find facility with id " + id ));
        log.debug("Found facility info {}", facility.toString());

        return featureConverter.mapFacilityEntityToDto(facility);
    }

    @Override
    @Transactional
    public void deleteFacilityInfoById(Integer id) {
        log.debug("Deleting facility with id {}", id);
        repository.deleteAllByRecordId(id);
    }

    @Override
    public List<FacilityDto> getAllFacilities() {
        List<FacilityEntity> facilityEntities = repository.findAll();
        log.debug("Found list of facilities size {}", facilityEntities.size());

        return facilityEntities.stream()
                .map(featureConverter::mapFacilityEntityToDto).collect(Collectors.toList());
    }
}
