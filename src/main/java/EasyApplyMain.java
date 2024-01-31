
public class EasyApplyMain {
	public static void main(String[] args) throws InterruptedException {
		Utils utils = new Utils();
		long start = System.currentTimeMillis();
		new LinkedIn().linkJobApply();
		// while (true) {
		//     try {
		//         new LinkedIn().linkJobApply();
		//     } catch (Exception e) {
		//         utils.prRed("Error in main: " + e.getMessage());
		//         // long end = System.currentTimeMillis();
		//         // utils.prYellow("---Took: " + Math.round((System.currentTimeMillis() - start) / 60000.0) + " minute(s).");
		//     }
		// }

	}

}
