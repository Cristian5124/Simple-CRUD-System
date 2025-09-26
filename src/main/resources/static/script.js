let editingProperty = null;

window.onload = function () {
    loadProperties();
    setupForm();
};

function setupForm() {
    document.getElementById('property-form').onsubmit = function (e) {
        e.preventDefault();
        handleFormSubmit();
    };
}

function handleFormSubmit() {

    const address = document.getElementById('address').value;
    const price = document.getElementById('price').value;
    const size = document.getElementById('size').value;
    const description = document.getElementById('description').value;

    if (!address || !price || !size) {
        alert('Please fill in all required fields');
        return;
    }

    const propertyData = {
        address: address,
        price: parseFloat(price),
        size: parseFloat(size),
        description: description
    };

    if (editingProperty) {
        updateProperty(editingProperty.id, propertyData);
    } else {
        createProperty(propertyData);
    }
}

function loadProperties() {
    fetch('/api/v1/properties')
        .then(response => response.json())
        .then(properties => {
            displayProperties(properties);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error loading properties');
        });
}

function createProperty(propertyData) {
    fetch('/api/v1/properties', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(propertyData)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 400) {
                throw new Error('Invalid data provided');
            } else {
                throw new Error('Server error');
            }
        })
        .then(property => {
            alert('Property created successfully!');
            clearForm();
            loadProperties();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error creating property: ' + error.message);
        });
}

function updateProperty(id, propertyData) {
    fetch('/api/v1/properties/' + id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(propertyData)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 400) {
                throw new Error('Invalid data provided');
            } else if (response.status === 404) {
                throw new Error('Property not found');
            } else {
                throw new Error('Server error');
            }
        })
        .then(property => {
            alert('Property updated successfully!');
            cancelEdit();
            loadProperties();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error updating property: ' + error.message);
        });
}

function deleteProperty(id) {
    if (confirm('Are you sure you want to delete this property?')) {
        fetch('/api/v1/properties/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.status === 204) {
                    alert('Property deleted successfully!');
                    loadProperties();
                } else if (response.status === 404) {
                    alert('Property not found');
                } else {
                    alert('Error deleting property');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error deleting property');
            });
    }
}

function displayProperties(properties) {
    const container = document.getElementById('properties-list');

    if (properties.length === 0) {
        container.innerHTML = '<p>No properties found. Add your first property above.</p>';
        return;
    }

    let html = '';
    for (let i = 0; i < properties.length; i++) {
        const property = properties[i];
        html += '<div class="property-card">';
        html += '<div class="property-address">' + property.address + '</div>';
        html += '<div class="property-details">';
        html += '<div class="property-detail"><strong>Price:</strong> $' + property.price + '</div>';
        html += '<div class="property-detail"><strong>Size:</strong> ' + property.size + ' sq m</div>';
        if (property.description) {
            html += '<div class="property-detail"><strong>Description:</strong> ' + property.description + '</div>';
        }
        html += '</div>';
        html += '<button class="btn-edit" onclick="editProperty(' + property.id + ')">Edit</button>';
        html += '<button class="btn-delete" onclick="deleteProperty(' + property.id + ')">Delete</button>';
        html += '</div>';
    }

    container.innerHTML = html;
}

function editProperty(id) {
    fetch('/api/v1/properties/' + id)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 404) {
                throw new Error('Property not found');
            } else {
                throw new Error('Server error');
            }
        })
        .then(property => {
            editingProperty = property;

            document.getElementById('property-id').value = property.id;
            document.getElementById('address').value = property.address;
            document.getElementById('price').value = property.price;
            document.getElementById('size').value = property.size;
            document.getElementById('description').value = property.description || '';

            document.getElementById('form-title').textContent = 'Edit Property';
            document.getElementById('submit-btn').textContent = 'Update Property';
            document.getElementById('cancel-btn').style.display = 'inline-block';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error loading property for edit: ' + error.message);
        });
}

function cancelEdit() {
    editingProperty = null;
    clearForm();

    document.getElementById('form-title').textContent = 'Add New Property';
    document.getElementById('submit-btn').textContent = 'Add Property';
    document.getElementById('cancel-btn').style.display = 'none';
}

function clearForm() {
    document.getElementById('property-form').reset();
    document.getElementById('property-id').value = '';
}