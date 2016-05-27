import java.lang.reflect.*;
import java.util.*;

enum Tweedle { DEE, DUM }

public class MemberExamples<T> {

    public boolean[][] b = {{ false, false }, { true, true } };
    public String name  = "Alice";
    private List<Integer> list;
    public T val;
    int e = 999;
    public Tweedle twin = Tweedle.DEE;

    static Scanner s=new Scanner(System.in);

    public static void main(String... args) {
        try {

            Class<?> c =  MemberExamples.class;
            System.out.println("SHOWING ALL VARIABLES OF CLASS : "+c.getName());

            //Field field = c.getField(memberName);
            Field[] fields = c.getDeclaredFields();

            System.out.println("________");
            //System.out.println("Type: "+field.getType().getName());
            //System.out.println("GenericType: "+field.getGenericType());

            System.out.println("________");

            for(Field f : fields) {
                System.out.println("Name : "+f.getName());
                System.out.println("Type : "+f.getType().getName());
                System.out.println("Modifier : "+getModifierName(f.getModifiers()));
                //System.out.println("is Synthetic : "+f.isSynthetic());
                System.out.println("is Enum Constant : "+f.isEnumConstant());
                System.out.println("Generic Type : "+f.getGenericType());
                System.out.println("_____________________");
            }


//        Because Field implements the interface java.lang.reflect.AnnotatedElement,
// it is possible to retrieve any runtime annotation with java.lang.annotation.RetentionPolicy.RUNTIME.

        // Getting and Setting values of Member Variables
        // Reference
        MemberExamples me = new MemberExamples();

        System.out.println("-------\nBEFORE name : "+me.name);
        Field n = MemberExamples.class.getDeclaredField("name");
        String newName = "Deepen";
        n.set(me, newName);
        System.out.println("AFTER name : "+me.name);

        // Primitive
        System.out.println("-------\nBEFORE name : "+me.e);
        n = MemberExamples.class.getDeclaredField("e");
        int i = 777;
        /* If you use value instead, it is an Integer,
        *  then it will throw IllegalArgumentException autobox checking is done
        *  at compile-time.
        *
        *  setInt() is for primitive not for Wrapper, so using setInt
        *  on Integer type will be a problem.
        * Example :
        * Integer i=9; and doing
        * n.setInt(obj,8); will throw the Exception.
        */
        System.out.println("<<<<"+Integer.class.isAssignableFrom(int.class));
        System.out.println("<<<<"+int.class.isAssignableFrom(Integer.class));

        if(!int.class.isAssignableFrom(Integer.class))
            n.setInt(me,i);

        System.out.println("AFTER name : "+me.e);


        System.out.println("-------\nBEFORE name : "+me.twin);
        Field t = c.getDeclaredField("twin");
        t.set(me, Tweedle.DUM);
        System.out.println("AFTER name : "+me.twin);

        /* Setting a field's value via reflection has a certain amount of performance
        *  overhead because various operations must occur such as validating
        *  access permissions. From the runtime's point of view,
        *  the effects are the same, and the operation is as atomic as if
        *  the value was changed in the class code directly.
        */

        /* When trying to change final fields, it throws IllegalAccessException.
        *  An access restriction exists which prevents final fields from being
        *  set after initialization of the class.
        *  However, Field is declared to extend AccessibleObject which
        *  provides the ability to suppress this check.
        *
        * If AccessibleObject.setAccessible() succeeds, then subsequent operations
        * on this field value will not fail do to this problem.
        * This may have unexpected side-effects; for example, sometimes the
        * original value will continue to be used by some sections of the application
        * even though the value has been modified.
        * AccessibleObject.setAccessible() will only succeed if the operation
        * is allowed by the security context.
        */

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static String getModifierName(int i) {

        String typeName = null;

        if(i == Modifier.ABSTRACT)
            typeName = "abstract";

        else if(i == Modifier.FINAL)
            typeName = "final";

        else if(i == Modifier.INTERFACE)
            typeName = "interface";

        else if(i == Modifier.NATIVE)
            typeName = "native";

        else if(i == Modifier.PRIVATE)
            typeName = "private";

        else if(i == Modifier.PROTECTED)
            typeName = "protected";

        else if(i == Modifier.PUBLIC)
            typeName = "public";

        else if(i == Modifier.STATIC)
            typeName = "static";

        else if(i == Modifier.STRICT)
            typeName = "strict";

        else if(i == Modifier.SYNCHRONIZED)
            typeName = "synchronized";

        else if(i == Modifier.TRANSIENT)
            typeName = "transient";

        else if(i == Modifier.VOLATILE)
            typeName = "volatile";

        else
            typeName = null;

        return typeName;
    }
}

// TRY THESE INPUTS
// $ java MemberExamples MemberExamples b
// Type: class [[Z
// GenericType: class [[Z
// $ java MemberExamples MemberExamples name
// Type: class java.lang.String
// GenericType: class java.lang.String
// $ java MemberExamples MemberExamples list
// Type: interface java.util.List
// GenericType: java.util.List<java.lang.Integer>
// $ java MemberExamples MemberExamples val
// Type: class java.lang.Object
// GenericType: T
