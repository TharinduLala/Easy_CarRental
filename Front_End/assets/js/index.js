const baseUrl = "http://localhost:8081/Back_End_war/";


$(document).ready(function () {
    $('#main').css('top', $('#navBar').outerHeight() + 'px');
    setCustomerDetails();
});

$('#customerLoginBtn').click(function (e) {
    e.preventDefault();
    loginCustomer();
});

function loginCustomer() {
    let userIdInputField = $("#userId");
    let passwordInputField = $("#password");
    if (!(userIdInputField.val() === '' || passwordInputField.val() === '')) {
        let userId = userIdInputField.val();
        let data = $("#loginForm").serialize();
        $.ajax({
            url: baseUrl + "login/customer",
            method: "POST",
            data: data,
            success: function (res) {
                if (res.statusCode === 200) {
                    localStorage.setItem('userId', userId);
                    location.replace("pages/customerPage.html");
                }
            },
            error: function (ob) {
                let message = ob.responseJSON.message;
                if (message === 'Wrong Password') {
                    passwordInputField.val('');

                } else {
                    userIdInputField.val('');
                    passwordInputField.val('');
                }
                loginErrorAlert(message, 'danger')
            }
        });
    } else {
        loginErrorAlert('Input UserId & Password', 'danger')
    }
}

const alertPlaceholder = $('#liveAlertPlaceholder')
const loginErrorAlert = (message, type) => {
    const wrapper = document.createElement('div')
    wrapper.innerHTML = [
        `<div style="position: absolute" class="alert alert-${type} alert-dismissible" role="alert">`,
        `   <div>${message}</div>`,
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
    ].join('')
    alertPlaceholder.append(wrapper)
}


/////////////////////////////////////////////////////////////////////////


