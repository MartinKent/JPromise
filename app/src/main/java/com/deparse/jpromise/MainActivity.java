package com.deparse.jpromise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.deparse.promise.JPromise;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JPromise(new JPromise.Fun() {

            @Override
            public void run(final JPromise promise, final Object... args) {
                promise.resolve(0);
            }
        }).then(new JPromise.Fun() {
            @Override
            public void run(final JPromise promise, final Object... args) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int i = (Integer) args[0];
                        System.out.println("i=" + i);
                        promise.resolve(i + 1);
                    }
                }, 100);
            }
        }).then(new JPromise.Fun() {
            @Override
            public void run(final JPromise promise, final Object... args) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int i = (Integer) args[0];
                        System.out.println("i=" + i);
                        promise.resolve(i + 1);
                    }
                }, 100);
            }
        }).then(new JPromise.Fun() {
            @Override
            public void run(final JPromise promise, final Object... args) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int i = (Integer) args[0];
                        System.out.println("i=" + i);
                        promise.resolve(i + 1);
                    }
                }, 100);
            }
        }).then(new JPromise.Fun() {
            @Override
            public void run(final JPromise promise, final Object... args) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int i = (Integer) args[0];
                        System.out.println("i=" + i);
                    }
                }, 100);
            }
        }).resolve();
    }
}
