package uz.sad.oldi_berdi.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {

    private Long id;
    private String name;
    private String locationType;
    private Long parentId;
    private String parentName;
    private boolean hasChildren;
}
