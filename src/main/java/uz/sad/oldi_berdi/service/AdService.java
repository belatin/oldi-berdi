package uz.sad.oldi_berdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.sad.oldi_berdi.entity.dto.AdCreateDto;
import uz.sad.oldi_berdi.entity.dto.AdDetailDto;
import uz.sad.oldi_berdi.entity.dto.AdListDto;

import java.util.List;

public interface AdService {
    Page<AdListDto> getAll(Pageable pageable);
    AdDetailDto getById(Long l);
    void create(AdCreateDto dto, String login);
    List<AdListDto> findAllByIds(List<Long> ids);
}
