
# Https ****************************************************

* port 844~~~~~~~~~~~~~~_3~~_

# Users ****************************************************

* admin - password
* user - password

# Endpoints ****************************************************

* GET       base endpoint or home

* GET       admin

* GET       authenticated

* GET       info

* GET       /user
  https://localhost:8443/user
  
* POST      user
  https://localhost:8443/user
    JSON:
        {
            "username": "userTest",
            "password": "password",
            "authorities": [
                {
                    "username": "userTest",
                    "authority": "ROLE_USER"
                }
            ]
        }

* GET       user/{username}
  https://localhost:8443/user/userTest
  
* PUT       user/{username}
  https://localhost:8443/user/userTest
  JSON:
        {
            "password": "password1"
        }

* GET       /user/{username}/authorities
  https://localhost:8443/user/userTest/authorities


* POST      /users/{username}/authorities
  https://localhost:8443/user/testuser/authorities
  JSON:
        {
        "username": "userTest",
        "authority": "ROLE_ADMIN"
        }

* DELETE    /user/{username}/authorities/{authority}
  https://localhost:8443/user/userTest/authorities/ROLE_ADMIN

* DELETE    /user/{username}
  https://localhost:8443/user/userTest
  

* GET       /action
  https://localhost:8443/action
  
* POST      /action
  https://localhost:8443/action
  JSON:
  {
        "price": 500.0,
        "action": "motor is changed",
        "name": "motor"
    }
  
* PUT       /action/{id}
  https://localhost:8443/action/5
  JSON:
  {
      "price": 450.0,
      "action": "motor is changed, but needs repair again",
      "name": "motor"
  }

* PATCH     /action/{id}
* DELETE    /action/{id}

* GET       /car
* POST      /car
* GET       /car/{id}
* PUT       /car/{id}
* PATCH     /car/{id}
* DELETE    /car/{id}

* GET       /component
* POST      /component
* GET       /component/{id}
* PUT       /component/{id}
* PATCH     /component/{id}
* DELETE    /component/{id}

* GET       /examination
* POST      /examination
* GET       /examination/{id}
* PUT       /examination/{id}
* PATCH     /examination/{id}
* DELETE    /examination/{id}

* GET       /reparation
* POST      /reparation
* GET       /reparation/{id}
* PUT       /reparation/{id}
* PATCH     /reparation/{id}
* DELETE    /reparation/{id}