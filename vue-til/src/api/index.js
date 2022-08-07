import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:8000/api',
})

function registerUser(userData) {
    return instance.post('/signup', userData);
}

export {registerUser};