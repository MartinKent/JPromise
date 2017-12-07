# JPromise
A java class used to solve nested callbacks

    //Sample
    public static void main(String... args) {
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
