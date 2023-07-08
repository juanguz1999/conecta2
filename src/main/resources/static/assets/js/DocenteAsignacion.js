
function actualizarSecciones() {
    var selectedCurso = $("#cursoSelect option:selected");
    var valorCursoSeleccionado = selectedCurso.val();
    console.log(valorCursoSeleccionado);

    //var gradoSelect = document.getElementById('gradoSelect');
    //var gradoId = gradoSelect.value;

    // Realizar la solicitud fetch con el parámetro 'gradoId'
    /*fetch('/obtenerSecciones?gradoId=' + gradoId)
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error en la respuesta de la solicitud AJAX');
            }
        })
        .then(function (secciones) {
            seccionSelect.innerHTML = '';
            var option = document.createElement('option');
            option.value = '';
            option.textContent = 'Seleccione una sección';
            seccionSelect.appendChild(option);
            secciones.forEach(function (seccion) {
                var option = document.createElement('option');
                option.value = seccion.id;
                option.textContent = seccion.nombreSeccion;
                seccionSelect.appendChild(option);
            });
        })
        .catch(function (error) {
            console.error(error);
        });*/
}