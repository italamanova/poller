import React, {useState} from 'react'

function validate(url) {
    return /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(url);
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