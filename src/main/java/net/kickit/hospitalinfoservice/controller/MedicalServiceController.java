package net.kickit.hospitalinfoservice.controller;

import lombok.AllArgsConstructor;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.exception.InvalidInputException;
import net.kickit.hospitalinfoservice.service.DbInteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class MedicalServiceController {
    private DbInteractionService dbInteractionService;

    @GetMapping(value = "/facility")
    public ResponseEntity<List<FacilityDto>> getAllFacilities(){
        return ResponseEntity.ok(dbInteractionService.getAllFacilities());
    }

    @GetMapping(value = "/facility/{id}")
    public ResponseEntity<FacilityDto> getFacilityInfoById(@PathVariable(value = "id") Integer id){
        Optional.ofNullable(id).orElseThrow(() -> new InvalidInputException("Facility id can not be null"));
        return ResponseEntity.ok(dbInteractionService.findFacilityInfoById(id));
    }

    @DeleteMapping(value = "/facility/{id}")
    public ResponseEntity<Object> deleteFacilityInfoById(@PathVariable(value = "id") Integer id){
        Optional.ofNullable(id).orElseThrow(() -> new InvalidInputException("Facility id can not be null"));
        dbInteractionService.deleteFacilityInfoById(id);
    return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/health")
    public ResponseEntity<String> checkHealth(){
        return ResponseEntity.ok("OK");
    }
}
