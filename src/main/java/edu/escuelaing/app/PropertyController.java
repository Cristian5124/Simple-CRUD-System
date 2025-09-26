package edu.escuelaing.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing properties.
 * Provides CRUD operations through HTTP endpoints.
 */
@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Gets all properties.
     *
     * @return list of all properties
     */
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Gets a property by ID.
     *
     * @param id the property ID
     * @return the property if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyRepository.findById(id);
        if (property.isPresent()) {
            return ResponseEntity.ok(property.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new property.
     *
     * @param property the property to create
     * @return the created property with 201 status, or 400 if validation fails
     */
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        try {
            if (property.getAddress() == null || property.getAddress().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (property.getPrice() == null || property.getPrice() <= 0) {
                return ResponseEntity.badRequest().build();
            }
            if (property.getSize() == null || property.getSize() <= 0) {
                return ResponseEntity.badRequest().build();
            }

            Property savedProperty = propertyRepository.save(property);
            return ResponseEntity.status(201).body(savedProperty);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Updates an existing property.
     *
     * @param id              the property ID
     * @param propertyDetails the updated property details
     * @return the updated property if found, 404 if not found, 400 if validation
     *         fails
     */
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        try {
            Optional<Property> optionalProperty = propertyRepository.findById(id);
            if (optionalProperty.isPresent()) {
                if (propertyDetails.getAddress() == null || propertyDetails.getAddress().trim().isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }
                if (propertyDetails.getPrice() == null || propertyDetails.getPrice() <= 0) {
                    return ResponseEntity.badRequest().build();
                }
                if (propertyDetails.getSize() == null || propertyDetails.getSize() <= 0) {
                    return ResponseEntity.badRequest().build();
                }

                Property property = optionalProperty.get();
                property.setAddress(propertyDetails.getAddress());
                property.setPrice(propertyDetails.getPrice());
                property.setSize(propertyDetails.getSize());
                property.setDescription(propertyDetails.getDescription());
                return ResponseEntity.ok(propertyRepository.save(property));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Deletes a property by ID.
     *
     * @param id the property ID
     * @return 204 if deleted successfully, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        try {
            if (propertyRepository.existsById(id)) {
                propertyRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}