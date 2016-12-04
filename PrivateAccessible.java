import java.lang.reflect.*;
	
class Abc {
	
	private int a=1;
	public int b=2;
	protected int c=3;
	
	private int getA() {
		return this.a;
	}
}


public class PrivateAccessible extends Abc {
	
	public static void main(String[] args) {

		// Accessing Private Variable
		Field[] field = Abc.class.getDeclaredFields();
		Field[] fieldx = Abc.class.getFields();
		Abc abc = new Abc();
		
		try {
			
			for(Field f : field) {
				f.setAccessible(true); // If you uncomment this it will throw IllegalAccessException
				// It allows private memberss to be set accessible
				f.setInt(abc,4);
			}
			
			for(Field f : field) {
				System.out.println(f.getName()+" : "+f.get(abc));
			}
			
			System.out.println("__________");
			for(Field f : fieldx) {
				System.out.println(f.getName()+" : "+f.get(abc));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// Accessing Private Method
		
		try {
			Method method = Abc.class.getDeclaredMethod("getA",null);
			
			abc.setAccessible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
