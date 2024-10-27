let homeSection = $('#homeSection');
let bookingsSection = $('#bookingsSection');
let vehiclesSection = $('#vehiclesSection');
let driversSection = $('#driversSection');
let customerSection = $('#customerSection');
let nav = $('#nav');
let navCloseBtn = $('#navCloseBtn');
let navHome = $('#navHome');
let navBookings = $('#navBookings');
let navVehicles = $('#navVehicles');
let navDrivers = $('#navDrivers');
let navCustomers = $('#navCustomers');
let currentPageH2 = $('#currentPageH2');

let adminLoginId = $('#adminLoginId');
let adminLoginPassword = $('#adminLoginPassword');
let showPasswordAdminLogin = $('#showPasswordAdminLogin');

/*customer Section*/
let searchCusTable = $('#searchCusTable');
let customerTable = $('#customerTable');
let removeCustomer = $('#removeCustomer');
/*customer Section*/

/*driver Section*/
let driverTable = $('#driverTable');
let searchDriverTable = $('#searchDriverTable');
let addDriverBtn = $('#addDriver');
let updateDriverBtn = $('#updateDriver');
let removeDriverBtn = $('#removeDriver');
let clearDriverFieldsBtn = $('#clearDriverFields');
let driverSecDriverId = $('#driverId');
let driverSecDriverEmail = $('#driverEmail');
let driverSecDriverName = $('#driverName');
let driverSecDriverAddress = $('#driverAddress');
let driverSecDriverTel = $('#driverTel');
let driverSecDriverPass = $('#driverPass');
let driverSecDriverLicense = $('#driverLicenseNo');
let driverFields = [driverSecDriverId, driverSecDriverEmail, driverSecDriverName, driverSecDriverAddress, driverSecDriverTel
    , driverSecDriverPass, driverSecDriverLicense]
/*driver Section*/

const baseUrl = "http://localhost:8081/Back_End_war/";

$(function () {
    let nawHeight = nav.outerHeight();
    homeSection.css('top', nawHeight + 'px');
    bookingsSection.css('top', nawHeight + 'px');
    vehiclesSection.css('top', nawHeight + 'px');
    driversSection.css('top', nawHeight + 'px');
    customerSection.css('top', nawHeight + 'px');
    currentPageH2.html('Home');
    updateDriverBtn.prop('disabled', true);
    removeDriverBtn.prop('disabled', true);

});

showPasswordAdminLogin.on('change', function () {
    $(this).prop("checked") ? adminLoginPassword.prop("type", "text") : adminLoginPassword.prop("type", "password");
});

$('#adminLoginBtn').on('click',function loginAdmin() {
    if (!checkFieldsEmpty([adminLoginId,adminLoginPassword])) {
        let userId = adminLoginId.val();
        let password = adminLoginPassword.val();
        let loginDetails={
            userId:userId,
            password:password}
        $.ajax({
            url: baseUrl + "login/admin",
            method: "POST",
            data: JSON.stringify(loginDetails),
            contentType: "application/json",
            success: function (res) {
                if (res.statusCode === 200) {
                    $('#adminLogin').removeClass('d-block');
                    $('#adminLogin').addClass('d-none');
                    $('#adminBodySection').removeClass('d-none');
                    $('#adminBodySection').addClass('d-block');
                    clearFields([adminLoginPassword,adminLoginId]);
                }
            },
            error: function (ob) {
                let message = ob.responseJSON.message;
                alert(message);
                if (message === 'Wrong Password') {
                    clearFields([adminLoginPassword])
                } else {
                    clearFields([adminLoginPassword,adminLoginId])
                }
            }
        });
    } else {
        alert('Please input UserId & Password');
    }
});

$('#adminLogoutBtn').on('click',function () {
    $('#adminLogin').removeClass('d-none');
    $('#adminLogin').addClass('d-block');
    $('#adminBodySection').removeClass('d-block');
    $('#adminBodySection').addClass('d-none');
});

