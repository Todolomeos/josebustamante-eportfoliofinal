<div class="artifact-showcase">
<p class="eyebrow">CS 499 · Capstone artifact · Java / Android / SQLite</p>
<h1>Weight Tracker</h1>
<p class="lead">One application. Three areas of growth: software design, algorithms, and databases.</p>
<p>Originally created in CS 360, Weight Tracker supports account creation, login, weight history, goals, and optional SMS notifications.</p>
<section aria-labelledby="comparison-title">
<h2 id="comparison-title">From the original app to the enhanced artifact</h2>
<h3>Original implementation</h3>
<p>The original Weight Tracker was a working Android application from CS 360. It gave me experience connecting screens, accepting user input, and storing information in SQLite. When I reviewed it for the capstone, I found that the code needed clearer responsibilities and stronger connections between users and their records. Several tasks were handled directly inside the Activities, and the database did not fully separate each user’s weights and goals. This version is included so the starting point of my work can be compared with the changes that followed.</p>
<p><a href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker-original">View original source</a> · <a href="artifacts/WeightTracker-original.zip">Original project ZIP</a></p>
<h3>Enhanced implementation</h3>
<p>I improved the same application across the three capstone categories. The enhanced version organizes responsibilities through helper classes, makes record processing easier to follow, and connects weight history and goals to individual users. These changes required me to consider how login, navigation, data entry, and database operations work together. The published artifact also includes a version 4 migration that was validated separately from the original local enhanced project. This version shows the completed changes, while the original remains available for comparison.</p>
<p><a href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante">View enhanced source</a> · <a href="artifacts/WeightTracker-final.zip">Enhanced project ZIP</a></p>
</section>
<section aria-labelledby="enhancements-title"><h2 id="enhancements-title">Three enhancements, one connected artifact</h2><h3>Software Design and Engineering</h3>
<p>I used AuthManager, ErrorHandler, and UIHelper to separate responsibilities. The Activities manage their screens and navigation. This helped me understand separation of concerns and made the code easier to maintain.</p>
<h3>Algorithms and Data Structures</h3>
<p>The application reads the records in one Cursor traversal. In the final published version, column indexes are reused during the loop to reduce repeated work. The traversal remains O(n). This work helped me explain the processing steps and follow the data flow more clearly.</p>
<h3>Databases</h3>
<p>I connected weight records and goals to their users through user relationships and foreign keys. Database queries check ownership when loading or changing information. These changes helped me apply relational database design and keep each user's data separate.</p>
</section>
<section aria-labelledby="code-title">
<h2 id="code-title">Before and After</h2>
<h3>Before</h3>
<p>In the original weight-loading loop, I used getColumnIndexOrThrow inside the loop to find the ID, weight, and date columns for each record. This meant the application repeated the same column lookups as it read the records.</p>
<pre style="background:transparent;color:#172c3e;border:0;border-radius:0;white-space:pre-wrap;overflow-wrap:anywhere;font:13px/1.7 Consolas,monospace;padding:0;"><code>int id = cursor.getInt(
        cursor.getColumnIndexOrThrow(
                DatabaseHelper.COL_WEIGHT_ID
        )
);</code></pre>
<h3>After</h3>
<p>In the final published version, I moved those column lookups before the loop and stored the indexes in idIndex, weightIndex, and dateIndex. The loop reuses these indexes when it reads each record. This reduces repeated work and makes the processing easier to follow.</p>
<pre style="background:transparent;color:#172c3e;border:0;border-radius:0;white-space:pre-wrap;overflow-wrap:anywhere;font:13px/1.7 Consolas,monospace;padding:0;"><code>int idIndex = cursor.getColumnIndexOrThrow(&quot;id&quot;);
int weightIndex = cursor.getColumnIndexOrThrow(&quot;weight&quot;);
int dateIndex = cursor.getColumnIndexOrThrow(&quot;date&quot;);
while (cursor.moveToNext()) {

    int id = cursor.getInt(
            idIndex
    );</code></pre>
<p>The Cursor still visits each of the n returned records once, so the traversal remains O(n). I did not run a benchmark or an automated algorithm test suite, so I do not claim a measured speed improvement.</p>
</section>

<section aria-labelledby="validation-title"><h2 id="validation-title">Testing Results</h2><ul class="testing-results"><li>Three database instrumentation tests passed.</li><li>Two users kept separate weight records and goals.</li><li>The published database migration from version 3 to 4 was checked.</li><li>Testing used a Pixel 7 emulator with Android 14 (API 34).</li></ul><div class="validation-detail"><h3>Database checks</h3><ul><li>Foreign keys enabled; foreign_key_check returned no violations</li><li>integrity_check returned ok</li><li>Cross-user update and delete attempts returned false</li><li>Invalid user inserts were rejected</li><li>No crashes observed during the tested migration and account/CRUD flows</li></ul><h3>Manual application checks</h3><p>Account creation, duplicate usernames, empty fields, valid and invalid login, navigation, add/edit/delete weights, goals, refresh, and two-user data separation.</p><p>The published version 4 artifact was validated in an isolated Android copy. The original local enhanced project remains at DATABASE_VERSION 3. Passwords remain in plaintext; input validation and data isolation do not provide secure password storage.</p><a class="text-link" href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md">Implementation and validation notes ↗</a></div></section>
<section aria-labelledby="learning-title">
<h2 id="learning-title">What I Learned from This Project</h2>
<p>Working on this application helped me become more patient and careful when making changes. A problem in one part of the app could affect another screen or a database operation. I learned to trace the data through the application, check the names used between Activities, and understand the cause of an error before changing the code.</p>
<p>I also learned to separate my original plans from the work I actually completed. Some ideas changed as I reviewed the code and applied instructor feedback. Explaining those decisions helped me understand the project better and made my documentation more accurate. The experience showed me why developers need to be clear about what was tested, what improved, and what still needs attention.</p>
<p>This artifact represents my growth from building a working application to reviewing and improving an existing one. I am still developing my skills, but I now have a better understanding of how to organize code, work with user data, and explain technical choices. These are habits I want to keep building as I begin my career in computer science.</p>
</section>
</div>
