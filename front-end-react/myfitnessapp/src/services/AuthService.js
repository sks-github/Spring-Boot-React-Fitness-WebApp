import axios from 'axios';

const SIGNUP_API_URL = "http://localhost:8080/signup"
const LOGIN_API_URL = "http://localhost:8080/login"

class AuthService{
    signUp(user){
        return axios.post(SIGNUP_API_URL, user);
    }

    logIn(userCredentials){
        return axios.post(LOGIN_API_URL, userCredentials);  //in axios response is already json()-ed
        return fetch(LOGIN_API_URL, {
            headers:{
                "Content-Type": "application/json",
            },
            method: "post",
            body:JSON.stringify(userCredentials)
        });
    }
}

export default new AuthService();