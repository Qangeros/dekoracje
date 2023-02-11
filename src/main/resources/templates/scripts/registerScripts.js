document.getElementById("submitBtn").addEventListener("click", function () {
    event.preventDefault();
    if ($("input[name='password']").val() === $("input[name='password1']").val()) {
        var password = $("input[name='password']").val();
        var bcrypt = dcodeIO.bcrypt;
        var salt = bcrypt.genSaltSync(10);
        var hash = bcrypt.hashSync(password, salt);
            var formData = {
                username: $("input[name='username']").val(),
                password: hash,
                email: $("input[name='email']").val(),
                userType: $("input[name='userType']:checked").val()
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
    }
});