package com.example.weighttrackerapp_josebustamante.helpers;

import android.view.View;
import android.widget.ProgressBar;

// ### NEW: UIHelper manages UI-related reusable actions.
// This keeps LoginActivity clean and improves software design.
public class UIHelper {

    // ### NEW: Constructor (not used now, but useful for future UI helpers)
    public UIHelper(Object context) {
        // No context needed for now
    }

    // ### NEW: Show loading indicator
    public void showLoading(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    // ### NEW: Hide loading indicator
    public void hideLoading(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
