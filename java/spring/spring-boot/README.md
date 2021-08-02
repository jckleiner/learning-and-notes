
### TODOs

* Async and Future: https://www.baeldung.com/spring-async
    Methods with return type?

        @EnableAsync -> is needed

        // define a thread pool
    	@Bean("doStuffExecutor")
        public TaskExecutor doStuffExecutor(int threads) {
            return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(threads));
        }

        @Async("doStuffExecutor")
        public void asyncDoStuff(URI someUri) {
            // doStuff
        }