/*Driver Page js Start*/
{
searchDriverTable.on("keyup", function () {
    let value = $(this).val().toLowerCase();
    $('#driverTable tr').filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
});

clearDriverFieldsBtn.on('click', function () {
    driverSecDriverId.prop('disabled', false);
    addDriverBtn.prop('disabled', false);
    updateDriverBtn.prop('disabled', true);
    removeDriverBtn.prop('disabled', true);
    clearFields(driverFields);
});

addDriverBtn.on('click', function () {
    let empty = checkFieldsEmpty(driverFields);
    if (!(empty)) {
            let driver = {
                driverNic:driverSecDriverId.val(),
                driverName:driverSecDriverName.val(),
                driverAddress: driverSecDriverAddress.val(),
                driverTel:driverSecDriverTel.val(),
                driverEmail: driverSecDriverEmail.val(),
                driverLicenseNo:  driverSecDriverLicense.val(),
                driverPassword: driverSecDriverPass.val(),
                driverAvailability:'AVAILABLE'
            }
            $.ajax({
                url: baseUrl + "driver",
                method: "POST",
                data: JSON.stringify(driver),
                contentType: "application/json",
                success: function (res) {
                    if (res.statusCode === 200) {
                        alert(res.message);
                        clearFields(driverFields);
                        loadDrivers();
                    }
                },
                error: function (ob) {
                    alert(ob.responseJSON.message);
                }
            });
    } else {
        alert('Please Fill All Fields');
    }
});

updateDriverBtn.on('click', function () {
    let empty = checkFieldsEmpty(driverFields);
    if (!(empty)) {
        let driver = {
            driverNic:driverSecDriverId.val(),
            driverName:driverSecDriverName.val(),
            driverAddress: driverSecDriverAddress.val(),
            driverTel:driverSecDriverTel.val(),
            driverEmail: driverSecDriverEmail.val(),
            driverLicenseNo:  driverSecDriverLicense.val(),
            driverPassword: driverSecDriverPass.val(),
            driverAvailability:'AVAILABLE'
        }
        $.ajax({
            url: baseUrl + "driver",
            method: "PUT",
            data: JSON.stringify(driver),
            contentType: "application/json",
            success: function (res) {
                if (res.statusCode === 200) {
                    alert(res.message);
                    clearFields(driverFields);
                    loadDrivers();
                    addDriverBtn.prop('disabled', false);
                    updateDriverBtn.prop('disabled', true);
                    removeDriverBtn.prop('disabled', true);
                    driverSecDriverId.prop('disabled', false);
                }
            },
            error: function (ob) {
                alert(ob.responseJSON.message);
            }
        });
    } else {
        alert("Please fill all fields to update");
    }
});

removeDriverBtn.on('click', function () {
    let id =driverSecDriverId.val();
    $.ajax({
        url: baseUrl + "driver?driverNic=" + id,
        method: "DELETE",
        success: function (res) {
            if (res.statusCode === 200) {
                alert(res.message);
                clearFields(driverFields);
                addDriverBtn.prop('disabled', false);
                updateDriverBtn.prop('disabled', true);
                removeDriverBtn.prop('disabled', true);
                driverSecDriverId.prop('disabled', false);
                loadDrivers();
            }
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
        }
    });

});

function loadDrivers() {
    driverTable.empty();
    $.ajax({
        url: baseUrl + "driver",
        method: "GET",
        success: function (res) {
            if (res.statusCode === 200) {
                for (const driver of res.data) {
                    let button = `<button  type="button" class="btn btn-primary btn-sm"  onclick="viewDriverDetails(this);">View Details</button>`;
                    let row = `<tr><td>${driver.driverNic}</td><td>${driver.driverName}<br>(${driver.driverAvailability})</td><td>${button}</td></tr>`;
                    driverTable.append(row);
                }
            }
        },
        error: function (ob) {
            alert("Failed to load table data");
        }
    });
}

function viewDriverDetails(btn) {
    let driverID = $(btn).closest("tr").find('td').eq(0).text();
    $.ajax({
        url: baseUrl + "driver/" + driverID,
        method: "GET",
        success: function (res) {
            if (res.statusCode === 200) {
                driverSecDriverId.val(res.data.driverNic).prop('disabled', true);
                driverSecDriverEmail.val(res.data.driverEmail);
                driverSecDriverName.val(res.data.driverName);
                driverSecDriverAddress.val(res.data.driverAddress);
                driverSecDriverTel.val(res.data.driverTel);
                driverSecDriverPass.val(res.data.driverPassword);
                driverSecDriverLicense.val(res.data.driverLicenseNo);
                addDriverBtn.prop('disabled', true);
                updateDriverBtn.prop('disabled', false);
                removeDriverBtn.prop('disabled', false);
            }
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
        }
    });
}
}

/*Driver Page js End*/


