function mostrarFechaActual() {
    var fecha = new Date();
    var dia = fecha.getDate();
    var mes = fecha.getMonth() + 1; // Los meses comienzan desde 0
    var anio = fecha.getFullYear();

    // Asegurarse de que los dígitos de día y mes tengan dos dígitos
    if (dia < 10) {
        dia = '0' + dia;
    }
    if (mes < 10) {
        mes = '0' + mes;
    }

    var fechaActual = dia + '/' + mes + '/' + anio;

    // Mostrar la fecha en un elemento HTML con el id "fecha"
    document.getElementById("fecha").innerHTML = fechaActual;
}

function actualizarSecciones() {
    var seccionSelect = document.getElementById('seccionSelect');
    var gradoSelect = document.getElementById('gradoSelect');
    var gradoId = gradoSelect.value;

    // Realizar la solicitud fetch con el parámetro 'gradoId'
    fetch('/obtenerSecciones?gradoId=' + gradoId)
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error en la respuesta de la solicitud AJAX');
            }
        })
        .then(function (secciones) {
            seccionSelect.innerHTML = '';
            secciones.forEach(function (seccion) {
                var option = document.createElement('option');
                option.value = seccion.id;
                option.textContent = seccion.nombreSeccion;
                seccionSelect.appendChild(option);
            });
        })
        .catch(function (error) {
            console.error(error);
        });
}

function asistenciaEstudiantes() {
    var gradoSelect = document.getElementById('gradoSelect');
    var seccionSelect = document.getElementById('seccionSelect');
    var tablaEstudiantes = document.getElementById('tablaEstudiantes');

    var gradoId = gradoSelect.value;
    var seccionId = seccionSelect.value;

    fetch('/obtenerEstudiantes?gradoId=' + gradoId + '&seccionId=' + seccionId)
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error en la respuesta de la solicitud AJAX');
            }
        })
        .then(function (estudiantes) {
            // Limpiar la tabla de estudiantes
            tablaEstudiantes.innerHTML = '';

            // Agregar los estudiantes a la tabla
            estudiantes.forEach(function (estudiante, index) {
                var fila = document.createElement('tr');

                var numeroCelda = document.createElement('th');
                numeroCelda.setAttribute('scope', 'row');
                numeroCelda.textContent = index + 1;

                var idCelda = document.createElement('td');
                idCelda.textContent = estudiante.id;
                idCelda.setAttribute('data-id', estudiante.id); // Atributo personalizado "data-id"

                var nombreCelda = document.createElement('td');
                nombreCelda.textContent = estudiante.nombre;
                nombreCelda.setAttribute('data-nombre', estudiante.nombre); // Atributo personalizado "data-nombre"

                var apellidoCelda = document.createElement('td');
                apellidoCelda.textContent = estudiante.apellido;
                apellidoCelda.setAttribute('data-apellido', estudiante.apellido); // Atributo personalizado "data-apellido"

                var asistenciaCelda = document.createElement('td');
                var selectAsistencia = document.createElement('select');
                selectAsistencia.setAttribute('class', 'form-control');
                selectAsistencia.setAttribute('name', 'estadoAsistencia');
                selectAsistencia.setAttribute('data-estudiante-id', estudiante.id); // Atributo personalizado para guardar el ID del estudiante
                var optionSi = document.createElement('option');
                optionSi.setAttribute('value', 'Si');
                optionSi.textContent = 'Si';
                var optionNo = document.createElement('option');
                optionNo.setAttribute('value', 'No');
                optionNo.textContent = 'No';
                var optionJustificado = document.createElement('option');
                optionJustificado.setAttribute('value', 'Justificado');
                optionJustificado.textContent = 'Justificado';

                selectAsistencia.appendChild(optionSi);
                selectAsistencia.appendChild(optionNo);
                selectAsistencia.appendChild(optionJustificado);

                asistenciaCelda.appendChild(selectAsistencia);

                var observacionCelda = document.createElement('td');
                var creacionBox = document.createElement('textarea');
                creacionBox.setAttribute('class', 'form-control');
                creacionBox.setAttribute('name', 'observaciones');
                creacionBox.setAttribute('data-estudiante-id', estudiante.id); // Atributo personalizado para guardar el ID del estudiante
                observacionCelda.appendChild(creacionBox);

                fila.appendChild(numeroCelda);
                fila.appendChild(idCelda);
                fila.appendChild(nombreCelda);
                fila.appendChild(apellidoCelda);
                fila.appendChild(asistenciaCelda);
                fila.appendChild(observacionCelda);

                tablaEstudiantes.appendChild(fila);

                // Obtener los datos de asistencia del estudiante
                fetch('/obtenerAsistencia?estudianteId=' + estudiante.id)
                    .then(function (response) {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error('Error en la respuesta de la solicitud AJAX');
                        }
                    })
                    .then(function (asistencia) {
                        // Asignar los valores de asistencia y observaciones a la fila correspondiente
                        selectAsistencia.value = asistencia.estadoAsistencia || 'Si';
                        creacionBox.value = asistencia.observaciones || '';
                    })
                    .catch(function (error) {
                        console.error(error);
                    });
            });
        })
        .catch(function (error) {
            console.error(error);
        });
}

function guardarAsistencia() {
    var tablaEstudiantes = document.getElementById('tablaEstudiantes');

    // Obtener los datos de asistencia de cada estudiante
    var filasEstudiantes = Array.from(tablaEstudiantes.getElementsByTagName('tr'));
    var datosAsistencia = filasEstudiantes.map(function (fila) {
        var estudianteID = fila.querySelector('td[data-id]').getAttribute('data-id');
        var estadoAsistencia = fila.querySelector('select[name="estadoAsistencia"]').value;
        var observaciones = fila.querySelector('textarea[name="observaciones"]').value;
        return { estudianteID, estadoAsistencia, observaciones };
    });

    // Enviar los datos de asistencia al servidor
    fetch('/guardarAsistencia', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosAsistencia)
    })
        .then(function (response) {
            if (response.ok) {
                console.log('Asistencia guardada correctamente');

            } else {
                throw new Error('Error al guardar la asistencia');
            }
        })
        .catch(function (error) {
            console.error(error);
        });
}