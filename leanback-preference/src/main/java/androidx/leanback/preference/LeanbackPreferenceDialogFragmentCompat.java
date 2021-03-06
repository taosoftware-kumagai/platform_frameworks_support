/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.leanback.preference;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;

/**
 * A fragment that shows {@link DialogPreference}, for example {@link
 * androidx.preference.ListPreference} or {@link androidx.preference.MultiSelectListPreference}.
 */
public class LeanbackPreferenceDialogFragmentCompat extends Fragment {

    public static final String ARG_KEY = "key";

    private DialogPreference mPreference;

    public LeanbackPreferenceDialogFragmentCompat() {
        if (Build.VERSION.SDK_INT >= 21) {
            LeanbackPreferenceFragmentTransitionHelperApi21.addTransitions(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Fragment rawFragment = getTargetFragment();
        if (!(rawFragment instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment " + rawFragment
                    + " must implement TargetFragment interface");
        }
    }

    /**
     * The {@link DialogPreference} that this dialog is showing.
     * @return The {@link DialogPreference} that this dialog is showing.
     */
    public DialogPreference getPreference() {
        if (mPreference == null) {
            final String key = getArguments().getString(ARG_KEY);
            final DialogPreference.TargetFragment fragment =
                    (DialogPreference.TargetFragment) getTargetFragment();
            mPreference = fragment.findPreference(key);
        }
        return mPreference;
    }
}
