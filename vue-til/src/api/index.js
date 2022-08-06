import axios from "axios";

function registerUser(userData) {
    const url = 'http://localhost:8000/api/signup';
    return axios.post(url, userData);
}

export {registerUser};