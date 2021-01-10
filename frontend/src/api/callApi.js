const httpCodeNoContent = 204;

export function getEndpointsFromApi() {
    return fetch('http://localhost:8080/api/endpoints')
        .then(data => {
            if (data.status === httpCodeNoContent) {
                return [];
            } else {
                return data.json();
            }
        })
}

export function addEndpointFromApi(endpoint) {
    return fetch('http://localhost:8080/api/endpoints', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(endpoint)
    })
        .then(data => data.json())
}

export function deleteEndpointFromApi(id) {
    return fetch('http://localhost:8080/api/endpoints/' + id, {
        method: 'DELETE'
    })
}