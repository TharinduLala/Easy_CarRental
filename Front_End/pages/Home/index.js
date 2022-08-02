const baseUrl = "http://localhost:8081/Back_End_war/";

let $homeSection = $('#home');
let $aboutUsSection = $('#aboutUs');
let $loginForm = $('#customerLoginSection');
let $headerSection = $('#header');
let $registerNewSection = $('#registerNew');
let $liveAlertPlaceholder = $('.liveAlertPlaceholder');
let $inputNic = $('#inputNic');
let $inputEmail = $('#inputEmail');
let $inputFirstName = $('#inputFirstName');
let $inputLastName = $('#inputLastName');
let $inputAddress = $('#inputAddress');
let $inputTel = $('#inputTel');
// let $inputNicImg = $('#inputNicImg');
let $inputPassword = $('#inputPassword');
let $inputAgainPassword = $('#inputAgainPassword');
let $showPasswordRegNew = $('#showPasswordRegNew');
let $showPasswordCusLogin = $('#showPasswordCusLogin');
let $registerNewCustomerBtn = $('#registerNewCustomerBtn');
let $customerLoginBtn = $('#customerLoginBtn');


$(function () {
    let navBar = $('#navBar');
    let nawHeight = navBar.outerHeight();
    $('#main').css('top', nawHeight + 'px');
    showAndHidePassword($showPasswordRegNew, [$inputPassword, $inputAgainPassword]);
    showAndHidePassword($showPasswordCusLogin, [$("#password")]);
});

$customerLoginBtn.on('click', function (e) {
    e.preventDefault();
    loginCustomer();
});

$('#registerNewLink').on('click', function () {
    $homeSection.removeClass("d-block");
    $aboutUsSection.removeClass("d-block");
    $loginForm.removeClass("d-block");
    $headerSection.removeClass("d-block");
    $registerNewSection.removeClass("d-none");
    $homeSection.addClass("d-none");
    $aboutUsSection.addClass("d-none");
    $loginForm.addClass("d-none");
    $headerSection.addClass("d-none");
    $registerNewSection.addClass("d-block");

});

$('#newRegToHome').on('click', function () {
    let fields = [$inputNic, $inputEmail, $inputFirstName, $inputLastName, $inputAddress, $inputTel, $inputPassword, $inputAgainPassword];
    clearFields(fields);
    $homeSection.removeClass("d-none");
    $aboutUsSection.removeClass("d-none");
    $loginForm.removeClass("d-none");
    $headerSection.removeClass("d-none");
    $registerNewSection.removeClass("d-block");
    $homeSection.addClass("d-block");
    $aboutUsSection.addClass("d-block");
    $loginForm.addClass("d-block");
    $headerSection.addClass("d-block");
    $registerNewSection.addClass("d-none");
})

$registerNewCustomerBtn.on('click', function (e) {
    e.preventDefault();
    addNewCustomer();
});

function addNewCustomer() {
    let fields = [$inputNic, $inputEmail, $inputFirstName, $inputLastName, $inputAddress, $inputTel, $inputPassword, $inputAgainPassword];
    let empty = checkFieldsEmpty(fields);
    if (!(empty)) {
        if ($inputPassword.val() === $inputAgainPassword.val()) {
            let customer = {
                customerNic: $inputNic.val(),
                customerName: $inputFirstName.val() + " " + $inputLastName.val(),
                customerEmail: $inputEmail.val(),
                customerAddress: $inputAddress.val(),
                customerTel: $inputTel.val(),
                customerPassword: $inputAgainPassword.val(),
                nicPhoto: null
            }
            $.ajax({
                url:baseUrl+"customer",
                method:"POST",
                data:JSON.stringify(customer),
                contentType: "application/json",
                success:function (res) {
                    if (res.statusCode === 200) {
                        clearFields(fields);
                        errorAlert(res.message, 'success', $registerNewCustomerBtn.parent().find($liveAlertPlaceholder));
                    }
                },
                error:function (ob) {
                    errorAlert(ob.responseJSON.message, 'warning', $registerNewCustomerBtn.parent().find($liveAlertPlaceholder));
                }
            });

            console.log(JSON.stringify(customer));
        } else {
            errorAlert('Passwords not matching', 'danger', $registerNewCustomerBtn.parent().find($liveAlertPlaceholder));
        }
    } else {
        errorAlert('Please Fill All Fields', 'danger', $registerNewCustomerBtn.parent().find($liveAlertPlaceholder));
    }
}

function loginCustomer() {
    let userIdInputField = $("#userId");
    let passwordInputField = $("#password");
    if (!checkFieldsEmpty([userIdInputField, passwordInputField])) {
        let userId = userIdInputField.val();
        let password = passwordInputField.val();
        // let data = $("#customerLoginSection").serialize();
        let loginDetails={userId:userId,
        password:password}
        $.ajax({
            url: baseUrl + "login/customer",
            method: "POST",
            data: JSON.stringify(loginDetails),
            contentType: "application/json",
            success: function (res) {
                if (res.statusCode === 200) {
                    localStorage.setItem('userId', `${userId}`);
                    location.replace("pages/customer/customerPage.html");
                }
            },
            error: function (ob) {
                let message = ob.responseJSON.message;
                if (message === 'Wrong Password') {
                    // passwordInputField.val('');
                    clearFields([passwordInputField])

                } else {
                    /*userIdInputField.val('');
                    passwordInputField.val('');*/
                    clearFields([userIdInputField, passwordInputField])
                }
                errorAlert(message, 'danger', $customerLoginBtn.parent().find($liveAlertPlaceholder))
            }
        });
    } else {
        errorAlert('Input UserId & Password', 'danger', $customerLoginBtn.parent().find($liveAlertPlaceholder))
    }
}

function clearFields(fields) {
    for (const field of fields) {
        field.val('');
    }
}

function checkFieldsEmpty(fields) {
    for (let field of fields) {
        if (field.val() === '') {
            return true;
        }
    }
    return false;
}

function showAndHidePassword(checkBox, fields) {
    for (const passwordField of fields) {
        checkBox.on('change', function () {
            $(this).prop("checked") ? passwordField.prop("type", "text") : passwordField.prop("type", "password");
        });
    }
}

const errorAlert = (message, type, holder) => {
    const wrapper = document.createElement('div')
    wrapper.innerHTML = [
        `<div style="position: absolute" class="alert alert-${type} alert-dismissible  text-wrap w-auto" role="alert">`,
        `   <div>${message}</div>`,
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
    ].join('')
    holder.append(wrapper)
}