/*Customer Page js Start*/
{
    searchCusTable.on("keyup", function () {
        let value = $(this).val().toLowerCase();
        $('#customerTable tr').filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    removeCustomer.on('click', function () {
        let cusId = $('#customerId').val();
        $.ajax({
            url: baseUrl + "customer?customerNic=" + cusId,
            method: "DELETE",
            success: function (res) {
                if (res.statusCode === 200) {
                    $('#customerId').val('');
                    $('#customerName').val('');
                    $('#customerEmail').val('');
                    $('#customerAddress').val('');
                    $('#customerTel').val('');
                    $('#customerPass').val('');
                    alert(res.message);
                    loadCustomers()
                }
            },
            error: function (ob) {
                alert(ob.responseJSON.message);
            }
        });
    });

    function loadCustomers() {
        customerTable.empty();
        $.ajax({
            url: baseUrl + "customer",
            method: "GET",
            success: function (res) {
                if (res.statusCode === 200) {
                    for (const customer of res.data) {
                        let button = `<button  type="button" class="btn btn-primary btn-sm"  onclick="viewCustomerDetails(this);">View Details</button>`;
                        let row = `<tr><td>${customer.customerNic}</td><td>${customer.customerName}</td><td>${button}</td></tr>`;
                        customerTable.append(row);
                    }
                }
            },
            error: function (ob) {
                alert("Failed to load table data");
            }
        });
    }

    function viewCustomerDetails(btn) {
        let cusId = $(btn).closest("tr").find('td').eq(0).text();
        $.ajax({
            url: baseUrl + "customer/" + cusId,
            method: "GET",
            success: function (res) {
                if (res.statusCode === 200) {
                    $('#customerId').val(res.data.customerNic);
                    $('#customerName').val(res.data.customerName);
                    $('#customerEmail').val(res.data.customerEmail);
                    $('#customerAddress').val(res.data.customerAddress);
                    $('#customerTel').val(res.data.customerTel);
                    $('#customerPass').val(res.data.customerPassword);
                }
            },
            error: function (ob) {
                alert(ob.responseJSON.message);
            }
        });
    }
}

/*Customer Page js End*/

/*common functions*/
function checkFieldsEmpty(fields) {
    for (let field of fields) {
        if (field.val() === '') {
            return true;
        }
    }
    return false;
}

function clearFields(fields) {
    for (const field of fields) {
        field.val('');
    }
}
/*common functions*/

/*Navigation through Sections */
{
    navHome.on('click', function () {
        currentPageH2.html("Home");
        homeSection.addClass('d-block').removeClass('d-none');
        bookingsSection.addClass('d-none').removeClass('d-block');
        vehiclesSection.addClass('d-none').removeClass('d-block');
        driversSection.addClass('d-none').removeClass('d-block');
        customerSection.addClass('d-none').removeClass('d-block');
        navCloseBtn.trigger('click');
    });

    navBookings.on('click', function () {
        currentPageH2.html("Bookings");
        homeSection.addClass('d-none').removeClass('d-block');
        bookingsSection.addClass('d-block').removeClass('d-none');
        vehiclesSection.addClass('d-none').removeClass('d-block');
        driversSection.addClass('d-none').removeClass('d-block');
        customerSection.addClass('d-none').removeClass('d-block');
        navCloseBtn.trigger('click');
    });

    navVehicles.on('click', function () {
        currentPageH2.html("Vehicles");
        homeSection.addClass('d-none').removeClass('d-block');
        bookingsSection.addClass('d-none').removeClass('d-block');
        vehiclesSection.addClass('d-block').removeClass('d-none');
        driversSection.addClass('d-none').removeClass('d-block');
        customerSection.addClass('d-none').removeClass('d-block');
        navCloseBtn.trigger('click');
    });

    navDrivers.on('click', function () {
        loadDrivers();
        currentPageH2.html("Driver");
        homeSection.addClass('d-none').removeClass('d-block');
        bookingsSection.addClass('d-none').removeClass('d-block');
        vehiclesSection.addClass('d-none').removeClass('d-block');
        driversSection.addClass('d-block').removeClass('d-none');
        customerSection.addClass('d-none').removeClass('d-block');
        navCloseBtn.trigger('click');
    });

    navCustomers.on('click', function () {
        loadCustomers();
        currentPageH2.html("Customer");
        homeSection.addClass('d-none').removeClass('d-block');
        bookingsSection.addClass('d-none').removeClass('d-block');
        vehiclesSection.addClass('d-none').removeClass('d-block');
        driversSection.addClass('d-none').removeClass('d-block');
        customerSection.addClass('d-block').removeClass('d-none');
        navCloseBtn.trigger('click');
    });
}
/*Navigation through Sections End */
