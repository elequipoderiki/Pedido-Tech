function formularioCompleto() {
  let nameInput = document.getElementById("nombre")
  let emailInput = document.getElementById("email")
  let messageInput = document.getElementById("mensaje")
  
  let formButton = document.querySelector("#formulario-contacto  button")

  formButton.addEventListener("click", (e) => {
    let form = document.getElementById("formulario-contacto")
    e.preventDefault()
    if(nameInput.value && emailInput.value && messageInput.value) {
      console.log("formulario: nombre, mensaje y email completos")
      form.submit()
    } else {
      console.log("formulario incompleto")
    }
  })
}

function mostrarProductosEnConsola() {
  let productos = ["monitor 14", "mouse logitech", "auriculares senheiser", "teclado logitech", "headset sony"]
  console.log("==========================================================")
  console.log("Productos disponibles:")
  productos.forEach(element => {
    console.log(element)
  });
  console.log("==========================================================")

}

async function getAllData() {
    let productos = []
    const url = `http://localhost:8080/api/product`;
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error(`Response status: ${response.status}`);
      }
  
      productos = await response.json();
      
    } catch (error) {
      console.error(error.message);
    }
    return productos;
  }

  
  async function printItems() {

    let itemTemplate = document.querySelector(".flex-container-row")

    itemTemplate.innerHTML = '<i class="bi bi-hourglass-top h1">Cargando</i>'

    let productos = await getAllData()

    if(productos)
      itemTemplate.innerText = ""

    for (let i=0; i< productos.length; i++) {
        
        let cardDiv = document.createElement("div")
        cardDiv.classList.add("flex-item")
        itemTemplate.appendChild(cardDiv)

        let cardAnchor = document.createElement("a")
        cardAnchor.href = "pages/product-template.html"
        cardAnchor.search = productos[i].id

        cardDiv.appendChild(cardAnchor)

        let cardImg = document.createElement("img")
        cardImg.src = productos[i].image
        cardAnchor.appendChild(cardImg)
    
        let cardTitle = document.createElement("div")
        cardTitle.innerHTML = productos[i].title + "<br><br>" + "<b>$"+productos[i].price + "</b><br>" + "Stock: " + productos[i].stock
        cardTitle.classList.add("card-title")
        cardAnchor.appendChild(cardTitle)

        let divButton = document.createElement("div")
        divButton.classList.add("button-section")
        let cardButton = document.createElement("button")
        cardButton.innerText = "Comprar"
        cardButton.classList.add("buy-button")
        cardButton.addEventListener("click", function(e) {
            comprar(productos[i].title, productos[i].price, productos[i].id, e.target.parentNode)
          })
        divButton.appendChild(cardButton)
        cardDiv.appendChild(divButton)

    }
}

printItems()
formularioCompleto()
mostrarProductosEnConsola()