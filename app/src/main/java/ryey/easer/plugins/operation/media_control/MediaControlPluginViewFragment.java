/*
 * Copyright (c) 2016 - 2017 Rui Zhao <renyuneyun@gmail.com>
 *
 * This file is part of Easer.
 *
 * Easer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Easer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Easer.  If not, see <http://www.gnu.org/licenses/>.
 */

package ryey.easer.plugins.operation.media_control;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import ryey.easer.R;
import ryey.easer.commons.plugindef.PluginViewFragment;
import ryey.easer.commons.plugindef.StorageData;

public class MediaControlPluginViewFragment extends PluginViewFragment {

    RadioButton radioButton_play_pause, radioButton_play, radioButton_pause, radioButton_previous, radioButton_next;

    {
        setDesc(R.string.operation_media_control);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plugin_operation__media_control, container, false);
        radioButton_play_pause = (RadioButton) view.findViewById(R.id.radioButton_play_pause);
        radioButton_play = (RadioButton) view.findViewById(R.id.radioButton_play);
        radioButton_pause = (RadioButton) view.findViewById(R.id.radioButton_pause);
        radioButton_previous = (RadioButton) view.findViewById(R.id.radioButton_previous);
        radioButton_next = (RadioButton) view.findViewById(R.id.radioButton_next);

        return view;
    }

    @Override
    protected void _fill(StorageData data) {
        if (data instanceof MediaControlOperationData) {
            MediaControlOperationData.ControlChoice choice = (MediaControlOperationData.ControlChoice) data.get();
            switch (choice) {
                case play_pause:
                    radioButton_play_pause.setChecked(true);
                    break;
                case play:
                    radioButton_play.setChecked(true);
                    break;
                case pause:
                    radioButton_pause.setChecked(true);
                    break;
                case previous:
                    radioButton_previous.setChecked(true);
                    break;
                case next:
                    radioButton_next.setChecked(true);
                    break;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public StorageData getData() {
        MediaControlOperationData.ControlChoice choice = null;
        if (radioButton_play_pause.isChecked()) {
            choice = MediaControlOperationData.ControlChoice.play_pause;
        } else if (radioButton_play.isChecked()) {
            choice = MediaControlOperationData.ControlChoice.play;
        } else if (radioButton_pause.isChecked()) {
            choice = MediaControlOperationData.ControlChoice.pause;
        } else if (radioButton_previous.isChecked()) {
            choice = MediaControlOperationData.ControlChoice.previous;
        } else if (radioButton_next.isChecked()) {
            choice = MediaControlOperationData.ControlChoice.next;
        }
        if (choice == null)
            return null;
        return new MediaControlOperationData(choice);
    }
}