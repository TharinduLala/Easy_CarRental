package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarDto {
    private String carRegNo;
    private String carBrand;
    private String carType;
    private String noOfPassengers;
    private String fuelType;
    private String transmissionType;
    private String carColour;
    private double lossDamagePayment;
    private double monthlyRate;
    private double dailyRate;
    private double freeKmForMonth;
    private double freeKmForDay;
    private double priceForExtraKm;
    private String carFrontImg;
    private String carBackImg;
    private String carSideImg;
    private String carInteriorImg;
    private String carAvailability;
}

/*
#ALTO PREMIUM - 3
insert into car values ('CAR-1111','AVAILABLE',null,'Suzuki Alto - Premium','White',null,null,null,'GENERAL',2500,100,2400,'Petrol',10000,64350,5,30,'MANUAL');

#ALTO K10 - 3
insert into car values ('CAR-1112','AVAILABLE',null,'Suzuki Alto K10','White',null,null,null,'GENERAL',3000,100,2400,'Petrol',10000,71390,5,35,'AUTO');

#Celerion
insert into car values ('CAR-1113','AVAILABLE',null,'Suzuki Celerio','Silver',null,null,null,'GENERAL',3300,100,2400,'Petrol',10000,77220,5,35,'AUTO');

insert into car values ('CAR-1114','AVAILABLE',null,'Perodua Axia','Silver',null,null,null,'GENERAL',3800,100,2400,'Petrol',10000,90200,5,35,'AUTO');

insert into car values ('CAR-1115','AVAILABLE',null,'Toyota Aqua','White',null,null,null,'GENERAL',5000,100,2400,'Petrol',10000,110330,5,49.50,'AUTO');


insert into car values ('CAR-1116','AVAILABLE',null,'Toyota Corolla Axio/ NZE141','Silver',null,null,null,'PREMIUM',5500,100,2400,'Petrol',15000,120330,5,49.50,'AUTO');

insert into car values ('CAR-1117','AVAILABLE',null,'Perodua Bezza Prime Sedan(2017)','Silver',null,null,null,'PREMIUM',5500,100,2400,'Petrol',15000,120330,5,49.50,'AUTO');

insert into car values ('CAR-1118','AVAILABLE',null,'Toyota Allion NZT 260','Silver',null,null,null,'PREMIUM',5800,100,2400,'Petrol',15000,155760,5,60,'AUTO');

insert into car values ('CAR-1119','AVAILABLE',null,'Toyota Axio NKR 165','Silver',null,null,null,'PREMIUM',6000,100,2400,'Petrol',15000,175230,5,65,'AUTO');


insert into car values ('CAR-1120','AVAILABLE',null,'Toyota Premio','White',null,null,null,'LUXURY',10000,100,2400,'Petrol',20000,227150,5,85,'AUTO');

insert into car values ('CAR-1121','AVAILABLE',null,'Mercedes-Benz S-Class','White',null,null,null,'LUXURY',18000,100,2400,'Petrol',20000,300000,4,100,'AUTO');

insert into car values ('CAR-1122','AVAILABLE',null,'BMW i8','White',null,null,null,'LUXURY',18000,100,2400,'Petrol',20000,300000,4,100,'AUTO');
*/
