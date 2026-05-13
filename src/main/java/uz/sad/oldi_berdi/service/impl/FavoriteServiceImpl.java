package uz.sad.oldi_berdi.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.sad.oldi_berdi.entity.Ad;
import uz.sad.oldi_berdi.entity.Favorite;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.dto.AdListDto;
import uz.sad.oldi_berdi.mapper.AdMapper;
import uz.sad.oldi_berdi.repository.AdRepository;
import uz.sad.oldi_berdi.repository.FavoriteRepository;
import uz.sad.oldi_berdi.service.FavoriteService;
import uz.sad.oldi_berdi.validator.AdValidator;
import uz.sad.oldi_berdi.validator.UserValidator;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserValidator userValidator;
    private final AdMapper adMapper;
    private final AdValidator adValidator;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserValidator userValidator, AdMapper adMapper, AdRepository adRepository, AdValidator adValidator) {
        this.favoriteRepository = favoriteRepository;
        this.userValidator = userValidator;
        this.adMapper = adMapper;
        this.adValidator = adValidator;
    }

    @Override
    public List<AdListDto> getFavorites(String login) {
        User user = userValidator.checkByLogin(login);
        return favoriteRepository.findAllByUserId(user.getId())
                .stream()
                .map(
                        fav -> adMapper.toListDto(fav.getAd()))
                .toList();
    }

    @Override
    public void toggle(Long adId, String login) {
        User user = userValidator.checkByLogin(login);
        Optional<Favorite> exist = favoriteRepository.findByUserIdAndAdId(user.getId(), adId);
        Ad ad = adValidator.checkById(adId);
        if (exist.isPresent()){
            favoriteRepository.deleteByUserIdAndAdId(user.getId(), ad.getId());
        }else{
            save(ad, user);
        }
    }

    @Override
    public void syncFromLocaleStorage(List<Long> adIds, String login) {
        User user = userValidator.checkByLogin(login);
        for (Long adId : adIds) {
            boolean exists =
                    favoriteRepository.findByUserIdAndAdId(user.getId(), adId)
                            .isPresent();
            if (!exists) {
                Ad ad = adValidator.checkById(adId);
                save(ad, user);
            }
        }
    }

    private void save(Ad ad, User user){
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setAd(ad);
        favoriteRepository.save(favorite);
    }
}
