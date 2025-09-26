CREATE DATABASE IF NOT EXISTS property_db;
USE property_db;

CREATE TABLE IF NOT EXISTS properties (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    size DOUBLE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO properties (address, price, size, description) VALUES
('123 Main Street, New York, NY', 450000.00, 85.5, 'Beautiful 2-bedroom apartment in downtown with great city views'),
('456 Oak Avenue, Los Angeles, CA', 750000.00, 120.0, 'Modern 3-bedroom house with backyard and garage'),
('789 Pine Road, Chicago, IL', 325000.00, 95.2, 'Cozy 2-bedroom condo near public transportation'),
('321 Elm Street, Miami, FL', 525000.00, 110.8, 'Luxury beachfront property with ocean views'),
('654 Maple Drive, Seattle, WA', 675000.00, 140.5, 'Family home with large garden and modern amenities');

SELECT * FROM properties;