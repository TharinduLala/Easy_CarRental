const baseUrl = "http://localhost:8081/Back_End_war/";

let homeSection = $('#home');
let aboutUsSection = $('#aboutUs');
let loginForm = $('#customerLoginSection');
let headerSection = $('#header');
let registerNewSection = $('#registerNew');


$(function () {
    let navBar = $('#navBar');
    let nawHeight=navBar.outerHeight();
    $('#main').css('top', nawHeight+'px');
});

$('#customerLoginBtn').on('click', function (e) {
    e.preventDefault();
    loginCustomer();
});

$('#registerNewLink').on('click', function () {
    homeSection.removeClass("d-block");
    aboutUsSection.removeClass("d-block");
    loginForm.removeClass("d-block");
    headerSection.removeClass("d-block");
    registerNewSection.removeClass("d-none");
    homeSection.addClass("d-none");
    aboutUsSection.addClass("d-none");
    loginForm.addClass("d-none");
    headerSection.addClass("d-none");
    registerNewSection.addClass("d-block");

});
$('#newRegToHome').on('click',function () {
    homeSection.removeClass("d-none");
    aboutUsSection.removeClass("d-none");
    loginForm.removeClass("d-none");
    headerSection.removeClass("d-none");
    registerNewSection.removeClass("d-block");
    homeSection.addClass("d-block");
    aboutUsSection.addClass("d-block");
    loginForm.addClass("d-block");
    headerSection.addClass("d-block");
    registerNewSection.addClass("d-none");
})

function loginCustomer() {
    let userIdInputField = $("#userId");
    let passwordInputField = $("#password");
    if (!(userIdInputField.val() === '' || passwordInputField.val() === '')) {
        let userId = userIdInputField.val();
        let data = $("#customerLoginSection").serialize();
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



