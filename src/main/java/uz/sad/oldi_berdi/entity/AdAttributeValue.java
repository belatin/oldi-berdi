package uz.sad.oldi_berdi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sad.oldi_berdi.entity.base.BaseEntity;

@Entity
@Table(name = "ad_attribute_values")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdAttributeValue extends BaseEntity {

    @Column
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private CategoryAttribute attribute;
}