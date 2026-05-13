package uz.sad.oldi_berdi.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;
    private Long parentId;
    private String parentName;
    private boolean hasChildren;

}
