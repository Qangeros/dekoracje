var token = localStorage.getItem("token");

function getAddress(event) {
    event.preventDefault();
    var searchString = document.getElementById("id").value;
    $.ajax({
        url: '/address/getbystring',
        type: 'GET',
        data: {searchString: searchString},
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function (response) {
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var address = response[i];
                    table += "<tr><td>" + address.street + "</td>"
                    table += "<td>" + address.city + "</td>"
                    table += "<td>" + address.postalCode + "</td>"
                    table += "<td>" + address.isWorkplace + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash'" +
                        " style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteAddress(event, " + address.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("addresses").innerHTML = table;
            } else {
                $("#address-result").html("Nie znaleziono adresu").fadeIn().delay(3000).fadeOut()
            }
        },
        error: function () {
            $("#address-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}

function showAllAddresses(event) {
    event.preventDefault();
    $.ajax({
        url: '/address/getall',
        type: 'GET',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function (response) {
            var table = "<table border='1'>"
            for (var i = 0; i < response.length; i++) {
                var address = response[i];
                table += "<tr><td>" + address.street + "</td>"
                table += "<td>" + address.city + "</td>"
                table += "<td>" + address.postalCode + "</td>"
                table += "<td>" + address.isWorkplace + "</td>"
                table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                    "style='font-size:24px; cursor:pointer;' " +
                    "onclick='deleteAddress(event, " + address.id + ")'></i></td>"
                table += "</tr>"
            }
            table += "</table>"
            document.getElementById("addresses").innerHTML = table;
        },
        error: function () {
            $("#address-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}

function deleteAddress(event, id) {
    event.preventDefault();
    if (confirm("Czy na pewno chcesz usunąć adres?")) {
        $.ajax({
            url: '/address/deletebyid',
            type: 'DELETE',
            data: {id: id},
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function () {
                showAllAddresses(event);
                $("#address-result").html("Usunięto adres").fadeIn().delay(3000).fadeOut();
            },
            error: function (jqXHR) {
                $("#address-result").html(jqXHR.responseJSON.message).fadeIn().delay(3000).fadeOut();
            }
        });
    }
}

function addAddress(event) {
    event.preventDefault();
    var address = {
        street: document.getElementById("street").value,
        city: document.getElementById("city").value,
        postalCode: document.getElementById("postalCode").value,
        isWorkplace: document.getElementById("isWorkplace").checked
    };
    $.ajax({
        url: '/address/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(address),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function () {
            document.getElementById("street").value = "";
            document.getElementById("city").value = "";
            document.getElementById("postalCode").value = "";
            document.getElementById("isWorkplace").checked = false;
            showAllAddresses(event);
            $("#address-result").html("Dodano adres").fadeIn().delay(3000).fadeOut();
        },
        error: function () {
            $("#address-result").html("Błąd podczas dodawania adresu").fadeIn().delay(3000).fadeOut();
        }
    });
}

function handleFormSubmit(event) {
    event.preventDefault();
    var idInput = document.getElementById("id");
    if (idInput.value.trim() === "") {
        showAllAddresses(event);
    } else {
        getAddress(event);
    }
}

function showForm(event) {
    event.preventDefault();
    document.getElementById("form-container").style.display =
        (document.getElementById("form-container").style.display === "none") ? "grid" : "none";
}