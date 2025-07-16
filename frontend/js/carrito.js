let cantidad = 0

let userProducts = []

function comprar(nombre, precio, id, parentNode) {

    let cart = document.getElementById("cantidad-carrito")
    let successMessage = document.createElement("div")
    let myproducts = localStorage.getItem("myproducts")
    console.log("leyendo local storage ",myproducts)
    if (!myproducts) {
        console.log("no hay productos guardados")
        userProducts.push({nombre, precio, id})
        let productos = JSON.stringify(userProducts)
        localStorage.setItem("myproducts", productos)
        cantidad += 1;    
        cart.innerText = cantidad

    } else {
        let productos = JSON.parse(localStorage.getItem("myproducts"))

        productos.push({nombre, precio, id})
        items = JSON.stringify(productos)
        localStorage.setItem("myproducts", items)
    
        cart.innerText = productos.length;
        console.log("hay algunos productos guardados", productos)
    }

    if (parentNode && parentNode.id != "description-section") {

        successMessage.classList.add("success-buy")
    } else {

        successMessage.classList.add("single-success-buy")
    }
    successMessage.innerHTML = "Agregado al <b>carrito</b>"
    parentNode.appendChild(successMessage)
    setTimeout(() => {
        successMessage.innerText = "";
        parentNode.removeChild(successMessage)
    }, 1000);

}

async function crearPedido() {

    let itemTemplate = document.querySelector("#lista-carrito")

    itemTemplate.innerHTML = '<i class="bi bi-hourglass-top h1">Creando pedido</i>'

    let productos = JSON.parse(localStorage.getItem("myproducts"))

    let pedido = {
        customer: "Ricardo",
        totalPrice: 0
    }

    const url = `http://localhost:8080/api/delivery`;

    const response = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(pedido),
        headers: {"Content-type": "application/json"}
    })
    .then(response => response.json())
    .then(async pedidonuevo => {
        for(let i=0; i < productos.length; i++) {
            await agregarProductoAlPedido(productos[i], pedidonuevo.id)
        }
        limpiarCarrito();

        itemTemplate.innerText = ""
        itemTemplate.innerHTML = `<i class="h1">Compra exitosa</i> 
        <h2>Gracias por elegirnos</h2>`

    })
    .catch(err => {
        console.log(err);
        renderCarrito();
    })
}

async function agregarProductoAlPedido(producto, pedidoId) {
    let pedidoYProducto = {
        pedidoId: pedidoId,
        productoId: producto.id
    }

    const url = `http://localhost:8080/api/productanddelivery`;
    const response = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(pedidoYProducto),
        headers: {"Content-type": "application/json"}
    })
    .then(response => response.json())
    .then(pedidoYProducto => console.log(pedidoYProducto))
    .catch(err => console.log(err))
}

function renderCarrito () {
    
    let lista = document.getElementById("item-list")
    let productos = JSON.parse(localStorage.getItem("myproducts"))

    if(productos) {
        let totalPrice = 0
        productos.forEach(producto => {
            totalPrice += producto.precio;
            let item = document.createElement("li");
            let description = document.createElement("p")
            description.innerText = producto.nombre
            let price = document.createElement("span")
            price.classList.add("fw-bold")
            price.innerText = "$" + producto.precio
            item.appendChild(description)
            item.appendChild(price)
            item.classList.add("list-group-item")
            item.classList.add("d-flex")
            item.classList.add("justify-content-between")
            lista.appendChild(item)
        });
        let contenedorItems = document.getElementById("lista-carrito")
        let total = document.createElement("div")
        total.classList.add("fw-bold")
        total.classList.add("pb-3")
        total.classList.add("pt-3")
        total.innerHTML = `<span>Total: $${totalPrice}</span>`
        contenedorItems.appendChild(total)
        let buyButton = document.createElement("button")
        buyButton.classList.add("btn")
        buyButton.classList.add("btn-success")  
        buyButton.classList.add("w-50")
        buyButton.classList.add("me-1")
        buyButton.classList.add("btn-lg")
        buyButton.innerText = "Pagar"
        buyButton.addEventListener("click", () => crearPedido())

        let removeButton = document.createElement("button")
        removeButton.classList.add("btn")
        removeButton.classList.add("btn-danger")  
        removeButton.classList.add("w-50")
        removeButton.classList.add("ms-1")
        removeButton.classList.add("btn-lg")
        removeButton.addEventListener("click", () => limpiarCarrito())
        removeButton.innerText = "Limpiar carrito"
        
        let buttons = document.createElement("div")
        buttons.classList.add("d-flex")
        buttons.classList.add("w-75")
        buttons.appendChild(buyButton)
        buttons.appendChild(removeButton)
        contenedorItems.appendChild(buttons)


    } else {
        let contenido = document.getElementById("lista-carrito")

        contenido.innerHTML = "<h2>No hay productos</h2>"
    }

}

function actualizarContadorCarrito () {
    let cart = document.getElementById("cantidad-carrito")
    let productos = JSON.parse(localStorage.getItem("myproducts"))
    if (productos) {
        cart.innerText = productos.length;
    } else {
        cart.innerText = "0";
    }
}

function limpiarCarrito() {
    let lista = document.getElementById("lista-carrito")
    lista.innerHTML = "<h2>No hay productos</h2>"
    localStorage.removeItem("myproducts")
    actualizarContadorCarrito()
}