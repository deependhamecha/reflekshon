import java.lang.reflect.*;

class Abc {
    
    // private void show(int a) {
    //     System.out.println("parameter value : "+a);
    // }
    
    public void showPublic(String a, String b) {
        System.out.println("showPublic in String : "+a);
    }
    public void showPublic(Object o, String b) {
        System.out.println("showPublic in Object : "+o);
    }
}

public class InvokingMethods {
    
    public static void main(String args[]) throws Exception {
        Class<?> c = Class.forName(args[0]);
        
        Method[] methods = c.getDeclaredMethods();
        
        for(Method m : methods) {
            System.out.println("\nInvoking Method : "+m.getName());
            Abc abc = new Abc();
            m.setAccessible(true);
            
//            m.invoke(abc,Integer.parseInt(args[1]));
            m.invoke(abc,args[1],args[2]);
        }
    }
}
