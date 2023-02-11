document.getElementById("submitBtn").addEventListener("click", function () {
    event.preventDefault();
    if (!$("input[name='username']").val() || !$("input[name='pwd']").val()) {
        $("#login-result").html("Uzupełnij wszystkie pola").fadeIn().delay(3000).fadeOut();
        return;
    } //TODO: hashowanie po froncie nie działa potem w backu, nie są takie same hasła tworzone przez bcrypt
        // var password = $("input[name='pwd']").val();
        // var bcrypt = dcodeIO.bcrypt;
        // var salt = bcrypt.genSaltSync(10);
        // var hash = bcrypt.hashSync(password, salt);
        var formData = {
            username: $("input[name='username']").val(),
            // password: hash
            password: $("input[name='pwd']").val()
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/registration/authenticate",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function () {
                $("#login-result").html("Zalogowano").fadeIn().delay(3000).fadeOut();
            },
            error: function () {
                $("#login-result").html("Wystąpił błąd podczas logowania").fadeIn().delay(3000).fadeOut();
            }
        });
});