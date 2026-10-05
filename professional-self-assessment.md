# Professional Self-Assessment

## Introduction

I am currently completing Module Six of the CS-499 Computer Science Capstone at Southern New Hampshire University. This professional self-assessment brings together what I have learned throughout the Computer Science program and the progress I have made while building my ePortfolio.

My professional interests include cybersecurity, data analysis, and software development. During the program, I have learned that these areas are connected. Software needs to be organized and reliable, data needs to be stored and processed correctly, and security needs to be considered throughout the development process.

My ePortfolio uses the Android Weight Tracker application as the main artifact across software design and engineering, algorithms and data structures, and databases. Using the same application for all three categories has helped me see how changes in one part of a system can affect other parts of the application.

## Professional Skills and Growth

One of the most important things I learned during the program is that software development is not only about making a program work. Good software should also be organized, maintainable, secure, and understandable to other developers and users.

Communication has been an important part of my growth. During the program, I completed written assignments, project documentation, presentations, and a code review video. The code review helped me practice explaining how an application works, identifying problems, and describing planned improvements in a way that another developer or stakeholder could understand.

Collaboration is also important in computer science. Even when working on individual projects, I learned that code should be written so another person can understand it. Clear naming, documentation, organization, and separation of responsibilities all support collaboration. I also used instructor feedback throughout the capstone to improve my work and make later enhancements more specific.

## Software Engineering

My software engineering enhancement focused on improving the structure of the Weight Tracker application.

The original application worked, but several responsibilities were handled directly inside the Activities. I improved the organization by using helper classes such as AuthManager, ErrorHandler, and UIHelper. AuthManager handles authentication-related logic, while the Activities focus more on the user interface and navigation. ErrorHandler and UIHelper help separate other responsibilities from the Activities.

This enhancement helped me better understand separation of concerns and maintainability. I also tested important functions after the changes, including login, account creation, navigation, adding weights, editing records, deleting records, and setting goals.

## Algorithms and Data Structures

The algorithms and data structures enhancement focused on how the application processes and displays weight records.

The application retrieves records from the SQLite database using a Cursor and processes the records one at a time. If there are n weight records, displaying all of them requires one pass through the records, so the processing is O(n).

The application also uses logic for adding, editing, deleting, and refreshing weight information. Adding a new weight uses a database insert operation after validation. Updating and deleting use both the weight ID and the user ID so the correct record is changed.

I tested these operations manually in the Android Emulator. I added multiple weight records, edited existing records, deleted records, and confirmed that the list refreshed correctly after the changes.

I did not run a formal performance benchmark, so I would not claim that the application became faster. The main improvement was making the data processing clearer, more organized, and better connected to the user data.

## Databases

The database enhancement improved the SQLite structure and the way the application separates user information.

I added user_id relationships so that weight and goal records belong to a specific user. I also added foreign key relationships between the users, weights, and goals tables.

The structure now follows relationships similar to:

Users → Weights

Users → Goals

Database methods use the logged-in user's ID when retrieving, updating, and deleting information. This helps prevent one user from seeing or changing another user's records.

I tested the database using two different user accounts. The first user added weight records and a goal. After logging out and signing in as the second user, the first user's information was not visible. When I logged back into the first account, the original information was still available.

This testing confirmed that the database correctly separates data between users.

I also experienced a database version problem during the enhancement. The application initially crashed because an older database on the emulator did not contain the new user_id column. I fixed the problem by updating the database version and recreating the database with the new structure.

## Security

Security has become more important to me throughout the Computer Science program.

In the Weight Tracker application, separating information by user helped improve data isolation. Input validation, authentication, permissions, and user-specific database operations also helped me understand how security connects to software design.

I also understand that the application still has areas that would need improvement before a professional release. For example, password storage should be improved with secure hashing instead of storing passwords in plain text. Recognizing these weaknesses is part of developing a security mindset.

## Experience Beyond the Capstone Artifact

Other coursework has also helped me build my computer science skills.

In my full-stack development coursework, I worked with the Travlr Getaways project using technologies such as Express, MongoDB, Mongoose, APIs, and application views. This helped me understand how a front end, server, API, and database work together.

This experience is different from the Android Weight Tracker project, but both helped me understand how software systems connect user interfaces, application logic, and data.

## How the Portfolio Fits Together

The code review, original code, enhanced code, and three enhancement narratives show the process of identifying weaknesses and improving a software application.

The Software Design and Engineering enhancement demonstrates better organization and maintainability.

The Algorithms and Data Structures enhancement demonstrates how the application processes and manages weight records.

The Database enhancement demonstrates improved relationships, data ownership, and user separation.

Together, these enhancements show my growth in software engineering, algorithms, databases, testing, security, communication, and problem-solving.

## Progress in Module Six

At this point, the three main technical enhancements have been completed.

The Weight Tracker application has been compiled successfully and tested in the Android Emulator. The database enhancement was also tested with two separate user accounts to confirm that user data is kept separate.

My current focus in Module Six is improving the professional self-assessment, reviewing the course outcomes, organizing the ePortfolio, and making sure the GitHub Pages site clearly presents the original and enhanced artifacts, narratives, and code review.

## Remaining Work Before Module Seven

Before the final Module Seven submission, I still need to review the complete ePortfolio and make sure all links work correctly.

I also need to confirm that the GitHub Pages site is clear and easy to navigate, that the original and enhanced artifacts are presented correctly, and that each narrative clearly explains the skills and course outcomes demonstrated.

The professional self-assessment will be reviewed again before the final submission so that it reflects the completed ePortfolio and any additional instructor feedback received during Module Six.

My goal is to enter Module Seven with the main content already completed so that the final week can focus on review, organization, and submission.
