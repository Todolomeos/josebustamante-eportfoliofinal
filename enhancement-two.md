# Enhancement Two — Algorithms and Data Structures

## Artifact and Rationale

The artifact is WeightListActivity, which reads a SQLite Cursor and creates a table row for each returned weight record. The preserved [original activity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/capstone/baseline/WeightListActivity_ORIGINAL.java) queried all weights and contained inline editing logic.

## Evidence of Enhancement

The [revised activity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/WeightListActivity.java) requests only the current user's records, resolves the required column indices before traversal, and closes the Cursor in a finally block.

Editing moves to AddWeightActivity with the entry ID, user ID, weight, and date. Initial rendering occurs through onResume, avoiding the former duplicate load between onCreate and onResume.

## Algorithm and Complexity

The list processes each returned weight record once. If there are n records, rendering the complete list requires one traversal, so the processing is O(n).

Moving column lookups outside the loop reduces repeated work but does not change the O(n) complexity. Filtering by user reduces which records are returned to the application, but no database performance benchmark was performed. For that reason, this portfolio does not claim a measured speed improvement.

Insert, update, and delete operations are handled through database methods. Update and delete operations use both the weight ID and user ID so the intended user's record is changed.

## Testing Evidence

The WeightListActivity flow was manually tested in the Android Emulator during the enhancement work. I added multiple weight records and confirmed that they appeared in the list. I edited an existing record and confirmed that the updated information appeared after returning to the list. I deleted a record and confirmed that it was removed. Repeated add and edit actions also confirmed that onResume refreshed the displayed records.

For this Algorithms and Data Structures enhancement specifically, no automated algorithm test suite or formal performance benchmark was run, so no automated pass/fail count or measured performance gain is claimed. The three instrumentation tests described in Enhancement Three were later created for database migration and user-scoped database validation; they are separate from the algorithm-performance evidence presented here.

## Reflection

This enhancement helped me understand that algorithmic improvement is not only about changing Big-O notation. Data selection, repeated work inside a loop, Cursor lifetime, Activity lifecycle, and record ownership also affect how clearly and reliably an application processes data.

Database access still runs on the UI thread and the table creates every visible row. Background queries and pagination would be reasonable future improvements for a larger dataset.

[Artifact overview and validation](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)

## Course Outcome Alignment

This enhancement supports Outcome 3 by evaluating traversal cost and design trade-offs, Outcome 4 through Cursor and lifecycle handling, and Outcome 2 through a clear technical explanation of what changed and what was not measured.

[Course outcomes across the portfolio](course-outcomes.md) · [Original and enhanced artifact](artifact.md)
