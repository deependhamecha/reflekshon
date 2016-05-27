import java.util.HashSet;
import java.util.Set;
import java.lang.reflect.*;

enum Ex {
    A,B
}

class Abc {
    public int a = 1;

    public void doit() {
        System.out.println("OBJECT : "+this);
    }

    Abc() {}
    Abc(String a) {}
}

public class ClassExamples {
    public static void main(String ar[]) {

        // getClass() throws an instance of Class not the instance's Class
        Class c = "foo".getClass();
        // This prints "class java.lang.String"
        System.out.println(c);
        // This prints "class Abc"
        //System.out.println(new Abc().getClass());

        c =  Ex.A.getClass();
        // Output : class Ex
        System.out.println(c);

        byte[] bytes = new byte[1024];
        c = bytes.getClass();
        // Output: class [B
        System.out.println(c);

        Set<String> s = new HashSet<String>();
        c = s.getClass();
        // Output: class java.util.HashSet
        System.out.println(c);

/* If the type is available but there is no instance then it is possible to
*  obtain a Class by appending ".class" to the name of the type.
*/
        // boolean b;
        // c = b.getClass();       // compile-time error
        // System.out.println(c);

        c = boolean.class;
        System.out.println(c);
        System.out.println(Ex.class);
        System.out.println(String.class);

        c = java.io.PrintStream.class;
        System.out.println(c);

        c = int[][][].class;

        // Output : [[[I
        System.out.println(c);

/* If the fully-qualified name of a class is available,
*  it is possible to get the corresponding Class using the static method Class.forName().
*  This cannot be used for primitive types.
*/
        try {
            c = Class.forName("java.lang.Integer");
            //c = Class.forName("com.duke.MyLocaleServiceProvider");
            System.out.println(c);
        } catch(Exception e) {
            e.printStackTrace();
        }

        /* Each Wrapper Class including void has TYPE variable which refers to
        * there respective primitive types.
        */
        c = Double.TYPE;
        System.out.println(c);

        // Returns super class for the given class
        c = HashSet.class.getSuperclass();
        System.out.println(c);

        System.out.println("All public classes, interfaces and enum inside it");
        Class cx[] = Character.class.getClasses();

        for(Class c1 : cx)
            System.out.println(c1);

        cx = Character.class.getDeclaredClasses();

        System.out.println("Returns all of the classes interfaces, and enums that are explicitly declared.");
/* Character contains two public member classes Character.Subset and
*  Character.UnicodeBlock and one private class Character.CharacterCache.
*/

        for(Class c1 : cx)
            System.out.println(c1);

        try {
            Field f = System.class.getField("out");
            c = f.getDeclaringClass();
            System.out.println("----\n"+c);

            // Remember : This cannot method access private members
            f = Abc.class.getField("a");
            c = f.getDeclaringClass();
            System.out.println("----\n"+c);

// Note : The declaring class of the anonymous class is null.
            Method m = Abc.class.getMethod("doit");
            c = m.getDeclaringClass();
            System.out.println("----\n"+c);

        } catch(Exception e) {
            e.printStackTrace();
        }

        // To Retrieve Class of the Outer Class, use Class.getEnclosingClass()
        Object o = new Object() {
            public void m() {}
        };
        System.out.println(o.getClass().getEnclosingClass());


        // All Methods
        c = String.class;
        Method[] methods = c.getMethods();

        System.out.println("--------------------");
        for(Method m: methods)
            System.out.println(m);

        // for specific method
        try {
            Method method = c.getMethod("substring", new Class[]{int.class});
            // Same as above but for multiple parameters use the above one's
            //Method method = MyObject.class.getMethod("charAt", int.class);
            System.out.println("++++\n"+method);

            // Parameter types
            Class[] parameterTypes = method.getParameterTypes();
            System.out.println("==========");
            for(Class cc: parameterTypes)
                System.out.println(cc);

            // Return Type
            Class returnType = method.getReturnType();

            System.out.println("_____\n"+returnType);

            // Invoking method
            Abc abc = new Abc("Hello World!");
            System.out.println("object : "+abc);
            method = Abc.class.getMethod("doit");
            //Object returnValue = method.invoke(null, "parameter-value1");
            Object returnValue = method.invoke(abc);
            //Use null when the method is static else the invoking object



                    // Now, Constructors

/* Using Java Reflection you can inspect the constructors of classes and
*  instantiate objects at runtime. This is done via the Java class java.lang.reflect.Constructor.
*/
            Constructor[] constructors = String.class.getConstructors();
            for(Constructor constructor : constructors)
                System.out.println(constructor);

            System.out.println("_____");

            // Constructor constructor = Abc.class.getConstructor(String.class);
            // Abc myObject = (Abc) constructor.newInstance("Happy Birthday");
            // System.out.println(myObject);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
