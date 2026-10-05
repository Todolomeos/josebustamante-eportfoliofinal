# Enhancement One — Software Design and Engineering

## Artifact and Rationale

The artifact is my Java Android Weight Tracker project. It combines account creation, login, weight history, goals, and optional SMS notifications. These features require clear separation between interface behavior, authentication, validation, and persistence.

## Evidence of Enhancement

[LoginActivity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/LoginActivity.java) delegates authentication to AuthManager, field validation to ErrorHandler, and loading-state presentation to UIHelper. The Activity focuses on navigation and coordinating those responsibilities.

[AddWeightActivity](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante/AddWeightActivity.java) uses one save path for adding and editing entries. The edit flow receives the original date and saves changes to both weight and date. Weight validation rejects empty, malformed, zero, negative, NaN, and infinite values.

## Testing Evidence

During the enhancement work, the core application flow was manually tested in the Android Emulator. Testing included account creation, login, navigation, adding weight records, editing records, deleting records, setting goals, and returning to the weight list to confirm that data refreshed correctly.

The testing confirmed that the refactoring did not remove the main application functions. Later database migration changes are documented separately under Enhancement Three.

## Reflection

These changes helped me understand separation of concerns and why responsibilities should not all remain inside one Activity. AuthManager focuses on authentication, ErrorHandler provides reusable validation and error behavior, and UIHelper supports reusable interface behavior. This reduces repeated logic and makes the application easier to understand and maintain.

The application still stores passwords in plaintext. Input validation improves data quality, but it is not a replacement for secure password storage. Password hashing would be required before treating the application as production-ready.

[Artifact overview and validation](https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md)

## Course Outcome Alignment

This enhancement supports Outcome 2 through technical communication, Outcome 4 through modular Android development, and Outcome 5 through validation and identification of remaining authentication weaknesses. The documentation and code organization also support collaborative review under Outcome 1.

[Course outcomes across the portfolio](course-outcomes.md) · [Original and enhanced artifact](artifact.md)
