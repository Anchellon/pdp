package entity.birds.enums;

/**
 * enum of all bird types
 *
 * @author novo
 * @since 2021/10/3
 */
public class BirdType {
    public enum PreyBirdTypes {
        HAWKS("hawk"),
        EAGLES("eagle"),
        OSPREY("osprey");

        private final String type;

        PreyBirdTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum WaterfowlTypes {
        DUCK("duck"),
        SWAN("swan"),
        GOOSE("goose");

        private final String type;

        WaterfowlTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum ShoreBirdTypes {
        GREAT_AUK("great awk"),
        HORNED_PUFFIN("horned puffin"),
        AFRICAN_JACANA("african jacana");

        private final String type;

        ShoreBirdTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum PigeonTypes {
        PIGEON("pigeon");

        private final String type;

        PigeonTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum ParrotTypes {
        ROSE_RING_PARAKEET("rose-ring parakeet"),
        GRAY_PARROT("gray parrot"),
        SULFUR_CRESTED_COCKATOO("sulfur crested cockatoo");

        private final String type;

        ParrotTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

    public enum OwlTypes {
        OWL("owl");

        private final String type;

        OwlTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum FlightlessBirdTypes {
        EMU("emu"),
        KIWI("kiwi"),
        MOAS("moas");

        private final String type;

        FlightlessBirdTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}
