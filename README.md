## About
football league management system is system to manage the clubs, matchs, players and staffs in league by searching, adding, deleting and updating

## API
these are the endpoints

 - Club
 
`/api/clubs/ or /api/clubs/{club_id}/` Methods: GET, POST, PUT, DELETE

 - Player
 
`/api/clubs/{club_id}/players/` Methods: GET 

`/api/clubs/{club_id}/players/{player_id}` Methods: GET, POST, PUT, DELETE

`/api/clubs/{club_id}/players/{player_id}?name={player_name}` Methods: GET 

`/api/clubs/{club_id}/players/{player_id}?date={player_birth_of_date}` Methods: GET 

`/api/clubs/{club_id}/players/{player_id}?country={player_country}` Methods: GET 

`/api/clubs/{club_id}/players/{player_id}?position={player_position}` Methods: GET 

 - Staff
 
`/api/clubs/{club_id}/staffs/` Methods: GET 

`/api/clubs/{club_id}/staffs/{staff_id}` Methods: GET, POST, PUT, DELETE

`/api/clubs/{club_id}/staffs/{staff_id}?name={staff_name}` Methods: GET 

`/api/clubs/{club_id}/staffs/{staff_id}?date={staff_birth_of_date}` Methods: GET 

`/api/clubs/{club_id}/staffs/{staff_id}?country={staff_country}` Methods: GET 

`/api/clubs/{club_id}/staffs/{staff_id}?role={staff_position}` Methods: GET

 - Match
 
`/api/clubs/{club_id}/matches/` Methods: GET 

`/api/clubs/{club_id}/matches?date={match_date}` Methods: GET

`/api/clubs/{club_id}/matches?status={status}` Methods: GET

`/api/clubs/{club_id}/matches?winner={winner}` Methods: GET  

## ERD
![enter image description here](https://i.imgur.com/bYkOLWP.jpg)
