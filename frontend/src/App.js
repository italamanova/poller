import React, {useState, useEffect, useRef} from 'react'
import EndpointTable from './tables/EndpointTable'
import AddEndpointForm from "./forms/AddEndpointForm";
import {getEndpointsFromApi, addEndpointFromApi, deleteEndpointFromApi} from './api/callApi';


function useInterval(callback, delay) {
    const savedCallback = useRef();

    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);

    useEffect(() => {
        let id = setInterval(() => {
            savedCallback.current();
        }, delay);
        return () => clearInterval(id);
    }, [delay]);
}

const App = () => {
    const [endpoints, setEndpoints] = useState([])
    const [counter, setCounter] = useState(0);

    useEffect(() => {
        let mounted = true;
        getEndpointsFromApi()
            .then(endpoints => {
                if (mounted) {
                    setEndpoints(endpoints)
                }
            })
        return () => mounted = false;
    }, [])

    const addEndpoint = (endpoint) => {
        endpoint.id = endpoints.length + 1
        addEndpointFromApi(endpoint).then(endpoint => {
            setEndpoints([...endpoints, endpoint])
        })
    }

    const deleteEndpoint = (id) => {
        deleteEndpointFromApi(id).then(() =>
            setEndpoints(endpoints.filter((endpoint) => endpoint.id !== id)))
    }

    useInterval(() => {
        setCounter(counter + 1);
        getEndpointsFromApi()
            .then(endpoints => {
                setEndpoints(endpoints)
            })
    }, 3000);

    return (
        <div className="container">
            <h1>Polling app</h1>
            <div className="flex-row">
                <div className="flex-large">
                    <h2>Add service</h2>
                    <AddEndpointForm addEndpoint={addEndpoint}/>
                </div>
                <div className="flex-large">
                    <h2>List of services</h2>
                    <EndpointTable endpoints={endpoints} deleteEndpoint={deleteEndpoint}/>
                </div>
            </div>
        </div>
    )
}

export default App