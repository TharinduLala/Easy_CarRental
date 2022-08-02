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

$(function () {
    let nawHeight = nav.outerHeight();
    homeSection.css('top', nawHeight + 'px');
    bookingsSection.css('top', nawHeight + 'px');
    vehiclesSection.css('top', nawHeight + 'px');
    driversSection.css('top', nawHeight + 'px');
    customerSection.css('top', nawHeight + 'px');
    currentPageH2.html('Home');

});

navHome.on('click',function () {
    currentPageH2.html("Home");
    homeSection.addClass('d-block').removeClass('d-none');
    bookingsSection.addClass('d-none').removeClass('d-block');
    vehiclesSection.addClass('d-none').removeClass('d-block');
    driversSection.addClass('d-none').removeClass('d-block');
    customerSection.addClass('d-none').removeClass('d-block');
    navCloseBtn.trigger('click');
});
navBookings.on('click',function () {
    currentPageH2.html("Bookings");
    homeSection.addClass('d-none').removeClass('d-block');
    bookingsSection.addClass('d-block').removeClass('d-none');
    vehiclesSection.addClass('d-none').removeClass('d-block');
    driversSection.addClass('d-none').removeClass('d-block');
    customerSection.addClass('d-none').removeClass('d-block');
    navCloseBtn.trigger('click');
});
navVehicles.on('click',function () {
    currentPageH2.html("Vehicles");
    homeSection.addClass('d-none').removeClass('d-block');
    bookingsSection.addClass('d-none').removeClass('d-block');
    vehiclesSection.addClass('d-block').removeClass('d-none');
    driversSection.addClass('d-none').removeClass('d-block');
    customerSection.addClass('d-none').removeClass('d-block');
    navCloseBtn.trigger('click');
});
navDrivers.on('click',function () {
    currentPageH2.html("Drivers");
    homeSection.addClass('d-none').removeClass('d-block');
    bookingsSection.addClass('d-none').removeClass('d-block');
    vehiclesSection.addClass('d-none').removeClass('d-block');
    driversSection.addClass('d-block').removeClass('d-none');
    customerSection.addClass('d-none').removeClass('d-block');
    navCloseBtn.trigger('click');
});
navCustomers.on('click',function () {
    currentPageH2.html("Customers");
    homeSection.addClass('d-none').removeClass('d-block');
    bookingsSection.addClass('d-none').removeClass('d-block');
    vehiclesSection.addClass('d-none').removeClass('d-block');
    driversSection.addClass('d-none').removeClass('d-block');
    customerSection.addClass('d-block').removeClass('d-none');
    navCloseBtn.trigger('click');
});
