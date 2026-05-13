package uz.sad.oldi_berdi.validator;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.Location;
import uz.sad.oldi_berdi.exception.CustomException;
import uz.sad.oldi_berdi.repository.LocationRepository;

@Component
public class LocationValidator {

    private final LocationRepository repository;

    public LocationValidator(LocationRepository repository) {
        this.repository = repository;
    }

    public Location checkById(Long id) {
        return repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new CustomException("Location by id = " + id + " not found"));
    }
}
