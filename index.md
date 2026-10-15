# Jose Bustamante

Computer Science ePortfolio

## Professional Self-Assessment

### My Growth and Career Goals

When I started the Computer Science program, I wanted to become a general software developer. Over time, my interests moved toward cybersecurity and data analysis. I enjoy understanding how systems work, finding problems, protecting information, and using data to make better decisions. The program helped me build a foundation in software design, algorithms, databases, and security. My ePortfolio shows how I used these skills to improve work I had already created.

My main capstone artifact is the Android Weight Tracker app from CS 360. I used the same app for software design and engineering, algorithms and data structures, and databases. Reviewing the original code helped me see where it needed clearer responsibilities, better data handling, and stronger user data separation. I also learned to explain what I changed and what still needs work.

### Collaboration and Decision Making

Course Outcome 1 is about building collaborative environments where people with different needs can help make decisions. In CS 340, I built the Grazioso Salvare dashboard using a Python CRUD module, MongoDB, and Dash. Its rescue filters, table, chart, and map helped me connect a stakeholder’s needs to a useful solution. I learned that a useful application starts with understanding what people need to do with their data. Clear questions, shared requirements, and readable documentation help technical and nontechnical people work toward the same goal.

In CS 499, I used instructor feedback to improve my work and explain my decisions more clearly. This was an individual project. It helped me practice listening to feedback and making changes that another person could review. My documentation and dashboard choices support shared understanding, although the capstone does not demonstrate work on a development team with diverse members.

### Professional Communication

Course Outcome 2 is about clear oral, written, and visual communication. My code review, enhancement narratives, and ePortfolio helped me explain technical work for different audiences. I learned to describe the original problem, explain a change, and connect it to a result. A reader should be able to understand why the work matters without knowing every line of code.

My journals also helped me think about how to present my work responsibly. I want employers to see real examples of my skills, while keeping private information, passwords, and API keys out of public files. I learned that clear communication includes being honest about limitations and keeping old plans separate from completed work.

### Algorithms and Computing Solutions

Course Outcome 3 is about using algorithmic principles to solve problems and understanding design trade-offs. CS 300 helped me compare data structures and algorithms and consider how a choice affects the work a program must do. That knowledge helped me review the weight-loading loop in my capstone app.

The Cursor traversal visits each returned record once, so it is O(n). In the final validated version, moving column lookups outside the loop reduces repeated work while keeping the same O(n) traversal. The earlier milestone ZIP still has these lookups inside the loop. I also made the data flow easier to follow when adding, editing, deleting, and refreshing records. I checked these actions manually. I did not run a benchmark or build an automated algorithm test suite, so I do not claim a measured performance gain. Sorting and statistics were early plans, not completed features I am presenting here.

### Tools and Practical Development

Course Outcome 4 is about using computing techniques, skills, and tools to build useful solutions. I used Java, Android Activities, helper classes, layouts, and SQLite to improve the Weight Tracker app. AuthManager handles database calls for login and account creation, ErrorHandler checks empty login fields and shows errors, and UIHelper handles the loading indicator. The Activities still manage their screens and navigation. This separation made the code easier for me to understand and maintain.

For the database enhancement, I connected weight records and goals to users and used the user ID when reading or changing data. Final validation of the published artifact used an isolated copy on a Pixel 7 emulator with Android 14, API 34. Three instrumentation tests passed, including the migration from database version 3 to 4. Foreign keys were enabled, foreign_key_check found no violations, and integrity_check returned ok. Checks also confirmed independent CRUD operations and goals for two users, rejection of an invalid user insert, and false results for cross-user updates and deletes. There were no crashes during these checks. The local original enhanced project remains at DATABASE_VERSION 3.

### Security Mindset

Course Outcome 5 is about anticipating vulnerabilities, reducing design flaws, and protecting data. CS 305 helped me understand the value of reviewing software for security problems and explaining how those problems should be addressed. It also helped me see security as part of design and testing, rather than a final step.

In the capstone app, using the user ID in queries and checking both the record ID and user ID for updates and deletes helped keep users’ data separate. Foreign keys also helped reject records that did not belong to a valid user. These are useful improvements, but passwords still remain in plaintext. Input validation is not the same as secure authentication, and password hashing was not implemented. Recognizing this limitation is part of my security mindset.

### Next Steps

Previous courses are examples of my learning across the program. They provide context for my skills and goals; they are not additional capstone artifacts. My capstone work shows how software design, algorithms, databases, communication, and security connect in one application.

I want to keep building my cybersecurity and data analysis skills after graduation. I am considering certifications such as Security+ and further training in data analysis. I also want to improve my testing skills and learn more about secure password storage. This program helped me become more careful about how I build, review, and explain software. I can now look at an existing project, find weaknesses, make useful changes, and describe the results in a clear way.

[Download my Professional Self-Assessment in Word](documents/Professional%20Self%20Assessment%20Jose%20Bustamante.docx)

## Explore the Work

My Weight Tracker application is the single capstone artifact used across all three enhancement categories. Each narrative below can be read directly on this website and also downloaded as a Word document.

- [Watch the two-part Code Review](code-review.md)
- [Compare the original and enhanced Weight Tracker artifact](artifact.md)
- [Read Enhancement One: Software Design and Engineering](enhancement-one.md)
- [Read Enhancement Two: Algorithms and Data Structures](enhancement-two.md)
- [Read Enhancement Three: Databases](enhancement-three.md)
- [Review the five course outcomes and their evidence](course-outcomes.md)
