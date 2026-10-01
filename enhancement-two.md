# Enhancement Two — Algorithms and Data Structures

## Artifact and rationale

The artifact is WeightListActivity, which reads a SQLite Cursor and creates a table row for each returned weight record. The preserved [original activity](https://github.com/Todolomeos/Weight-Tracking-/blob/e2fe0379d679287ae36a1100b8172018793bd507/capstone/baseline/WeightListActivity_ORIGINAL.java) queried all weights and contained inline editing logic.

## Evidence of enhancement

The [revised activity](https://github.com/Todolomeos/Weight-Tracking-/blob/e2fe0379d679287ae36a1100b8172018793bd507/app/src/main/java/com/example/weighttrackerapp_josebustamante/WeightListActivity.java) requests only the current user's records, resolves the three column indices before traversal, and closes the cursor in a finally block. Editing moves to AddWeightActivity with the entry ID, user ID, weight and date. Initial rendering occurs through onResume, avoiding the former onCreate/onResume duplicate load.

## Complexity and reflection

Rendering remains O(n) in the number of returned rows. Moving column lookups out of the loop reduces repeated work without changing this complexity. Filtering narrows which records are displayed, but it does not prove that the underlying database query is faster. No benchmark has been performed. Database access still runs on the UI thread and the table constructs every row; background queries and pagination are future improvements.

This enhancement illustrates how data selection, cursor lifetime and activity lifecycle affect a traversal beyond its loop syntax.

[Artifact overview and validation](https://github.com/Todolomeos/Weight-Tracking-/blob/e2fe0379d679287ae36a1100b8172018793bd507/CAPSTONE.md)
