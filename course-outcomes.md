# Course Outcome Alignment

This page explains how the Weight Tracker artifact, code review, enhancement narratives, testing, and professional self-assessment support the five CS-499 course outcomes.

| Outcome | Evidence in this portfolio | Current limits |
| --- | --- | --- |
| 1. Collaborative environments and organizational decision making | The code review, technical narratives, documented trade-offs, and use of instructor feedback show how technical decisions can be explained for review by peers, instructors, and stakeholders. | The portfolio does not claim a completed team-development project. |
| 2. Professional oral, written, and visual communication | The two-part Code Review, enhancement narratives, Professional Self-Assessment, GitHub Pages organization, and linked source evidence present the project for technical and nontechnical audiences. | The communication evidence is based on the completed portfolio materials and code review recordings. |
| 3. Algorithmic solutions and design trade-offs | WeightListActivity processes records with one cursor traversal, resolves column indices before the loop, closes the cursor reliably, and avoids the former duplicate initial load. Rendering remains O(n). The portfolio clearly states that no performance benchmark was performed. | No measured performance gain or change in asymptotic rendering complexity is claimed. |
| 4. Computing techniques, skills, and tools that deliver value | Java, Android Studio, SQLite, helper classes, user-scoped CRUD operations, lifecycle handling, validation, and database migration techniques are used to improve the Weight Tracker application. Core functionality was manually tested in the Android Emulator, and the published version 4 migration passed three Android instrumentation tests on Android 14/API 34. | The validation does not claim coverage of every Android device or every possible damaged legacy database. |
| 5. Security mindset and data privacy | Foreign keys, user ownership, user-scoped update/delete operations, input validation, permission handling, and two-user data-separation testing demonstrate attention to privacy and security. Migration tests also confirmed that invalid user IDs are rejected by foreign-key constraints and that ownerless or orphaned legacy records are not assigned to another user. | Password hashing remains a future production-level security improvement. |

Together, the code review, artifact enhancements, testing evidence, narratives, and Professional Self-Assessment provide the evidence used to support these outcomes.
