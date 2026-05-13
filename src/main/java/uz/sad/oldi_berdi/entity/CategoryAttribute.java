package uz.sad.oldi_berdi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sad.oldi_berdi.entity.base.BaseEntity;
import uz.sad.oldi_berdi.entity.enums.AttributeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category_attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAttribute extends BaseEntity {

    @Column(nullable = false, length = 80)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private AttributeType attributeType;

    @Column(nullable = false)
    private boolean required = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryAttributeOption> options = new ArrayList<>();
}