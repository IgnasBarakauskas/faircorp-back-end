insert into BUILDING(id, amount_of_floors, name ) values(-10, 5, 'Espace Fauriel');
insert into BUILDING(id, amount_of_floors, name ) values(-9, 3, 'Personal House');

insert into ROOM(id, name, floor, current_temperature, target_temperature, building_id) values(-10, 'Room1', 1, 22.3, 20.0,-10);
insert into ROOM(id, name, floor, building_id) values(-9, 'Room2', 1, -10);
insert into ROOM(id, name, floor, building_id) values(-8, 'Room3', 1, -10);

insert into HEATER(id, heater_status, name, power, room_id) values(-10, 'ON', 'Heater1', 2000, -10);
insert into HEATER(id, heater_status, name, power, room_id) values(-9, 'ON', 'Heater2', null, -10);

insert into RWINDOW(id, window_status, name, room_id) values(-10, 'CLOSED', 'Window 1', -10);
insert into RWINDOW(id, window_status, name, room_id) values(-9, 'CLOSED', 'Window 2', -10);
insert into RWINDOW(id, window_status, name, room_id) values(-8, 'OPEN', 'Window 1', -9);
insert into RWINDOW(id, window_status, name, room_id) values(-7, 'CLOSED', 'Window 2', -9);