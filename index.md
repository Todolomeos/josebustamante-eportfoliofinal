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

### Professional Growth and Career Goals

My coursework helped me understand the kind of work I want to do and the habits I want to bring to a workplace. I am interested in cybersecurity and data analysis because I enjoy finding problems, understanding information, and thinking about how to protect it. Software development gives me a foundation for both areas. Knowing how an application is built helps me ask better questions about how it uses data and where it may fail.

In CS 340, my Grazioso Salvare dashboard connected a Python CRUD module and MongoDB to a table, chart, map, and rescue filters. That project helped me see how technical work can support a person's decisions. The goal was not just to show data. It was to help someone find useful information without searching through every record by hand. This experience strengthened my interest in work that connects data, clear communication, and practical needs.

CS 300 helped me think more carefully about the steps a program takes to solve a problem. CS 305 helped me look for security concerns and explain why they matter. Together with my other coursework, these experiences changed how I approach a project. I try to understand the problem first, review the existing work, make a clear change, and check the result. When something does not work, I need to understand the cause instead of only making the error disappear.

One strength I am developing is explaining my work honestly. I can describe what I changed, why I changed it, and what evidence supports the result. I also recognize when a feature still needs improvement. My Weight Tracker project taught me that a working application can still have security limitations, and that a clearer loop does not automatically prove a speed increase. Being precise about these differences is a habit I want to bring to my professional work.

As a new graduate, I want to contribute through careful problem-solving, readable code, and a willingness to learn from feedback. I want to understand the needs of the people using a system and communicate clearly with the people maintaining it. I still have more to learn, especially about secure password storage, automated testing, and data analysis. I am considering Security+ and further study in these areas as part of my next steps.

This portfolio brings my learning together through one capstone artifact. The three Weight Tracker enhancements show how software design, algorithms, databases, and security affect each other. The code review and narratives explain my decisions and what I learned along the way. I am finishing the program with a stronger foundation, clearer career goals, and work I can use to explain what I am ready to contribute and where I want to grow.

[Professional Self-Assessment Word document](documents/Professional%20Self%20Assessment%20Jose%20Bustamante.docx)
