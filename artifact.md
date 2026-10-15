<div class="artifact-showcase">
<p class="eyebrow">CS 499 · Capstone artifact · Java / Android / SQLite</p>
<h1>Weight Tracker</h1>
<p class="lead">One application. Three areas of growth: software design, algorithms, and databases.</p>
<p>Originally created in CS 360, Weight Tracker supports account creation, login, weight history, goals, and optional SMS notifications.</p>
<section aria-labelledby="comparison-title">
<h2 id="comparison-title">From the original app to the enhanced artifact</h2>
<h3>Original implementation</h3>
<p>In the original application, more logic remained inside the Activities. The weight-loading loop repeated column lookups for each record, and weight records and goals were not fully connected to individual users. Earlier database upgrades could also discard stored data.</p>
<p><a href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker-original">View original source</a></p>
<h3>Enhanced implementation</h3>
<p>I separated authentication, validation, and interface support into helper classes. In the final published version, column indexes are resolved before the O(n) traversal. Weight records and goals belong to individual users, and update and delete operations check ownership. The version 4 migration preserves legacy data.</p>
<p><a href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante">View enhanced source</a></p>
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
    );

    double weight = cursor.getDouble(</code></pre>
<p>The Cursor still visits each of the n returned records once, so the traversal remains O(n). I did not run a benchmark or an automated algorithm test suite, so I do not claim a measured speed improvement.</p>
</section>

<section aria-labelledby="validation-title"><h2 id="validation-title">Testing Results</h2><ul class="testing-results"><li>Three database instrumentation tests passed.</li><li>Two users kept separate weight records and goals.</li><li>The published database migration from version 3 to 4 was checked.</li><li>Testing used a Pixel 7 emulator with Android 14 (API 34).</li></ul><div class="validation-detail"><h3>Database checks</h3><ul><li>Foreign keys enabled; foreign_key_check returned no violations</li><li>integrity_check returned ok</li><li>Cross-user update and delete attempts returned false</li><li>Invalid user inserts were rejected</li><li>No crashes observed during the tested migration and account/CRUD flows</li></ul><h3>Manual application checks</h3><p>Account creation, duplicate usernames, empty fields, valid and invalid login, navigation, add/edit/delete weights, goals, refresh, and two-user data separation.</p><p>The published version 4 artifact was validated in an isolated Android copy. The original local enhanced project remains at DATABASE_VERSION 3. Passwords remain in plaintext; input validation and data isolation do not provide secure password storage.</p><a class="text-link" href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md">Implementation and validation notes ↗</a></div></section>
<section class="project-files" aria-labelledby="files-title"><h2 id="files-title">Project files</h2><div class="file-actions"><a class="action secondary" href="artifacts/WeightTracker-original.zip">Download Original Project</a><a class="action secondary" href="artifacts/WeightTracker-final.zip">Download Enhanced Project</a><a class="action secondary" href="code-review.html">Watch Code Review</a></div></section>
</div>
