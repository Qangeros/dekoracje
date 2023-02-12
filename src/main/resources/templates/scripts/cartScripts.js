var token = localStorage.getItem("token");
function showCart(event) {
    event.preventDefault();
    $.ajax({
        url: '/cart/getall',
        type: 'GET',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function (response) {
            var table = "<table border='1'>"
            var total = 0;
            for (var i = 0; i < response.length; i++) {
                var product = response[i];
                table += "<tr><td>" + product.id + "</td>"
                table += "<td>" + product.productName + "</td>"
                table += "<td>" + product.price.toFixed(2) + " zł </td>"
                table += "<td>" + product.supplierName + "</td>"
                table += "<td>" + product.amount + "</td>"
                table += "<td>" + (product.price * product.amount).toFixed(2) + " zł </td>"
                table += "<td><input type='number' class='form-control' id='amount' pattern='\d{0,5}' size='3'/></td>"
                table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                    "style='font-size:24px; cursor:pointer;' " +
                    "onclick='deleteCart(event, " + product.id + ")'></i></td>"

                table += "</tr>"
                total += product.price * product.amount;
            }

            table += "</table>"
            document.getElementById("cart").innerHTML = table;

            var totalPrice = "<p style='text-align:right;'>" + "Łączna cena: " +
                total.toFixed(2) + " zł</p>";
            document.getElementById("cart-total").innerHTML = totalPrice;
        },
        error: function () {
            $("#cart-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}

function deleteCart(event, id) {
    event.preventDefault();
    if (confirm("Czy na pewno chcesz usunąć produkt z koszyka?")) {
        $.ajax({
            url: '/cart/deletebyid',
            type: 'DELETE',
            data: {id: id},
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function () {
                showCart(event);
                $("#cart-result").html("Usunięto produkt z koszyka").fadeIn().delay(3000).fadeOut();
            },
            error: function (jqXHR) {
                $("#cart-result").html(jqXHR.responseJSON.message).fadeIn().delay(3000).fadeOut();
            }
        });
    }
}

function updateCart(event) {
    var updates = [];
    $(".form-control").each(function () {
        if ($(this).val() != '') {
            var id = $(this).closest("tr").find("td:first").text();
            var newAmount = $(this).val();
            updates.push({id: id, amount: newAmount});
        }
    });
    if (updates.length > 0) {
        $.ajax({
            url: '/cart/updatelist',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updates),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function (response) {
                showCart(event);
                $("#cart-result").html("Zaktualizowano koszyk").fadeIn().delay(3000).fadeOut();
            },
            error: function () {
                $("#cart-result").html("Błąd podczas aktualizowania koszyku")
                    .fadeIn().delay(3000).fadeOut();
            }
        });
    }
}

function finalize(event) {
    event.preventDefault();
    if (confirm("Czy na pewno chcesz zakupić produkty?")) {
        $.ajax({
            url: '/cart/updateofs',
            type: 'PUT',
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function () {
                showCart(event);
                $("#cart-result").html("Zakupiono produkty").fadeIn().delay(3000).fadeOut();
            },
            error: function () {
                $("#cart-result").html("Błąd podczas zakupienia koszyka").fadeIn().delay(3000).fadeOut();
            }
        });
    }
}
