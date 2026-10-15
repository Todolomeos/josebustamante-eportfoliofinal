# Code Review

## Written Reflection

Reviewing my original Weight Tracker application helped me understand that working code can still need improvement. The app already allowed users to create accounts, log in, enter weights, and set goals. However, I needed to look more closely at how the code was organized, how records were processed, and how the database connected information to each user. The review gave me a starting point for planning changes across software design and engineering, algorithms and data structures, and databases.

For software design, I focused on the responsibilities inside the Activities. When screen behavior, validation, and authentication are handled together, it becomes harder to follow the code and make changes safely. This guided my decision to use AuthManager, ErrorHandler, and UIHelper. Separating these responsibilities helped me understand which part of the application should handle each task. The Activities still manage their screens and navigation, while the helpers support specific operations. This was an important lesson in maintainability, because a change should be understandable to someone reviewing the project later.

For algorithms and data structures, I examined how WeightListActivity reads and displays weight records. The Cursor visits the returned records one at a time, so processing n records requires an O(n) traversal. In the final published version, the column indexes are found before the loop and reused as each record is read. This reduces repeated work without changing the traversal's complexity. My early plan included sorting and statistics, but those are not completed features I am claiming. I also did not run a performance benchmark or an automated algorithm test suite. Learning to explain these limits helped me make more accurate statements about my work.

For databases, I focused on ownership of weight records and goals. Storing information was not enough; the application also needed to know which user owned each record. The enhancement connected weights and goals to users and used the user ID when loading or changing data. Checking both the record ID and user ID for updates and deletes helped keep accounts separate. This also helped me understand the difference between data isolation and authentication security. Passwords remain in plaintext, so the project still needs secure password storage before a professional release.

Testing helped me connect the planned changes to actual results. Manual checks covered account creation, login, navigation, adding, editing, and deleting weights, goals, and list refresh. The two-user test checked whether each account kept its own information. Later, the published version 4 database migration was checked separately in an isolated Android copy, where three database instrumentation tests passed. Those later results are evidence from the enhancement work, rather than tests completed during the original code review. Keeping that distinction clear shows how the project developed from a plan into implemented and checked changes.

The review also helped me practice explaining technical decisions to other people. A useful review needs to describe the existing behavior, identify a specific problem, and explain why a proposed change is appropriate. Instructor feedback encouraged me to give more detail about helper responsibilities, complexity, testing, and security limitations. Applying that feedback made my explanations more precise. Although this was an individual project, readable code, comments, and clear reasoning give another developer a better starting point for discussing or maintaining the application.

The main lesson I took from this process was to review more than whether a feature works. I now pay closer attention to how responsibilities are divided, how data moves through the application, what testing supports a claim, and what problems remain. The code review helped connect all three enhancements to the same Weight Tracker artifact and gave me a clearer way to explain my growth throughout the capstone.

## Code Review Recordings

My CS-499 code review consists of two recordings, presented in their original order.

### Part 1 — 19 minutes, 11 seconds

<iframe style="width:100%;max-width:520px;margin:16px 0;" src="https://drive.google.com/file/d/1buxAYtySBmCxsf1Ah_nwqcC5SeZ9Mwkb/preview" title="Code Review Part 1" allow="fullscreen" allowfullscreen></iframe>

[Code Review Part 1](https://drive.google.com/file/d/1buxAYtySBmCxsf1Ah_nwqcC5SeZ9Mwkb/view)

### Part 2 — 1 minute, 47 seconds

<iframe style="width:100%;max-width:520px;margin:16px 0;" src="https://drive.google.com/file/d/148FaIsaezfK0jaH2spNcbmbRKBW0CI1P/preview" title="Code Review Part 2" allow="fullscreen" allowfullscreen></iframe>

[Code Review Part 2](https://drive.google.com/file/d/148FaIsaezfK0jaH2spNcbmbRKBW0CI1P/view)

[Original and enhanced code](artifact.md) · [Course outcome alignment](course-outcomes.md)
