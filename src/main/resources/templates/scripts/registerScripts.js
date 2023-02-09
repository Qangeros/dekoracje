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
                success: function (result) {
                    console.log(result);
                },
                error: function (error) {
                    console.log(error);
                }
            });
    }
});