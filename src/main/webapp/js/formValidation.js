$(document)
		.ready(
				function() {
					var inpObj = document.getElementById("usersForm");
					var firstName = document.getElementById("firstName")
					var lastName = document.getElementById("lastName")
					var email = document.getElementById("email")
					var addressCountry = document
							.getElementById("addressCountry")
					var addressCep = document.getElementById("addressCep")
					var addressCity = document.getElementById("addressCity")
					var addressState = document.getElementById("addressState")
					var username = document.getElementById("username")
					var password = document.getElementById("password")
					var confirmPassword = document
							.getElementById("confirmPassword")
					console.log(password.value);
					console.log(confirmPassword.value);
					if (usersForm.firstName.value === ""
							|| usersForm.firstName.value === undefined) {
						document.getElementById("firstNameError").innerHTML = "erro";
						return false;
					}
					if (!firstName.value.match(/[A-Za-zÀ-ú]+/gi)) {
						document.getElementById("firstNameError").innerHTML = "erro";
						return false;
					}
					if (password.value !== confirmPassword.value) {
						document.getElementById("passwordError").innerHTML = inpObj.validationMessage;
						return false;
					}
				});