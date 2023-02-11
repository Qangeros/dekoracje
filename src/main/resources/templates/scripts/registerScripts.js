document.getElementById("submitBtn").addEventListener("click", function () {
    event.preventDefault();
    if (!$("input[name='name']:checked").val() || !$("input[name='username']").val()
        || !$("input[name='email']").val()) {
        $("#registration-result").html("Uzupełnij wszystkie pola").fadeIn().delay(3000).fadeOut();
        return;
    }
    if ($("input[name='password']").val() === $("input[name='password1']").val() && $("input[name='password']") != "") {
        //TODO: hashowanie po froncie nie działa potem w backu, nie są takie same hasła tworzone przez bcrypt

        // var password = $("input[name='password']").val();
        // var bcrypt = dcodeIO.bcrypt;
        // var salt = bcrypt.genSaltSync(10);
        // var hash = bcrypt.hashSync(password, salt);
            var formData = {
                username: $("input[name='username']").val(),
                // password: hash,
                password: $("input[name='password']").val(),
                email: $("input[name='email']").val(),
                role: $("input[name='name']:checked").val()
            };
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/registration/add",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function () {
                    $("#registration-result").html("Konto zostało utworzone").fadeIn().delay(3000).fadeOut();
                },
                error: function () {
                    $("#registration-result").html("Wystąpił błąd podczas rejestracji").fadeIn().delay(3000).fadeOut();
                }
            });
    } else {
        $("#registration-result").html("Hasła nie zgadzają się!").fadeIn().delay(3000).fadeOut();
    }
});