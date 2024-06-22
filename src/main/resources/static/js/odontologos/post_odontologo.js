window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault(); // Previene el envío del formulario por defecto

        // Validación de datos
        const matricula = document.querySelector('#matricula').value.trim();
        const nombre = document.querySelector('#nombre').value.trim();
        const apellido = document.querySelector('#apellido').value.trim();
        let valid = true;
        let errorMessage = '';

        // Validar que la matrícula no esté vacía y solo contenga caracteres alfanuméricos
        if (matricula === '' || !/^[a-zA-Z0-9]+$/.test(matricula)) {
            valid = false;
            errorMessage += '<p>La matrícula es obligatoria y debe contener solo letras y números.</p>';
        }

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

        // Si los datos no son válidos, mostrar mensaje de error y no enviar el formulario
        if (!valid) {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                             '<strong>Error:</strong> ' + errorMessage + '</div>';
            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
            return;
        }

        // Crear el JSON con los datos del odontólogo
        const formData = {
            matricula: matricula,
            nombre: nombre,
            apellido: apellido
        };

        // Configuración de la solicitud fetch
        const url = '/odontologos';
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
                                   '<strong></strong> Odontólogo agregado </div>';
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
        document.querySelector('#matricula').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/post_odontologos.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});