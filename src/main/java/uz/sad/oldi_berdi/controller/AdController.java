package uz.sad.oldi_berdi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.sad.oldi_berdi.entity.dto.*;
import uz.sad.oldi_berdi.service.AdService;
import uz.sad.oldi_berdi.service.CategoryAttributeService;
import uz.sad.oldi_berdi.service.CategoryService;
import uz.sad.oldi_berdi.service.LocationService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/ads")
public class AdController {
    private final AdService service;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final CategoryAttributeService attributeService;

    public AdController(LocationService locationService, CategoryService categoryService, AdService service, CategoryAttributeService attributeService) {
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.service = service;
        this.attributeService = attributeService;
    }

    @GetMapping("")
    public String getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            Model model,
            HttpServletRequest request
    ){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<AdListDto> adsList = service.getAll(pageable);
        model.addAttribute("ads", adsList);

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "ad/fragments :: ad-list";
        }
        return "ad/list";
    }

    @GetMapping("/{id}")
    public String getDetail(@PathVariable Long id, Model model) {
        AdDetailDto adDetail = service.getById(id);
        model.addAttribute("ad", adDetail);
        System.out.println(adDetail);
        return "ad/detail";
    }

    @GetMapping("/create")
    public String createPage( AdCreateDto createDto){

        return "ad/test";
    }

    @PostMapping("/create")
    public String create(Principal principal, @ModelAttribute AdCreateDto dto){
        service.create(dto, principal.getName());
        return "redirect:/ads";
    }

    @GetMapping("/categories/root")
    @ResponseBody
    public List<CategoryDto> getRootCategories(){
        return categoryService.getRootCategories();
    }

    @GetMapping("/categories/children")
    @ResponseBody
    public List<CategoryDto> getChildCategories(@RequestParam Long parentId){
        return categoryService.getChildCategories(parentId);
    }

    @GetMapping("/locations/root")
    @ResponseBody
    public List<LocationDto> getRootLocations(){
        return locationService.getRootLocations();
    }

    @GetMapping("/locations/children")
    @ResponseBody
    public List<LocationDto> getChildLocations(@RequestParam Long parentId){
        return locationService.getChildCities(parentId);
    }


    @GetMapping("/attributes")
    @ResponseBody
    public List<CategoryAttributeDto> getAttributes(@RequestParam Long categoryId){
        return attributeService.getByCategoryId(categoryId);
    }

}
