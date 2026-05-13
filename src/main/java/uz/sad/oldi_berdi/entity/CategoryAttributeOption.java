package uz.sad.oldi_berdi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sad.oldi_berdi.entity.base.BaseEntity;

@Entity
@Table(name = "category_attribute_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAttributeOption extends BaseEntity {

    @Column(nullable = false, length = 80)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private CategoryAttribute attribute;
}
