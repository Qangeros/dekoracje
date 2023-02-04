function getCustomer(event) {
    event.preventDefault();
    var searchString = document.getElementById("id").value;
    $.ajax({
        url: '/customer/getbystring',
        type: 'GET',
        data: { searchString: searchString },
        success: function(response) {
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var customer = response[i];
                    table += "<tr><td>" + customer.name + "</td>"
                    table += "<td>" + customer.surname + "</td>"
                    table += "<td>" + customer.email + "</td>"
                    table += "<td>" + customer.phone + "</td>"
                    table += "<td>" + customer.address + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash'" +
                        " style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteCustomer(event, " + customer.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("customers").innerHTML = table;
            } else {
                $("#address-result").html("Nie znaleziono klienta").fadeIn().delay(3000).fadeOut()
            }
        },
        error: function() {
            $("#customer-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}

function showAllCustomers(event) {
    event.preventDefault();
    $.ajax({
        url: '/customer/getall',
        type: 'GET',
        success: function(response) {
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var customer = response[i];
                    table += "<tr><td>" + customer.name + "</td>"
                    table += "<td>" + customer.surname + "</td>"
                    table += "<td>" + customer.email + "</td>"
                    table += "<td>" + customer.phone + "</td>"
                    table += "<td>" + customer.address + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                        "style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteCustomer(event, " + customer.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("customers").innerHTML = table;
            }
        },
        error: function() {
            $("#customers-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function deleteCustomer(event, id){
    event.preventDefault();
    if (confirm("Czy na pewno chcesz usunąć klienta?")) {
        $.ajax({
            url: '/customer/deletebyid',
            type: 'DELETE',
            data: { id: id },
            success: function() {
                showAllCustomers(event);
                $("#customer-result").html("Usunięto klienta").fadeIn().delay(3000).fadeOut();
            },
            error: function(jqXHR) {
                $("#customer-result").html(jqXHR.responseJSON.message).fadeIn().delay(3000).fadeOut();
            }
        });
    }
}
function handleFormSubmit(event) {
    event.preventDefault();
    var idInput = document.getElementById("id");
    if (idInput.value.trim() === "") {
        showAllCustomers(event);
    } else {
        getCustomer(event);
    }
}
function showForm(event){
    event.preventDefault();
    document.getElementById("form-container").style.display =
        (document.getElementById("form-container").style.display === "none") ? "grid" : "none";
}