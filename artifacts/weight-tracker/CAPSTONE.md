# CS-499 Weight Tracker Enhancements

The Android source in this repository is based on WeightTrackerApp_JoseBustamante2.

## Software Design and Engineering

LoginActivity delegates authentication to AuthManager, validation to ErrorHandler, and loading-state presentation to UIHelper. AddWeightActivity uses a shared save path for adding and editing entries. The revised edit flow carries the original date into the form and updates both weight and date, scoped to the current user. Weight and goal input must be positive and finite.

## Algorithms and Data Structures

WeightListActivity traverses the query Cursor once to render the current user's entries. Column indices are resolved before traversal, the Cursor closes in a finally block, and the initial list loads through onResume rather than both onCreate and onResume.

Rendering remains O(n) in the number of displayed rows. No measured performance benchmark or improvement in asymptotic complexity is claimed. Database access still runs on the UI thread and the table renders every row, so pagination and background queries remain reasonable future improvements.

## Databases

The schema relates weights and goals to users, enables foreign-key enforcement, and limits each user to one goal. Updates and deletes check both entry ID and user ID.

The published version 4 DatabaseHelper replaces the earlier destructive upgrade behavior with a migration that preserves complete old tables in *_legacy_v4 tables. Records with identifiable valid owners are copied into the active schema. Ownerless, orphaned, and global legacy records remain archived instead of being assigned to another user.

## Android Validation

On October 5, 2026, the published version 4 DatabaseHelper was validated in an isolated Android test copy without changing its migration logic.

Environment:

- Pixel 7 Android Emulator
- Android 14 / API 34
- application and test APKs installed successfully
- published version 4 DatabaseHelper compiled successfully

Three Android instrumentation tests passed.

The validation covered:

1. An exact version 3 schema with two users, four weights, and two user goals.
2. A synthetic legacy ownership scenario containing valid, ownerless, and orphaned records.
3. A synthetic global legacy schema without user ownership columns.

The tests confirmed:

- successful opening and reopening at database version 4
- preservation of both user IDs and authentication
- foreign-key enforcement enabled
- foreign_key_check returned no violations
- integrity_check returned ok
- add, edit, and delete operations worked for both users
- another user's record could not be edited or deleted
- separate goals persisted for both users
- inserts for nonexistent user ID 999 were rejected by the foreign-key constraint
- ownerless, orphaned, and global legacy records remained archived instead of being assigned to another user

The application was also opened through LoginActivity and the account/list flow was checked through the emulator UI. Login, list display, add, edit, delete, and goal operations worked for both users, and no application crash was observed during the tested flows.

The original local project folder remains on DATABASE_VERSION 3. The version 4 migration belongs to the published enhanced artifact and was validated separately so the original folder remained unchanged.

## Limitations

The validation applies to the published DatabaseHelper and the tested scenarios on Android 14/API 34. It does not claim coverage of every Android device or every possible damaged database.

Passwords are still stored as plaintext. This student project should not be represented as production-ready secure authentication until password storage is improved.
