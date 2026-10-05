# Professional Self-Assessment

## Introduction

As I prepare my final CS-499 Computer Science Capstone ePortfolio at Southern New Hampshire University, this professional self-assessment brings together what I have learned throughout the Computer Science program and the growth demonstrated in my Weight Tracker project.

My professional interests include cybersecurity, data analysis, and software development. During the program, I learned that these areas are connected. Software needs to be organized and reliable, data needs to be stored and processed correctly, and security needs to be considered throughout the development process.

My ePortfolio uses the Android Weight Tracker application as the main artifact across software design and engineering, algorithms and data structures, and databases. Using the same application for all three categories helped me understand how changes in one part of a system can affect the rest of the application.

## Professional Skills and Growth

One of the most important things I learned during the program is that software development is not only about making a program work. Good software should also be organized, maintainable, secure, and understandable to other developers and users.

Communication has been an important part of my growth. During the program, I completed technical writing, project documentation, presentations, and a code review video. The code review helped me practice explaining how an application works, identifying weaknesses, and describing planned improvements for another developer, instructor, or stakeholder.

Collaboration is also important in computer science. Even when a project is completed individually, the code and documentation should be clear enough for another person to review and continue. Clear naming, organization, documentation, and separation of responsibilities support that goal. I also used instructor feedback during the capstone to make my technical explanations more specific and to add clearer evidence for testing, complexity, database design, and security.

## Software Engineering

My Software Design and Engineering enhancement focused on improving the structure of the Weight Tracker application.

The original application placed several responsibilities directly inside the Activities. I improved the organization by using helper classes such as AuthManager, ErrorHandler, and UIHelper. AuthManager handles authentication-related logic, while Activities focus more on navigation and user interaction. ErrorHandler and UIHelper support reusable validation, error handling, and interface behavior.

The core application flow was manually tested in the Android Emulator after the enhancement work. Testing included account creation, login, navigation, adding weight records, editing records, deleting records, setting goals, and returning to the list to confirm that the application continued working after the changes.

## Algorithms and Data Structures

The Algorithms and Data Structures enhancement focused on how the application processes and displays weight records.

The application retrieves records from SQLite using a Cursor and processes them one at a time. If there are n records, displaying the complete list requires one traversal, so the processing is O(n).

The revised logic resolves column indices before the loop, closes the Cursor in a finally block, and uses onResume to refresh the list without the earlier duplicate initial load. Add, edit, delete, and refresh behavior was manually tested in the Android Emulator.

For this algorithm enhancement, no formal performance benchmark or automated algorithm-performance suite was run. Because of that, I do not claim a measured speed improvement. The improvement is in clearer processing, less repeated work, better lifecycle handling, and user-connected data operations. The later instrumentation tests described in the database enhancement were focused on migration and user-scoped database behavior, not algorithm performance.

## Databases

The Database enhancement improved the SQLite structure and the way the application separates user information.

Weights and goals are connected to users with user_id relationships and foreign keys. Database operations use the logged-in user's ID when retrieving, updating, and deleting information.

I tested user data separation with two different accounts. User A created weight records and a goal. After logging out and signing in as User B, User A's information was not visible. When I returned to User A, the original information was still available.

The published enhanced artifact also includes a later version 4 migration that preserves legacy tables and copies records with known owners into the active schema. On October 5, 2026, that migration was compiled and validated separately in an isolated Android test copy on Android 14/API 34.

Three instrumentation tests passed. They confirmed successful migration to version 4, foreign-key enforcement, database integrity, user-scoped add/edit/delete operations, independent goals for two users, rejection of invalid user IDs, and safe archiving of records that could not be connected to a valid owner. No application crash was observed during the tested migration and account/CRUD flows.

The original local project folder remains on DATABASE_VERSION 3. The version 4 validation was performed against the published enhanced artifact without modifying the original folder.

## Security

Security has become more important to me throughout the Computer Science program.

In the Weight Tracker application, user-specific database operations, foreign keys, input validation, and permission handling helped improve data protection and integrity. The two-user test also provided evidence that one account could not see another account's stored weight and goal information.

The version 4 migration testing also showed that records with unknown or invalid ownership are not assigned to another user. Invalid user IDs were rejected by foreign-key constraints.

I also learned that recognizing a weakness is part of having a security mindset. The application still stores passwords in plaintext. I do not consider that secure authentication, and password hashing would be an important improvement before a professional release.

## Course Outcome Reflection

### Outcome 1 — Collaborative Environments and Decision Making

The code review, documentation, technical narratives, and use of instructor feedback helped me practice presenting technical decisions so other people can review them. I learned that collaboration requires understandable code and clear explanations of trade-offs, limitations, and test results. I do not claim that this artifact was a completed team-development project, but the way the work is documented supports collaborative review.

### Outcome 2 — Professional Communication

The two-part code review, written narratives, ePortfolio pages, and this professional self-assessment demonstrate oral and written communication. I worked to explain technical ideas in a way that is accurate but still understandable to different audiences.

### Outcome 3 — Algorithmic Solutions and Trade-offs

The Algorithms and Data Structures enhancement demonstrates this outcome through the O(n) Cursor traversal, reduction of repeated work inside the loop, lifecycle-based refresh behavior, and evaluation of design trade-offs. I also separated measured facts from assumptions by not claiming a performance improvement without a benchmark.

### Outcome 4 — Computing Techniques, Skills, and Tools

The Weight Tracker demonstrates the use of Java, Android development, SQLite, database relationships, validation, Activity lifecycle handling, helper classes, CRUD operations, and Android instrumentation testing. These tools were used together to improve organization, data ownership, migration behavior, and reliability.

### Outcome 5 — Security Mindset

The database enhancement demonstrates user ownership, data isolation, foreign-key relationships, user-scoped updates and deletes, input validation, and safe handling of records with unknown ownership. The project also identifies plaintext password storage as a remaining vulnerability instead of presenting the application as fully secure.

## How the Portfolio Fits Together

The code review, original code, enhanced code, and three enhancement narratives show the process of identifying weaknesses and improving the Weight Tracker application.

The Software Design and Engineering enhancement demonstrates better organization and maintainability. The Algorithms and Data Structures enhancement demonstrates how the application processes and manages weight records. The Database enhancement demonstrates improved relationships, ownership, migration behavior, and separation of user data.

Together, these enhancements show my growth in software engineering, algorithms, databases, testing, security, communication, and problem-solving.

## Portfolio Summary

The three required enhancement categories are represented in this ePortfolio, and the technical work is supported by manual Android Emulator testing and dedicated database migration instrumentation testing.

This portfolio presents the original and enhanced artifact, the code review, the three enhancement narratives, the course outcome evidence, and this professional self-assessment as one complete view of my growth throughout the Computer Science program.
