# Enhancement Three — Databases

## Artifact and rationale

The artifact is DatabaseHelper. The [earlier helper](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/DatabaseHelper_before_database_enhancement.java) stored a global goal and restricted weight updates and deletions by entry ID alone. Its schema lacked enforced relationships between users and their records.

## Evidence of enhancement

The [revised helper](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/DatabaseHelper.java) enables foreign keys, requires key data with NOT NULL, associates weights and goals with users, and limits each user to one goal. Update and delete predicates include both entry ID and user ID. Parameterized selections keep user values separate from SQL syntax.

Database version 4 handles the fact that two earlier schema variants shared version 3. SQLiteOpenHelper performs the upgrade transactionally. The migration archives complete legacy tables and copies records with known valid owners into the active schema. Ownerless weights and global goals remain archived rather than being assigned to a guessed user. This retains their data, but archived records are not shown in the app and require a deliberate recovery process.

## Validation and reflection

Migration SQL extracted from the Java helper passed Python SQLite checks for three earlier schema layouts. The checks covered copied records, archived records, foreign-key consistency and user-scoped updates. Android instrumentation and emulator upgrade testing remain pending.

This enhancement demonstrates that database work includes ownership, integrity and migration behavior, not only successful insertion. The archived data and plaintext passwords also require further privacy and security work before production use.

[Artifact overview and validation](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)


## Course outcome alignment

This enhancement supports Outcome 4 through relational integrity and migration techniques, Outcome 5 through ownership checks and preserving records without guessing ownership, and Outcome 3 through considering migration and retention trade-offs. Android upgrade testing and password security remain incomplete.

[Course outcomes across the portfolio](course-outcomes.md) · [Original and enhanced artifact](artifact.md)
