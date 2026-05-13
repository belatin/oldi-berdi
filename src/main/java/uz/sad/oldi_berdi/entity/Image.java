package uz.sad.oldi_berdi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sad.oldi_berdi.entity.base.BaseEntity;

@Entity
@Table(name = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity {

    @Column(nullable = false)
    private String url;

    @Column(name = "is_main", nullable = false)
    private boolean main = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;
}
