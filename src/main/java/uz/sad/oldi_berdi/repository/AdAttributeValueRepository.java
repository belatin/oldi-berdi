package uz.sad.oldi_berdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.sad.oldi_berdi.entity.AdAttributeValue;
import uz.sad.oldi_berdi.entity.dto.AttributeValueDto;

import java.util.List;

public interface AdAttributeValueRepository extends JpaRepository<AdAttributeValue, Long> {
    @Query("""
            select new
            uz.sad.oldi_berdi.entity.dto.AttributeValueDto(ca.name, av.value)
            from AdAttributeValue av
                 join CategoryAttribute ca
                 on av.attribute.id = ca.id
            where av.ad.id = :adId
            """)
    List<AttributeValueDto> findAllByAdId(@Param("adId") Long adId);
}
