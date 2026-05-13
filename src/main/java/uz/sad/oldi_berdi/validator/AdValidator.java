package uz.sad.oldi_berdi.validator;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.Ad;
import uz.sad.oldi_berdi.repository.AdRepository;

@Component
public class AdValidator {
    public final AdRepository repository;

    public AdValidator(AdRepository repository) {
        this.repository = repository;
    }

    public Ad checkById(Long id){
        return repository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new RuntimeException("Ad by id = " + id + " not found")
        );
    }
}
