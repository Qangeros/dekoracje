function getDocument(event) {
    event.preventDefault();
    var searchString = document.getElementById("id").value;
    $.ajax({
        url: '/document/getbystring',
        type: 'GET',
        data: { searchString: searchString },
        success: function(response) {
            var table = "<table border='1'>"
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var document = response[i];
                    table += "<tr><td>" + document.name + "</td>"
                    table += "<td>" + document.date + "</td>"
                    table += "<td>" + document.price + "</td>"
                    table += "<td>" + document.isPurchase + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash'" +
                        " style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteDocument(event, " + document.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("documents").innerHTML = table;
            } else {
                $("#address-result").html("Nie znaleziono dokumentu").fadeIn().delay(3000).fadeOut()
            }

        },
        error: function() {
            $("#document-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function showAllDocuments(event) {
    event.preventDefault();
    $.ajax({
        url: '/document/getall',
        type: 'GET',
        success: function(response) {
            var table = "<table border='1'>"
                for (var i = 0; i < response.length; i++) {
                    var document = response[i];
                    table += "<tr><td>" + document.name + "</td>"
                    table += "<td>" + document.date + "</td>"
                    table += "<td>" + document.price + "</td>"
                    table += "<td>" + document.isPurchase + "</td>"
                    table += "<td style='text-align:center;'><i class='fa fa-trash' " +
                        "style='font-size:24px; cursor:pointer;' " +
                        "onclick='deleteDocument(event, " + document.id + ")'></i></td>"
                    table += "</tr>"
                }
                table += "</table>"
                document.getElementById("documents").innerHTML = table;
        },
        error: function() {
            $("#documents-result").html("Wystąpił błąd").fadeIn().delay(3000).fadeOut();
        }
    });
}
function deleteDocument(event, id){
    event.preventDefault();
    if (confirm("Czy na pewno chcesz usunąć dokument?")) {
        $.ajax({
            url: '/document/deletebyid',
            type: 'DELETE',
            data: { id: id },
            success: function() {
                showAllDocuments(event);
                $("#document-result").html("Usunięto dokument").fadeIn().delay(3000).fadeOut();
            },
            error: function(jqXHR) {
                $("#document-result").html(jqXHR.responseJSON.message).fadeIn().delay(3000).fadeOut();
            }
        });
    }
}
function addDocument(event) {
    event.preventDefault();
    var doc = {
        name: document.getElementById("name").value,
        date: document.getElementById("date").value,
        price: document.getElementById("price").value,
        isPurchase: document.getElementById("isPurchase").checked
    };
    $.ajax({
        url: '/document/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(doc),
        success: function() {
            document.getElementById("name").value = "";
            document.getElementById("date").value = "";
            document.getElementById("price").value = "";
            document.getElementById("isPurchase").checked = false;
            showAllDocuments(event);
            $("#document-result").html("Dodano dokument").fadeIn().delay(3000).fadeOut();
        },
        error: function() {
            $("#document-result").html("Błąd podczas dodawania dokumentu").fadeIn().delay(3000).fadeOut();
        }
    });
}
function handleFormSubmit(event) {
    event.preventDefault();
    var idInput = document.getElementById("id");
    if (idInput.value.trim() === "") {
        showAllDocuments(event);
    } else {
        getDocument(event);
    }
}
function showForm(event){
    event.preventDefault();
    document.getElementById("form-container").style.display =
        (document.getElementById("form-container").style.display === "none") ? "grid" : "none";
}

//TODO: generowanie automatyczne przy dodawaniu produktów do stocku