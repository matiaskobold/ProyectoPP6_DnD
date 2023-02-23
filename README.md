# ProyectoPP6_DnD
Springboot REST API / Backend + Frontend
https://hub.docker.com/repository/docker/matiaskobold/proyectoisa/general

Soporte Maven:
> mvn clean install

Para correr el proyecto con Docker, desde el directorio del proyecto:
> docker build -f Dockerfile -t docker-spring-boot .



REST API:
>Characters:
>{"name":"Gimli",
>"age":200,
>"race":"Dwarf",
>"player_username":"matias",
>"player_discord":"matias#345"
>}

>Homes:
>{
"address": "Cabildo",
"city": "Buenos Aires",
"country": "Argentina"
}

>Clans:
>{"name":"Nombre de clan",
"description":"Descripcion de clan",
"main_language":"ingles",
"operations_base":"argentina",
"title": "titulo de clan"
}

>Songs:
>{"name":"Nombre de song",
"description":"Descripcion de song",
"clasification":"Clasificacion de song",
"participants":1}

Metodos:

>POST /characters
>>Crea un character nuevo. Enviar parametros de character.

>GET /characters o /characters/{id}
>>Devuelve todos los characters o un character por ID. Con self links.

>PUT /characters/{id}
>>Modifica un character. Enviar parametros de character.

>DELETE /characters/{id}
>>Elimina un character por ID.

>GET /homes y POST /homes 
>>funciona igual que los anteriores pero con los parámetros de homes.

>POST /characters/{id}/homes
>>Con parametros de homes, crea una nueva home y se la asigna a un character.

>PUT /characters/{idC}/homes/{idH}
>>Le asigna una casa ya creada a un character, por si este cambia la casa o no tenia casa.

>DELETE /characters/{id}/homes
>>Se elimina la casa de un character.

>POST /clans, GET /clans, GET /clans/{id}, PUT /clans/{id}, DELETE /clans/{id}
>>Funciona igual que los anteriores pero con los parametros de clan donde corresponda.

>POST /clans/{id}/songs
>>Crea una cancion con el body de songs y se la asigna a un clan por ID.

>GET /clans/{id}/songs
>>Devuelve todas las canciones del clan por ID.

>GET /songs/{id}, PUT /songs/{id}, DELETE songs/{id}
>>Funciona igual que los anteriores pero con los parametros de Song cuando corresponda
