package net.kickit.hospitalinfoservice.service;


import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import net.kickit.hospitalinfoservice.model.Facility;
import net.kickit.hospitalinfoservice.service.impl.DataExtractorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class DataExtractorImplTest {

 private ObjectMapper objectMapper = JsonMapper.builder().enable(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS).build();
 private DataExtractor dataExtractor = new DataExtractorImpl(objectMapper);

    @Test
    void getFeatureListFromFileTest() throws IOException {
        final String filePath = "src/test/resources/file/test_task.txt";

        List<Facility> featureListFromFile = dataExtractor.getFeatureListFromFile(filePath);

        assertThat(featureListFromFile.size()).isEqualTo(1);
        assertThat(featureListFromFile.get(0)).isNotNull();
    }
}
