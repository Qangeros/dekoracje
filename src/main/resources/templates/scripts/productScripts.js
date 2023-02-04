function getProduct(event) {
    event.preventDefault();
    var searchString = document.getElementById("id").value;
    $.ajax({
        url: '/product/getbystring',
        type: 'GET',
        data: { searchString: searchString },
        success: function(response) {
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var product = response[i];
                    table += "<tr><td>" + product.name + "</td>"
                    table += "<td>" + product.type + "</td>"
                    table += "<td>" + product.price.toFixed(2) + " zł </td>"
                    table += "<td>" + product.supplierName + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash'" +
                        " style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteProduct(event, " + product.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("products").innerHTML = table;
            } else {
                $("#address-result").html("Nie znaleziono produktu").fadeIn().delay(3000).fadeOut()
            }
        },
        error: function() {
            $("#product-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function showAllProducts(event) {
    event.preventDefault();
    $.ajax({
        url: '/product/getall',
        type: 'GET',
        success: function(response) {
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var product = response[i];
                    table += "<tr><td>" + product.name + "</td>"
                    table += "<td>" + product.type + "</td>"
                    table += "<td>" + product.price.toFixed(2) + " zł </td>"
                    table += "<td>" + product.supplierName + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                        "style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteProduct(event, " + product.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("products").innerHTML = table;
            }
        },
        error: function() {
            $("#products-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function deleteProduct(event, id){
    event.preventDefault();
    if (confirm("Czy na pewno chcesz usunąć produkt?")) {
        $.ajax({
            url: '/product/deletebyid',
            type: 'DELETE',
            data: { id: id },
            success: function() {
                showAllProducts(event);
                $("#product-result").html("Usunięto produkt").fadeIn().delay(3000).fadeOut();
            },
            error: function(jqXHR) {
                $("#product-result").html(jqXHR.responseJSON.message).fadeIn().delay(3000).fadeOut();
            }
        });
    }
}
function addProduct(event) {
    event.preventDefault();
    var product = {
        name: document.getElementById("name").value,
        price: document.getElementById("price").value,
        supplierId: document.getElementById("supplierId").value,
    };
    $.ajax({
        url: '/product/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(product),
        success: function() {
            document.getElementById("name").value = "";
            document.getElementById("price").value = "";
            document.getElementById("supplierId").value = "";
            showAllProducts(event);
            $("#product-result").html("Dodano produkt").fadeIn().delay(3000).fadeOut();
        },
        error: function() {
            $("#product-result").html("Błąd podczas dodawania produktu").fadeIn().delay(3000).fadeOut();
        }
    });
}
function handleFormSubmit(event) {
    event.preventDefault();
    var idInput = document.getElementById("id");
    if (idInput.value.trim() === "") {
        showAllProducts(event);
    } else {
        getProduct(event);
    }
}
function showForm(event){
    event.preventDefault();
    document.getElementById("form-container").style.display =
        (document.getElementById("form-container").style.display === "none") ? "grid" : "none";
}
//TODO: supplierId jako lista, i żeby wyświetlało nazwę zamiast id, typ