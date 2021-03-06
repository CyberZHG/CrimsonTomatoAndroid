package zhaohg.crimson.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import zhaohg.crimson.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Setting setting = Setting.getInstance();
        setting.init(this);

        // Init period setting.
        final SeekBar seekBarPeriod = (SeekBar) this.findViewById(R.id.seek_bar_period);
        final TextView textViewPeriodNum = (TextView) this.findViewById(R.id.text_view_period_num);
        seekBarPeriod.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    progress = 1;
                }
                textViewPeriodNum.setText(progress + getString(R.string.setting_period_num_suffix));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress == 0) {
                    progress = 1;
                }
                setting.setPeriod(progress);
            }
        });
        seekBarPeriod.setProgress(setting.getPeriod());

        final SeekBar seekBarSuiteNum = (SeekBar) this.findViewById(R.id.seek_bar_suite_num);
        final TextView textViewSuiteNum = (TextView) this.findViewById(R.id.text_view_suite_num);
        seekBarSuiteNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    progress = 1;
                }
                textViewSuiteNum.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress == 0) {
                    progress = 1;
                }
                setting.setSuiteNum(progress);
            }
        });
        seekBarSuiteNum.setProgress(setting.getSuiteNum());

        final SeekBar seekShortBreak = (SeekBar) this.findViewById(R.id.seek_bar_short_break);
        final TextView textViewShortBreak = (TextView) this.findViewById(R.id.text_view_short_break);
        seekShortBreak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    progress = 1;
                }
                textViewShortBreak.setText(progress + getString(R.string.setting_period_num_suffix));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress == 0) {
                    progress = 1;
                }
                setting.setShortBreak(progress);
            }
        });
        seekShortBreak.setProgress(setting.getShortBreak());

        final SeekBar seekLongBreak = (SeekBar) this.findViewById(R.id.seek_bar_long_break);
        final TextView textViewLongBreak = (TextView) this.findViewById(R.id.text_view_long_break);
        seekLongBreak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    progress = 1;
                }
                textViewLongBreak.setText(progress + getString(R.string.setting_period_num_suffix));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress == 0) {
                    progress = 1;
                }
                setting.setLongBreak(progress);
            }
        });
        seekLongBreak.setProgress(setting.getLongBreak());

        // Init vibrate setting.
        final CheckBox checkBoxVibrate = (CheckBox) this.findViewById(R.id.check_box_vibrate);
        checkBoxVibrate.setChecked(setting.isVibrate());
        checkBoxVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setVibrate(isChecked);
            }
        });

        // Init show progress setting.
        final CheckBox checkBoxShowProgress = (CheckBox) this.findViewById(R.id.check_box_show_progress);
        checkBoxShowProgress.setChecked(setting.isShowProgress());
        checkBoxShowProgress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setShowProgress(isChecked);
            }
        });

        // Init Sync setting.
        final EditText editTextDefaultTitle = (EditText) this.findViewById(R.id.edit_text_title);
        editTextDefaultTitle.setText(setting.getDefaultTitle());
        editTextDefaultTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                setting.setDefaultTitle(s.toString());
            }
        });
        final CheckBox checkBoxSyncToGoogleCalendar = (CheckBox) this.findViewById(R.id.check_box_sync_to_calendar);
        checkBoxSyncToGoogleCalendar.setChecked(setting.isSyncToCalendar());
        checkBoxSyncToGoogleCalendar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setSyncToCalendar(isChecked);
            }
        });

        // Init calendar setting.
        final Button buttonChooseCalendar = (Button) findViewById(R.id.button_choose_calendar);
        buttonChooseCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, ChooseCalendarActivity.class);
                startActivity(intent);
            }
        });

        // Init debug button.
        final Button buttonDebug = (Button) findViewById(R.id.button_debug_mode);
        if (setting.isDebugMode()) {
            buttonDebug.setVisibility(View.VISIBLE);
            buttonDebug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(SettingActivity.this, DebugModeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final Setting setting = Setting.getInstance();
        setting.init(this);
        final TextView textViewCurrentCalendar = (TextView) findViewById(R.id.text_view_current_calendar);
        textViewCurrentCalendar.setText(setting.getCalendarName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
