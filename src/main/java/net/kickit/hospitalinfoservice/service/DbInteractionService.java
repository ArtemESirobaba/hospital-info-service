package net.kickit.hospitalinfoservice.service;


import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.model.Facility;

import java.util.List;

public interface DbInteractionService {
    public FacilityDto findFacilityInfoById(Integer id);
    public void deleteFacilityInfoById(Integer id);
    public List<FacilityDto> getAllFacilities();
}
