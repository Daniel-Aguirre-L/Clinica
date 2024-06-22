window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_paciente');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault(); // Previene el envío del formulario por defecto

        // Validación de datos
        const nombre = document.querySelector('#nombre').value.trim();
        const apellido = document.querySelector('#apellido').value.trim();
        const cedula = document.querySelector('#cedula').value.trim();
        const fechaIngreso = document.querySelector('#fechaIngreso').value;
        const calle = document.querySelector('#calle').value.trim();
        const numero = document.querySelector('#numero').value.trim();
        const localidad = document.querySelector('#localidad').value.trim();
        const provincia = document.querySelector('#provincia').value.trim();
        const email = document.querySelector('#email').value.trim();

        let valid = true;
        let errorMessage = '';

        // Validar que el nombre no esté vacío y solo contenga letras
        if (nombre === '' || !/^[a-zA-Z\s]+$/.test(nombre)) {
            valid = false;
            errorMessage += '<p>El nombre es obligatorio y solo debe contener letras.</p>';
        }

        // Validar que el apellido no esté vacío y solo contenga letras
        if (apellido === '' || !/^[a-zA-Z\s]+$/.test(apellido)) {
            valid = false;
            errorMessage += '<p>El apellido es obligatorio y solo debe contener letras.</p>';
        }

        // Validar que la cédula no esté vacía y sea un número
        if (cedula === '' || isNaN(cedula)) {
            valid = false;
            errorMessage += '<p>La cédula es obligatoria y debe ser un número.</p>';
        }

        // Validar que la fecha de ingreso no esté vacía y sea una fecha válida
        if (fechaIngreso === '') {
            valid = false;
            errorMessage += '<p>La fecha de ingreso es obligatoria.</p>';
        }

        // Validar que la calle no esté vacía
        if (calle === '') {
            valid = false;
            errorMessage += '<p>La calle es obligatoria.</p>';
        }

        // Validar que el número no esté vacío y sea un número
        if (numero === '' || isNaN(numero)) {
            valid = false;
            errorMessage += '<p>El número es obligatorio y debe ser un número.</p>';
        }

        // Validar que la localidad no esté vacía
        if (localidad === '') {
            valid = false;
            errorMessage += '<p>La localidad es obligatoria.</p>';
        }

        // Validar que la provincia no esté vacía
        if (provincia === '') {
            valid = false;
            errorMessage += '<p>La provincia es obligatoria.</p>';
        }

        // Validar que el email no esté vacío y tenga un formato válido
        if (email === '' || !/\S+@\S+\.\S+/.test(email)) {
            valid = false;
            errorMessage += '<p>El email es obligatorio y debe tener un formato válido.</p>';
        }

        // Si los datos no son válidos, mostrar mensaje de error y no enviar el formulario
        if (!valid) {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                             '<strong>Error:</strong> ' + errorMessage + '</div>';
            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
            return;
        }

        // Crear el JSON con los datos del paciente
        const formData = {
            nombre: nombre,
            apellido: apellido,
            cedula: cedula,
            fechaIngreso: fechaIngreso,
            domicilio: {
                calle: calle,
                numero: numero,
                localidad: localidad,
                provincia: provincia
            },
            email: email
        };

        // Configuración de la solicitud fetch
        const url = '/pacientes';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        // Enviar la solicitud
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                   '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                   '<strong></strong> Paciente agregado </div>';
                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                 '<strong>Error, intente nuevamente</strong> </div>';
                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            });
    });

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#cedula').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
        document.querySelector('#email').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/post_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});