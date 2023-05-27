import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-app.js";
import { getAuth, onAuthStateChanged } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-auth.js";

//Firebase
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyDX8Uc4Po4-rPKrzxh2JWkXxd16O7I1Eik",
    authDomain: "conectadosp-6c872.firebaseapp.com",
    projectId: "conectadosp-6c872",
    storageBucket: "conectadosp-6c872.appspot.com",
    messagingSenderId: "803161019115",
    appId: "1:803161019115:web:cff53e5d1c2c5a285e24ae",
    measurementId: "G-7VG5NV4KJK"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth();

onAuthStateChanged(auth, (user) => {
    if (user) {
        // El usuario ha iniciado sesión
        console.log(user);
        console.log(user.photoURL);
        document.querySelector("#user-name").textContent = user.displayName;
        document.querySelector("#profile-picture").src = user.photoURL;

    } else {
        // El usuario no ha iniciado sesión
        document.querySelector("#user-name").textContent = "";
    }
});

document.querySelector("#sign-out-button").addEventListener("click", () => {
    auth.signOut().then(() => {
        location.replace('/');
    });
});