
# Project Social with React and Spring Boot




## References
 - https://github.com/facebook/create-react-app
 - https://facebook.github.io/create-react-app/docs/troubleshooting
 - https://babeljs.io/repl
## Installation Guides
### Required Tools
 - Node v8+ for npm
 - Visual Studio Code - Latest Version
 - Java 8+
 - Netbeans
 - Tomcat
### Installing Node Js (npm) & Visual Studio Code
 - Playlist - https://www.youtube.com/playlist?list=PLBBog2r6uMCQN4X3Aa_jM9qVjgMCHMWx6
Steps
  - Step 01: Installing NodeJs and NPM - Node Package Manager
  - Step 02: Quick Introduction to NPM
  - Step 03: Installing Visual Studio Code - Front End Java Script Editor
### Installing Java, Eclipse & Embedded Maven
 - Playlist - https://www.youtube.com/playlist?list=PLBBog2r6uMCSmMVTW_QmDLyASBvovyAO3
Steps
 - 0: Overview - Installation Java, Eclipse and Maven
 - 1: Installing Java JDK
 - 2: Installing Eclipse IDE
 - 3: Using Embedded Maven in Eclipse
 - 4: Troubleshooting Java, Eclipse and Maven
 ### Troubleshooting Installations
 - Node JS and NPM
https://docs.npmjs.com/common-errors
https://docs.npmjs.com/getting-started/troubleshooting
 - Visual Studio Code
https://code.visualstudio.com/docs/supporting/errors
https://code.visualstudio.com/docs/supporting/FAQ
### Getting Started with Spring, Spring Boot and JPA
 - Spring Tutorial for Beginners - https://www.youtube.com/watch?v=edgZo2g-LTM
 - Spring Boot Tutorial for Beginners - https://www.youtube.com/watch?v=pcdpk3Yd1EA
 - JPA and Hibernate Tutorial for Beginners - https://www.youtube.com/watch?v=MaI0_XdpdP8
## Introduction
### Technologies Used
1. Frontend: ReactJS: ReactJS is a popular JavaScript library for building user interfaces. In your project, it will be used to create the interactive web application that users can access to participate in auctions.
2. Styling: Tailwind CSS: Tailwind CSS is a utility-first CSS framework that makes it easy to create a visually appealing and responsive design for your online auction platform.
3. API Integration: Axios: Axios is a promise-based HTTP client that will be used to make API requests to your backend server, allowing your frontend to communicate with the server.
4. State Management: Redux: Redux is a state management library for JavaScript applications. You can use it to manage the application's state, especially in scenarios where data needs to be shared across different components.
5. Backend: Spring Boot: Spring Boot is a Java-based framework for building robust and scalable web applications. In your project, it will serve as the backend server responsible for handling user authentication, managing auctions, and processing bids.
6. Authentication: JWT (JSON Web Tokens): JWT is a widely used authentication method that securely communicates user identity between the frontend and backend. It will help ensure that only authorized users can participate in auctions.
7. Image Storage: Cloudinary: Cloudinary is a cloud-based media management solution. You can use it to store and manage images associated with auction items, allowing users to see the products being auctioned.
### Project Description
1. User Registration and Authentication: Users will be able to register and log in securely using JWT. Spring Boot will handle user authentication and authorization.
2. Auction Listing: Sellers can create listings for items they want to auction. These listings will include product details, images (stored on Cloudinary), and auction parameters such as starting price, duration, and reserve price.
3. Bidding: Registered users can place bids on items in real-time. Redux will manage the state of the auction, including the current highest bid, remaining time, and other relevant information.
4. Auction Management: Spring Boot will manage auctions, handle the logic for determining winning bids, and enforce auction rules.
5. Payment Integration: You may consider integrating payment gateways for secure transactions when an auction ends.
6. Real-Time Updates: Use WebSocket technology to provide real-time updates to users about the status of auctions they are participating in.
7. Responsive Design: Tailwind CSS will ensure that your application is responsive and looks great on various devices.
8. Data Communication: Axios will facilitate communication between the frontend and backend, ensuring seamless data transfer.
