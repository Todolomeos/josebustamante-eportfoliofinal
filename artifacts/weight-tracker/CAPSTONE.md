# CS-499 Weight Tracker enhancements

The Android source in this repository is based on WeightTrackerApp_JoseBustamante2. The original launch-plan README is preserved.

## Software design and engineering
LoginActivity delegates authentication to AuthManager, validation to ErrorHandler and loading-state presentation to UIHelper. AddWeightActivity uses a shared save path for adding and editing entries. The revised edit flow carries the original date into the form and updates both weight and date, scoped to the current user. Weight and goal input must be positive and finite.

## Algorithms and data structures
WeightListActivity traverses the query cursor once to render the current user's entries. Column indices are resolved before traversal, the cursor closes in a finally block, and the initial list loads through onResume rather than both onCreate and onResume. Rendering remains linear in the number of displayed rows; no benchmark or improvement in asymptotic complexity is claimed. Database access still runs on the UI thread and the table renders every row, so pagination and background queries remain future work.

## Databases
The schema relates weights and goals to users, enables foreign-key enforcement and limits each user to one goal. Updates and deletes check both entry ID and user ID. Version 4 replaces the former destructive upgrade with a transactional migration: complete old tables remain in *_legacy_v4 tables, and records with identifiable valid owners are copied to the active schema. Ownerless weights and global goals remain archived, without guessing ownership. Archived health records still require a deliberate recovery and retention policy.

## Validation and limitations
The migration SQL extracted directly from DatabaseHelper.java passed Python SQLite checks for a global-goal schema, an owned-goal schema and an older schema without weight ownership. The checks verified preserved archives, active records, foreign-key consistency and user-scoped updates. These checks do not replace Android instrumentation or device testing.

The Android build was attempted but is not yet verified in this restricted environment. Passwords remain stored as plaintext; this student project is not ready to handle production health information. SMS behavior and the complete account/CRUD flow require device or emulator validation before release.

## Open locally
Open this folder in Android Studio and configure the local Android SDK. Run assembleDebug and test account creation, login, adding/editing/deleting weights, per-user goals and SMS permission behavior. Verify an upgrade using a populated version-3 database in addition to a clean install.
