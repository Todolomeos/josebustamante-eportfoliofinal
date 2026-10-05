# Weight Tracker — Original and Enhanced Artifact

Weight Tracker is an Android application written in Java. It supports account creation, login, weight records, per-user goals, and optional SMS notifications.

## Original Work

The archived original files preserve the application before the CS-499 enhancements.

- [Browse the archived original application files](https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker-original)
- [Download the original application archive](artifacts/WeightTracker-original.zip)
- [Original WeightListActivity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/WeightListActivity_ORIGINAL.java)
- [DatabaseHelper before the database enhancement](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/DatabaseHelper_before_database_enhancement.java)

## Enhanced Work

- [Browse the enhanced source](https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante)
- [Download the complete enhanced Android project](artifacts/WeightTracker-final.zip)
- [Implementation and validation notes](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)

The enhanced project includes the application resources, Java source, Gradle files, and preserved comparison files.

## What Changed

| Area | Earlier implementation | Enhanced implementation |
| --- | --- | --- |
| Software design | More responsibilities remained directly inside Activities | Authentication, validation, and interface support were separated into helper classes, and add/edit behavior was organized more clearly |
| Algorithms and processing | Repeated work occurred during list traversal and initial loading could happen more than once | Column indices are resolved before traversal, the Cursor is closed reliably, and the list refreshes through onResume |
| Database | User ownership and goal relationships were not fully enforced | user_id relationships, foreign keys, per-user goals, and owner-scoped updates/deletes protect data separation |
| Upgrade handling | Earlier database upgrades could lose data | The current version 4 migration preserves legacy tables and migrates records with known owners |

## Verification

Core Weight Tracker functionality was compiled and manually tested in the Android Emulator during the enhancement work. Testing included account creation, login, navigation, adding, editing, and deleting weights, goal behavior, list refresh, and two-user data separation.

The current repository also includes a later version 4 database migration. Its migration SQL has been checked against three earlier schema layouts for archive preservation, foreign-key consistency, and user-scoped operations.

A dedicated Android version 3 to version 4 upgrade test remains the final migration-specific validation item before the Module Seven submission. Password storage remains a known security limitation and is not represented as secure hashing.
