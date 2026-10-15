<div class="artifact-showcase">
<p class="eyebrow">CS 499 · Capstone artifact · Java / Android / SQLite</p>
<h1>Weight Tracker</h1>
<p class="lead">One application. Three areas of growth: software design, algorithms, and databases.</p>
<p>Originally created in CS 360, Weight Tracker supports account creation, login, weight history, goals, and optional SMS notifications.</p>
<section aria-labelledby="comparison-title">
<h2 id="comparison-title">From the original app to the enhanced artifact</h2>
<div class="comparison-grid">
<article class="comparison-card before"><span class="stage">Before</span><h3>Original implementation</h3><ul><li>More application logic inside Activities</li><li>Column lookups repeated during record traversal</li><li>Weight records and goals not fully scoped to users</li><li>Earlier upgrades could discard stored data</li></ul><a class="text-link" href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker-original">View original source ↗</a></article>
<article class="comparison-card after"><span class="stage">After</span><h3>Enhanced implementation</h3><ul><li>Separate authentication, validation, and UI helpers</li><li>Column indexes resolved before the O(n) traversal</li><li>User-scoped records, goals, updates, and deletes</li><li>Version 4 migration preserves legacy data</li></ul><a class="text-link" href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/tree/main/artifacts/weight-tracker/app/src/main/java/com/example/weighttrackerapp_josebustamante">View enhanced source ↗</a></article>
</div></section>
<section aria-labelledby="enhancements-title"><h2 id="enhancements-title">Three enhancements, one connected artifact</h2><div class="enhancement-grid">
<article class="enhancement-card"><span class="number">01</span><h3>Software Design &amp; Engineering</h3><p>AuthManager, ErrorHandler, and UIHelper separate responsibilities. Activities manage their screens and navigation.</p><p class="skill">Separation of concerns · Maintainability</p></article>
<article class="enhancement-card"><span class="number">02</span><h3>Algorithms &amp; Data Structures</h3><p>A single Cursor traversal displays the records. Reused column indexes reduce repeated work without changing O(n).</p><p class="skill">Algorithmic reasoning · Data flow</p></article>
<article class="enhancement-card"><span class="number">03</span><h3>Databases</h3><p>User relationships and foreign keys connect records to their owners. Queries check ownership when loading and changing data.</p><p class="skill">Relational design · Data isolation</p></article>
</div></section>
<section aria-labelledby="code-title"><h2 id="code-title">Before and After</h2><p>I changed the weight-loading loop so it looks up the column indexes once before reading the records. The examples below show the original code and the final published version.</p><div class="comparison-grid code-comparison"><figure><figcaption>Before: column lookup inside the loop</figcaption><pre><code>do {
    int id = cursor.getInt(
            cursor.getColumnIndexOrThrow(
                    DatabaseHelper.COL_WEIGHT_ID
            )
    );
</code></pre></figure><figure><figcaption>After: column indexes set before the loop</figcaption><pre><code>int idIndex = cursor.getColumnIndexOrThrow(&quot;id&quot;);
int weightIndex = cursor.getColumnIndexOrThrow(&quot;weight&quot;);
int dateIndex = cursor.getColumnIndexOrThrow(&quot;date&quot;);
while (cursor.moveToNext()) {

    int id = cursor.getInt(
            idIndex
    );
</code></pre></figure></div><p>Traversal remains O(n). No benchmark or automated algorithm test suite was run, and no measured speed improvement is claimed.</p></section>
<section aria-labelledby="validation-title"><h2 id="validation-title">Testing Results</h2><ul class="testing-results"><li>Three database instrumentation tests passed.</li><li>Two users kept separate weight records and goals.</li><li>The published database migration from version 3 to 4 was checked.</li><li>Testing used a Pixel 7 emulator with Android 14 (API 34).</li></ul><div class="validation-detail"><h3>Database checks</h3><ul><li>Foreign keys enabled; foreign_key_check returned no violations</li><li>integrity_check returned ok</li><li>Cross-user update and delete attempts returned false</li><li>Invalid user inserts were rejected</li><li>No crashes observed during the tested migration and account/CRUD flows</li></ul><h3>Manual application checks</h3><p>Account creation, duplicate usernames, empty fields, valid and invalid login, navigation, add/edit/delete weights, goals, refresh, and two-user data separation.</p><p>The published version 4 artifact was validated in an isolated Android copy. The original local enhanced project remains at DATABASE_VERSION 3. Passwords remain in plaintext; input validation and data isolation do not provide secure password storage.</p><a class="text-link" href="https://github.com/Todolomeos/josebustamante-eportfoliofinal/blob/main/artifacts/weight-tracker/CAPSTONE.md">Implementation and validation notes ↗</a></div></section>
<section class="project-files" aria-labelledby="files-title"><h2 id="files-title">Project files</h2><div class="file-actions"><a class="action secondary" href="artifacts/WeightTracker-original.zip">Download Original Project</a><a class="action secondary" href="artifacts/WeightTracker-final.zip">Download Enhanced Project</a><a class="action secondary" href="code-review.html">Watch Code Review</a></div></section>
</div>
