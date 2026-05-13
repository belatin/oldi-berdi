package uz.sad.oldi_berdi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.sad.oldi_berdi.service.FavoriteService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {


    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public String getFavoritesPage() {
        return "favorites/list";
    }


    @PostMapping("/sync")
    @ResponseBody
    public ResponseEntity<Void> sync(@RequestBody List<Long> adIds, Principal principal){
        favoriteService.syncFromLocaleStorage(adIds, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/toggle/{adId}")
    @ResponseBody
    public ResponseEntity<Void> toggle(@PathVariable Long adId, Principal principal){
        favoriteService.toggle(adId, principal.getName());
        return ResponseEntity.ok().build();
    }
}
