# Redis


### MGET

In den Redis-Container rein:
`docker-compose exec redis redis-cli`
Dann siehst du einen prompt: exec redis redis-cli127.0.0.1:6379>
Datensatz hinzufügen:
`SET key 'value'`
Das sollte mit einem OK quittieren.
Mit GET MEETING:haus-garten-freizeit:ag_123 kann man den Datensatz dann auch wieder abrufen:
"{\"accessRestrictions\": {}, \"moderators\": [\"pusch@adesso.de\", \"test01@example.com\"], \"maxAttendees\": 42}"