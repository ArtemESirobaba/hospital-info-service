package net.kickit.hospitalinfoservice.service;

import net.kickit.hospitalinfoservice.model.Facility;

import java.io.IOException;
import java.util.List;

public interface DataExtractor {
    public List<Facility> getFeatureListFromFile(String pathToFile) throws IOException;
}
