// Importe las funciones que necesita de los SDK

import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-analytics.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-auth.js";
import { GoogleAuthProvider, signInWithPopup } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-auth.js";
import { signInWithCredential } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-auth.js";

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
const analytics = getAnalytics(app);

window.handleCredentialResponse = function (response) {
  console.log(response);

  // Verificar si el inicio de sesión fue exitoso
  if (response.credential) {
    const auth = getAuth();
    const googleCredential = GoogleAuthProvider.credential(response.credential);
    signInWithCredential(auth, googleCredential)
      .then((userCredential) => {
        // El inicio de sesión fue exitoso
        const user = userCredential.user;
        const profilePictureURL = user.photoURL;
        console.log("Inicio de sesión exitoso");
        // Redirigir al usuario a otra página
        window.location.href = 'vistaprincipal';
      })
      .catch((error) => {
        // Ocurrió un error al iniciar sesión
        const errorCode = error.code;
        const errorMessage = error.message;
        console.log("Error al iniciar sesión");
      });
  } else {
    console.log("Error al iniciar sesión");
  }
}