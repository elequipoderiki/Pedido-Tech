async function renderProduct() {
    let productContent = document.getElementById("product-content")
            
    let icon = document.getElementById("loading-icon")
    icon.innerHTML = '<i class="bi bi-hourglass-top h1">Cargando</i>'
    

    const id =  window.location.search.toString().slice(1)
    const url = `http://localhost:8080/api/product/${id}`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
    
        producto = await response.json();
        if(producto ) {
          icon.innerHTML = ""
          icon.style.marginTop = 0
        }

        let imagen = document.getElementById("product-content-img")
        imagen.src = producto.image
        let productTitle = document.getElementById("product-title")
        productTitle.innerText = producto.title
        let descriptionSection = document.getElementById("description-section")
        let productDescription = document.getElementById("product-description")
        productDescription.innerText = producto.description
        let textPrice = document.getElementById("text-price")
        textPrice.innerText = "$" + producto.price 
        let buyButton = document.createElement("button")
        buyButton.innerText = "Comprar"
        buyButton.addEventListener("click", function(e) {
            comprar(producto.title, producto.price, producto.id, e.target.parentNode)
          })

        buyButton.classList.add("single-buy-button")
        
        descriptionSection.appendChild(buyButton)
    } catch (error) {
        console.error(error.message);
    }

}
renderProduct()
