import org.apache.commons.lang3.RandomStringUtils;

class Main {
    public static void main(String[] args) {
        // ruleid: randomstringutils-insecure-rng
        String a = RandomStringUtils.randomAlphanumeric(32);
        // ruleid: randomstringutils-insecure-rng
        String b = RandomStringUtils.random(10, true, true);
        // ok: randomstringutils-insecure-rng
        String c = RandomStringUtils.random(10, 0, 0, true, true, null, new SecureRandom());
    }
}
