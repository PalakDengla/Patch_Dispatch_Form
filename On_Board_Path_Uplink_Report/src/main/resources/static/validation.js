$(document).ready(function () {

    function validateEmail() {
        let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        let email = $('#email').val();
        if (!emailPattern.test(email)) {
            $('#emailError').text('Please enter a valid email address.');
        } else {
            $('#emailError').text('');

            return true;
        }
    }


    function validatePassword() {
        let passwordPattern = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";
        let password = $('#password').val();
        if (!passwordPattern.test(password)) {
            $('#passwordError').text('Password must be at least 8 characters long and contain at least one number, one uppercase letter, and one lowercase letter.'); // Display error message if password is invalid
            return false;
        } else {
            $('#passwordError').text('');
            return true;
        }
    }


    $('#email').on('input', validateEmail);
    $('#password').on('input', validatePassword);
});
