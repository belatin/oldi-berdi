package uz.sad.oldi_berdi.service;

import uz.sad.oldi_berdi.entity.dto.CategoryDto;
import uz.sad.oldi_berdi.entity.dto.LocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAll();
    LocationDto getById(Long id);
    List<LocationDto> getRootLocations();
    List<LocationDto> getChildCities(Long parentId);

}
