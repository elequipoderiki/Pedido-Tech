# Pedido Tech
Tienda online de productos tech
- El proyecto productosAPI contiene el servidor de la página. El servidor está implementado con tecnología SpringBoot, framework web del lenguaje JAVA. La persistencia de los datos fué implementada con H2, base de datos en memoria.
- El proyecto frontend contiene código de la interfaz de usuario. Fue implementado con Javascript, HTML5 y CSS.

#### API
La carpeta productosAPI contiene el código para ejecutar el servidor de la página
- abra una terminal en la carpeta productosAPI
- ejecute el comando <code>mvnw package</code>
- luego ejecute el comando <code>mvnw spring-boot:run</code>

Debido a que la persistencia de los datos está implementada con H2 no es necesario ejecutar un motor de base de datos, al ejecutar el servidor ya se incluye la base de datos en la aplicación junto a algunos datos de prueba.
#### Interfaz de usuario
La interfaz de usuario se ejecuta de la siguiente manera
- Ejecute el servidor de la API
- En la carpeta frontend abrir el archivo index.html en el navegador
  - Seleccione los productos que desea comprar y pulse el botón comprar, estos se agregarán al carrito de arriba 
    
    <img width="500px" src="./frontend/img/comprar.png" alt="image_name png" />
  &nbsp;
  - Vaya al carrito de compras y pague o cancele la compra

    <img width="500px" src="./frontend/img/pagar.png" alt="image_name png" />
    &nbsp;
La interfaz de usuario se comunica con el servidor que ejecuta la API por medio de endpoints. Estos se enumeran a continuación
#### Endpoints
