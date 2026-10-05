# Jose Bustamante

## Computer Science ePortfolio

My professional interests are **cybersecurity and data analysis**. This CS-499 portfolio connects those interests through the design, processing, and protection of information in my Weight Tracker Android application.

## Professional Self-Assessment

[Read my Professional Self-Assessment](professional-self-assessment.md). It introduces my skills, professional goals, course outcome evidence, and the relationship between the three technical enhancements in this portfolio.

## Explore the Work

- [Code Review — both video parts and reflection](code-review.md)
- [Original and enhanced Weight Tracker artifact](artifact.md)
- [Enhancement One — Software Design and Engineering](enhancement-one.md)
- [Enhancement Two — Algorithms and Data Structures](enhancement-two.md)
- [Enhancement Three — Databases](enhancement-three.md)
- [Alignment with the five course outcomes](course-outcomes.md)

## Portfolio Status

The three required enhancement categories are represented in this portfolio. Core Weight Tracker functionality was compiled and manually tested in the Android Emulator, including login, account creation, navigation, adding, editing, deleting, and refreshing weight records, user goals, and user data separation.

The published enhanced artifact also includes a later SQLite database migration from version 3 to version 4. On October 5, 2026, that published version 4 DatabaseHelper was compiled and validated in an isolated Android test copy using a Pixel 7 emulator running Android 14/API 34. Three instrumentation tests passed. The validation confirmed migration behavior, foreign-key enforcement, database integrity, user-scoped CRUD operations, independent goals, and preserved separation between two users. No application crash was observed during migration or the tested account and CRUD flows.

The original local project folder remains on DATABASE_VERSION 3. The version 4 migration is part of the published enhanced artifact and was validated separately without modifying the original local folder.
