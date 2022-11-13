
# faircorp-back-end

> Prepared by Ignas BARAKAUSKAS CPS2

In total created:

* 2 Different types of users
* 4 Related tables
* 4 Data Transfer Objects(DTO)
* 4 Data Access Objects (DAO)
* 22 Endpoints
* 43 Tests to test **DAO** and **Endpoint** functionality

Credentials of users:

* Role **USER**
  * username: user
  * password: myPassword
* ROLE **ADMIN**
  * username: admin
  * password: admin

Final application is hosted at [faircorp](https://faircorp-ignas-barakauskas.cleverapps.io)

To test end-points you can use following link [Swagger](https://faircorp-ignas-barakauskas.cleverapps.io/swagger-ui/index.html#/)

# **End-points**

## building-controller (Requires user with any role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/building | returns a list of all buildings|
| **POST** | /api/building | creates a new building |
| **GET** | /api/building/{id} | returns a specific building by id|
| **DELETE** | /api/building/{id} | deletes a building by id|
| **PUT** | /api/building/{id}/switch | inverts building's status if it was LOCKED it will become UNLOCKED or if it was UNLOCKED it will become LOCKED|

## room-controller (Requires user with any role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/rooms | returns a list of all rooms|
| **POST** | /api/rooms | creates a new room |
| **GET** | /api/rooms/{id} | returns a specific room by id|
| **DELETE** | /api/rooms/{id} | deletes a room by id|
| **PUT** | /api/rooms/{id}/switch-heater | inverts all heaters statuses in room if it was ON it will become OFF or if it was OFF it will become ON|
| **PUT** | /api/rooms/{id}/switch-window | inverts all windows statuses in room if it was OPEN it will become CLOSED or if it was CLOSED it will become OPEN|

## heater-controller (Requires user with any role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/heaters | returns a list of all heaters|
| **POST** | /api/heaters | creates a new heater |
| **GET** | /api/heaters/{id} | returns a specific heater by id|
| **DELETE** | /api/heaters/{id} | deletes a heater by id|
| **PUT** | /api/heaters/{id}/switch | inverts heater's status if it was ON it will become OFF or if it was OFF it will become ON|

## window-controller (Requires user with any role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/windows | returns list of all windows|
| **POST** | /api/windows | creates a new window |
| **GET** | /api/windows/{id} | returns a specific window by id|
| **DELETE** | /api/windows/{id} | deletes a window by id|
| **PUT** | /api/windows/{id}/switch | inverts window's status if it was OPEN it will become CLOSED or if it was CLOSED it will become OPEN|

## user-controller (Required user with admin role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/users | returns list of all users (user must have admin role to do so)|

# **Test coverage**

| Element | Class, % | Method, % | Line, % |
|--|--|--|--|
|api(Controller)|100% (10/10)|100% (52/52)| 92% (138/150)|
|dao|100% (4/4)|100% (4/4)|100% (14/14)|
|dto|100% (8/8)|87% (84/96)|91% (124/136)|
|model|100% (14/14)|83% (102/122)|74% (132/178)|
|services|100% (2/2)|100% (8/8)| 100% (54/54)|