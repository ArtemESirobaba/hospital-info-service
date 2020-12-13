package net.kickit.hospitalinfoservice.controller;

import ma.glasnost.orika.MapperFacade;
import net.kickit.hospitalinfoservice.configuration.TestConfiguration;
import net.kickit.hospitalinfoservice.dto.FacilityDto;
import net.kickit.hospitalinfoservice.dto.ServicesDto;
import net.kickit.hospitalinfoservice.exception.FacilityNotFoundException;
import net.kickit.hospitalinfoservice.service.DbInteractionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        MedicalServiceController.class,
        ExceptionHelper.class,
        MapperFacade.class,
        DbInteractionService.class})
@Import({TestConfiguration.class})
public class MedicalServiceControllerTest {

    @MockBean
    private DbInteractionService dbInteractionService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void handlesHttpRequestCorrectlyGetAllFacilities() throws Exception {
        final Integer id = 218;

        ServicesDto servicesDto = ServicesDto.builder()
                .allHours(true).englishSpeaking(false).entities(true).premium(false)
                .pi(true).vip(false).build();

        FacilityDto facilityDto = FacilityDto.builder()
                .id(id)
                .companyId(31)
                .isOnline(true)
                .isVisible(true)
                .nameEn("any name")
                .nameRu("любое имя")
                .nameUa("Будь яке иия")
                .type("service")
                .services(servicesDto)
                .build();

        when(dbInteractionService.getAllFacilities()).thenReturn(Arrays.asList(facilityDto));

        mockMvc.perform(get("/api/facility")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].companyId").value(facilityDto.getCompanyId()));
    }

    @Test
    void handlesHttpRequestCorrectlyGetFacilityById() throws Exception {
        final Integer id = 218;
        ServicesDto servicesDto = ServicesDto.builder()
                .allHours(true).englishSpeaking(false).entities(true).premium(false)
                .pi(true).vip(false).build();

        FacilityDto facilityDto = FacilityDto.builder()
                .id(id)
                .companyId(31)
                .isOnline(true)
                .isVisible(true)
                .nameEn("any name")
                .nameRu("любое имя")
                .nameUa("Будь яке иия")
                .type("service")
                .services(servicesDto)
                .build();

        when(dbInteractionService.findFacilityInfoById(id)).thenReturn(facilityDto);

        mockMvc.perform(get("/api/facility/218")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.companyId").value(facilityDto.getCompanyId()));
    }

    @Test
    void handlesHttpRequestCorrectlyTriggeredDeleteById() throws Exception {
        final Integer id = 218;

        doNothing().when(dbInteractionService).deleteFacilityInfoById(id);

        ArgumentCaptor<Integer> inputCaptor = ArgumentCaptor.forClass(Integer.class);

        mockMvc.perform(delete("/api/facility/218")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(dbInteractionService, timeout(1)).deleteFacilityInfoById(inputCaptor.capture());


        Integer parameters = inputCaptor.getValue();

        assertThat(parameters).isEqualTo(id);
    }


    @Test
    void handlesHttpRequestCorrectlyThrowsExceptionWhenIdNotFound() throws Exception {
        final Integer wrongId = 333;

        when(dbInteractionService.findFacilityInfoById(wrongId)).thenThrow(new FacilityNotFoundException(" Cannot find facility with id " + wrongId ));

        mockMvc.perform(get("/api/facility/" + wrongId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getHealth() throws Exception {

        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk());
    }

}
