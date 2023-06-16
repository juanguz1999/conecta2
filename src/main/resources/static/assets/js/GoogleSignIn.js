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

        // Verificar el dominio del correo electrónico
        const email = user.email;
        const emailDomain = email.split('@')[1];

        if (emailDomain === 'alincoln.edu.pe') {
          // El dominio del correo electrónico es correcto
          // Continúa con el proceso de inicio de sesión

          // Enviar los datos del usuario a la aplicación de Spring
          fetch('/guardarUsuario', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              id: 0,
              nombre: user.displayName,
              apellido: user.displayName,
              correoElectronico: user.email,
              contrasena: userCredential,
              tipousuario: 0,
              fecharegistro: 12,
              // Agrega más campos aquí
            })
          });

          // Redirigir al usuario a otra página
          window.location.href = 'vistaprincipal';
        } else {
          // El dominio del correo electrónico no es correcto
          // Cierra la sesión del usuario y muestra un mensaje de error
          auth.signOut();
          alert('El dominio del correo electrónico no es válido');
        }
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