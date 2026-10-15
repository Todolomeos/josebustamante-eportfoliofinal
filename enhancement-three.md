# Enhancement Three: Databases – Jose Bustamante

## 1. Briefly describe the artifact. What is it? When was it created?

The artifact I chose is my Android Weight Tracker app. I created it in a previous course. Users can create an account, log in, enter their weight, set a goal weight, and track their progress.

The app uses SQLite to store data locally. Before this enhancement, it stored users, weight records, and goals, but the weight and goal records were not fully connected to each user.

For this enhancement, I changed how the app stores and loads data so each user has their own weight history and goal.

## 2. Why did you select this artifact? What skills does it showcase? How was it improved?

I chose this app because it gave me a chance to show my database design and data management skills. It already used SQLite, but its database needed changes to handle multiple users correctly. Weight records and goals could be treated as shared data, even though each user should only see their own information.

I added a user_id field to the weight and goal tables. I also added foreign keys to connect those tables to the user’s table. These database relationships link each weight record and goal to a specific user. This helps keep the data consistent and makes it easier to manage. The relationships are Users to Weights and Users to Goals.

I changed the database methods to use the logged-in user's ID. The app now loads only that user's weight records. When a record is updated or deleted, it checks both the weight ID and the user ID. Each user can also have their own goal weight.

After a successful login, the app gets the user's database ID and passes it to the weight and goal screens. This lets those screens use the correct user's data.

I kept the SMS goal notification feature. When a user enters a weight that meets or falls below their goal, the app can send a congratulatory SMS if the user has entered a phone number and granted SMS permission.

These changes show my skills in SQLite, relational database concepts, foreign keys, CRUD operations, database queries, data validation, and Android app development.

## 3. Did you meet the course outcomes you planned to meet with this enhancement?

Yes, I met the course outcomes I planned to support with this enhancement. I used computing tools and techniques to improve the app so it can handle multiple users more reliably. This supports Course Outcome 4 through Java and SQLite integration, Course Outcome 3 through relational design and tested CRUD behavior, and Course Outcome 5 through user data isolation and validation checks.

I also worked on designing and testing software solutions. I reviewed the original database, found problems in its structure, and improved the relationships between users, weights, and goals.

I made progress on the security outcome through data isolation. Using user IDs in database queries helps prevent a user from accidentally viewing, changing, or deleting another user's information. Passwords are still stored in plaintext. Data isolation helps protect each user’s records, but it does not provide secure password storage.

The changes also improved the software design. The database methods now have clearer jobs, and the tables are better connected. I do not need major changes to my original outcome-coverage plan. This enhancement helped me show my database skills while also improving the software design and security of the app.

## 4. Reflect on the process of enhancing and modifying the artifact. What did you learn? What challenges did you face?

This enhancement helped me understand why database design matters. I learned that storing records is only part of the work. The database also needs clear relationships so the app knows which records belong to each user.

Adding user_id helped me understand foreign keys and data isolation. I also learned that database change can affect several parts of an app, including login, adding records, editing records, deleting records, and displaying data.

One challenge was that the emulator still had an older version of the database. The app initially crashed because that database did not have the new user_id column. I fixed this by updating the database version and recreating the database with the new structure. The local original enhanced project and the Enhanced_Code folder in the submitted database milestone ZIP still use DATABASE_VERSION 3; its Original_Code folder uses version 2. For the final validation of the published artifact, I used an isolated copy to check the migration from version 3 to version 4. That check was separate from the earlier database recreation.

Another challenge was passing the same user ID between the login screen, weight list, add weight screen, and goal screen. Some Intent value names did not match. I fixed the problem by making those names consistent.

I also had Java compilation errors after editing files through PowerShell. I learned that invisible characters, such as a UTF-8 BOM, can cause these errors and how to remove them.

To test the changes, I created two users. The first user added weight records and a goal. I logged out and signed in as the second user, and the first user's information was not visible. When I signed back into the first account, the original data was still there. This test confirmed that the database kept the two users' information separate. I also completed final validation of the published artifact in an isolated copy on a Pixel 7 emulator with Android 14 (API 34). All three instrumentation tests passed, including the version 3 to 4 migration check. Foreign keys were enabled, foreign_key_check returned no violations, and integrity_check returned ok. I checked independent create, read, update, and delete operations for two users. Attempts to update or delete another user’s record returned false. The two users kept separate goals, and an insert with an invalid user ID was rejected. There were no crashes during these checks.

Through this work, I learned more about SQLite, foreign keys, database relationships, CRUD operations, data isolation, debugging, and testing. I also saw how better database design can make an app more reliable and help protect user data.

## Word Copy

[Download the final Word document](documents/Enhancement%20Three%20Final%20Databases%20Jose%20Bustamante.docx)
