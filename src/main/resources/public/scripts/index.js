// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAnyfl80Wu2h8Lu2MODIR9HMVV2pb4tHPI",
  authDomain: "developerpizza-9763b.firebaseapp.com",
  projectId: "developerpizza-9763b",
  storageBucket: "developerpizza-9763b.appspot.com",
  messagingSenderId: "823280058309",
  appId: "1:823280058309:web:698c79cf821a3b870d15ac",
  measurementId: "G-FQZ3SY0521"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const auth = getAuth(app);

document.addEventListener('DOMContentLoaded', () => {
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const loginButton = document.getElementById('login');
    const error = document.getElementById('error');

    loginButton.addEventListener('click', () => {
        fetch(`/login/`, {
            method: 'POST',
            body: `{"username":"${usernameInput.value}","password":"${passwordInput.value}"}`,
            credentials: 'include' // Very important so that the browser will retain the Cookie when we log in
            // by default the browser will throw the Cookie away
        }).then((res) => {
            if (res.status === 204) {
                window.location.href = '/pages/startorder.html';

            } else {
                alert('Invalid username or password');
            }
        });
    });
    function getJSessionId(){
        var jsId = document.cookie.match(/JSESSIONID=[^;]+/);
        if(jsId != null) {
            if (jsId instanceof Array)
                jsId = jsId[0].substring(11);
            else
                jsId = jsId.substring(11);
        }
        return jsId;
    }
});

signInWithEmailAndPassword(auth, email, password)
    .then((userCredentials) => {
    //Signed in
    const user = userCredentials.user;
    })
    .catch((error) => {
    const errorCode = error.code;
    const errorMessage = error.message;
    });