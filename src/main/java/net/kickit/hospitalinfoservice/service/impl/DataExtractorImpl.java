package net.kickit.hospitalinfoservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.kickit.hospitalinfoservice.model.Facility;
import net.kickit.hospitalinfoservice.service.DataExtractor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataExtractorImpl implements DataExtractor {

    private final ObjectMapper objectMapper;

    public List<Facility> getFeatureListFromFile(String pathToFile) throws IOException {
        Path path = Paths.get(pathToFile);
        byte[] bytes = Files.readAllBytes(path);

        JsonNode node = objectMapper.readTree(bytes);
        node = node.get("features");

        TypeReference<List<Facility>> typeRef = new TypeReference<List<Facility>>(){};

        return objectMapper.readValue(node.traverse(), typeRef);
    }

}
