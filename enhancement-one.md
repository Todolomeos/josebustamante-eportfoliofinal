# Enhancement One — Software Design and Engineering

## Artifact and rationale

The artifact is my Java Android Weight Tracker project. It combines account creation, login, weight history, goals and optional SMS notifications. These features require clear boundaries between interface behavior, authentication and persistence.

## Evidence of enhancement

[LoginActivity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/LoginActivity.java) delegates authentication to AuthManager, field validation to ErrorHandler, and loading-state presentation to UIHelper. The activity handles navigation and coordinates those responsibilities.

[AddWeightActivity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/AddWeightActivity.java) uses saveWeight for both new and existing entries. The revised edit flow receives the original date and saves changes to both weight and date. Weight and goal validation reject zero, negative and non-finite values as well as malformed numeric input.

## Reflection and limits

These changes make the responsibilities and edit behavior easier to inspect. They also show why an interface must match what the database actually saves: requiring a date while updating only the weight creates misleading behavior. Authentication still stores plaintext passwords, and complete device testing remains pending; the artifact should not be presented as production-ready.

[Artifact overview and validation](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)


## Course outcome alignment

This enhancement supports Outcome 2 through clear technical explanations, Outcome 4 through modular Android development, and Outcome 5 through input validation and identifying remaining authentication weaknesses. Documentation supports collaborative review under Outcome 1, but it is not evidence of a completed team project.

[Course outcomes across the portfolio](course-outcomes.md) · [Original and enhanced artifact](artifact.md)
