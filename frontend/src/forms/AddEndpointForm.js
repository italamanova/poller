import React, {useState} from 'react'

function validate(url) {
    return /^https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#()?&//=]*)$/i.test(url)
}

const AddEndpointForm = (props) => {
    const initialFormState = {url: ''}
    const [endpoint, setEndpoint] = useState(initialFormState)
    const [error, setError] = useState(false)

    const handleInputChange = (event) => {
        const {name, value} = event.target
        setEndpoint({...endpoint, [name]: value})
    }

    return (
        <form
            onSubmit={(event) => {
                event.preventDefault()
                if (!endpoint.url) return
                if (!validate(endpoint.url)) {
                    setError(true);
                    return
                }
                setError(false);
                props.addEndpoint(endpoint)
                setEndpoint(initialFormState)
            }}
        >
            <label>Name (url)</label>
            <input
                type="text"
                name="url"
                value={endpoint.url}
                placeholder="https://..."
                onChange={handleInputChange}
            />
            {error && <p><span style={{color: "red"}}>Please make sure the url has correct format</span></p>}
            <button>Add new service</button>
        </form>
    )
}

export default AddEndpointForm