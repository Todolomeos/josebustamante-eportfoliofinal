# Enhancement Three — Databases

## Artifact and Rationale

The artifact is DatabaseHelper. The [earlier helper](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/DatabaseHelper_before_database_enhancement.java) did not fully enforce ownership relationships between users, weight records, and goals.

## Evidence of Enhancement

The [revised helper](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/DatabaseHelper.java) enables foreign keys, requires important fields with NOT NULL, connects weights and goals to users through user_id, and limits each user to one goal.

Update and delete operations include both entry ID and user ID. Parameterized selections keep user values separate from SQL syntax.

The published enhanced artifact also contains a version 4 migration. It preserves complete legacy tables in *_legacy_v4 tables and copies records with known valid owners into the active schema. Records without clear ownership remain archived instead of being assigned to another user.

## Validation Evidence

The user-owned database design was manually tested in the Android Emulator during the enhancement work. Two different user accounts were used. User A created weight and goal data. After logging in as User B, User A's information was not visible. Returning to User A showed that the original data was still available.

The later version 4 migration was also validated separately on October 5, 2026. The published DatabaseHelper was compiled without editing its migration logic in an isolated Android test copy. The validation used a Pixel 7 emulator running Android 14/API 34.

Three Android instrumentation tests passed:

- An exact version 3 schema preserved two users, four weight records, and two user goals while archiving the original tables.
- A legacy ownership scenario kept valid user-owned records active while leaving ownerless and orphaned records only in the legacy archive.
- A global legacy schema without user ownership kept global weight and goal data in the legacy archive instead of assigning it to a user.

The tests also confirmed:

- database reopening at version 4
- foreign-key enforcement enabled
- foreign_key_check returned no violations
- integrity_check returned ok
- add, edit, and delete operations worked independently for both users
- attempts to edit or delete another user's record returned false
- separate goals persisted for both users
- inserts for a nonexistent user were rejected by the foreign-key constraint
- no application crash occurred during migration, login, list display, add, edit, delete, or goal operations

The original local project folder remains at DATABASE_VERSION 3. The version 4 validation was performed against the published enhanced artifact in an isolated copy so the original folder was not modified.

## Reflection

This enhancement taught me that database work is not only about inserting and reading records. Ownership, relationships, integrity, migration behavior, and privacy all affect the quality of a database design.

The application still stores passwords in plaintext, so secure password hashing remains an important future improvement before production use.

[Artifact overview and validation](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)

## Course Outcome Alignment

This enhancement supports Outcome 4 through relational database and migration techniques, Outcome 5 through ownership checks, foreign-key enforcement, and data separation, and Outcome 3 through consideration of migration and retention trade-offs.

[Course outcomes across the portfolio](course-outcomes.md) · [Original and enhanced artifact](artifact.md)
