# Weight Tracker — Original and Enhanced Artifact

Weight Tracker is an Android application written in Java. It supports account creation, login, weight records, per-user goals and optional SMS notifications. The application predates the current CS-499 enhancements; an exact original creation date is not established here.

## Original work

The archived Original_Code folder from the Milestone Four submission preserves the earlier application files. Its archive contains the original app folder as supplied; it does not include a complete standalone Gradle project. Additional baseline files preserve intermediate stages for comparison.

- [Browse the archived original application files](https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker-original)
- [Download the original application archive](artifacts/WeightTracker-original.zip)

- [Original WeightListActivity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/WeightListActivity_ORIGINAL.java)
- [DatabaseHelper before the database enhancement](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/DatabaseHelper_before_database_enhancement.java)

## Enhanced work

- [Browse the enhanced source](https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante)
- [Download the complete enhanced Android project](artifacts/WeightTracker-final.zip)
- [Implementation and validation notes](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)

The enhanced download includes the project resources, Gradle wrapper and preserved comparison files. Browsable text source is also provided; the ZIP includes the binary launcher resources and wrapper. Local SDK configuration and generated build files are excluded.

## What changed

| Area | Earlier implementation | Enhanced implementation |
| --- | --- | --- |
| Design | Editing was embedded in the list activity | A shared form handles adding and editing, including the date |
| Processing | Column indices were looked up inside the traversal and initial loading was duplicated | Indices resolve once and initial rendering occurs through onResume |
| Database | Global goals and insufficient ownership checks | Per-user goals, foreign keys and owner-scoped updates/deletes |
| Upgrade | Schema variants shared version 3; an upgrade could drop tables | Version 4 archives legacy tables and migrates records with known owners |

## Verification

Migration SQL extracted from the Java helper passed checks for three historical schema layouts, archive preservation, foreign-key consistency and user-scoped updates. Android compilation and emulator testing are pending. Password storage remains a security limitation. These limits are part of the assessment of this student artifact.
