package uz.sad.oldi_berdi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import uz.sad.oldi_berdi.entity.dto.AdListDto;
import uz.sad.oldi_berdi.service.AdService;
import uz.sad.oldi_berdi.service.FavoriteService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdApiController {

    private final AdService adService;
    private final FavoriteService favoriteService;

    public AdApiController(AdService adService, FavoriteService favoriteService) {
        this.adService = adService;
        this.favoriteService = favoriteService;
    }

    @PostMapping("/by-ids")
    public List<AdListDto> getAdsByIds(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return adService.findAllByIds( ids);
    }

    @GetMapping("/my")
    public List<AdListDto> getMyFavorites(Principal principal) {
        if (principal == null) {
            return Collections.emptyList();
        }
        return favoriteService.getFavorites(principal.getName());
    }

}
