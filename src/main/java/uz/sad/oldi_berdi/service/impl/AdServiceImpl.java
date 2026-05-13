package uz.sad.oldi_berdi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.sad.oldi_berdi.entity.*;
import uz.sad.oldi_berdi.entity.dto.AdCreateDto;
import uz.sad.oldi_berdi.entity.dto.AdDetailDto;
import uz.sad.oldi_berdi.entity.dto.AdListDto;
import uz.sad.oldi_berdi.entity.dto.AttributeValueDto;
import uz.sad.oldi_berdi.exception.CustomException;
import uz.sad.oldi_berdi.mapper.AdMapper;
import uz.sad.oldi_berdi.repository.*;
import uz.sad.oldi_berdi.service.AdService;
import uz.sad.oldi_berdi.validator.AdValidator;
import uz.sad.oldi_berdi.validator.CategoryValidator;
import uz.sad.oldi_berdi.validator.LocationValidator;
import uz.sad.oldi_berdi.validator.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final AdValidator adValidator;
    private final UserValidator userValidator;
    private final CategoryAttributeRepository categoryAttributeRepository;
    private final CategoryValidator categoryValidator;
    private final LocationValidator locationValidator;
    private final AdAttributeValueRepository adAttributeValueRepository;


    public AdServiceImpl(AdRepository adRepository, AdMapper adMapper, AdValidator adValidator,
                         UserValidator userValidator, CategoryAttributeRepository categoryAttributeRepository,
                         CategoryValidator categoryValidator, LocationValidator locationValidator,
                         AdAttributeValueRepository adAttributeValueRepository) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.adValidator = adValidator;
        this.userValidator = userValidator;
        this.categoryAttributeRepository = categoryAttributeRepository;
        this.categoryValidator = categoryValidator;
        this.locationValidator = locationValidator;
        this.adAttributeValueRepository = adAttributeValueRepository;
    }


    @Override
    public Page<AdListDto> getAll(Pageable pageable) {
        return adRepository.findAllByDeletedFalse(pageable).
                map(adMapper::toListDto);
    }

    @Override
    public AdDetailDto getById(Long id) {
        List<AttributeValueDto> attributeValue = adAttributeValueRepository.findAllByAdId(id);
        AdDetailDto detailDto = adMapper.toDetailDto(adValidator.checkById(id));
        detailDto.setAttributeCharacteristic(attributeValue.stream()
                .collect(Collectors.toMap(
                        AttributeValueDto::getName,
                        AttributeValueDto::getValue)));
        return detailDto;
    }

    @Override
    public void create(AdCreateDto dto, String login) {

        User user = userValidator.checkByLogin(login);
        Location location = locationValidator.checkById(dto.getLocationId());
        Category category = categoryValidator.checkById(dto.getCategoryId());
        Ad ad = adMapper.fromDto(dto, user, location, category);
        if (dto.getAttributeValues() != null) {
            List<AdAttributeValue> values = dto.getAttributeValues().entrySet().stream()
                    .filter(e -> e.getValue() != null && !e.getValue().isBlank())
                    .map(e -> {
                        CategoryAttribute attr = categoryAttributeRepository.findById(e.getKey())
                                .orElseThrow(() -> new CustomException("Attribute not found"));
                        AdAttributeValue val = new AdAttributeValue();
                        val.setAd(ad);
                        val.setAttribute(attr);
                        val.setValue(e.getValue());
                        return val;
                    })
                    .toList();
            ad.setAdAttributeValues(values);
        }
        adRepository.save(ad);
    }

    @Override
    public List<AdListDto> findAllByIds(List<Long> ids) {
        List<Ad> ads = adRepository.findAllById(ids);
        Map<Long, Ad> adMap = ads
                .stream()
                .collect(Collectors.toMap(Ad::getId, Function.identity()));
        return ids.stream()
                .filter(adMap::containsKey)
                .map(adMap::get)
                .map(adMapper::toListDto)
                .collect(Collectors.toList());
    }
}
