const baseUrl = "http://localhost:8081/Back_End_war/";

let customerNic = localStorage.getItem('userId');
let customerPassword = '';
let customerNicImg = '';

let updateCusNicInput = $('#updateCusNicInput');
let updateCusNameInput = $('#updateCusNameInput');
let updateCusEmailInput = $('#updateCusEmailInput');
let updateCusAddressInput = $('#updateCusAddressInput');
let updateCusTelInput = $('#updateCusTelInput');

setCustomerDetails();

loadCars()

$('#customerLogoutBtn').on('click', function () {
    logout();
});

$('#editCustomerDetailsBtn').on('click', function () {
    loadCustomer();
});

$('#saveEditedDetailsBtn').on('click', function () {
    updateCustomer();
});

$('#closeEditedDetailsBtn').on('click', function () {
    customerPassword = '';
    customerNicImg = '';
});

function updateCustomer() {
    if (!(updateCusNameInput.val() === '' ||
        updateCusEmailInput.val() === '' ||
        updateCusAddressInput.val() === '' ||
        updateCusTelInput.val() === '')) {

        let customer = {
            customerNic: updateCusNicInput.val(),
            customerName: updateCusNameInput.val(),
            customerEmail: updateCusEmailInput.val(),
            customerAddress: updateCusAddressInput.val(),
            customerTel: updateCusTelInput.val(),
            customerPassword: customerPassword,
            nicPhoto: customerNicImg
        }

        $.ajax({
            url: baseUrl + "customer",
            method: "PUT",
            data: JSON.stringify(customer),
            contentType: "application/json",
            success: function (res) {
                if (res.statusCode === 200) {
                    alert(res.message);
                    setCustomerDetails();
                    customerPassword = '';
                    customerNicImg = '';
                    $('#closeEditedDetailsBtn').trigger("click");
                }
            },
            error: function (ob) {
                alert(ob.responseJSON.message);
            }
        });
    } else {
        alert("Please fill all fields to update");
    }
}

function loadCustomer() {
    $.ajax({
        url: baseUrl + "customer/" + customerNic,
        method: "GET",
        success: function (res) {
            if (res.statusCode === 200) {
                updateCusNicInput.val(res.data.customerNic);
                updateCusNameInput.val(res.data.customerName);
                updateCusEmailInput.val(res.data.customerEmail);
                updateCusAddressInput.val(res.data.customerAddress);
                updateCusTelInput.val(res.data.customerTel);
                customerPassword = res.data.customerPassword;
                customerNicImg = res.data.nicPhoto
            }
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
        }
    });
}

function logout() {
    localStorage.clear();
    location.replace("../../index.html");
}

function setCustomerDetails() {
    $.ajax({
        url: baseUrl + "customer/" + customerNic,
        method: "GET",
        success: function (res) {
            if (res.statusCode === 200) {
                $('#customerNicLi').html("NIC : " + res.data.customerNic);
                $('#customerNameLi').html("NAME : " + res.data.customerName);
                $('#customerEmailLi').html("EMAIL : " + res.data.customerEmail);
                $('#customerAddressLi').html("ADDRESS : " + res.data.customerAddress);
                $('#customerTelLi').html("TEL : " + res.data.customerTel);
            }
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
        }
    });
}

function bindClickEventsToCarContainer() {
    let carDetailsContainer = $('.carDetailsContainer');
    $('.viewCarDetailsBtn').off().on('click', function () {
        carDetailsContainer.not($(this)).css("display", "none");//close other opened window
        carDetailsContainer.children().not('.carDetailsContainerCloseBtn').remove();//remove details container elements without close button
        $(this).parent().parent().closest('div').find($('.carDetailsContainer')).css("display", "block");//show details container
        let text = $(this).parent().parent().closest('div').find('h2').text();//find and get value of hidden h2 tag which contains car regNumber
        $.ajax({
            url: baseUrl + "car/" + text,
            method: "GET",
            success: function (res) {
                if (res.statusCode === 200) {
                    let detailsList = `
                            <ul class="list-group bg-dark">
                                <li style="color:white;" class="list-group-item bg-dark ">Loss Damage Waiver<br>${res.data.lossDamagePayment}LKR</li>
                                <li style="color:white;" class="list-group-item bg-dark ">${res.data.monthlyRate} LKR/month<br>(${res.data.freeKmForMonth}Km free for month)</li>
                                <li style="color:white;" class="list-group-item bg-dark ">${res.data.dailyRate} LKR/day<br>(${res.data.freeKmForDay}Km free for day)</li>
                                <li style="color:white;" class="list-group-item bg-dark ">${res.data.priceForExtraKm} LKR/Extra Km</li>
                                <li style="color:white;" class="list-group-item bg-dark ">With Driver<br>LKR1000/day</li>
                            </ul>
                        `;
                    carDetailsContainer.append(detailsList);
                }
            },
            error: function () {
                alert("Failed to load Details");
            }
        });
    });

    $('.carDetailsContainerCloseBtn').off().on('click', function () {
        $(this).parent().css("display", "none");
    });


    $('.bookThisCar').off().on('click', function () {
        let car = $(this).parent().parent().closest('div').find('h2').text();
        placeBooking(car);
    });
}

