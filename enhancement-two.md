# Enhancement Two (Algorithms & Data Structures) Jose Bustamante

## 1. Briefly describe the artifact. What is it? When was it created?

The artifact I selected is the WeightListActivity component from my Android Weight Tracker application. I originally created this artifact in a previous course when I was learning how to manage data, display dynamic content, and build interactive mobile interfaces. The artifact loads weight entries from a database and displays them in a dynamic table, allowing users to add, edit, and delete entries. It was originally functional but lacked strong algorithmic structure, efficient data handling, and clear organization.

## 2. Justify the inclusion of the artifact in your ePortfolio. Why did you select this item? What skills do it showcase? How was it improved?

I selected this artifact because it clearly demonstrates my skills in algorithms and data structures, which is the focus of this enhancement. The artifact uses several algorithmic concepts such as:

- Iterating through database results using a cursor

- Dynamically constructing UI elements using loops

- Managing structured data through lists and table rows

- Implementing conditional logic for editing vs. inserting data

- Using structured data storage through a relational database

For this enhancement, I improved the artifact in several ways:

- Refactored the weight loading algorithm to make it clearer and more maintainable while reducing repeated work. The Cursor visits each of the n returned weight records once, so this traversal is O(n). In the final validated version, I moved the column lookups outside the loop so the app can reuse the column indexes for each record. The earlier milestone ZIP still performs these lookups inside the loop. This reduces repeated work but does not change the O(n) traversal. This describes the loop, not the total cost of the database query or drawing the screen. The app reads the current user’s records through DatabaseHelper, loops through the Cursor to display rows, and chooses to insert or update based on the editing state. Update and delete operations use the record ID and user ID; I did not measure their database query costs.

- Improved data structure usage by organizing table rows and UI components more systematically.

- Added better validation logic, improving reliability and reducing errors.

- Enhanced modularity by separating responsibilities across activities and helper classes.

- Improved update and insert algorithms to handle user IDs, editing states, and dynamic refreshes.

These improvements show my ability to analyze existing code, identify weaknesses, and apply algorithmic principles to make the solution clearer and more maintainable while reducing repeated work.

## 3. Did you meet the course outcomes you planned to meet with this enhancement? Any updates?

Yes, I met the outcomes I planned in Module One. Specifically:

- I demonstrated the ability to design and evaluate computing solutions using algorithmic principles, which is a core program outcome.

- I improved the artifact using data structures and algorithmic reasoning, showing progress toward competency in the algorithms and data structures category.

- I applied software engineering practices by refactoring code, improving readability, and enhancing maintainability.

My final work focused on the Cursor loop, clearer data handling, and update logic. Sorting and statistics were part of my early plan, but I am not claiming them as completed enhancements. This work supports my planned outcomes through algorithmic reasoning and maintainability.

## 4. Reflect on the process of enhancing and modifying the artifact. What did you learn? What challenges did you face?

Enhancing this artifact taught me how important it is to write code that is both efficient and easy to understand. I learned how small algorithmic improvements such as reorganizing loops, improving conditional logic, and structuring data flow can reduce repeated work and make the code easier to follow. I did not run a benchmark, so I cannot claim a measured speed increase.

Challenges I faced included:

- Debugging broken package structures after reorganizing the project.

- Fixing mismatched IDs and layout inconsistencies between XML and Java.

- Ensuring correct data flow between activities, especially when editing existing entries.

- Maintaining clean separation of concerns, especially when adding helper classes like AuthManager and UIHelper.

Despite these challenges, the enhancement strengthened my understanding of algorithmic design, data handling, and structured programming. It also helped me improve my debugging skills and reinforced the importance of clean, maintainable code. I manually checked adding an entry and seeing it in the list, editing an entry and seeing the new value, deleting an entry and checking that it was gone, and refreshing the list after these changes. I did not create an automated algorithm test suite. The three instrumentation tests are database checks for Enhancement Three, not tests of this algorithm.

## Word Copy

[Download the final Word document](documents/Enhancement%20Two%20Final%20Jose%20Bustamante.docx)
