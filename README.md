## Sobre el Laboratorio V1
Este proyecto implementa una API REST para la gestión de Productos y Categorías
como parte del Proyecto 3 del Bachillerato en Ingeniería del Software de CENFOTEC.

La API permite realizar operaciones CRUD completas sobre dos entidades principales:
Producto y Categoría, donde un producto pertenece a una categoría y una categoría
puede tener múltiples productos.

La seguridad se maneja mediante JWT (JSON Web Tokens). Al hacer login, el sistema
devuelve un token que debe enviarse en cada request. Existen dos roles: SUPER_ADMIN_ROLE
que puede crear, actualizar y eliminar registros, y USER que solo puede consultar.

Los passwords se almacenan encriptados con MD5 en la base de datos. Al arrancar el
proyecto se insertan automáticamente dos usuarios y dos roles, sin
necesidad de crearlos manualmente.

Para probar la API se incluye una colección de Postman con todos los endpoints
configurados con variables de entorno, de forma que al hacer login el token se
guarda automáticamente y se usa en los demás requests sin necesidad de copiarlo
manualmente.