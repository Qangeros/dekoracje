var token = localStorage.getItem("token");
function getSupplier(event) {
    event.preventDefault();
    var searchString = document.getElementById("id").value;
    $.ajax({
        url: '/supplier/getbystring',
        type: 'GET',
        data: { searchString: searchString },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function(response) {
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var supplier = response[i];
                    table += "<tr><td>" + supplier.name + "</td>"
                    table += "<td>" + supplier.address + "</td>"
                    table += "<td>" + supplier.email + "</td>"
                    table += "<td>" + supplier.phone + "</td>"
                    table += "<td>" + supplier.nip + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash'" +
                        " style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteSupplier(event, " + supplier.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("suppliers").innerHTML = table;
            } else {
                $("#address-result").html("Nie znaleziono dostawcy").fadeIn().delay(3000).fadeOut()
            }
        },
        error: function() {
            $("#supplier-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function showAllSuppliers(event) {
    event.preventDefault();
    $.ajax({
        url: '/supplier/getall',
        type: 'GET',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function(response) {
            var table = "<table border='1'>"
                for (var i = 0; i < response.length; i++) {
                    var supplier = response[i];
                    table += "<tr><td>" + supplier.name + "</td>"
                    table += "<td>" + supplier.address + "</td>"
                    table += "<td>" + supplier.email + "</td>"
                    table += "<td>" + supplier.phone + "</td>"
                    table += "<td>" + supplier.nip + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                        "style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteSupplier(event, " + supplier.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("suppliers").innerHTML = table;
        },
        error: function() {
            $("#suppliers-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function deleteSupplier(event, id){
    event.preventDefault();
    if (confirm("Czy na pewno chcesz usunąć dostawcę?")) {
        $.ajax({
            url: '/supplier/deletebyid',
            type: 'DELETE',
            data: { id: id },
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function() {
                showAllSuppliers(event);
                $("#supplier-result").html("Usunięto dostawcę").fadeIn().delay(3000).fadeOut();
            },
            error: function(jqXHR) {
                $("#supplier-result").html(jqXHR.responseJSON.message).fadeIn().delay(3000).fadeOut();
            }
        });
    }
}
function handleFormSubmit(event) {
    event.preventDefault();
    var idInput = document.getElementById("id");
    if (idInput.value.trim() === "") {
        showAllSuppliers(event);
    } else {
        getSupplier(event);
    }
}
function showForm(event){
    event.preventDefault();
    document.getElementById("form-container").style.display =
        (document.getElementById("form-container").style.display === "none") ? "grid" : "none";
}