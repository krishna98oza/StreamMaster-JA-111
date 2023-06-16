//import java.util.Map;
//
//import com.masai.entities.Customer;
//import com.masai.entities.Product;
//import com.masai.entities.Transaction;
//
//public class FileExists {
//	
//	public static Map<Integer, Product> productFile() {
//
//		Map<Integer, Product> pFile = null;
//
//		File f = new File("Product.ser");
//		boolean flag = false;
//		try {
//			if (!f.exists()) {
//				f.createNewFile();
//				flag = true;
//			}
//
//			if (flag) {
//
//				pFile = new LinkedHashMap<>();
//				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
//				oos.writeObject(pFile);
//				return pFile;
//
//			} else {
//
//				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
//				pFile = (Map<Integer, Product>) ois.readObject();
//
//				return pFile;
//
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//		}
//		return pFile;
//	}
//
//	public static Map<String, Customer> customerFile() {
//
//		Map<String, Customer> cFile = null;
//
//		File f = new File("Customer.ser");
//		boolean flag = false;
//		try {
//			if (!f.exists()) {
//				f.createNewFile();
//				flag = true;
//			}
//
//			if (flag) {
//				
//				cFile = new LinkedHashMap<>();
//				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
//				oos.writeObject(cFile);
//				return cFile;
//
//			} else {
//				
//				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
//				cFile = (Map<String, Customer>) ois.readObject();
//
//				return cFile;
//
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//
//			System.out.println(e.getMessage());
//		}
//		return cFile;
//
//	}
//}
