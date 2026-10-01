# Professional Self-Assessment

## Introduction

I am continuing my Bachelor of Science in Computer Science at Southern New Hampshire University. I am currently in week five of the eight-week CS-499 capstone course. This self-assessment brings together my progress from the beginning of the course through the current week. It will be updated as I complete the remaining weeks.

My professional interests are cybersecurity and data analysis. I want to develop the skills to protect information, understand patterns in data, and communicate findings that support informed decisions. My portfolio connects these interests through software design, algorithms and data structures, and databases.

## Progress across weeks one through five

The course sequence has given my work a clear direction: selecting an artifact and planning enhancements, reviewing the original code, developing improvements in software design and engineering, examining algorithms and data structures, and strengthening the database. My selected artifact is the Weight Tracker Android application. Using one application across the three categories helps me see how a change in one part of a system affects the others.

The code review is presented in two video parts. It provides a foundation for explaining the original application and the reasons for improving it. Reviewing code before changing it has helped me focus on specific problems rather than describing improvements only in general terms.

The software design work separates responsibilities for authentication, input validation, and interface behavior. The proposed corrections also address an editing problem: the form asks for a date, so the saved update should include that date as well as the weight. This reinforces the importance of making the interface and the underlying behavior agree.

The algorithms work examines how the application selects and displays weight records. The revised code resolves column indices before traversing the cursor, closes the cursor reliably, and avoids loading the initial list twice. Rendering is still linear in the number of displayed records. I have learned to distinguish a concrete reduction in repeated work from an unmeasured claim of better overall performance.

The database work emphasizes relationships and ownership. Weight entries and goals belong to users, and updates and deletions check the user as well as the entry. The proposed version-four migration retains legacy tables and copies records with identifiable owners into the active schema. Records without clear ownership remain archived rather than being assigned to another person. This connects database integrity with privacy.

## Professional skills and values

Collaboration requires clear explanations, understandable code, and an honest account of what has been verified. My documentation and code review give others a way to inspect my decisions and identify areas for further improvement.

Communicating with stakeholders means explaining both the intended behavior and its limits. A technical reviewer needs evidence from the implementation, while a user needs to understand what happens to their information. I want my portfolio to make that distinction clear through accessible navigation, explanations, and source links.

Software engineering, algorithms, and databases provide a foundation for my interest in data analysis. Accurate records and predictable processing are essential before data can support useful conclusions. This artifact demonstrates data handling; it does not yet demonstrate a completed statistical analysis or a cybersecurity investigation.

My interest in cybersecurity also leads me to look for weaknesses instead of assuming that a working feature is secure. Plaintext password storage remains a limitation in this application. Identifying that limitation, restricting record operations by ownership, and examining upgrade behavior are steps toward a stronger security mindset. Further security improvements and testing are still necessary.

## How the portfolio fits together

The original code, two-part code review, enhanced source, and three enhancement narratives show the relationship between identifying a problem, proposing a change, and explaining the resulting behavior. Software design supports maintainability, algorithmic review supports more deliberate processing, and database improvements support integrity and ownership. Together, these areas provide a foundation for the cybersecurity and data analysis work I want to pursue.

At this stage, SQL migration checks have passed for three historical schema layouts. Android compilation and emulator testing remain pending. The enhanced source is available in GitHub; it should not be described as a completed, fully tested release.

## Remaining weeks: six through eight

During the remaining course weeks, I plan to refine this assessment, incorporate actual instructor feedback, complete the outstanding validation, and verify that the portfolio presents both the original and enhanced work clearly. The course instructions place the final ePortfolio submission in Module Seven. I will update this reflection through the end of the eight-week course with the work and feedback actually completed.

The final assessment will also include specific coursework examples beyond Weight Tracker once those examples are identified.
