package eu.livotov.android.appskeleton.activity;

import android.os.Bundle;

import com.mikepenz.materialize.MaterializeBuilder;

import eu.livotov.android.appskeleton.R;
import eu.livotov.android.appskeleton.activity.base.BaseActivity;

/**
 * Created by dlivotov on 07/06/2016.
 */

public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MaterializeBuilder().withActivity(this).build();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        showMessage("Hello !");
    }
}
