import React from 'react'

const EndpointTable = (props) => (
    <table>
        <thead>
        <tr>
            <th>Name (Url)</th>
            <th>State</th>
            <th>Created</th>
            <th>Updated</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        {props.endpoints.length > 0 ? (
            props.endpoints.map((endpoint) => (
                <tr key={endpoint.id}>
                    <td>{endpoint.url}</td>
                    <td>{endpoint.active ? "OK" : "FAIL"}</td>
                    <td>{endpoint.created}</td>
                    <td>{endpoint.updated}</td>
                    <td>
                        <button
                            onClick={() => props.deleteEndpoint(endpoint.id)}
                            className="button muted-button"
                        >
                            Delete
                        </button>
                    </td>
                </tr>
            ))
        ) : (
            <tr>
                <td colSpan={3}>No endpoints</td>
            </tr>
        )}
        </tbody>
    </table>
)

export default EndpointTable