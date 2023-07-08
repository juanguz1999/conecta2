
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
        });
}

function actualizarCursos() {

    var gradoSelect = document.getElementById('gradoSelect');
    var seccionSelect = document.getElementById('seccionSelect');
    var cursoSelect = document.getElementById('cursoSelect');
    var gradoId = gradoSelect.value;
    var seccionId = seccionSelect.value;


    // Realizar la solicitud fetch con el parámetro 'gradoId'
    fetch('/obtenerCursos?gradoId=' + gradoId + '&seccionId=' + seccionId)
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error en la respuesta de la solicitud AJAX');
            }
        })
        .then(function (cursos) {
            cursoSelect.innerHTML = '';
            cursos.forEach(function (curso) {
                var option = document.createElement('option');
                option.value = curso.id;
                option.textContent = curso.nombreCurso;
                cursoSelect.appendChild(option);
            });
        })
        .catch(function (error) {
            console.error(error);
        });
}


function notasEstudiantes() {
    var gradoSelect = document.getElementById('gradoSelect');
    var seccionSelect = document.getElementById('seccionSelect');
    var cursoSelect = document.getElementById('cursoSelect');
    var tablaEstudiantes = document.getElementById('tablaEstudiantes');

    var gradoId = gradoSelect.value;
    var seccionId = seccionSelect.value;
    var cursoId = cursoSelect.value;

    fetch('/obtenerEstudiantesCurso?gradoId=' + gradoId + '&seccionId=' + seccionId + '&cursoId=' + cursoId)
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

                //Nota para PC1
                var PC1Celda = document.createElement('td');
                var selectPC1 = document.createElement('select');
                selectPC1.setAttribute('class', 'form-control');
                selectPC1.setAttribute('name', 'PC1');
                selectPC1.setAttribute('data-estudiante-id', estudiante.id); // Atributo personalizado para guardar el ID del estudiante

                for (let i = 0; i <= 20; i++) {
                    var option = document.createElement('option');
                    option.setAttribute('value', i);
                    option.textContent = `${i}`;
                    selectPC1.appendChild(option);
                }
                PC1Celda.appendChild(selectPC1);

                //Nota para PC2
                var PC2Celda = document.createElement('td');
                var selectPC2 = document.createElement('select');
                selectPC2.setAttribute('class', 'form-control');
                selectPC2.setAttribute('name', 'PC2');
                selectPC2.setAttribute('data-estudiante-id', estudiante.id); // Atributo personalizado para guardar el ID del estudiante

                for (let i = 0; i <= 20; i++) {
                    var option = document.createElement('option');
                    option.setAttribute('value', i);
                    option.textContent = `${i}`;
                    selectPC2.appendChild(option);
                }
                PC2Celda.appendChild(selectPC2);

                //Nota para PC3
                var PC3Celda = document.createElement('td');
                var selectPC3 = document.createElement('select');
                selectPC3.setAttribute('class', 'form-control');
                selectPC3.setAttribute('name', 'PC3');
                selectPC3.setAttribute('data-estudiante-id', estudiante.id); // Atributo personalizado para guardar el ID del estudiante

                for (let i = 0; i <= 20; i++) {
                    var option = document.createElement('option');
                    option.setAttribute('value', i);
                    option.textContent = `${i}`;
                    selectPC3.appendChild(option);
                }
                PC3Celda.appendChild(selectPC3);

                //Nota para Final
                var FINALCelda = document.createElement('td');
                var selectFINAL = document.createElement('select');
                selectFINAL.setAttribute('class', 'form-control');
                selectFINAL.setAttribute('name', 'Final');
                selectFINAL.setAttribute('data-estudiante-id', estudiante.id); // Atributo personalizado para guardar el ID del estudiante

                for (let i = 0; i <= 20; i++) {
                    var option = document.createElement('option');
                    option.setAttribute('value', i);
                    option.textContent = `${i}`;
                    selectFINAL.appendChild(option);
                }
                FINALCelda.appendChild(selectFINAL);

                //Poner la fila
                fila.appendChild(numeroCelda);
                fila.appendChild(idCelda);
                fila.appendChild(nombreCelda);
                fila.appendChild(apellidoCelda);
                fila.appendChild(PC1Celda);
                fila.appendChild(PC2Celda);
                fila.appendChild(PC3Celda);
                fila.appendChild(FINALCelda);

                tablaEstudiantes.appendChild(fila);

            });

            // Obtener las calificaciones existentes de la base de datos
            fetch('/obtenerCalificaciones?gradoId=' + gradoId + '&seccionId=' + seccionId + '&cursoId=' + cursoId)
                .then(function (response) {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Error en la respuesta de la solicitud AJAX');
                    }
                })
                .then(function (data) {
                    // Recorrer las calificaciones existentes y asignar los valores correspondientes
                    for (let key in data) {
                        if (data.hasOwnProperty(key)) {
                            var calificacion = data[key];

                            var pc1 = calificacion["PC1"];
                            var select = document.querySelector('select[data-estudiante-id="' + key + '"][name="PC1"]');
                            if (select) {
                                select.value = pc1.toString();
                            }

                            var pc2 = calificacion["PC2"];

                            var select = document.querySelector('select[data-estudiante-id="' + key + '"][name="PC2"]');
                            if (select) {
                                select.value = pc2.toString();
                            }

                            var pc3 = calificacion["PC3"];

                            var select = document.querySelector('select[data-estudiante-id="' + key + '"][name="PC3"]');
                            if (select) {
                                select.value = pc3.toString();
                            }

                            var exfinal = calificacion["EXFINAL"];

                            var select = document.querySelector('select[data-estudiante-id="' + key + '"][name="Final"]');
                            if (select) {
                                select.value = exfinal.toString();
                            }

                        }
                    }

                });

        })
        .catch(function (error) {
            console.error(error);
        });
}

function guardarNotas() {
    var tablaEstudiantes = document.getElementById('tablaEstudiantes');
    var gradoId = document.getElementById('gradoSelect').value;
    var seccionId = document.getElementById('seccionSelect').value;
    var cursoId = document.getElementById('cursoSelect').value;
    // Obtener los datos de asistencia de cada estudiante
    var filasEstudiantes = Array.from(tablaEstudiantes.getElementsByTagName('tr'));
    var notasEstudiante = filasEstudiantes.map(function (fila) {
        var estudianteID = fila.querySelector('td[data-id]').getAttribute('data-id');
        var PC1 = fila.querySelector('select[name="PC1"]').value;
        var PC2 = fila.querySelector('select[name="PC2"]').value;
        var PC3 = fila.querySelector('select[name="PC3"]').value;
        var Final = fila.querySelector('select[name="Final"]').value;

        return { estudianteID, PC1, PC2, PC3, Final, gradoId, seccionId, cursoId };
    });

    // Enviar los datos de asistencia al servidor
    fetch('/guardarNotasEstudiante', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(notasEstudiante)
    })
        .then(function (response) {
            if (response.ok) {
                console.log('Notas guardadas correctamente');

            } else {
                throw new Error('Error al guardar la asistencia');
            }
        })
        .catch(function (error) {
            console.error(error);
        });
}