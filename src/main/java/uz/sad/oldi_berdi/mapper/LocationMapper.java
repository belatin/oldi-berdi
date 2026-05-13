package uz.sad.oldi_berdi.mapper;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.Location;
import uz.sad.oldi_berdi.entity.dto.LocationDto;

@Component
public class LocationMapper {
    public LocationDto toDto(Location location) {

        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setLocationType(location.getType().name());
        if (location.getParent() != null) {
            dto.setParentId(location.getParent().getId());
            dto.setParentName(location.getParent().getName());
        }
        return dto;
    }
}
