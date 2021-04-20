package com.yusril.kpperamalan.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.activity.tentang.TentangActivity

class SettingsPreferenceFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var LOCALIZATION: String
    private lateinit var ABOUT: String
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        LOCALIZATION=resources.getString(R.string.key_localization)
        ABOUT=resources.getString(R.string.key_about)
        val preferenceLanguage: Preference? = findPreference(LOCALIZATION)
        val preferenceAbout: Preference? = findPreference(ABOUT)
        preferenceLanguage?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
            false
        }
        preferenceAbout?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(context, TentangActivity::class.java)
            startActivity(intent)
            false
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        TODO("Not yet implemented")
    }
    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}