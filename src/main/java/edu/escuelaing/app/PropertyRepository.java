package edu.escuelaing.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Property entity.
 * Provides basic CRUD operations through JpaRepository.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    /**
     * JpaRepository provides the following methods automatically:
     * - save(Property property) - Creates or updates a property
     * - findById(Long id) - Finds a property by ID
     * - findAll() - Gets all properties
     * - deleteById(Long id) - Deletes a property by ID
     * - count() - Gets the total number of properties
     */
    
}