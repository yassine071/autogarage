# documenten***********************************************
*backend(N)->documenten->installatie.yassin
*backend(N)->documenten->functionele& technische ontwerp
*backend(N)->documenten->verantwoording
# Https ****************************************************

* port 8443
* https://localhost:8443/

# Users ****************************************************

* admin - password
* user - password

# Endpoints/Json ****************************************************
// Hiermee haalt u alle users op
* GET       /user
  https://localhost:8443/user
// hiermee maakt u een nieuwe user aan met de bij behorende gemachtigde rol
* POST      user
  https://localhost:8443/user
    JSON:
        {
            "username": "user",
            "password": "password",
            "authorities": [
                {
                    "username": "user",
                    "authority": "ROLE_USER"
                }
            ]
        }
 //hiermee haalt u alleen specifiek de user door username te veranderen naar des betreffende user.
* GET       user/{username}
  https://localhost:8443/user/user
//hiermee update u de gegevens van de user door username te veranderen in de desbetreffende user. Onderstaand is de wachtwoord gewijzigd van user.
* PUT       user/{username}
  https://localhost:8443/user/user
  JSON:
        {
            "password": "password1"   }


//hiermee haalt u specifiek de rol van de user
* GET       /user/{username}/authoritiy
  https://localhost:8443/user/user/authority

//door de authority te veranderen naar desbetreffende authority verwijderd u dit.
* DELETE    /user/{username}/authority/{authority}
  https://localhost:8443/user/userTest/authorities/ROLE_USER

***************************************************************************
//hiermee haalt u de action lijst op
* GET       /action
  https://localhost:8443/action
// hiermee maakt  een nieuwe action aan
* POST      /action
  https://localhost:8443/action
  JSON:
 {
        "price": 500.0,
        "action": "motor is changed",
        "name": "motor"
    }

//hiermee haalt u alleen specifiek de action door de id te veranderen naar des betreffende id.
* GET       action/{id}
  https://localhost:8443/action/5
// door de id te kiezen van de desbetreffende action kunt u dit updaten.
* PUT       /action/{id}
  https://localhost:8443/action/5
  JSON:
  {  "price": 450.0,
      "action": "motor is changed, but needs repair again",
      "name": "motor"  }

// door de specifieke id te kiezen die bij de action hoort kunt u dit verwijderen.
* DELETE    /action/{id}
  https://localhost:8443/action/5
  JSON:
  {
      "price": 450.0,
      "action": "motor is changed, but needs repair again",
      "name": "motor"
  }

**********************************************************************************
//hiermee haalt u de cars op.
* GET       /car
https://localhost:8443/car

//hiermee maakt u de car aan.
* POST      /car
https://localhost:8443/car
JSON:
  {
      "brand": “volkswagen”,
      "customer_name": "Jan Hendrik",
      "License_plate": "45-io-89",
       "model": “polo”
  }
// hiermee haalt u de specifieke car op door de id te selecteren die bij de desbetreffende car hoort
* GET       /car/{id}
https://localhost:8443/car/3
JSON:
  {    "brand": “volkswagen”,
      "customer_name": "Jan Hendrik",
      "License_plate": "45-io-89",
       "model": “polo”     }

//hiermee update je de gegevens van de desbetreffende car op door de id te selecteren van de car.
* PUT       /car/{id}
https://localhost:8443/car/3

  {
      "brand": “volkswagen”,
      "customer_name": "Jan Hendrik",
      "License_plate": "45-io-89",
       "model": “golf”
}
//door de specifieke id te kiezen kunt u de desbetreffende car verwijderen
* DELETE    /car/{id}
https://localhost:8443/car/3

// hiermee haal je alle gegevens van de aanwezige componenten aan.
* GET       /component
https://localhost:8443/component

//hiermee voeg je een nieuwe component aan.
* POST      /component
https://localhost:8443/ component
JSON:
  {
      "component": “luchtfilter”,
      "price": 35.0,
  }


//hiermee haalt u de specifieke component aan door de id aan te geven van de desbetreffende component.
* GET       /component/{id}
https://localhost:8443/ component/3
JSON:
  {
      "component": “luchtfilter”,
      "price": 35.0,
  }
// hiermee veranderd u de status van de desbetreffende component.
* PUT       /component/{id}
https://localhost:8443/ component/3
JSON:
  {
      "component": “luchtfilter”,
      "price": 45.0,
  }

* DELETE    /component/{id}
https://localhost:8443/ component/3

 ****************************************************************************
//hiermee haalt u de laast van de examination op.
* GET       /examination
https://localhost:8443/examination

//hiermee maakt u een nieuwe examination aan.
* POST      /examination
https://localhost:8443/examination
  {
      "date": “13-11-2020”,
      "appointment": "audi",
      "costumer": "user",
}

//hiermee haalt u de desbetreffende examination op.
* GET       /examination/{id}
https://localhost:8443/examination/3
  {
      "date": “13-11-2020”,
      "appointment": "audi",
      "costumer": "user",
}

//hiermee wijzigt u de examination.
* PUT       /examination/{id}
https://localhost:8443/examination/3
  {
      "date": “17-11-2020”,
      "appointment": "audi",
      "costumer": "user",
}


//hiermee verwijderd u de desbetreffende examination
* DELETE    /examination/{id}
https://localhost:8443/examination/3
  **********************************************************************************
//hiermee haal je de reparation lijst op
* GET       /reparation
https://localhost:8443/reparation

//hiermee maakt u een nieuwe reparation aan.
* POST      /reparation
https://localhost:8443/ reparation
JSON:
  {
      "status": “bezig”,
      "date": “17-11-2020”,
  }
//hiermee haalt u de desbetreffende reparation op.
* GET       /reparation/{id}
https://localhost:8443/ reparation/3
JSON:
  {
      "status": “bezig”,
      "date": “17-11-2020”,
  }
//hiermee wijzigt u de desbetreffende reparation.
* PUT       /reparation/{id}
https://localhost:8443/ reparation/3
JSON:
  {
      "status": “gedaan”,
      "date": “17-11-2020”,
  }
//hiermee verwijdert u de desbetreffende reparation.
* DELETE    /reparation/{id}

