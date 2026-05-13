package uz.sad.oldi_berdi.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.sad.oldi_berdi.entity.*;
import uz.sad.oldi_berdi.entity.dto.AdCreateDto;
import uz.sad.oldi_berdi.entity.dto.AdDetailDto;
import uz.sad.oldi_berdi.entity.dto.AdListDto;
import uz.sad.oldi_berdi.entity.enums.AdStatus;
import uz.sad.oldi_berdi.service.FileStorageService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AdMapper {

    private final FileStorageService fileStorageService;

    public AdMapper(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public AdDetailDto toDetailDto(Ad ad) {
        return AdDetailDto.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .description(ad.getDescription())
                .price(ad.getPrice())
                .categoryName(ad.getCategory().getName())
                .status(ad.getStatus().name())
                .username(ad.getUser().getUsername())
                .sellerPhone(ad.getUser().getPhone())
                .allowPhoneCall(ad.getUser().isAllowPhoneCall())
                .allowTelegram(ad.getUser().isAllowTelegram())
                .locationName(ad.getLocation().getName())
                .createdAt(ad.getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm")))
                .imagesUrls(
                        ad.getImages().stream()
                                .map(Image::getUrl)
                                .toList())
                .build();
    }

    public AdListDto toListDto(Ad ad){
        return AdListDto.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .price(ad.getPrice())
                .categoryName(ad.getCategory().getName())
                .locationName(ad.getLocation().getName())
                .createdAt(ad.getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm")))
                .imageUrl(
                ad.getImages().stream()
                        .filter(Image::isMain)
                        .map(Image::getUrl)
                        .findFirst()
                        .orElse(
                                ad.getImages().isEmpty() ? null
                                        : ad.getImages().get(0).getUrl()
                        )
        )
                .build();
    }

    public Ad fromDto(AdCreateDto dto, User user, Location location, Category category) {
        Ad ad = new Ad();
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        ad.setStatus(AdStatus.ACTIVE);
        ad.setUser(user);
        ad.setCategory(category);
        ad.setLocation(location);
        List<Image> images = ad.getImages();
        List<MultipartFile> multipartFiles = dto.getImages();

        if (multipartFiles != null && !multipartFiles.isEmpty()){
            for (int i = 0; i < multipartFiles.size(); i++) {
                MultipartFile file = multipartFiles.get(i);
                if (!file.isEmpty()) {
                    String fileName = fileStorageService.save(file);
                    Image image = new Image();
                    image.setUrl(fileName);

                    boolean isMain = (dto.getMainImageIndex() != null) ? dto.getMainImageIndex() == i : i == 0;
                    image.setMain(isMain);
                    image.setAd(ad);
                    images.add(image);
                }
            }
        }
        ad.setImages(images);

        return ad;
    }
}
