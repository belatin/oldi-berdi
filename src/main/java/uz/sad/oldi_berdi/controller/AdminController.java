package uz.sad.oldi_berdi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.dto.AdListDto;
import uz.sad.oldi_berdi.entity.dto.CategoryAttributeDto;
import uz.sad.oldi_berdi.entity.dto.CategoryDto;
import uz.sad.oldi_berdi.entity.dto.UserDto;
import uz.sad.oldi_berdi.service.AdService;
import uz.sad.oldi_berdi.service.CategoryAttributeService;
import uz.sad.oldi_berdi.service.CategoryService;
import uz.sad.oldi_berdi.service.UserService;

import java.util.List;

@Controller()
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final AdService adService;
    private final CategoryAttributeService categoryAttributeService;
    private final CategoryService categoryService;

    public AdminController(UserService userService, AdService adService, CategoryAttributeService categoryAttributeService, CategoryService categoryService) {
        this.userService = userService;
        this.adService = adService;
        this.categoryAttributeService = categoryAttributeService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String stat(Model model){
        model.addAttribute("totalUsers", userService.countUsers());
        model.addAttribute("blockedUsers", userService.countOfBlockedUsers());
        model.addAttribute("totalAds", adService.countAds());
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<UserDto> usersDto =  userService.getAll();
        model.addAttribute("users", usersDto);
        return "admin/users";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        UserDto dto =  userService.getById(id);
        model.addAttribute("users", dto);
        return "admin/user-detail";
    }

    @PostMapping("/users/{id}/block")
    public String block(@PathVariable("id") Long id){
        userService.blockUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/unblock")
    public String unBlock(@PathVariable("id") Long id){
        userService.unBlockUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/ads")
    public String getAllAds(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<AdListDto> adsPage = adService.getAllAdsForAdmin(pageable);
        model.addAttribute("ads", adsPage);
        return "admin/ads";
    }

    @PostMapping("/ads/{id}/delete")
    public String deleteAd(@PathVariable Long id) {
        adService.deleteAdById(id);
        return "redirect:/admin/ads";
    }


    // Category attribute management
    @GetMapping("/category-attributes")
    public String listAttributes(@RequestParam(required = false) Long categoryId, Model model) {
        List<CategoryDto> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        if (categoryId != null) {
            List<CategoryAttributeDto> attributes = categoryAttributeService.getByCategoryId(categoryId);
            model.addAttribute("attributes", attributes);
            model.addAttribute("selectedCategoryId", categoryId);
        }
        return "admin/category-attributes";
    }

    @GetMapping("/category-attributes/create")
    public String createAttributeForm(@RequestParam Long categoryId, Model model) {
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("attribute", new CategoryAttributeDto());
        return "admin/attribute-form";
    }


}