function placeBooking(car) {
    console.log("hello" + car);
}

function loadCars() {
    $.ajax({
        url: baseUrl + "car",
        method: "GET",
        success: function (res) {
            if (res.statusCode === 200) {
                for (const car of res.data) {
                    appendCar(car.carType, car.carFrontImg, car.carBrand, car.noOfPassengers, car.fuelType, car.transmissionType, car.carColour, car.carRegNo);
                }
            }
        },
        error: function () {
            alert("Failed to load Data");
        }
    });
}

function appendCar(type, imgUrl, carBrand, noOfPassengers, fuelType, transmissionType, carColour, carRegNum) {
    const car = `<div class="car-main-container w-25  shadow-lg p-3 m-4  bg-body rounded position-relative">
    <div style="display: flex;flex-direction: column;justify-content: center;align-items: center;width: 100%;background-color: transparent;border-radius: 16px;margin-bottom: 20px;overflow: hidden;position: relative">
        <img alt="car" src=${imgUrl} style="width: 100%;border-radius: 16px;">
    </div>
    <div class="d-flex flex-row justify-content-between align-items-center position-relative" style="width: 100%;padding: 0 10px">
        <h3 class="text-wrap" style="font-size: 20px;line-height: 30px;color: #33334f;margin-bottom: 0;font-family: 'Nunito', sans-serif;font-weight: 600;width: 100%;white-space: nowrap;text-overflow: ellipsis;word-wrap: break-word;">
            ${carBrand}</h3>
            <h2 class="d-none">${carRegNum}</h2>
    </div>
    <ul class="d-flex flex-row justify-content-start align-items-center flex-wrap"
        style="width: 100%;list-style: none">
        <li class="d-flex flex-row justify-content-start align-items-center mt-4"
            style="width: 50%">
            <svg style="width: 20px;height: auto;fill: #189cf4;margin-right: 6px;"
                 viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path d="M12.3,12.22A4.92,4.92,0,0,0,14,8.5a5,5,0,0,0-10,0,4.92,4.92,0,0,0,1.7,3.72A8,8,0,0,0,1,19.5a1,1,0,0,0,2,0,6,6,0,0,1,12,0,1,1,0,0,0,2,0A8,8,0,0,0,12.3,12.22ZM9,11.5a3,3,0,1,1,3-3A3,3,0,0,1,9,11.5Zm9.74.32A5,5,0,0,0,15,3.5a1,1,0,0,0,0,2,3,3,0,0,1,3,3,3,3,0,0,1-1.5,2.59,1,1,0,0,0-.5.84,1,1,0,0,0,.45.86l.39.26.13.07a7,7,0,0,1,4,6.38,1,1,0,0,0,2,0A9,9,0,0,0,18.74,11.82Z"></path>
            </svg>
            <span>${noOfPassengers} People<br>(with driver)</span>
        </li>
        <li class="d-flex flex-row justify-content-start align-items-center mt-4"
            style="width: 50%">
            <svg style="width: 20px;height: auto;fill: #189cf4;margin-right: 6px;"
                 viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path d="M19.088,4.95453c-.00732-.00781-.00952-.01819-.01715-.02582s-.01819-.00995-.02606-.01733a9.97886,9.97886,0,0,0-14.08948,0c-.00787.00738-.01837.00964-.02606.01733s-.00983.018-.01715.02582a10,10,0,1,0,14.1759,0ZM12,20a7.9847,7.9847,0,0,1-6.235-3H9.78027a2.9636,2.9636,0,0,0,4.43946,0h4.01532A7.9847,7.9847,0,0,1,12,20Zm-1-5a1,1,0,1,1,1,1A1.001,1.001,0,0,1,11,15Zm8.41022.00208L19.3999,15H15a2.99507,2.99507,0,0,0-2-2.81573V9a1,1,0,0,0-2,0v3.18427A2.99507,2.99507,0,0,0,9,15H4.6001l-.01032.00208A7.93083,7.93083,0,0,1,4.06946,13H5a1,1,0,0,0,0-2H4.06946A7.95128,7.95128,0,0,1,5.68854,7.10211l.65472.65473A.99989.99989,0,1,0,7.75732,6.34277l-.65466-.65466A7.95231,7.95231,0,0,1,11,4.06946V5a1,1,0,0,0,2,0V4.06946a7.95231,7.95231,0,0,1,3.89734,1.61865l-.65466.65466a.99989.99989,0,1,0,1.41406,1.41407l.65472-.65473A7.95128,7.95128,0,0,1,19.93054,11H19a1,1,0,0,0,0,2h.93054A7.93083,7.93083,0,0,1,19.41022,15.00208Z"></path>
            </svg>
            <span>Color<br> ${carColour}</span>
            
        </li>
        <li class="d-flex flex-row justify-content-start align-items-center mt-4"
            style="width: 50%">
            <svg style="width: 20px;height: auto;fill: #189cf4;margin-right: 6px;"
                 viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path d="M20.54,6.29,19,4.75,17.59,3.34a1,1,0,0,0-1.42,1.42l1,1-.83,2.49a3,3,0,0,0,.73,3.07l2.95,3V19a1,1,0,0,1-2,0V17a3,3,0,0,0-3-3H14V5a3,3,0,0,0-3-3H5A3,3,0,0,0,2,5V19a3,3,0,0,0,3,3h6a3,3,0,0,0,3-3V16h1a1,1,0,0,1,1,1v2a3,3,0,0,0,6,0V9.83A5,5,0,0,0,20.54,6.29ZM12,19a1,1,0,0,1-1,1H5a1,1,0,0,1-1-1V12h8Zm0-9H4V5A1,1,0,0,1,5,4h6a1,1,0,0,1,1,1Zm8,1.42L18.46,9.88a1,1,0,0,1-.24-1l.51-1.54.39.4A3,3,0,0,1,20,9.83Z"></path>
            </svg>
            <span>${fuelType}</span>
        </li>
        <li class="d-flex flex-row justify-content-start align-items-center mt-4"
            style="width: 50%">
            <svg style="width: 20px;height: auto;fill: #189cf4;margin-right: 6px;"
                 viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path d="M12,12a1,1,0,1,0,1,1A1,1,0,0,0,12,12Zm9.71-2.36a0,0,0,0,1,0,0,10,10,0,0,0-19.4,0,0,0,0,0,1,0,0,9.75,9.75,0,0,0,0,4.72,0,0,0,0,1,0,0A10,10,0,0,0,9.61,21.7h0a9.67,9.67,0,0,0,4.7,0h0a10,10,0,0,0,7.31-7.31,0,0,0,0,1,0,0,9.75,9.75,0,0,0,0-4.72ZM12,4a8,8,0,0,1,7.41,5H4.59A8,8,0,0,1,12,4ZM4,12a8.26,8.26,0,0,1,.07-1H6v2H4.07A8.26,8.26,0,0,1,4,12Zm5,7.41A8,8,0,0,1,4.59,15H7a2,2,0,0,1,2,2Zm4,.52A8.26,8.26,0,0,1,12,20a8.26,8.26,0,0,1-1-.07V18h2ZM13.14,16H10.86A4,4,0,0,0,8,13.14V11h8v2.14A4,4,0,0,0,13.14,16ZM15,19.41V17a2,2,0,0,1,2-2h2.41A8,8,0,0,1,15,19.41ZM19.93,13H18V11h1.93A8.26,8.26,0,0,1,20,12,8.26,8.26,0,0,1,19.93,13Z"></path>
            </svg>
            <span>${transmissionType}</span>
        </li>
    </ul>
    <div class="text-success">
        <hr>
    </div>
    <div aria-label="Basic outlined example" class="btn-group " role="group">
        <button class="btn btn-outline-dark me-2 viewCarDetailsBtn" type="button">VIEW DETAILS</button>
        <button class="btn btn-primary ps-5 pe-5 bookThisCar text-wrap " type="button">BOOK NOW</button>
    </div>
   <div class="carDetailsContainer rounded" style="position: absolute;top: 20px;left: 0;right: 0;margin: auto;background: black;color: white;display: none;width: 80%;height: 50%;opacity: 0.9;z-index: 5;">
        <button aria-label="Close" class="btn-close btn-close-white carDetailsContainerCloseBtn" type="button"></button>
    </div>
</div>`;

    if (type === 'GENERAL') {
        $('#generalCarsContainer').append(car);
        bindClickEventsToCarContainer();
    } else if (type === 'PREMIUM') {
        $('#premiumCarsContainer').append(car);
        bindClickEventsToCarContainer();
    } else if (type === 'LUXURY') {
        $('#luxuryCarsContainer').append(car);
        bindClickEventsToCarContainer();
    }
}
