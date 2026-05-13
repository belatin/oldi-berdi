package uz.sad.oldi_berdi.service;

import uz.sad.oldi_berdi.entity.dto.AdListDto;
import uz.sad.oldi_berdi.entity.dto.FavoriteDto;

import java.util.List;

public interface FavoriteService {
    List<AdListDto> getFavorites(String login);
    void toggle(Long adId, String login);
    void syncFromLocaleStorage(List<Long> adIds, String login);
}
