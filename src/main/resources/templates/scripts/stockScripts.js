var token = localStorage.getItem("token");
function getStock(event) {
    event.preventDefault();
    var searchString = document.getElementById("id").value;
    $.ajax({
        url: '/stock/getbystring',
        type: 'GET',
        data: { searchString: searchString },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function(response) {
            response.sort((a, b) => (a.id > b.id) ? 1 : -1);
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var stock = response[i];
                    table += "<tr><td>" + stock.id + "</td>"
                    table += "<td>" + stock.productName + "</td>"
                    table += "<td>" + stock.amount + "</td>"
                    table += "<td><input type='number' class='form-control' id='new-amount' pattern='\d{0,5}' size='3'/></td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                        "style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteStock(event, " + stock.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("stocks").innerHTML = table;
            } else {
                $("#stock-result").html("Nie znaleziono stanu magazynowego").fadeIn().delay(3000).fadeOut()
            }
        },
        error: function() {
            $("#stock-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function showAllStocks(event) {
    event.preventDefault();
    $.ajax({
        url: '/stock/getall',
        type: 'GET',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function(response) {
            response.sort((a, b) => (a.id > b.id) ? 1 : -1);
            var table = "<table border='1'>"
                for (var i = 0; i < response.length; i++) {
                    var stock = response[i];
                    table += "<tr><td>" + stock.id + "</td>"
                    table += "<td>" + stock.productName + "</td>"
                    table += "<td>" + stock.amount + "</td>"
                    table += "<td><input type='number' class='form-control' id='new-amount' pattern='\d{0,5}' size='3'/></td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                        "style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteStock(event, " + stock.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("stocks").innerHTML = table;
        },
        error: function() {
            $("#stock-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function deleteStock(event, id){
    event.preventDefault();
    if (confirm("Czy na pewno chcesz usunąć adres?")) {
        $.ajax({
            url: '/stock/deletebyid',
            type: 'DELETE',
            data: { id: id },
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function() {
                showAllStocks(event);
                $("#stock-result").html("Usunięto stan magazynowy").fadeIn().delay(3000).fadeOut();
            },
            error: function(jqXHR) {
                $("#stock-result").html(jqXHR.responseJSON.message).fadeIn().delay(3000).fadeOut();
            }
        });
    }
}
function addStock(event) {
    event.preventDefault();
    var stock = {
        productId: document.getElementById("productName").value,
        amount: document.getElementById("new-amount").value,
    };
    $.ajax({
        url: '/stock/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(stock),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function() {
            document.getElementById("productName").value = "";
            document.getElementById("amount").value = "";
            showAllStocks(event);
            $("#stock-result").html("Dodano stan magazynowy").fadeIn().delay(3000).fadeOut();
        },
        error: function() {
            $("#stock-result").html("Błąd podczas dodawania stanu magazynowego").fadeIn().delay(3000).fadeOut();
        }
    });
}
function handleFormSubmit(event) {
    event.preventDefault();
    var idInput = document.getElementById("id");
    if (idInput.value.trim() === "") {
        showAllStocks(event);
    } else {
        getStock(event);
    }
}

function updateStocks() {
    var updates = [];
    $(".form-control").each(function() {
        if ($(this).val() != '') {
            var id = $(this).closest("tr").find("td:first").text();
            var newAmount = $(this).val();
            updates.push({id: id, amount: newAmount});
        }
    });

    if (updates.length > 0) {
        $.ajax({
            url: '/stock/updatelist',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updates),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function(response) {
                showAllStocks(event);
                $("#stock-result").html("Zaktualizowano stany magazynowe").fadeIn().delay(3000).fadeOut();
            },
            error: function() {
                $("#stock-result").html("Błąd podczas aktualizowania stanu magazynowego")
                    .fadeIn().delay(3000).fadeOut();
            }
        });
    }
}
