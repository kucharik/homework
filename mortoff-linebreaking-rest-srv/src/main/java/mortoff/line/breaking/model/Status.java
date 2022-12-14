package mortoff.line.breaking.model;

public enum Status {
    READY (100){
        @Override
        public boolean isReady() {
            return true;
        }
    },
    PROCESSING (10){
        @Override
        public boolean isReady() {
            return false;
        }
    },
    RAW (0){
    	@Override
        public boolean isReady() {
            return false;
        }
    };

	Status(int i) {
	}

	public abstract boolean isReady();
	}
