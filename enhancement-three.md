# Enhancement Three — Databases

## Artifact and Rationale

The artifact is DatabaseHelper. The [earlier helper](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/DatabaseHelper_before_database_enhancement.java) did not fully enforce ownership relationships between users, weight records, and goals.

## Evidence of Enhancement

The [revised helper](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/DatabaseHelper.java) enables foreign keys, requires important fields with NOT NULL, connects weights and goals to users through user_id, and limits each user to one goal.

Update and delete operations include both entry ID and user ID. Parameterized selections keep user values separate from SQL syntax.

The current repository also contains a version 4 migration. It preserves complete legacy tables in *_legacy_v4 tables and copies records with known valid owners into the active schema. Records without clear ownership remain archived instead of being assigned to another user.

## Validation Evidence

The user-owned database design was compiled and manually tested in the Android Emulator during the enhancement work. Two different user accounts were used. User A created weight and goal data. After logging in as User B, User A's information was not visible. Returning to User A showed that the original data was still available. This confirmed user data separation in the tested version.

After the later version 4 migration was added to the repository, migration SQL extracted from DatabaseHelper was checked against three earlier schema layouts. Those checks covered copied records, archived records, foreign-key consistency, and user-scoped updates.

A dedicated Android version 3 to version 4 upgrade test remains the final migration-specific validation step. This distinction avoids claiming that a migration-specific emulator test has already been completed when it has not.

## Reflection

This enhancement taught me that database work is not only about inserting and reading records. Ownership, relationships, integrity, migration behavior, and privacy all affect the quality of a database design.

The application still stores passwords in plaintext, so secure password hashing remains an important future improvement before production use.

[Artifact overview and validation](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)

## Course Outcome Alignment

This enhancement supports Outcome 4 through relational database and migration techniques, Outcome 5 through ownership checks and data separation, and Outcome 3 through consideration of migration and retention trade-offs.

[Course outcomes across the portfolio](course-outcomes.md) · [Original and enhanced artifact](artifact.md)
