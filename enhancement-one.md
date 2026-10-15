# Enhancement One Software Design/Engineering

Narrative

## Briefly describe the artifact. What is it? When was it created?

The artifact I selected is the Weight Tracker App that I originally created in CS 360: Mobile Architect and Programming. It is an Android application written in Java that allows users to log their weight, view their history, and track progress over time. The app includes screens for login, adding weight entries, and viewing a list of past entries. I created this project during my earlier coursework, and it represents one of my first complete mobile applications.

## Justify the inclusion of the artifact in your ePortfolio. Why did you select this item? What skills do it showcase? How was it improved?

I selected this artifact because it clearly demonstrates my skills in software design and engineering, especially in mobile development. The app shows that I can build a functional Android application, organize UI layouts, connect screens, and manage user input. It also shows that I can work with activities, adapters, and a local SQLite database.

For Milestone Two, I improved the artifact by focusing on better software design, cleaner architecture, and stronger engineering practices. These improvements include:

1. Separating logic from UI (major design improvement)

Before enhancement, LoginActivity and MainActivity mixed UI code with logic. I created new helper classes:

AuthManager – handles login validation and authentication logic

ErrorHandler – manages error messages and input validation

UIHelper – organizes repeated UI tasks

This makes the code easier to maintain, test, and extend. LoginActivity calls ErrorHandler to check for empty fields before it calls AuthManager. AuthManager uses DatabaseHelper to check login credentials, check whether a username already exists, and create an account. In the later database enhancement, login returns the user ID. UIHelper shows and hides the loading indicator around these calls. LoginActivity handles button actions, messages, and navigation. In the integrated database version, it passes USER_ID to WeightListActivity. The weight and goal Activities use that same ID when they load or change data. MainActivity keeps its screen and SMS permission navigation responsibilities.

2. Improving the login flow and error handling

I added:

Empty-field validation

Clear error messages

Better navigation logic

Clearer input checks

3. Improving screen organization and layout

I replaced older layout with ConstraintLayout, which is more modern and flexible. This makes the UI cleaner and easier to modify.

4. Improving readability and maintainability

I reorganized methods, added comments, and removed repeated code. I also improved naming conventions to follow Java best practices.

These improvements show real software-engineering skills, not just basic coding.

## Did you meet the course outcomes you planned to meet in Module One? Any updates?

Yes, I met the outcomes I planned to cover, especially:

Software Engineering / Design

I used real engineering techniques such as:

Separation of concerns

Modular classes

Improved architecture

Better UI design

Cleaner code structure

## Security Mindset

I improved input validation and login flow, reducing risks of bad input or weak logic. Passwords are still stored in plain text. Checking input helps avoid empty values, but it does not make authentication secure. Password hashing remains a needed improvement.

## Professional Communication

I created a clear narrative, organized code, and prepared documentation for the ePortfolio.

Updates:

I expanded my outcome coverage by adding more technical details than originally planned. I also improved the artifact more deeply after reviewing feedback from my instructor.

## Reflect on the process of enhancing and modifying the artifact. What did you learn? What challenges did you face?

Enhancing this artifact taught me how important, good software design is for long-term maintainability. When I reviewed my old code, I realized that even though the app worked, the structure was not clean. Logic and UI were mixed, error handling was weak, and the layout was outdated. Improving these areas helped me understand how professional developers think about architecture, not just functionality.

What I learned:

How to separate logic into helper classes

How to improve UI using ConstraintLayout

How to write cleaner, more readable code

How to think about maintainability and testing

How to improve user experience with better validation

How to apply real software-engineering principles

Challenges I faced:

Refactoring old code without breaking functionality

Deciding which logic belonged in new classes

Updating layouts while keeping the app stable

Making sure the new architecture was consistent

Ensuring the enhancements matched the capstone requirements

This enhancement helped me grow as a software engineer. It showed me how to take an existing project and make it more professional, more organized, and more aligned with industry standards. I checked account creation, duplicate usernames, empty fields, valid and invalid login, and navigation to the weight and goal screens. I also checked adding, editing, and deleting weight entries, setting goals, and refreshing the list after changes. These manual checks helped me confirm that the refactor kept the main app flow working.

## Word Copy

[Download the final Word document](documents/Enhancement%20One%20Final%20Software%20Design%20Engineering.docx)
