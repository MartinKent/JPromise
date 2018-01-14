package com.deparse.promise;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huangshihai@chinaedu.com
 */

public final class JPromise {

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private List<Fun> funs = new ArrayList<>();

    public JPromise() {
    }

    public JPromise(Fun fun) {
        then(fun);
    }

    public synchronized Fun resolve(final Object... args) {
        checkShutdown();
        if (0 == funs.size()) {
            shutDown();
            return null;
        }
        final Fun fun = funs.get(0);
        funs.remove(0);
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                fun.run(JPromise.this, args);
            }
        });
        return fun;
    }

    public synchronized JPromise then(Fun fun) {
        checkShutdown();
        funs.add(fun);
        return this;
    }

    private void checkShutdown() {
        if (isShutdown()) {
            throw new JPromiseException("JPromise is shutdown.");
        }
    }

    public boolean isShutdown() {
        return cachedThreadPool.isShutdown();
    }

    public final void shutDown() {
        if (isShutdown()) {
            return;
        }
        cachedThreadPool.shutdownNow();
    }

    @Override
    protected void finalize() throws Throwable {
        shutDown();
        super.finalize();
    }

    //For test
    public static void main(String... args) {
        new JPromise(new Fun() {

            @Override
            public void run(final JPromise promise, final Object... args) {
                promise.resolve(0);
            }
        }).then(new Fun() {
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
        }).then(new Fun() {
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
        }).then(new Fun() {
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
        }).then(new Fun() {
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

    public interface Fun {
        void run(final JPromise promise, final Object... args);
    }

    public static class JPromiseException extends RuntimeException {
        public JPromiseException(String message) {
            super(message);
        }
    }
}
