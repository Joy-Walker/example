public class JdkVersionCheck {
    public static void main(String[] args) {
        // 方法1: 通过 java.version 系统属性
        String version = System.getProperty("java.version");
        System.out.println("Java Version: " + version);

        // 方法2: 通过 java.vm.specification.version
        String vmVersion = System.getProperty("java.vm.specification.version");
        System.out.println("JVM Specification Version: " + vmVersion);

        // 方法3: 通过 Runtime version
        Runtime.Version runtimeVersion = Runtime.version();
        System.out.println("Runtime Version: " + runtimeVersion);
        System.out.println("Major Version: " + runtimeVersion.feature());
        System.out.println("Interim Version: " + runtimeVersion.interim());
        System.out.println("Patch Version: " + runtimeVersion.patch());

        // 方法4: 通过 Security Provider
        String vendor = System.getProperty("java.vendor");
        String vendorUrl = System.getProperty("java.vendor.url");
        System.out.println("Vendor: " + vendor);
        System.out.println("Vendor URL: " + vendorUrl);

        // 方法5: 通过 VM Name
        String vmName = System.getProperty("java.vm.name");
        System.out.println("VM Name: " + vmName);
    }
}
