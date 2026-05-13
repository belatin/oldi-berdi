package uz.sad.oldi_berdi.service.impl;

import org.springframework.stereotype.Service;
import uz.sad.oldi_berdi.entity.Location;
import uz.sad.oldi_berdi.entity.dto.CategoryDto;
import uz.sad.oldi_berdi.entity.dto.LocationDto;
import uz.sad.oldi_berdi.mapper.LocationMapper;
import uz.sad.oldi_berdi.repository.LocationRepository;
import uz.sad.oldi_berdi.service.LocationService;
import uz.sad.oldi_berdi.validator.LocationValidator;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final LocationValidator locationValidator;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper mapper, LocationValidator locationValidator) {
        this.locationRepository = locationRepository;
        this.locationMapper = mapper;
        this.locationValidator = locationValidator;
    }

    @Override
    public List<LocationDto> getAll() {
        return locationRepository.findAllByDeletedFalse().stream().map(locationMapper::toDto).toList();
    }

    @Override
    public LocationDto getById(Long id) {
        return locationMapper.toDto(locationValidator.checkById(id));
    }

    @Override
    public List<LocationDto> getRootLocations() {
        List<Location> roots = locationRepository.findAllByParentNull();
        return roots.stream().map(
                location -> {
                    LocationDto dto = locationMapper.toDto(location);
                    boolean hasChildren = locationRepository.existsByParentId(location.getId());
                    dto.setHasChildren(hasChildren);
                    return dto;
                }).toList();
    }

    @Override
    public List<LocationDto> getChildCities(Long parentId) {
        List<Location> children = locationRepository.findAllByParentId(parentId);
        return children.stream().map(
                location -> {
                    LocationDto dto = locationMapper.toDto(location);
                    boolean hasChildren = locationRepository.existsByParentId(location.getId());
                    dto.setHasChildren(hasChildren);
                    return dto;
                }).toList();
    }
}
