# ChessTime App
> Chess for the busy
## Description
ChessTime is your average multiplayer chess app with a twist: You can take as long as you like to make your move. Your opponent will be notified as soon as you do! Both players up for an intense match? No problem, as chess time also works for fast paced real-time matches.
It offers an intuitive user interface, networking and matchmaking features. 

## Features
- **Real-time & Asyncronous Chess Games**: Compete against players from around the world
- **Push notification**: Get notified as soon as opponent makes their move.
- **Fair matchmaking**: Always fight matched opponents with the ELO system.
- **User-Friendly Interface**: Intuitive operation for a seamless gaming experience.

## Architecture Overview
### Client
- **Model-View-Presenter (MVP)** architecture for separating the user interface, business logic, and data model.

### Server
- Rest-API based on **Java Servlets**
- **Firebase Cloud Messaging** for asynchronous communication with clients.
- MySQL Database for Game persistence.
